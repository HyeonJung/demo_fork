package framework.com.example.demo.service.coin.Token;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.com.example.demo.domain.token.tokenTransaction.TransactionVO;
import framework.com.example.demo.service.coin.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class TokenLogicService {
    @Autowired
    TransactionService transactionService;
    private HttpResponse Excute(HttpGet request) throws IOException {
        return HttpClientBuilder.create().build().execute(request);

    }

    public static String encodeURIComponent(String s) {
        String result = null;

        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    public void InsertTransactionAmount(String nftCode, String Enddate) throws Exception {
        LocalDateTime ldt;
        try {
            DateTimeFormatter DATEFORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            DateTimeFormatter DATEFORMATTER = new DateTimeFormatterBuilder().append(DATEFORMATTER1)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
            ldt = LocalDateTime.parse(Enddate, DATEFORMATTER);
        }catch (Exception ex){
            throw new Exception("기준 날짜 형식 오류");
        }

        String url = GetUrl(nftCode);
        boolean isCheck =false;
        int page =1;
        JsonObject jResult = new JsonObject();
        JsonArray jarray = new JsonArray();
        while (!isCheck){
            Thread.sleep(3000);
            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/" + url + "/transfers?page=" + page);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            HttpResponse response = Excute(request);
            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);

                JsonArray items = new JsonArray();
                try {
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                    items = jsonObject.getAsJsonArray("result");
                    if (items == null) {
                        break;
                    }
                }catch (Exception ex){

                }
                for (int j = 0; j < items.size(); j++) {

                    String createdAt = items.get(j).getAsJsonObject().get("createdAt").toString().replace("\"", "");
                    Instant instant = Instant.ofEpochSecond(Long.parseLong(createdAt));
                    LocalDateTime tsTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));

                    if (ldt.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        isCheck =true;
                        break;
                    }
                    String sDateTime = tsTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    for(int i=0; i< jarray.size(); i++){
                        if(jarray.get(i).getAsJsonObject().get("date").getAsString().equals(sDateTime.toString())){
                            jarray.get(i).getAsJsonObject().addProperty("count", jarray.get(i).getAsJsonObject().get("count").getAsLong() + 1);
                            break;
                        }
                        if(i == jarray.size() -1){
                            JsonObject data = new JsonObject();
                            data.addProperty("date",sDateTime);
                            data.addProperty("count", 1L);
                            jarray.add(data);
                        }
                    }
                    if(jarray.size() == 0){
                        JsonObject data = new JsonObject();
                        data.addProperty("date",sDateTime);
                        data.addProperty("count", 1L);
                        jarray.add(data);
                    }
                }
                page ++;

            }else{
                isCheck =true;
            }
        }

        for(int i=0; i<jarray.size(); i++){
            String date = jarray.get(i).getAsJsonObject().get("date").getAsString();
            Long amount = jarray.get(i).getAsJsonObject().get("count").getAsLong();
            String code = nftCode;
            TransactionVO vo = TransactionVO.builder()
                    .nftCode(code)
                    .amount(amount)
                    .date(date)
                    .createdDate(LocalDateTime.now())
                    .modifiedDate(LocalDateTime.now())
                    .build();
            transactionService.create(vo);
        }
    }

    public ArrayList<TransactionVO>  readTransactionAmount(String nftCode){
        ArrayList<TransactionVO> result = transactionService.read(nftCode);
        return result;
    }

    public void  test(){
    }


    public String GetUrl(String nftCode){

        if(nftCode.equals("TSO")){
            return "0x1068fc803c8f4cdaa4ece881e6be747f48119a1a";
        }else if(nftCode.equals("BMZ")){
            return "0xff99f673d453245512e28f4dfbe4fb24428e55c1";
        }else if (nftCode.equals("MG")){
            return "0x3b80e85929758018079cc4c7118ce61ce34cc221";
        }
        else return "0x1068fc803c8f4cdaa4ece881e6be747f48119a1a";
    }

}
