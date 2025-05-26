/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.util.Scanner;

public class MessageApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");

        while (true) {
            System.out.println("Menu:\n1) Send Message\n2) Show Recently Sent Messages\n3) Quit");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                System.out.print("How many messages do you want to send? ");
                int num = Integer.parseInt(input.nextLine());

                for (int i = 0; i < num; i++) {
                    System.out.print("Enter recipient number (+27...): ");
                    String recipient = input.nextLine();
                    System.out.print("Enter your message: ");
                    String msg = input.nextLine();

                    Message message = new Message(recipient, msg);

                    if (!message.checkRecipientCell()) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain international code.");
                        continue;
                    }

                    if (!message.checkMessageLength()) {
                        int excess = msg.length() - 250;
                        System.out.println("Message exceeds 250 characters by " + excess + ", please reduce size.");
                        continue;
                    }

                    System.out.print("Choose action (send/store/disregard): ");
                    String option = input.nextLine();
                    String result = message.sendMessage(option);
                    System.out.println(result);

                    if (option.equalsIgnoreCase("send")) {
                        System.out.println(message.printMessage());
                    }
                }

                System.out.println("Total messages attempted: " + Message.getTotalMessagesSent());

            } else if (choice.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (choice.equals("3")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        input.close();
    }
}