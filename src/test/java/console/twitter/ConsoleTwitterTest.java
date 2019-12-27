package console.twitter;

import console.twitter.api.ConsoleTwitter;
import console.twitter.handler.impl.CommandHandler;
import console.twitter.storage.PostStore;
import console.twitter.storage.UserStore;
import org.junit.Assert;
import org.junit.Test;

import static console.twitter.handler.impl.Constants.REVERSE_CHRONOLOGICAL_ORDER;

public class ConsoleTwitterTest {

    PostStore postStore = new PostStore();
    UserStore userStore = new UserStore();
    CommandHandler commandHandler = new CommandHandler(postStore, userStore, REVERSE_CHRONOLOGICAL_ORDER);
    ConsoleTwitter consoleTwitter = new ConsoleTwitter(commandHandler);

    @Test
    public void userShouldAbleToPostATweet(){
        String input = "Alice -> I love the weather today";
        String response = consoleTwitter.process(input);

        Assert.assertEquals("",response);
    }

    @Test
    public void userShouldAbletoReadHisMessages(){

        consoleTwitter.process("Alice -> I love the weather today");
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consoleTwitter.process("Alice -> Its raining!!");
        String response = consoleTwitter.process("Alice");
        System.out.println(response);

        Assert.assertEquals("Its raining!! (0 seconds ago)\nI love the weather today (2 seconds ago)",response);
    }

    @Test
    public void userShouldReadAllMessagesWithWall(){
        consoleTwitter.process("Alice -> Good Morning");
        consoleTwitter.process("Bob -> Good Afternoon");
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consoleTwitter.process("Bob -> Good Night");

        String response = consoleTwitter.process("Bob wall");
        System.out.println(response);

        Assert.assertEquals("Bob - Good Night (0 seconds ago)\nBob - Good Afternoon (0 seconds ago)",response);
    }

    @Test
    public void followerShouldAbleToFollowUser(){
        consoleTwitter.process("Alice -> Good Morning");
        consoleTwitter.process("Alice -> Good Night");
        consoleTwitter.process("Bob -> Hello Everyone");

        String response = consoleTwitter.process("Bob follows Alice");

        Assert.assertEquals("", response);
    }

    @Test
    public void followerShouldAbleToReadSubscribedUsersTweet(){
        consoleTwitter.process("Alice -> Good Morning");
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consoleTwitter.process("Alice -> Good Night");
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consoleTwitter.process("Bob -> Hello Everyone");
        consoleTwitter.process("Bob follows Alice");

        String response = consoleTwitter.process("Bob wall");

        Assert.assertEquals("Bob - Hello Everyone (0 seconds ago)\nAlice - Good Night (0 seconds ago)\nAlice - Good Morning (0 seconds ago)",response);
    }


}
