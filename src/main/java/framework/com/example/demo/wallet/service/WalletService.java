package framework.com.example.demo.wallet.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.com.example.demo.service.Scraper.Scraper;
import org.apache.hc.core5.http.NameValuePair;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WalletService {
    public void getContract(String adress) throws Exception {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("address",adress);
        List<NameValuePair> param = new ArrayList<>();
        Scraper scraper = new Scraper();
        scraper.setJsonBody(requestBody);
        scraper.ContentType="application/json; charset=UTF-8";
        scraper.Go("https://www.klaypad.app/api/nfts", param, "POST");
        String html = scraper.Html;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(html, JsonObject.class);

        jsonObject.keySet().forEach(keyStr ->
        {
            Object keyvalue = jsonObject.get(keyStr);
            System.out.println("key: "+ keyStr + " value: " + keyvalue);

        });

    }

    public String getFloorFP(String url) throws Exception {
        JsonObject requestBody = new JsonObject();
        List<NameValuePair> param = new ArrayList<>();
        Scraper scraper = new Scraper();
        scraper.version="HTTP2";
        scraper.setJsonBody(requestBody);
        scraper.ContentType="application/json; charset=UTF-8";
        scraper.Go(url, param, "GET");
        String html = scraper.Html;

        String value ="zI3NDcyMw==:statsV2:floorPrice\",\"__typename\":\"PriceType\",\"unit\":\"2300\"}";
        Pattern ptn = Pattern.compile("floorPrice\\\",\\\"__typename\\\":\\\"PriceType\\\",\\\"unit\\\":\\\"(?<fp>.*?)\\\"");
        Matcher mc = ptn.matcher(html);
        String result = "";
        if(mc.find()){
            result = mc.group("fp");
            System.out.println(result);
        }
        return result;

    }

}
