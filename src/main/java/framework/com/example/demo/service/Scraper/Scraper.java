package framework.com.example.demo.service.Scraper;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.web.servlet.server.Encoding;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Scraper {
    public String Html;
    public java.util.stream.Stream Stream;
    public Encoding encoding;
    public String ContentType;
    public String Method;
    public CloseableHttpClient client;
    public CookieStore cookieStore = new BasicCookieStore();
    public Scraper(){
        this.ContentType="application/x-www-form-urlencoded; charset=utf8;";

    }

    public void Go(String targetUrl) throws Exception {
        this.Go(targetUrl, new ArrayList<NameValuePair>());
    }
    public void Go(String targetUrl, List<NameValuePair> params ) throws Exception {
        if(params.size() == 0){
            this.Go(targetUrl, params, "GET");
        }else{
            this.Go(targetUrl, params, "POST");
        }
    }
    public void Go(String targetUrl, List<NameValuePair> params , String method) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                (TrustManager) new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sc);

        if(method.equals("POST")){
            HttpHost proxy = new HttpHost("localhost", 8888, "http");
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

            //피들러 캡쳐
            //client = HttpClients.custom().setSSLSocketFactory(csf).setRoutePlanner(routePlanner).setDefaultCookieStore(cookieStore).build();
            client = HttpClients.custom().setSSLSocketFactory(csf).setDefaultCookieStore(cookieStore).build();

            HttpPost httpPost = new HttpPost(targetUrl);

            httpPost.setEntity(new UrlEncodedFormEntity(params));



            CloseableHttpResponse response = client.execute(httpPost);
            List<Cookie> cookies1 = cookieStore.getCookies();

          /*  Header[] headers = response.getHeaders("Set-Cookie");
            for (Header headerElem : headers) {
                String cookies = "";
                cookies = headerElem.getValue(); // Set-Cookie의 value 가져 온다
                if (cookies != null && !cookies.equals("")) { // 로그인시에는 JSESSIONID를
                    // 세션obj 에 넣어 준다.
                    System.out.println("getFileDown > setLoginVO :" + cookies);
                    String[] split = null;
                    String delimStr = ";";
                    split = cookies.split(delimStr);
                    String cookieNmVal;
                    String cookieNm;
                    String cookieValue;
                    int equalStrPos;
                    for( int i=0; i<split.length; i++){
                        if(split[i].trim().indexOf("=") > 0
                           && split[i].trim().indexOf("path") < 0
                           && split[i].trim().indexOf("domain") < 0
                           && split[i].trim().indexOf("expires") < 0){
                            cookieNmVal = split[i].trim();
                            equalStrPos = cookieNmVal.indexOf("=", 1);
                            cookieNm = cookieNmVal.substring(0,equalStrPos);
                            cookieValue = cookieNmVal.substring(equalStrPos+1, cookieNmVal.length() - equalStrPos-1);

                        }
                    }
                }
                System.out.println("getFileDown > Header :" + headerElem.getValue());
                System.out.println("getFileDown > cookies:" + cookies);
            }*/


            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
        }else if(method.equals("GET")){
            HttpHost proxy = new HttpHost("localhost", 8888, "http");
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

            //피들러 캡쳐
            //client = HttpClients.custom().setSSLSocketFactory(csf).setRoutePlanner(routePlanner).setDefaultCookieStore(cookieStore).build();
            client = HttpClients.custom().setSSLSocketFactory(csf).setDefaultCookieStore(cookieStore).build();

            HttpGet httpGet = new HttpGet(targetUrl);

            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            this.Html = result;
            if(result.contains("로그아웃")){
                System.out.println("로그인 성공");
            }else{
                System.out.println("로그인 실패");
            }
        }
    }

    public void Close() {
        try {
            this.client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
