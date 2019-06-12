package com.example.aleks.wordgame.Models;

public class Chat {
    String message;
    Boolean queue;

    public Chat(String message, Boolean queue) {
        this.message = message.toLowerCase();
        this.queue = queue;
    }

    public Boolean getQueue() {
        return queue;
    }

    public void setQueue(Boolean queue) {
        this.queue = queue;
    }

    public String getMessage() {
        return message.toLowerCase();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
