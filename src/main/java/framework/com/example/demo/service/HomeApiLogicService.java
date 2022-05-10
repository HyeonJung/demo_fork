package framework.com.example.demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import framework.com.example.demo.model.entity.Soldier;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.HomeApiResponse;
import framework.com.example.demo.model.network.response.UserApiResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Service
public class HomeApiLogicService {
    //https://code-resting.tistory.com/43
    //https://doinge-coding.tistory.com/entry/spring-webClient%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95
    public Header<HomeApiResponse> read() throws IOException {
        HttpGet request = new HttpGet("https://www.lbank.info/request/ticker/tick24hr?symbol=usd&");
        request.addHeader("REFERER","www.lbank.info");

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
        JsonArray items = jsonObject.getAsJsonArray("data") ;
        String name ="";
        String price ="";
        String percent="";
        for (JsonElement item : items) {
            if(item.getAsJsonObject().get("s").toString().replace("\"","").equals("tsg/usdt")) {
                System.out.println(item.getAsJsonObject().get("s").toString().replace("\"","") +
                        "-" +
                        item.getAsJsonObject().get("c").toString().replace("\"","") +
                        "-" +
                        item.getAsJsonObject().get("ch").toString().replace("\"","") );
                name = item.getAsJsonObject().get("s").toString().replace("\"","");
                price = item.getAsJsonObject().get("c").toString().replace("\"","");
                percent = item.getAsJsonObject().get("ch").toString().replace("\"","");
                break;
            }
        }


        JsonObject users = UserInit(GetRate(price));
        List<Soldier> soldiers = gson.fromJson(users.getAsJsonArray("users"),
                new TypeToken<List<Soldier>>(){}.getType());


        HomeApiResponse homeApiResponse = new HomeApiResponse();
        homeApiResponse.setName(name);
        homeApiResponse.setPercent(percent);
        homeApiResponse.setPrice(GetRate(price));
        homeApiResponse.setSoldiers(soldiers);
        선미Rate();

        return  response(homeApiResponse);

    }


    private Header<HomeApiResponse> response(HomeApiResponse homeApiResponse){
        return Header.OK(homeApiResponse);
    }

    private String GetRate(String price) throws IOException {
        String url ="https://ko.valutafx.com/LookupRate.aspx?to=KRW&from=USD&amount=" +
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

        return jsonObject.get("Rate").toString().replace("\"","");
    }

    private HttpResponse Excute(HttpGet request) throws IOException {
        return  HttpClientBuilder.create().build().execute(request);

    }

    public static String encodeURIComponent(String s)
    {
        String result = null;

        try
        {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e)
        {
            result = s;
        }

        return result;
    }

    public JsonObject UserInit(String price){
        JsonObject jsonObject = new JsonObject();
        JsonArray Users = new JsonArray();

        double Qty =1000;
        JsonObject User = new JsonObject();
        User.addProperty("name","대장");
        User.addProperty("qty","1");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 833.5;
        User = new JsonObject();
        User.addProperty("name","중장");
        User.addProperty("qty","2");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 666.5;
        User = new JsonObject();
        User.addProperty("name","소장");
        User.addProperty("qty","3");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 500;
        User = new JsonObject();
        User.addProperty("name","준장");
        User.addProperty("qty","4");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 200;
        User = new JsonObject();
        User.addProperty("name","대령");
        User.addProperty("qty","10");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 100;
        User = new JsonObject();
        User.addProperty("name","중령");
        User.addProperty("qty","20");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 66.5;
        User = new JsonObject();
        User.addProperty("name","소령");
        User.addProperty("qty","30");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 33.5;
        User = new JsonObject();
        User.addProperty("name","대위");
        User.addProperty("qty","50");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 30;
        User = new JsonObject();
        User.addProperty("name","중위");
        User.addProperty("qty","70");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 26.5;
        User = new JsonObject();
        User.addProperty("name","소위");
        User.addProperty("qty","100");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 50;
        User = new JsonObject();
        User.addProperty("name","준위");
        User.addProperty("qty","30");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 23.5;
        User = new JsonObject();
        User.addProperty("name","원사");
        User.addProperty("qty","100");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 21.5;
        User = new JsonObject();
        User.addProperty("name","상사");
        User.addProperty("qty","330");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 20;
        User = new JsonObject();
        User.addProperty("name","중사");
        User.addProperty("qty","550");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 18.5;
        User = new JsonObject();
        User.addProperty("name","하사");
        User.addProperty("qty","700");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 16.5;
        User = new JsonObject();
        User.addProperty("name","병장");
        User.addProperty("qty","1000");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 15;
        User = new JsonObject();
        User.addProperty("name","상병");
        User.addProperty("qty","1500");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 10;
        User = new JsonObject();
        User.addProperty("name","일병");
        User.addProperty("qty","2500");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);

        Qty = 8.5;
        User = new JsonObject();
        User.addProperty("name","이등병");
        User.addProperty("qty","3000");
        User.addProperty("getQty",Qty);
        User.addProperty("day_item",Math.floor(Double.parseDouble(price) * Qty));
        User.addProperty("month_item",Math.floor(Double.parseDouble(price) * Qty * 30));
        Users.add(User);


        jsonObject.add("users",Users);
        return jsonObject;
    }

    public void 선미Rate() throws IOException {
        String url ="https://www.mexc.com/api/platform/spot/market/symbols";
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer","https://www.mexc.com/exchange/FAVOR_USDT");
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

//        return jsonObject.get("Rate").toString().replace("\"","");
    }
}
