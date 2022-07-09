package framework.com.example.demo.snapshot.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.snapshot.Snapshot;
import framework.com.example.demo.snapshot.TokenSnapshotPK;
import framework.com.example.demo.snapshot.repository.SnapshotRepository;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SnapshotService {
    @Autowired
    SnapshotRepository snapshotRepository;

    public void getSnapShot() throws Exception {
        try {
            String conractUrl = "0xff99f673d453245512e28f4dfbe4fb24428e55c1";
            String stakingUrl = "0xc7e3aa68f2fb0091bd3c7392eff139b5f95e11a0";

            for (int i = 203; i >= 1; i--) {
                Thread.sleep(6500);

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
                    if (items == null) {
                        break;
                    }


                    for (int j = items.size() - 1; j >= 0; j--) {

                        String createdAt = items.get(j).getAsJsonObject().get("createdAt").toString().replace("\"", "");
                        Instant instant = Instant.ofEpochSecond(Long.parseLong(createdAt));
                        LocalDateTime tsTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));
                        LocalDateTime agoDate = LocalDateTime.of(2022, 05, 31, 00, 00, 00);

                        if (agoDate.isBefore(tsTime)) {
                            //agoDate 보다 과거이면 리턴
                            break;
                        }
                        String tokenId = items.get(j).getAsJsonObject().get("tokenId").toString().replace("\"", "");
                        String fromAddress = items.get(j).getAsJsonObject().get("fromAddress").toString().replace("\"", "");
                        String toAddress = items.get(j).getAsJsonObject().get("toAddress").toString().replace("\"", "");

                        Snapshot sn = new Snapshot();
                        if (!toAddress.equals(stakingUrl)) {
                            sn.setAddress(toAddress);
                            sn.setTokenId(tokenId);
                            sn.setNftCode("BMZ");
                            sn.setDate(tsTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                            insert(sn);
                        }

                    }
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public boolean insert(Snapshot dto){
        Snapshot tokenInfo = Snapshot.builder()
                .tokenId(dto.getTokenId())
                .nftCode(dto.getNftCode())
                .address(dto.getAddress())
                .date(dto.getDate())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();


        TokenSnapshotPK tokenInfoPK = new TokenSnapshotPK();
        tokenInfoPK.setTokenId(dto.getTokenId());
        tokenInfoPK.setNftCode(dto.getNftCode());

        Optional<Snapshot> snapshot = snapshotRepository.findById(tokenInfoPK);
        return snapshot.map(user->{
                    Snapshot tokenInfo2 = Snapshot.builder()
                            .tokenId(dto.getTokenId())
                            .nftCode(dto.getNftCode())
                            .address(dto.getAddress())
                            .date(dto.getDate())
                            .createdDate(user.getCreatedDate())
                            .modifiedDate(LocalDateTime.now())
                            .build();
                    snapshotRepository.save(tokenInfo2);
                    return true;
                })
                .orElseGet(() -> {
                    snapshotRepository.save(tokenInfo);
                    return true;
                });

    }

    private HttpResponse Excute(HttpGet request) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                (TrustManager) new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
        };

        CloseableHttpClient client;
        CookieStore cookieStore = new BasicCookieStore();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sc);

        final SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();
        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .build();
        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        HttpHost proxy = new HttpHost("localhost", 8888);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        //피들러 캡쳐
        client = HttpClients.custom().setConnectionManager(cm).setRoutePlanner(routePlanner).setDefaultCookieStore(cookieStore).build();
        //client = HttpClients.custom().build();

        CloseableHttpResponse response = client.execute(request);
        return response;
    }
}
