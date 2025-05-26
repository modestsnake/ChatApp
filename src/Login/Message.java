/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.util.Random;

public class Message {

    private static int messageCount = 0;
    private String messageId;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private String messageHash;
    private boolean isSent = false;

    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageId = generateMessageId();
        this.messageNumber = ++messageCount;
        this.messageHash = createMessageHash();
    }

    private String generateMessageId() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkRecipientCell() {
        return recipient.matches("^\\+27\\d{9}$");
    }

    public boolean checkMessageLength() {
        return messageText.length() <= 250;
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return messageId.substring(0, 2) + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }

    public String sendMessage(String option) {
        switch (option.toLowerCase()) {
            case "send":
                isSent = true;
                return "Message successfully sent.";
            case "store":
                // Placeholder, actual store logic can use JSON
                return "Message successfully stored.";
            case "disregard":
                return "Press 0 to delete message.";
            default:
                return "Invalid option.";
        }
    }

    public String printMessage() {
        return "Message ID: " + messageId + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    public boolean isSent() {
        return isSent;
    }

    public static int getTotalMessagesSent() {
        return messageCount;
    }
}
