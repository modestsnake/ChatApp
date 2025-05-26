/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class QuickChatApp extends JFrame {

    private JButton sendMessageBtn;
    private JButton viewMessagesBtn;
    private JButton quitBtn;

    public QuickChatApp(String username) {
        setTitle("QuickChat - Welcome " + username);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to QuickChat", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        sendMessageBtn = new JButton("Send Message");
        viewMessagesBtn = new JButton("View Stored Messages");
        quitBtn = new JButton("Quit");

        add(welcomeLabel);
        add(sendMessageBtn);
        add(viewMessagesBtn);
        add(quitBtn);

        sendMessageBtn.addActionListener(this::sendMessageAction);
        viewMessagesBtn.addActionListener(this::viewMessagesAction);
        quitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void sendMessageAction(ActionEvent e) {
        String recipient = JOptionPane.showInputDialog(this, "Enter recipient number (+27...)");
        if (recipient == null) return;

        String messageText = JOptionPane.showInputDialog(this, "Enter your message (max 250 characters)");
        if (messageText == null) return;

        Message message = new Message(recipient, messageText);

        if (!message.checkRecipientCell()) {
            JOptionPane.showMessageDialog(this, "Invalid cell number. Must start with +27 and be 12 characters.");
            return;
        }

        if (!message.checkMessageLength()) {
            int extra = messageText.length() - 250;
            JOptionPane.showMessageDialog(this, "Message too long by " + extra + " characters.");
            return;
        }

        String[] actions = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an action",
                "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, actions, actions[0]);

        if (choice == -1) return;

        String result = message.sendMessage(actions[choice]);
        JOptionPane.showMessageDialog(this, result);

        if (actions[choice].equalsIgnoreCase("Store")) {
            MessageStorage.storeMessage(message);
        }

        if (actions[choice].equalsIgnoreCase("Send")) {
            JOptionPane.showMessageDialog(this, message.printMessage());
        }
    }

    private void viewMessagesAction(ActionEvent e) {
        List<Message> messages = MessageStorage.loadMessages();

        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No stored messages.");
            return;
        }

        StringBuilder output = new StringBuilder();
        for (Message m : messages) {
            output.append(m.printMessage()).append("\n\n");
        }

        JTextArea area = new JTextArea(output.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scroll, "Stored Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuickChatApp("User");
        });
    }
}
