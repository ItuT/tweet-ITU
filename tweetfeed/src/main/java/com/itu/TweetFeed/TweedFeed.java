package com.itu.TweetFeed;

import com.itu.Logs.SimpleLog;

import java.util.List;
import java.util.Map;

public class TweedFeed {

    public void printTweedFeedFromList(List<String> userAlphaList, List<Map<String,String>> listOfTweets,
                                        Map<String,String[]> treeMapOfSortedKeys) {
        SimpleLog.log("printTweedFeedFromList(List<String> userAlphaList, List<Map<String,String>> listOfTweets,\n" +
                "                                        Map<String,String[]> treeMapOfSortedKeys)");
        for (String userListed : userAlphaList) {

            if(!treeMapOfSortedKeys.containsKey(userListed)){
                System.out.println(userListed);
                continue;
            }
            String keyUser = userListed;
            String[] valueUser = treeMapOfSortedKeys.get(userListed);
            System.out.println(keyUser);

            for (Map tweetUserMap : listOfTweets) {
                for (Object tweetWithUser : tweetUserMap.keySet()) {

                    String keyTweet = tweetWithUser.toString();
                    String valueTweet = tweetUserMap.get(keyTweet).toString();

                    if (keyUser.equals(keyTweet) || keyUser.contains(keyTweet) || keyUser == keyTweet) {


                        System.out.println("\t @" + keyUser + ": " + valueTweet);

                    } else {
                        if (valueUser.length > 0) {
                            for (int i = 0; i < valueUser.length; i++) {
                                if (valueUser[i].equals(keyTweet) || valueUser[i].contains(keyTweet) ||
                                        valueUser[i] == keyTweet) {
                                    System.out.println("\t @" + valueUser[i] + ": " + valueTweet);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
