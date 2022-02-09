package com.FerrisIOT;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class GetVal {
    public static LinkedList<String> GetData(String SID) {
        //Data Request
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost lis = new HttpPost("http://10.35.81.223:8000/test");
        //Set Header
        lis.addHeader("Request", "cams");
        //Set Body
        try {
            StringEntity entity = new StringEntity(SID);
            lis.setEntity(entity);
        } catch (
                UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        //Data Response
        CloseableHttpResponse B = null;
        try {
            B = client.execute(lis);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> data = null;
        try {
            String temp = EntityUtils.toString(lis.getEntity());
            String[] temp2 = temp.split("\n");
            for (String indiv :temp2){
                data.add(indiv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }




}
