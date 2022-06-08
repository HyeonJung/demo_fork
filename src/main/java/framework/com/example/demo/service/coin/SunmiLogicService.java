package framework.com.example.demo.service.coin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import framework.com.example.demo.domain.grilla.GrillaApiRepository;
import framework.com.example.demo.domain.grilla.grillafp;
import framework.com.example.demo.domain.metakongs.MetakongsApiRepository;
import framework.com.example.demo.domain.metakongs.metakongsfp;
import framework.com.example.demo.domain.soldierfp.SoldierFPApiRepository;
import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.domain.sunmi.SunmiApiRepository;
import framework.com.example.demo.domain.sunmi.ssunmi;
import framework.com.example.demo.domain.token.tokenmapng.TokenMapngVO;
import framework.com.example.demo.model.coin.Soldier;
import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.model.coin.sunmi;
import jdk.vm.ci.meta.Local;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SunmiLogicService extends CoinBaseService<sunmi> {

    @Autowired
    TokenApiService tokenApiService;
    private final SunmiApiRepository sunmiApiRepository;
    private final SoldierFPApiRepository soldierFPApiRepository;

    private  final  MetakongsApiRepository metakongsApiRepository;

    private final GrillaApiRepository grillaApiRepository;
    private String GetRate(String price) throws IOException {
        String url = "https://ko.valutafx.com/LookupRate.aspx?to=KRW&from=USD&amount=" +
                encodeURIComponent(price) +
                "&offset=" +
                encodeURIComponent("-540");
        HttpGet request = new HttpGet(url);

        HttpResponse response = Excute(request);
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


        jsonObject.add("users", Users);
        return jsonObject;
    }

    public JsonObject 선미Rate() throws IOException {
        String url = "https://www.mexc.com/api/platform/spot/market/symbols";
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer", "https://www.mexc.com/exchange/FAVOR_USDT");
        HttpResponse response = Excute(request);
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

    public String 메타콩즈Rate() throws IOException {
        String usdt = "0";

        String url = "https://www.mexc.com/api/platform/spot/market/symbols";
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer", "https://www.mexc.com/exchange/FAVOR_USDT");
        HttpResponse response = Excute(request);
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

    @Override
    public Unit<sunmi> getCoin() throws IOException, URISyntaxException, ScriptException,InterruptedException {
        Unit<sunmi> unit = new Unit<sunmi>();


        /*  선미   */
        String 선미FP = "0";
        String 솔져스FP = "0";
        String 메타콩즈FP = "0";
        String 지릴라FP = "0";

        JsonObject 선미 = 선미Rate();
        JsonArray 환율리스트 = 선미.getAsJsonObject("data")
                .getAsJsonArray("USDT");
        String 선미usdt = "";
        String 메타콩즈Rate = 메타콩즈Rate();
        for (JsonElement 환율 : 환율리스트) {
            if (환율.getAsJsonObject()
                    .get("currency")
                    .toString().replace("\"", "").equals("FAVOR")){
                선미usdt = 환율.getAsJsonObject()
                        .get("c")
                        .toString().replace("\"", "");

            }
        }
        선미usdt = GetRate(선미usdt);
        메타콩즈Rate = GetRate(메타콩즈Rate);
        List<ssunmi> sunmiList = sunmiApiRepository.findAll();
        if(sunmiList.size() > 0){
            선미FP = sunmiList.get(0).getFp();
        }
        List<soldierfp> soldierList = soldierFPApiRepository.findAll();
        if(soldierList.size() > 0){
            솔져스FP = soldierList.get(0).getFp();
        }
        List<metakongsfp> metakongsfps = metakongsApiRepository.findAll();
        if(metakongsfps.size() > 0){
            메타콩즈FP = metakongsfps.get(0).getFp();
        }
        List<grillafp> grillafps = grillaApiRepository.findAll();
        if(grillafps.size() > 0){
            지릴라FP = grillafps.get(0).getFp();
        }
        unit.setSunmiOnePrice(String.valueOf(Math.floor(Double.parseDouble(선미usdt) * 4.16)));
        unit.setMetaKongsOnePrice(String.valueOf(Math.floor(Double.parseDouble(메타콩즈Rate) * 4)));
        unit.setGrillaOnePrice(String.valueOf(Math.floor(Double.parseDouble(메타콩즈Rate) * 0.4)));
        unit.setSunmiFP(선미FP);
        unit.setSoldiersFP(솔져스FP);
        unit.setMetakongsFP(메타콩즈FP);
        unit.setGrillaFP(지릴라FP);

        /**********************/

        HttpGet request = new HttpGet("https://www.lbank.info/request/ticker/tick24hr?symbol=usd&");
        request.addHeader("REFERER", "www.lbank.info");
        HttpResponse response = Excute(request);
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
        List<Soldier> soldiers = gson.fromJson(UserInit(GetRate(unit.getPrice()), 선미usdt, 메타콩즈Rate, 메타콩즈Rate,  선미FP, 솔져스FP, 메타콩즈FP, 지릴라FP).getAsJsonArray("users"),
                new TypeToken<List<Soldier>>() {
                }.getType());

        unit.setPrice(GetRate(unit.getPrice()));
        unit.setSoldiers(soldiers);

        return unit;
    }

    public void GetTsoDayAmount() throws IOException, InterruptedException {

        for (int i = 30; i  >= 1; i--) {
            Thread.sleep(1500);

            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/0x1068fc803c8f4cdaa4ece881e6be747f48119a1a/transfers?page=" + i);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            HttpResponse response = Excute(request);
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
                    LocalDateTime agoDate = LocalDateTime.now().minusDays(2);

                    if (agoDate.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        break;
                    }
                    String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                    String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                    String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");
                    String isStaking = "None";
                    TokenMapngVO vo = new TokenMapngVO();
                    vo.setTokenId(tokenId);
                    vo.setNftCode("TSO");
                    vo.setModifiedDate(LocalDateTime.now());
                    String data = "0x4c1c2794e3414712f92a786b6bc68fe22216ed7e";
                    if (toAddress.equals(data)) {
                        isStaking = "Staking";
                        vo.setAddress(fromAddress);
                        vo.setStakingStatus(isStaking);
                    } else {
                        isStaking = "None";
                        vo.setAddress(toAddress);
                        vo.setStakingStatus(isStaking);
                    }
                    tokenApiService.update(vo);
                }
            }
        }
    }

    public void GetBmzDayAmount() throws IOException, InterruptedException {

        for (int i = 30; i  >= 1; i--) {
            Thread.sleep(1500);

            HttpGet request = new HttpGet("https://api-cypress-v2.scope.klaytn.com/v2/tokens/0xff99f673d453245512e28f4dfbe4fb24428e55c1/transfers?page=" + i);
            request.addHeader("REFERER", "https://scope.klaytn.com/");
            HttpResponse response = Excute(request);
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
                    LocalDateTime agoDate = LocalDateTime.now().minusDays(2);

                    if (agoDate.isAfter(tsTime)) {
                        //agoDate 보다 과거이면 리턴
                        break;
                    }
                    String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                    String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                    String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");
                    String isStaking = "None";
                    TokenMapngVO vo = new TokenMapngVO();
                    vo.setTokenId(tokenId);
                    vo.setNftCode("TSO");
                    vo.setModifiedDate(LocalDateTime.now());
                    String data = "0xc7e3aa68f2fb0091bd3c7392eff139b5f95e11a0";
                    if (toAddress.equals(data)) {
                        isStaking = "Staking";
                        vo.setAddress(fromAddress);
                        vo.setStakingStatus(isStaking);
                    } else {
                        isStaking = "None";
                        vo.setAddress(toAddress);
                        vo.setStakingStatus(isStaking);
                    }
                    tokenApiService.update(vo);
                }
            }
        }
    }

}
