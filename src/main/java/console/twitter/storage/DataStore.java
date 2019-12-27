package console.twitter.storage;

import java.util.stream.Stream;

public interface DataStore<T> {
    void store(T t);

    Stream<T> load();
}
