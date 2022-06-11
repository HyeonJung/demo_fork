package framework.com.example.demo.service.Scraper;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.web.servlet.server.Encoding;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Scraper {
    public String Html;
    public java.util.stream.Stream Stream;
    public Encoding encoding;
    public String ContentType;
    public String Method;

    public Scraper(){
        this.ContentType="application/x-www-form-urlencoded; charset=utf8;";

    }

    public void Go(String targetUrl) throws Exception {
        this.Go(targetUrl, "");
    }
    public void Go(String targetUrl, String postData) throws Exception {
        if(postData.equals("")){
            this.Go(targetUrl, postData, "GET");
        }else{
            this.Go(targetUrl, postData, "POST");
        }
    }
    public void Go(String targetUrl, String postData, String method) throws Exception {
        if(method.equals("POST")){
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://www.lsstation.co.kr/member_login_ok.php");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoginAction", "login"));
            params.add(new BasicNameValuePair("memId", "soldiers"));
            params.add(new BasicNameValuePair("memPw", "1234"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            client.close();


        }
    }



}
