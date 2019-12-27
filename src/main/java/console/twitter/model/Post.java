package console.twitter.model;

public class Post {

    private Username username;
    private long timestamp;
    private String message;

    public Post(Username username, long timestamp, String message) {
        this.username = username;
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Username getUsername() {
        return username;
    }
}
