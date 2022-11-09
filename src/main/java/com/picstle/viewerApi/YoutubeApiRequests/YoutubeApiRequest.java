package com.picstle.viewerApi.YoutubeApiRequests;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class YoutubeApiRequest {

    private String dataApiUrl = "https://www.googleapis.com/youtube/v3/search?key=";
   // https://www.googleapis.com/youtube/v3/search?key=
    // AIzaSyCeLrQSGNx77l83pnVLxlHiMsT4d_8k8Z4
    // &channelId=
    // UCMduSI8-aUA0Odv9FnVBaPg
    // &part=snippet,id&order=date&maxResults=10
    private String dataAcessKey = "AIzaSyCeLrQSGNx77l83pnVLxlHiMsT4d_8k8Z4";
    private String chennalId = "";
    private StringBuilder dataResponse;


    public String getChData(String chennalId) throws IOException {
        String CoUrl = dataApiUrl+dataAcessKey+"&channelId="+chennalId+"&part=snippet,id&order=date&maxResults=10";
        URL url = new URL(CoUrl);
        System.out.println(chennalId+"    "+CoUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
      //  JSONObject jsonObject = new JSONObject();

     //   jsonObject.append("data",connection.getInputStream().toString());
      //  StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        System.out.println("this is response we want "+response.toString());
        dataResponse = response;
        return dataResponse.toString();
//
//        JSONTokener jsonTokener = new JSONTokener(response.toString());
//        JSONArray jsonArray = new JSONArray(jsonTokener);

        //System.out.println(jsonArray);
    }


}
