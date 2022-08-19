package be.intecbrussel;

import lombok.Data;

@Data
public class Message {

    private int messageId;

    private int fromUserId;

    private int toUserId;

    private String messageContent;

}