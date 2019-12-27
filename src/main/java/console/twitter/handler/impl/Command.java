package console.twitter.handler.impl;


import console.twitter.model.Post;
import console.twitter.model.Username;

import java.util.function.Function;

abstract class Command{

    private Username username;

    protected Command(Username username) {
        this.username = username;
    }

    public Username getUsername() {
        return username;
    }
}

class Tweet extends Command{
    private String message;

    Tweet(String message,Username username) {
        super(username);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class Read extends Command{
    private Function<Post,String> display;

    Read(Function<Post, String> display, Username username) {
        super(username);
        this.display = display;
    }

    public Function<Post, String> getDisplay() {
        return display;
    }
}


class Follow extends Command{
    private Username followee;

    Follow(Username follower, Username username) {
        super(username);
        this.followee = follower;
    }

    public Username getFollowee() {
        return followee;
    }
}

class Wall extends Command{

    private Function<Post,String> display;

    Wall(Function<Post, String> display, Username username) {
        super(username);
        this.display = display;
    }

    public Function<Post, String> getDisplay() {
        return display;
    }
}