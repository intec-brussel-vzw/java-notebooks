package be.intecbrussel;

import lombok.Data;

import java.util.Objects;

@Data
public class Message {

    private int messageId;

    private int fromUserId;

    private int toUserId;

    private String messageContent;

}