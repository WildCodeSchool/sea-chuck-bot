package dev.chuckbot.service;

public class RocketChatChannel {

    private final String channel;

    public RocketChatChannel(String channel) {
        this.channel = channel;
    }

    public void publishMessage(String message) {
        System.out.println(String.format("Posted on Channel %s: %s", message, channel));
    }
}
