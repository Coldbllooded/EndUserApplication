package com.FerrisIOT.HTTP;

import com.FerrisIOT.GetVal;
import com.FerrisIOT.HTTPS;
import com.FerrisIOT.Select;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class HttpController {
    public static String HTTPC(String UN, String PW){
        String Status = "Null";
        String URI = "https://10.35.80.77:8000/test";
        //Get
        HTTPS.HTTPs(URI);
        //HTTPS.HTTPGET(URI);
        //Post
        //CloseableHttpResponse response = HTTPS.HTTPPOST(URI,UN,PW);
        //Response use
        /*
        try {
            Status = EntityUtils.toString(response.getEntity());
            if(Status.equals("%INVALID"))
            {
                //Reset to login
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Session and User IDs
        //String[] UID = Status.split("\\|");
        //Session ID = UID[0]
        //User ID = UID[1]
        LinkedList names = GetVal.GetData(Status);
        new Select(names);




        try {
            response.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
*/
        return(Status);
    }
}
