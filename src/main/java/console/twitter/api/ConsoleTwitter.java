package console.twitter.api;

public class ConsoleTwitter {

    private ConsoleHandler commandHandler;

    public ConsoleTwitter(ConsoleHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public String process(String userInput){
        return commandHandler.handle(userInput);

    }
}
