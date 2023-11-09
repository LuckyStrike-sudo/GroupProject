package com.example.boardswipe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Profile {
    private String name;
    private URL gameImage;
    private URL websiteLink;
    private int rating;
    private int minPlayTime;
    private int maxPlayTime;
    private int minPlayers;
    private int maxPlayers;
    private double weight;
    public Profile(int one, int tens, int hundred, int thousand, int tenThou, int hunThou) throws Exception {
        try{
            URL url = new URL("http://www.boardgamegeek.com/boardgame/" + hunThou + tenThou
                    + thousand + hundred + tens + one);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("Profile.html"));

            String line;
            while((line = reader.readLine()) != null) {
                writer.write(line);
            }

            reader.close();
            writer.close();

            BufferedReader file = new BufferedReader(new FileReader("Profile.html"));

            String data;
            while((data = file.readLine()) != null){

                if(data.contains("href") && data.contains("boardgamegeek")){
                    int startIdx = data.indexOf("href=\"");
                    int endIdx = data.indexOf("\"", startIdx);
                    websiteLink = new URL(data.substring(startIdx + 1, endIdx));
                }

                if(data.contains("href") && data.contains("itemrep/img")){
                    int startIdx = data.indexOf("href=\"");
                    int endIdx = data.indexOf("\"", startIdx);
                    gameImage = new URL(data.substring(startIdx + 1, endIdx));
                }

                if(data.contains("\"averageweight\":") && data.contains("\"average\":") && data.contains("\"name\":") && data.contains("\"minplayers\":") && data.contains("\"maxplayers\":")
                        && data.contains("\"minplaytime\":") && data.contains("\"maxplaytime\":")){

                    int startIdx = data.indexOf("\"averageweight\":");
                    int endIdx = data.indexOf(",");
                    weight = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"average\":\"");
                    endIdx = data.indexOf("\"");
                    rating = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"name\":\"");
                    endIdx = data.indexOf("\"");
                    name = data.substring(startIdx + 1, endIdx);

                    startIdx = data.indexOf("\"minplayers\":\"");
                    endIdx = data.indexOf("\"");
                    minPlayers = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"maxplayers\":\"");
                    endIdx = data.indexOf("\"");
                    maxPlayers = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"minplaytime\":\"");
                    endIdx = data.indexOf("\"");
                    minPlayTime = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"maxplaytime\":\"");
                    endIdx = data.indexOf("\"");
                    maxPlayTime = Integer.parseInt(data.substring(startIdx + 1, endIdx));

                    startIdx = data.indexOf("\"minplayers\":\"");
                    endIdx = data.indexOf("\"");
                    minPlayers = Integer.parseInt(data.substring(startIdx + 1, endIdx));
                }
            }

            file.close();

        } catch(Exception e) {
            e = new Exception("Website could not be accessed");
            throw e;
        }
    }

    public Profile(String gameName, URL image, URL link, int rate, int leastPlayTime, int mostPlayTime, int leastPlayers, int mostPlayers, double difficulty){
        name = gameName;
        gameImage = image;
        websiteLink = link;
        rating = rate;
        minPlayTime = leastPlayTime;
        maxPlayTime = mostPlayTime;
        minPlayers = leastPlayers;
        maxPlayers = mostPlayers;
        weight = difficulty;
    }

    public String getName() {
        return name;
    }

    public URL getGameImage() {
        return gameImage;
    }

    public URL getWebsiteLink() {
        return websiteLink;
    }

    public int getRating() {
        return rating;
    }

    public int getMinPlayTime() {
        return minPlayTime;
    }

    public int getMaxPlayTime() {
        return maxPlayTime;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public double getWeight() {
        return weight;
    }
}
