package framework.com.example.demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import framework.com.example.demo.domain.soldierfp.SoldierFPApiRepository;
import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.domain.sunmi.SunmiApiRepository;
import framework.com.example.demo.domain.sunmi.ssunmi;
import framework.com.example.demo.model.entity.Soldier;
import framework.com.example.demo.model.entity.Unit;
import framework.com.example.demo.model.entity.sunmi;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.HomeApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.hexile.cloudflarescraper.CloudflareScraper;
import xyz.hexile.cloudflarescraper.CloudflareScraperBuilder;
import xyz.hexile.cloudflarescraper.ScraperException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class SunmiLogicService extends CoinBaseService<sunmi> {
    private final SunmiApiRepository sunmiApiRepository;
    private final SoldierFPApiRepository soldierFPApiRepository;

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

    public JsonObject UserInit(String price, String 선미FP, String 솔저스FP) {
        JsonObject jsonObject = new JsonObject();
        JsonArray Users = new JsonArray();

        double Qty = 1000;
        JsonObject User = new JsonObject();
        User.addProperty("name", "대장");
        User.addProperty("qty", "1");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 833.5;
        User = new JsonObject();
        User.addProperty("name", "중장");
        User.addProperty("qty", "2");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 666.5;
        User = new JsonObject();
        User.addProperty("name", "소장");
        User.addProperty("qty", "3");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 500;
        User = new JsonObject();
        User.addProperty("name", "준장");
        User.addProperty("qty", "4");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 200;
        User = new JsonObject();
        User.addProperty("name", "대령");
        User.addProperty("qty", "10");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 100;
        User = new JsonObject();
        User.addProperty("name", "중령");
        User.addProperty("qty", "20");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 66.5;
        User = new JsonObject();
        User.addProperty("name", "소령");
        User.addProperty("qty", "30");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 33.5;
        User = new JsonObject();
        User.addProperty("name", "대위");
        User.addProperty("qty", "50");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 30;
        User = new JsonObject();
        User.addProperty("name", "중위");
        User.addProperty("qty", "70");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 26.5;
        User = new JsonObject();
        User.addProperty("name", "소위");
        User.addProperty("qty", "100");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 50;
        User = new JsonObject();
        User.addProperty("name", "준위");
        User.addProperty("qty", "30");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 23.5;
        User = new JsonObject();
        User.addProperty("name", "원사");
        User.addProperty("qty", "100");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 21.5;
        User = new JsonObject();
        User.addProperty("name", "상사");
        User.addProperty("qty", "330");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 20;
        User = new JsonObject();
        User.addProperty("name", "중사");
        User.addProperty("qty", "550");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 18.5;
        User = new JsonObject();
        User.addProperty("name", "하사");
        User.addProperty("qty", "700");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 16.5;
        User = new JsonObject();
        User.addProperty("name", "병장");
        User.addProperty("qty", "1000");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 15;
        User = new JsonObject();
        User.addProperty("name", "상병");
        User.addProperty("qty", "1500");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 10;
        User = new JsonObject();
        User.addProperty("name", "일병");
        User.addProperty("qty", "2500");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));;

        Users.add(User);

        Qty = 8.5;
        User = new JsonObject();
        User.addProperty("name", "이등병");
        User.addProperty("qty", "3000");
        User.addProperty("getQty", Qty);
        User.addProperty("day_item", Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item", Math.floor(Double.parseDouble(price) * Qty * 30));
        User.addProperty("sunmi", Math.floor(((Double.parseDouble(솔저스FP) * Qty) /  (Double.parseDouble(선미FP)  * 4.16) * 100) * Double.parseDouble(선미FP) / 100));

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

    public String 선미FP() throws IOException, URISyntaxException {
        HttpGet request = new HttpGet("http://220.230.124.179:11111/api/Test/Index?url=https://opensea.io/collection/sunmiya-club-official");
        request.addHeader("Host","opensea.io");
        request.addHeader("sec-ch-ua-platform","\"Windows\"");
        request.addHeader("sec-ch-ua","\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"");
        request.addHeader("Accept","*/*");
        request.addHeader("Sec-Fetch-Site","none");
        request.addHeader("Sec-Fetch-Mode","cors");
        request.addHeader("Sec-Fetch-Dest","empty");
        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; WOW64; Trident/7.0; rv:11.0) like Gecko");
        HttpResponse response = Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }
        Pattern pattern = Pattern.compile("\\\\\\\"floorPrice\\\\\\\":\\{\\\\\\\"unit\\\\\\\":\\\\\\\"(.*?)\\\\\\\""); //영문자만
        String val = result; //대상문자열
        Matcher match = pattern.matcher(val);
        match.find();
        result=match.group(1);
        return result;
    }

    public String 솔져스FP() throws IOException, URISyntaxException {

        HttpGet request = new HttpGet("http://220.230.124.179:11111/api/Test/Index?url=https://opensea.io/collection/thesoldiersofficial");
        request.addHeader("Host","opensea.io");
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        HttpResponse response = Excute(request);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            // return it as a String
            result = EntityUtils.toString(entity);

            System.out.println(result);
        }
        Pattern pattern = Pattern.compile("\\\\\\\"floorPrice\\\\\\\":\\{\\\\\\\"unit\\\\\\\":\\\\\\\"(.*?)\\\\\\\""); //영문자만
        String val = result; //대상문자열
        Matcher match = pattern.matcher(val);
        match.find();
        result=match.group(1);
        return result;
    }
    @Override
    public Unit<sunmi> getCoin() throws IOException, URISyntaxException, ScriptException, ScraperException, InterruptedException {
        Unit<sunmi> unit = new Unit<sunmi>();


        /*  선미   */
        String 선미FP = "0";
        String 솔져스FP = "0";
        JsonObject 선미 = 선미Rate();
        JsonArray 환율리스트 = 선미.getAsJsonObject("data")
                .getAsJsonArray("USDT");
        String usdt = "";
        for (JsonElement 환율 : 환율리스트) {
            if (환율.getAsJsonObject()
                    .get("currency")
                    .toString().replace("\"", "").equals("FAVOR")){
                usdt = 환율.getAsJsonObject()
                        .get("c")
                        .toString().replace("\"", "");

            }
        }
        usdt = GetRate(usdt);

        List<ssunmi> sunmiList = sunmiApiRepository.findAll();
        if(sunmiList.size() > 0){
            선미FP = sunmiList.get(0).getFp();
        }
        List<soldierfp> soldierList = soldierFPApiRepository.findAll();
        if(soldierList.size() > 0){
            솔져스FP = soldierList.get(0).getFp();
        }

/*
        CompletableFuture<String> 솔져스Task = CompletableFuture.supplyAsync(()-> {
            try {
                return 솔져스FP();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        while( !솔져스Task.isDone()){
            System.out.println("Processing....");
        }

        String 솔져스FP = "0";
        try {
            솔져스FP = 솔져스Task.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
*/


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
        List<Soldier> soldiers = gson.fromJson(UserInit(GetRate(unit.getPrice()), 선미FP, 솔져스FP).getAsJsonArray("users"),
                new TypeToken<List<Soldier>>() {
                }.getType());

        unit.setPrice(GetRate(unit.getPrice()));
        unit.setSoldiers(soldiers);

        return unit;
    }


    public void TestWebClient(){
        WebClient.ResponseSpec responseSpec = WebClient.create().get()
                .uri("https://opensea.io/collection/sunmiya-club-official")
                .header("Host","opensea.io")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

    }

    public void test2() throws URISyntaxException, ScraperException, IOException, ScriptException, InterruptedException {

        CloudflareScraper cloudflareScraper = new CloudflareScraperBuilder(new URI("https://opensea.io/collection/sunmiya-club-official"))
                .setConnectionTimeout(5000)
                .setReadTimeout(5000)
                .setChallengeDelay(4000) // At least 4000 milliseconds, otherwise Cloudflare won't give you a clearance cookie
                .build();
        if (cloudflareScraper.connect()) {

            // Pass this cookies in your request
            List<HttpCookie> cookies = cloudflareScraper.getCookies();
        }
    }
}
