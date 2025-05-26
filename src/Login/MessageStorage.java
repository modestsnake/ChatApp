/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading messages to/from a JSON file using Gson.
 */
public class MessageStorage {

    private static final String FILE_PATH = "messages.json";
    private static final Gson gson = new Gson();

    // Load messages from JSON file
    public static List<Message> loadMessages() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error reading messages: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void storeMessage(Message message) {
        List<Message> messages = loadMessages();
        messages.add(message);

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Error writing message: " + e.getMessage());
        }
    }
}