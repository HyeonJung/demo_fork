package framework.com.example.demo.service.coin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import framework.com.example.demo.model.coin.Soldier;
import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.token.TokenMapping;
import framework.com.example.demo.token.service.FloorPriceInfoService;
import framework.com.example.demo.token.service.TokenInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.*;
import java.net.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CoinService{
    @Autowired
    FloorPriceInfoService floorPriceInfoService;

    @Autowired
    TokenInfoService tokenInfoService;


    private String GetRate(String price) throws Exception {
        String url = "https://ko.valutafx.com/LookupRate.aspx?to=KRW&from=USD&amount=" +
                encodeURIComponent(price) +
                "&offset=" +
                encodeURIComponent("-540");
        HttpGet request = new HttpGet(url);

        ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

        return jsonObject.get("Rate").toString().replace("\"", "");
    }

    private HttpResponse Excute(HttpGet request) throws IOException {
//        RequestConfig.Builder requestBuilder = RequestConfig.custom();
//        requestBuilder.setProxy(new HttpHost("127.0.0.1", 8888, "http"));
//

        //.setDefaultRequestConfig(requestBuilder.build())
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

    public JsonObject UserInit(String price, String 선미price, String 메타콩즈price, String 지릴라Price, String 선미FP, String 솔저스FP, String 메타콩즈FP, String 지릴라FP) {
        JsonObject jsonObject = new JsonObject();
        JsonArray Users = new JsonArray();

        double Qty = 1000;
        JsonObject User = new JsonObject();
        User.addProperty("name", "대장");
        User.addProperty("qty", "1");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 833.5;
        User = new JsonObject();
        User.addProperty("name", "중장");
        User.addProperty("qty", "2");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 666.5;
        User = new JsonObject();
        User.addProperty("name", "소장");
        User.addProperty("qty", "3");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 500;
        User = new JsonObject();
        User.addProperty("name", "준장");
        User.addProperty("qty", "4");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 200;
        User = new JsonObject();
        User.addProperty("name", "대령");
        User.addProperty("qty", "10");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 100;
        User = new JsonObject();
        User.addProperty("name", "중령");
        User.addProperty("qty", "20");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 66.5;
        User = new JsonObject();
        User.addProperty("name", "소령");
        User.addProperty("qty", "30");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 33.5;
        User = new JsonObject();
        User.addProperty("name", "대위");
        User.addProperty("qty", "50");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 30;
        User = new JsonObject();
        User.addProperty("name", "중위");
        User.addProperty("qty", "70");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 26.5;
        User = new JsonObject();
        User.addProperty("name", "소위");
        User.addProperty("qty", "100");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 50;
        User = new JsonObject();
        User.addProperty("name", "준위");
        User.addProperty("qty", "30");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 23.5;
        User = new JsonObject();
        User.addProperty("name", "원사");
        User.addProperty("qty", "100");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 21.5;
        User = new JsonObject();
        User.addProperty("name", "상사");
        User.addProperty("qty", "330");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 20;
        User = new JsonObject();
        User.addProperty("name", "중사");
        User.addProperty("qty", "550");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 18.5;
        User = new JsonObject();
        User.addProperty("name", "하사");
        User.addProperty("qty", "700");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 16.5;
        User = new JsonObject();
        User.addProperty("name", "병장");
        User.addProperty("qty", "1000");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 15;
        User = new JsonObject();
        User.addProperty("name", "상병");
        User.addProperty("qty", "1500");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 10;
        User = new JsonObject();
        User.addProperty("name", "일병");
        User.addProperty("qty", "2500");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 8.5;
        User = new JsonObject();
        User.addProperty("name", "이등병");
        User.addProperty("qty", "3000");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));

        Users.add(User);

        Qty = 2.5;
        User = new JsonObject();
        User.addProperty("name", "베이비몽즈");
        User.addProperty("qty", "");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));
        Users.add(User);


        Qty = 3.3;
        User = new JsonObject();
        User.addProperty("name", "스페셜몽즈");
        User.addProperty("qty", "");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(선미price)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;
        User.addProperty("metakongs", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 4) * 100) * Double.parseDouble(메타콩즈FP) / 100));
        User.addProperty("grilla", Math.floor(((Double.parseDouble(price) * Qty) /  (Double.parseDouble(메타콩즈price)  * 0.4) * 100) * Double.parseDouble(지릴라FP) / 100));
        Users.add(User);

        jsonObject.add("users", Users);
        return jsonObject;
    }

    /**
     * 선미 수수료
     * @return
     * @throws IOException
     */
    public JsonObject sunmiRate() throws Exception {
        String url = "https://www.mexc.com/api/platform/spot/market/symbols";
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer", "https://www.mexc.com/exchange/FAVOR_USDT");
        ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        return jsonObject;
//        return jsonObject.get("Rate").toString().replace("\"","");
    }

    /**
     * 메콩 수수료
     * @return
     * @throws IOException
     */

    public String mkzRate() throws Exception {
        String usdt = "0";

        String url = "https://www.mexc.com/api/platform/spot/market/symbols";
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer", "https://www.mexc.com/exchange/FAVOR_USDT");
        ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

        JsonArray 환율리스트 = jsonObject.getAsJsonObject("data")
                .getAsJsonArray("USDT");
        for (JsonElement 환율 : 환율리스트) {
            if (환율.getAsJsonObject()
                    .get("currency")
                    .toString().replace("\"", "").equals("MKC")){
                usdt = 환율.getAsJsonObject()
                        .get("c")
                        .toString().replace("\"", "");

            }
        }
        return usdt;
//        return jsonObject.get("Rate").toString().replace("\"","");
    }

    /**
     * 코인수집
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ScriptException
     * @throws InterruptedException
     */
    public Unit getCoin() throws Exception, URISyntaxException, ScriptException,InterruptedException {
        Unit unit = new Unit();

        String sunmiUsdt = "";
        String mkzRate = mkzRate();

        JsonObject sunmi = sunmiRate();
        sunmiUsdt = getSunmiUsdt(sunmiUsdt, sunmi);


        Gson gson = getTSOGson(unit);


        sunmiUsdt = GetRate(sunmiUsdt);
        mkzRate = GetRate(mkzRate);
        String tsoRate = GetRate(unit.getPrice());
        String sunmiFp = floorPriceInfoService.getFP("SUNMI");
        String soldiersFp = floorPriceInfoService.getFP("TSO");
        String metaKongzFp = floorPriceInfoService.getFP("MTKZ");
        String grillaFp = floorPriceInfoService.getFP("GRILLA");
        String bmz = floorPriceInfoService.getFP("BMZ");

        setPrice(unit, sunmiUsdt, mkzRate, sunmiFp, soldiersFp, metaKongzFp, grillaFp, bmz);

        List<Soldier> soldiers = gson.fromJson(UserInit(tsoRate, sunmiUsdt, mkzRate, mkzRate,  sunmiFp, soldiersFp, metaKongzFp, grillaFp).getAsJsonArray("users"),
                new TypeToken<List<Soldier>>() {
                }.getType());

        unit.setPrice(GetRate(unit.getPrice()));
        unit.setSoldiers(soldiers);

        return unit;
    }

    private Gson getTSOGson(Unit unit) throws Exception {
        HttpGet request = new HttpGet("https://www.lbank.info/request/ticker/tick24hr?symbol=usd&");
        request.addHeader("REFERER", "www.lbank.info");
        ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        JsonArray items = jsonObject.getAsJsonArray("data");
        for (JsonElement item : items) {
            if (item.getAsJsonObject().get("s").toString().replace("\"", "").equals("tsg/usdt")) {
                System.out.println(item.getAsJsonObject().get("s").toString().replace("\"", "") +
                        "-" +
                        item.getAsJsonObject().get("c").toString().replace("\"", "") +
                        "-" +
                        item.getAsJsonObject().get("ch").toString().replace("\"", ""));
                unit.setName(item.getAsJsonObject().get("s").toString().replace("\"", ""));
                unit.setPrice(item.getAsJsonObject().get("c").toString().replace("\"", ""));
                unit.setPercent(item.getAsJsonObject().get("ch").toString().replace("\"", ""));
                break;
            }
        }
        return gson;
    }

    private String getSunmiUsdt(String sunmiUsdt, JsonObject sunmi) {
        JsonArray exchangeRates = sunmi.getAsJsonObject("data")
                .getAsJsonArray("USDT");

        for (JsonElement exchangeRate : exchangeRates) {
            if (exchangeRate.getAsJsonObject()
                    .get("currency")
                    .toString().replace("\"", "").equals("FAVOR")){
                sunmiUsdt = exchangeRate.getAsJsonObject()
                        .get("c")
                        .toString().replace("\"", "");

            }
        }
        return sunmiUsdt;
    }

    private void setPrice(Unit unit, String sunmiUsdt, String mkzRate, String sunmiFp, String soldiersFp, String metaKongzFp, String grillaFp, String bmzFp) {
        unit.setSunmiOnePrice(String.valueOf(Math.floor(Double.parseDouble(sunmiUsdt) * 4.16)));
        unit.setMetaKongsOnePrice(String.valueOf(Math.floor(Double.parseDouble(mkzRate) * 4)));
        unit.setGrillaOnePrice(String.valueOf(Math.floor(Double.parseDouble(mkzRate) * 0.4)));
        unit.setSunmiFP(sunmiFp);
        unit.setSoldiersFP(soldiersFp);
        unit.setMetakongsFP(metaKongzFp);
        unit.setGrillaFP(grillaFp);
        unit.setBmzFP(bmzFp);
    }

    public void GetTsoDayAmount() throws Exception, InterruptedException {

        String conractUrl = GetContractUrl("TSO");
        String stakingUrl = GetStakingUrl("TSO");
        for (int i = 30; i  >= 1; i--) {
            Thread.sleep(1500);

            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/" + conractUrl + "/transfers?page=" + i);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }

            if (result != "") {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("result");
                if(items == null){
                    break;
                }


                for (int j = items.size()-1 ; j >= 0; j--) {

                    String createdAt = items.get(j).getAsJsonObject().get("createdAt").toString().replace("\"", "");
                    Instant instant = Instant.ofEpochSecond(Long.parseLong(createdAt));
                    LocalDateTime tsTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));
                    LocalDateTime agoDate = LocalDateTime.now().minusDays(3);

                    if (agoDate.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        break;
                    }
                    String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                    String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                    String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");
                    TokenMapping tokenMapping = new TokenMapping();
                    tokenMapping.setTokenId(tokenId);
                    tokenMapping.setNftCode("TSO");
                    tokenMapping.setModifiedDate(LocalDateTime.now());

                    if (toAddress.equals(stakingUrl)) {
                        tokenMapping.setStakingStatus("Staking");
                        tokenMapping.setAddress(fromAddress);
                    } else {
                        tokenMapping.setAddress(toAddress);
                        tokenMapping.setStakingStatus("None");
                    }

                    tokenInfoService.update(tokenMapping);
                }
            }
        }
    }

    public void GetBmzDayAmount() throws Exception, InterruptedException {

        String conractUrl = GetContractUrl("BMZ");
        String stakingUrl = GetStakingUrl("BMZ");
        for (int i = 30; i  >= 1; i--) {
            Thread.sleep(1500);

            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/" + conractUrl + "/transfers?page=" + i);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }

            if (result != "") {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("result");
                if(items == null){
                    break;
                }


                for (int j = items.size()-1 ; j >= 0; j--) {

                    String createdAt = items.get(j).getAsJsonObject().get("createdAt").toString().replace("\"", "");
                    Instant instant = Instant.ofEpochSecond(Long.parseLong(createdAt));
                    LocalDateTime tsTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));
                    LocalDateTime agoDate = LocalDateTime.now().minusDays(3);

                    if (agoDate.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        break;
                    }
                    String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                    String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                    String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");
                    TokenMapping tokenMapping = new TokenMapping();
                    tokenMapping.setTokenId(tokenId);
                    tokenMapping.setNftCode("BMZ");
                    tokenMapping.setModifiedDate(LocalDateTime.now());

                    if (toAddress.equals(stakingUrl)) {
                        tokenMapping.setStakingStatus("Staking");
                        tokenMapping.setAddress(fromAddress);
                    } else {
                        tokenMapping.setAddress(toAddress);
                        tokenMapping.setStakingStatus("None");
                    }

                    tokenInfoService.update(tokenMapping);
                }
            }
        }
    }

    public void GetMGDayAmount() throws Exception, InterruptedException {
        String conractUrl = GetContractUrl("MG");
        String stakingUrl = GetStakingUrl("MG");

        for (int i = 15; i  >= 1; i--) {
            Thread.sleep(1500);

            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/" + conractUrl + "/transfers?page=" + i);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            ClassicHttpResponse response = (ClassicHttpResponse)Excute(request);
            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }

            if (result != "") {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("result");
                if(items == null){
                    break;
                }


                for (int j = items.size()-1 ; j >= 0; j--) {

                    String createdAt = items.get(j).getAsJsonObject().get("createdAt").toString().replace("\"", "");
                    Instant instant = Instant.ofEpochSecond(Long.parseLong(createdAt));
                    LocalDateTime tsTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));
                    LocalDateTime agoDate = LocalDateTime.now().minusDays(3);

                    if (agoDate.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        break;
                    }
                    String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                    String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                    String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");
                    TokenMapping tokenMapping = new TokenMapping();
                    tokenMapping.setTokenId(tokenId);
                    tokenMapping.setNftCode("MG");
                    tokenMapping.setModifiedDate(LocalDateTime.now());

                    if (toAddress.equals(stakingUrl)) {
                        tokenMapping.setStakingStatus("Staking");
                        tokenMapping.setAddress(fromAddress);
                    } else {
                        tokenMapping.setAddress(toAddress);
                        tokenMapping.setStakingStatus("None");
                    }

                    tokenInfoService.update(tokenMapping);
                }
            }
        }
    }

    public String GetContractUrl(String nftCode){
        if(nftCode.equals("TSO")){
            return "0x1068fc803c8f4cdaa4ece881e6be747f48119a1a";
        }else if(nftCode.equals("BMZ")){
            return "0xff99f673d453245512e28f4dfbe4fb24428e55c1";
        }else if(nftCode.equals("MG")){
            return "0x3b80e85929758018079cc4c7118ce61ce34cc221";
        }
        else return "";
    }

    public String GetStakingUrl(String nftCode){
        if(nftCode.equals("TSO")){
            return "0x4c1c2794e3414712f92a786b6bc68fe22216ed7e";
        }else if(nftCode.equals("BMZ")){
            return "0xc7e3aa68f2fb0091bd3c7392eff139b5f95e11a0";
        }else if(nftCode.equals("MG")){
            return "";
        }
        else return "";

    }
}
