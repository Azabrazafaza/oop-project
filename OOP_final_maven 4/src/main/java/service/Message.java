package service;

import lombok.Getter;
import users.Employee;
import users.User;

import java.time.LocalDateTime;

public class Message {
    @Getter
    private Employee sender;
    @Getter
    private Employee recipient;
    private String content;
    private LocalDateTime timestamp;

    public Message(Employee sender, Employee recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String formatMessage() {
        return "From: " + sender + "\nTo: " + recipient + "\nMessage: " + content + "\nSent: " + timestamp;
    }

    public void sendMessage(User recipient) {
        System.out.println("Message sent to: " + recipient.getName());

    }
    @Override
    public String toString() {
        return formatMessage();
    }
}
