package console.twitter.handler.impl;

import console.twitter.model.Post;

import java.util.Comparator;
import java.util.function.Function;

public class Constants {

    public static Comparator<Post> REVERSE_CHRONOLOGICAL_ORDER = (post1, post2) -> Long.compare(post2.getTimestamp(), post1.getTimestamp());

    public static Function<Post,String> WALL_DISPLAY= post -> post.getUsername().getValue()
            .concat(" - ")
            .concat(post.getMessage())
            .concat(getTimeLapse(post.getTimestamp(),System.currentTimeMillis()));

    public static Function<Post,String> READ_DISPLAY= post ->
            post.getMessage()
                    .concat(getTimeLapse(post.getTimestamp(),System.currentTimeMillis()));

    //TODO add test of this method
    private static String getTimeLapse(long fromTime, long toTime){
        long secondsElaspsed = (toTime - fromTime)/1000;
        if(secondsElaspsed < 60)
            return " ("+secondsElaspsed + " seconds ago)";
        else if(secondsElaspsed < 3600 )
            return " ("+secondsElaspsed/60 + " minutes ago)";
        else if(secondsElaspsed < 86400)
            return " ("+(secondsElaspsed/60)/60 + " hours ago)";
        else if(secondsElaspsed < 2592000)
            return " ("+(secondsElaspsed/60)/60/24 + " days ago)";
        else if (secondsElaspsed < 31104000 )
            return  " ("+(secondsElaspsed/60)/60/24/30 + " months ago)";
        else
            return  " ("+(secondsElaspsed/60)/60/24/30/12 + " years ago)";
    }

}
