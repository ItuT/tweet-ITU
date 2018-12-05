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
public class UsersTest {


    @Spy
    Users users;


    File userFile = new File("user.txt");
    BufferedReader brUserFile = null;
    BufferedReader brTweetsFile = null;

    @Before
    public void setUp() throws  IOException {
        brUserFile = new BufferedReader(new FileReader(userFile));
    }

    @Test
    public void testExtractUsers() throws IOException {
        users = new Users();
        users.extractUsers(brUserFile);

        assertEquals("ExtractUsers failed: ", 2,users.getMapUserFollows().size());
    }

    @Test
    public void testUpdateUsersToAlphabeticalList() throws IOException {
        users = new Users();
        users.extractUsers(brUserFile);
        users.updateUsersToAlphabeticalList();

        assertEquals("UpdateUsersToAlphabeticalList failed: ", "Alan",users.getUserAlphaList().get(0));
        assertEquals("UpdateUsersToAlphabeticalList failed: ", "Martin",users.getUserAlphaList().get(1));
        assertEquals("UpdateUsersToAlphabeticalList failed: ", "Ward",users.getUserAlphaList().get(2));
    }
}