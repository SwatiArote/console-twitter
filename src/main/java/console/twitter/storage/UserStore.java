package console.twitter.storage;

import console.twitter.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserStore implements DataStore<User>{

    private List<User> users = new ArrayList<>();

    public Stream<User> load(){
        return users.stream();
    }

    public void store(User user){
        users.add(user);
    }
}
