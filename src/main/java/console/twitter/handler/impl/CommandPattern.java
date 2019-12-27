package console.twitter.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandPattern {
    TWEET("(?<user>.*) -> (?<message>.*)", new String[]{"user","message"}),
    READ("(?<user>.*)", new String[]{"user"}),
    WALL("(?<user>.*) wall", new String[]{"user"}),
    FOLLOW("(?<follower>.*) follows (?<followee>.*)", new String[]{"follower","followee"});

    private String value;
    private String[] groups;

    CommandPattern(String value, String[] groups) {
        this.value = value;
        this.groups = groups;
    }

    public String getValue() {
        return value;
    }

    public String[] getGroups() {
        return groups;
    }

    public String[] getParameters(String command){
        Pattern pattern = Pattern.compile(this.value);
        Matcher matcher = pattern.matcher(command);
        matcher.find();
        List<String> params = new ArrayList<>();
        for(String group : this.groups){
            params.add(matcher.group(group));
        }
        return params.toArray(new String[0]);
    }

    public boolean matches(String command){
        Pattern pattern = Pattern.compile(this.value);
        Matcher matcher = pattern.matcher(command);
        return matcher.matches();
    }
}
