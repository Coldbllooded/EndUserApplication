package com.FerrisIOT.HTTP;

import com.FerrisIOT.GetVal;
import com.FerrisIOT.Select;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.client.*;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import com.FerrisIOT.GetVal.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpTimeoutException;
import java.util.LinkedList;

public class HttpController {
    public static String HTTPC(String UN, String PW){
        String Status = "Null";
        HttpGet request = new HttpGet("http://10.35.81.223:8000/test");
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password")
        );

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // 401 if wrong user/password
            System.out.println(response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //Http Post
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://10.35.81.223:8000/test");

        //Set Header
        post.addHeader("Request","authentication");
        //Set Body
        try {
            StringEntity entity = new StringEntity(UN + "|" + PW);
            post.setEntity(entity);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //HTTP Response
        try {
            Status = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Session and User IDs
        String[] UID = Status.split("\n");
        //User ID = UID[0]
        //Session ID = UID[1]
        LinkedList names = GetVal.GetData(UID[1]);
        new Select(names);


        try {
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return(Status);
    }
}
