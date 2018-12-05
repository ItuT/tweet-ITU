import com.itu.Tweets.Tweets;
import com.itu.Users.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(Users.class)
public class TweetsTest {


    @Spy
    Tweets tweets;

    File tweetFile = new File("tweet.txt");
    BufferedReader brTweetsFile = null;

    @Before
    public void setUp() throws  IOException {
        brTweetsFile = new BufferedReader(new FileReader(tweetFile));
    }

    @Test
    public void testExtractTweets()  {
        tweets = new Tweets();
        tweets.extractTweets(brTweetsFile);
        assertEquals("extractTweets failed: ", 3,tweets.getListOfTweets().size());
    }

    @Test
    public void testMapUserTweet()  {
        tweets = new Tweets();
        tweets.extractTweets(brTweetsFile);
        assertEquals("extractTweets failed: ",
                " There are only two hard things in Computer Science: cache invalidation, " +
                        "naming things and off-by-1 errors.",tweets.getMapUserTweet().get("Ward")[0].toString());
    }

}