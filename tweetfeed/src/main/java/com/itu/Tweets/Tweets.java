package com.itu.Tweets;

import com.itu.Logs.SimpleLog;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tweets {

    private HashMap<String,String[]> mapUserTweet = new HashMap<>();
    private List<Map<String,String>> listOfTweets = new ArrayList<>();

    public void extractTweets(BufferedReader brTweetsFile){
        SimpleLog.log("extractTweets(BufferedReader brTweetsFile)");
        String st;
        try {
            while ((st = brTweetsFile.readLine()) != null) {
                String[] names = st.split(">");
                updateMapUserTweets(names[0],names[1]);

            }
        }catch (IOException ioe){
            SimpleLog.log(ioe.getMessage());
        }
    }

    private void updateMapUserTweets(String user, String tweet){
        SimpleLog.log("updateMapUserTweets(String user, String tweet)");
        String[] userTweets = new String[1];
        userTweets[0] = tweet;

        if(mapUserTweet.containsKey(user)){
            String[] currentFollowing = mapUserTweet.get(user);
            String[] both = ArrayUtils.addAll(userTweets, currentFollowing);

            mapUserTweet.put(user,both);
        }else{
            mapUserTweet.put(user,userTweets);
        }

        Map<String,String> map = new HashMap<>();
        map.put(user,tweet);
        listOfTweets.add(map);
    }

    public HashMap<String, String[]> getMapUserTweet() {
        return mapUserTweet;
    }

    public List<Map<String, String>> getListOfTweets() {
        return listOfTweets;
    }
}
