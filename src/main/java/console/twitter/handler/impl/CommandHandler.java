package console.twitter.handler.impl;

import console.twitter.api.ConsoleHandler;
import console.twitter.api.ConsoleTwitter;
import console.twitter.model.Post;
import console.twitter.model.User;
import console.twitter.model.Username;
import console.twitter.storage.DataStore;

import java.util.Comparator;
import java.util.stream.Collectors;

import static console.twitter.handler.impl.Constants.READ_DISPLAY;
import static console.twitter.handler.impl.Constants.WALL_DISPLAY;

public class CommandHandler implements ConsoleHandler {
    private DataStore<Post> postStore;
    private DataStore<User> userStore;
    private Comparator<Post> postSorter;

    public CommandHandler(DataStore<Post> postStore, DataStore<User> userStore, Comparator<Post> postSorter) {
        this.postStore = postStore;
        this.userStore = userStore;
        this.postSorter = postSorter;
    }

    @Override
    public String handle(String consoleCommand) {
        Command command = interpretCommand(consoleCommand);
        return execute(command);
    }
//TODO add unit tests
    private String execute(Command command){
        Username username = command.getUsername();

        User user = userStore.load()
                                .filter(u -> u.getUsername().equals(username))
                                .findFirst()
                            .orElseGet(() -> {
                                                User newUser = new User(username);
                                                userStore.store(newUser);
                                                return newUser;
                                            });

        if(command instanceof Tweet){
            postStore.store(new Post(username,System.currentTimeMillis(),((Tweet) command).getMessage()));
            return "";
        }else if(command instanceof Read){
            return postStore.load()
                                    .filter(post -> post.getUsername().equals(username))
                                    .sorted(postSorter)
                                    .map(((Read) command).getDisplay())
                                    .collect(Collectors.joining("\n"));
        }else if(command instanceof Wall){
            return postStore.load()
                                    .filter(post -> post.getUsername().equals(username) || user.getFollows().contains(post.getUsername()))
                                    .sorted(postSorter)
                                    .map(((Wall) command).getDisplay())
                                    .collect(Collectors.joining("\n"));
        }else if(command instanceof Follow){
            user.follow(((Follow) command).getFollowee());
            return "";
        }
        else return "";

    }



    private Command interpretCommand(String userInput) {

        if(CommandPattern.TWEET.matches(userInput)){
            String[] commandParams = CommandPattern.TWEET.getParameters(userInput);
            String message = commandParams[1];
            String username = commandParams[0];
            return new Tweet(message,new Username(username));
        }else if(CommandPattern.WALL.matches(userInput)){
            String[] commandParams = CommandPattern.WALL.getParameters(userInput);
            String username = commandParams[0];
            return new Wall(WALL_DISPLAY,new Username(username));
        }else if(CommandPattern.FOLLOW.matches(userInput)){
            String[] commandParams = CommandPattern.FOLLOW.getParameters(userInput);
            String followee = commandParams[0];
            String follower = commandParams[1];
            return new Follow(new Username(follower),new Username(followee));
        }else if(CommandPattern.READ.matches(userInput)){
            String[] commandParams = CommandPattern.READ.getParameters(userInput);
            String username = commandParams[0];
            return new Read(READ_DISPLAY,new Username(username));
        }
        else {
            throw new RuntimeException("Invalid Command");
        }
    }
}
