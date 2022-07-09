package framework.com.example.demo.service.Scraper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.client5.http.ssl.ClientTlsStrategyBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.nio.ssl.TlsStrategy;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.io.CloseMode;
import org.springframework.boot.web.servlet.server.Encoding;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.text.html.HTML;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Scraper<T> {
    public String Html;
    public java.util.stream.Stream Stream;
    public Encoding encoding;
    public String ContentType;
    public JsonObject jsonBody = new JsonObject();

    public String Method;
    public CloseableHttpClient client;
    public CookieStore cookieStore = new BasicCookieStore();
    public Scraper(){
        this.ContentType="application/x-www-form-urlencoded; charset=utf8;";
    }

    public String version = "";
    public void setJsonBody(T value){
        Gson gson = new Gson();

        String jsonString = gson.toJson(value);
        Type type = new TypeToken<T>(){}.getType();
        jsonBody =  gson.fromJson(jsonString, JsonObject.class);
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

        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, trustAllCerts, new SecureRandom());
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sc);
        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(csf)
                .build();
        if(method.equals("POST")){
            HttpHost proxy = new HttpHost("localhost", 8888);
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

            //피들러 캡쳐
            //client = HttpClients.custom().setSSLSocketFactory(csf).setRoutePlanner(routePlanner).setDefaultCookieStore(cookieStore).build();
            client = HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(cookieStore).build();

            HttpPost httpPost = new HttpPost(targetUrl);

            String reqeustBody = "";
            if(this.ContentType == "application/json; charset=UTF-8"){
                httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
                Gson gson = new Gson();
                reqeustBody = gson.toJson(jsonBody);
                httpPost.setEntity(new StringEntity(reqeustBody));
            }else{
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            }

            CloseableHttpResponse response = client.execute(httpPost);
            List<Cookie> cookies1 = cookieStore.getCookies();

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            Html = result;
        }else if(method.equals("GET")){
            if(!this.version.equals("")) {
                /*****/
                try (final CloseableHttpAsyncClient client = HttpAsyncClients.customHttp2()
                        .build()) {

                    client.start();

                    final HttpHost target = new HttpHost("https", "opensea.io");
                    final HttpClientContext clientContext = HttpClientContext.create();

                    final SimpleHttpRequest request = SimpleRequestBuilder.get()
                            .setHttpHost(target)
                            .setPath("/collection/mtdz-1")
                            .setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
                            .setHeader("referer", "https://opensea.io/")
                            .build();

                    System.out.println("Executing request " + request);
                    final Future<SimpleHttpResponse> future = client.execute(
                            SimpleRequestProducer.create(request),
                            SimpleResponseConsumer.create(),
                            clientContext,
                            new FutureCallback<SimpleHttpResponse>() {

                                @Override
                                public void completed(final SimpleHttpResponse response) {
                                    System.out.println(request + "->" + new StatusLine(response));
                                    final SSLSession sslSession = clientContext.getSSLSession();
                                    if (sslSession != null) {
                                        System.out.println("SSL protocol " + sslSession.getProtocol());
                                        System.out.println("SSL cipher suite " + sslSession.getCipherSuite());
                                    }
                                    Html = response.getBodyText();
                                    System.out.println(response.getBody());
                                }

                                @Override
                                public void failed(final Exception ex) {
                                    System.out.println(request + "->" + ex);
                                }

                                @Override
                                public void cancelled() {
                                    System.out.println(request + " cancelled");
                                }

                            });
                    future.get();

                    System.out.println("Shutting down");
                    client.close(CloseMode.GRACEFUL);
                }

                /****/
            }else {
                HttpHost proxy = new HttpHost("localhost", 8888);
                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

                //피들러 캡쳐
                client = HttpClients.custom().setConnectionManager(cm).setRoutePlanner(routePlanner).setDefaultCookieStore(cookieStore).build();
                //client = HttpClients.custom().setSSLSocketFactory(csf).setDefaultCookieStore(cookieStore).build();


                HttpGet httpGet = new HttpGet(targetUrl);

                CloseableHttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                this.Html = result;
                if (result.contains("로그아웃")) {
                    System.out.println("로그인 성공");
                } else {
                    System.out.println("로그인 실패");
                }
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
