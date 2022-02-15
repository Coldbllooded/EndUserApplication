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
    public static LinkedList<Camera> GetData(String USID) {
        //Data Request
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost lis = new HttpPost("http://10.35.81.223:8000/test");
        //Set Header
        lis.addHeader("Request", "cams");
        //Set Body
        StringEntity entity = null;
        try {
            entity = new StringEntity(USID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        lis.setEntity(entity);
        //Data Response
        CloseableHttpResponse B = null;
        try {
            B = client.execute(lis);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<Camera> data = new LinkedList<>();
        try {
            String temp = EntityUtils.toString(lis.getEntity());
            String[] temp2 = temp.split("\n");
            for (String indiv :temp2){
                String[] camera = indiv.split("\\|");
                //Now we have array index 0 of array 'camera' as always the UUID of the thing we are looking at, and index 1 as the camera name
                data.add(new Camera(camera[0],camera[1]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }




}
