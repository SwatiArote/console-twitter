package console.twitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {

    public Username getUsername() {
        return username;
    }

    private Username username;
    private List<Username> follows = new ArrayList<>();

    public User(Username username) {
        this.username = username;
    }

    public List<Username> getFollows() {
        return Collections.unmodifiableList(follows);
    }

    public void follow(Username username){
        follows.add(username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
