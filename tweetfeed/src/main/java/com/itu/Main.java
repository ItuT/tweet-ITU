package com.itu;

import java.io.*;

import com.itu.Logs.SimpleLog;
import com.itu.Users.Users;
import com.itu.TweetFeed.TweedFeed;
import com.itu.Tweets.Tweets;

public class Main {
    public static void main(String[] args) {
        File tweetFile = new File("tweet.txt");
        File userFile = new File("user.txt");
        try {
            SimpleLog.log("Program Started");
            BufferedReader brUserFile = new BufferedReader(new FileReader(userFile));
            BufferedReader brTweetsFile = new BufferedReader(new FileReader(tweetFile));

           // extract main.java.com.itu.Users
            Users users = new Users();
            users.extractUsers(brUserFile);

            // update main.java.com.itu.Users To Alphabetical List
            users.updateUsersToAlphabeticalList();

           // extract main.java.com.itu.Tweets
            Tweets tweets = new Tweets();
            tweets.extractTweets(brTweetsFile);

          //  print Tweed Feed
            TweedFeed tweedFeed = new TweedFeed();
            tweedFeed.printTweedFeedFromList(users.getUserAlphaList(),tweets.getListOfTweets(),users.getUserFollowersAlphabeticalListMap());

        } catch (FileNotFoundException fnfe) {
            SimpleLog.log("File was not found "+ fnfe.getLocalizedMessage());
        }
    }
}
