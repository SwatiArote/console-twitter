package console.twitter.storage;

import console.twitter.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PostStore implements DataStore<Post> {

    private List<Post> posts = new ArrayList<>();

    @Override
    public void store(Post post){
        posts.add(post);
    }

    @Override
    public Stream<Post> load(){
        return posts.stream();
    }
}
