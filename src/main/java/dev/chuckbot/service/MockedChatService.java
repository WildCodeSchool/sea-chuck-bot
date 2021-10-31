package dev.chuckbot.service;

import dev.chuckbot.ChuckNorrisJokeService;
import dev.chuckbot.Joke;

public class MockedChatService {

    private final ChuckNorrisJokeService chuckNorrisJokeService;
    private String tag;
    private RocketChatChannel channel;

    public MockedChatService(String channelName, String tag, ChuckNorrisJokeService chuckNorrisJokeService) {
        this.channel = new RocketChatChannel(channelName);
        this.tag = tag;
        this.chuckNorrisJokeService = chuckNorrisJokeService;
    }

    public void publishMessage(String message) {
        if (message.matches(tag)) {
            try {
                Joke jokeEntities = chuckNorrisJokeService.getNewestJoke();
                channel.publishMessage(jokeEntities.getJokeText());
            } catch (Exception ex) {
                System.err.println("Wow! That's sad: " + ex.getMessage());
            }
        }
    }

}
