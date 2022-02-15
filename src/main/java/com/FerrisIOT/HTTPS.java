package com.FerrisIOT;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class HTTPS {
    public static void HTTPGET(String URI)
    {
        HttpGet request = new HttpGet(URI);
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password")
        );

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .setSSLContext(SSLContext.getDefault())//Don't know if this works
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static CloseableHttpResponse HTTPPOST(String URI, String UN, String PW)
    {
        //Http Post
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(URI);

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
        return (response);
    }
    public static void HTTPs(String URI) {
        URL myUrl = null;
        try {
            myUrl = new URL(URI);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
        conn.setRequestMethod("POST");
        String REP = conn.getResponseMessage();
        System.out.println(REP);




        conn.disconnect();

    }
}
