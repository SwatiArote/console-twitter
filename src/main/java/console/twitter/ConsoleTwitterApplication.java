package console.twitter;

import console.twitter.api.ConsoleHandler;
import console.twitter.api.ConsoleTwitter;
import console.twitter.handler.impl.CommandHandler;
import console.twitter.model.Post;
import console.twitter.model.User;
import console.twitter.storage.DataStore;
import console.twitter.storage.PostStore;
import console.twitter.storage.UserStore;

import java.util.Scanner;

import static console.twitter.handler.impl.Constants.REVERSE_CHRONOLOGICAL_ORDER;

public class ConsoleTwitterApplication {

    public static void main(String[] args) {

        DataStore<Post> postStore = new PostStore();
        DataStore<User> userStore = new UserStore();
        ConsoleHandler commandHandler = new CommandHandler(postStore, userStore, REVERSE_CHRONOLOGICAL_ORDER);

        ConsoleTwitter consoleTwitter = new ConsoleTwitter(commandHandler);
        System.out.println("Welcome to console twitter");
        do {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String response = consoleTwitter.process(userInput);
            System.out.println(response);
        }while (true);
    }

}
