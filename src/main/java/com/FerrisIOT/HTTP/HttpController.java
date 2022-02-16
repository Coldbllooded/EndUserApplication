package com.FerrisIOT.HTTP;

public class HttpController {
    public static String HTTPC(String UN, String PW){
        String Status = "Null";
        String URI = "https://10.35.80.77:8000/test";
        //Get
        //HTTPS.HTTPs(URI);
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
        LinkedList names = Operations.GetData(Status);
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
