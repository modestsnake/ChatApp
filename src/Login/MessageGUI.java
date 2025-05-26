/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import javax.swing.*;

public class MessageGUI {

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Send Message", "Show Sent Messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to QuickChat!",
                    "QuickChat", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                String recipient = JOptionPane.showInputDialog("Enter recipient number (+27...)");
                String messageText = JOptionPane.showInputDialog("Enter your message (max 250 chars)");

                Message message = new Message(recipient, messageText);

                if (!message.checkRecipientCell()) {
                    JOptionPane.showMessageDialog(null, "Invalid cell number. Must start with +27 and be 12 characters.");
                    continue;
                }

                if (!message.checkMessageLength()) {
                    int extra = messageText.length() - 250;
                    JOptionPane.showMessageDialog(null, "Message too long by " + extra + " characters.");
                    continue;
                }

                String[] sendOptions = {"Send", "Disregard", "Store"};
                int action = JOptionPane.showOptionDialog(null, "Choose an action",
                        "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, sendOptions, sendOptions[0]);

                String result = message.sendMessage(sendOptions[action]);
                JOptionPane.showMessageDialog(null, result);

                if (sendOptions[action].equalsIgnoreCase("Store")) {
                    MessageStorage.storeMessage(message);
                }

                if (sendOptions[action].equalsIgnoreCase("Send")) {
                    JOptionPane.showMessageDialog(null, message.printMessage());
                }

            } else if (choice == 1) {
                var messages = MessageStorage.loadMessages();
                if (messages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No stored messages found.");
                } else {
                    StringBuilder output = new StringBuilder();
                    for (Message m : messages) {
                        output.append(m.printMessage()).append("\n\n");
                    }
                    JTextArea textArea = new JTextArea(output.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
                    JOptionPane.showMessageDialog(null, scrollPane, "Stored Messages", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                break; // Quit
            }
        }
    }
}