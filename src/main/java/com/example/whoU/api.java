package com.example.whoU;

import com.google.gson.Gson;

import java.io.IOException;

import java.net.URI;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;

//Create an object,input a file into this class
public class api {

    String apiKey = "0621432fce7383c1bc2986859c3dea0258aedad3dea59b55e89f075133ec9ec1";

    //Empty constructor
    public api() throws IOException, InterruptedException {
    }

    //By using the md5 strings from earlier, I am able to scan the databases to see if the file is malware.
    //The API is only able to use md5 strings


    //This code does not work due to the header structure. The response says that no file is found. The generate code on
    //the website does not help
    public static String report(String md5Str) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/files/" + md5Str))
                .header("accept", "application/json")
                .header("x-apikey", "0621432fce7383c1bc2986859c3dea0258aedad3dea59b55e89f075133ec9ec1")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();


        return body;

        //Generated code

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://www.virustotal.com/api/v3/files"))
//                .header("accept", "application/json")
//                .header("Content-Type", "text/plain")
//                .header("content-diposition", file.toString())
//                .header("x-apikey", "0621432fce7383c1bc2986859c3dea0258aedad3dea59b55e89f075133ec9ec1")
//                .POST((HttpRequest.BodyPublishers.ofFile(Paths.get(file.toString()))))
//                .build();
//
//

    }

    //This code takes the url, adds and scans into the database, opening up the brow
    public void urlScan(String url) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/urls"))
                .header("accept", "application/json")
                .header("x-apikey", "0621432fce7383c1bc2986859c3dea0258aedad3dea59b55e89f075133ec9ec1")
                .header("content-type", "application/x-www-form-urlencoded")
                .method("POST", HttpRequest.BodyPublishers.ofString("url=" + url))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //Pulls JSON from the request
        Gson gson = new Gson();
        String work = gson.toJson(response.body());
        String URL = "";

        //Iterates list
        List<String> listy = new ArrayList<String>();
        for (String splits : work.split("u")) {
            for (String spits2 : splits.split("-")) {
                if (!spits2.contains(" ")) {
                    listy.add(spits2);
                }
            }
        }

        URL = listy.get(1);
        //Opens desktop
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.virustotal.com/gui/url/" + URL));
    }
}



