QuickChat Java Messaging App – Part 2 (PROG5121 PoE)

This is the Part 2 implementation of the QuickChat app for the Programming 1A (PROG5121) Portfolio of Evidence. It extends the login/registration system from Part 1 and adds a full messaging system with GUI (JFrame), JSON storage, validation, and unit testing.

Features Implemented in Part 2

Messaging Functionality
- Users can send messages to other users using a simple and intuitive GUI.
- Each message includes:
  - A **unique 10-digit ID**
  - An auto-incremented **message number**
  - The recipient’s **cell phone number**
  - The message **text** (limited to 250 characters)
  - A **message hash** in the format: `XX:Y:FIRSTLAST`

---

### 🛡️ Input Validation
Validation rules are applied before sending or storing:
| Field        | Validation Rule                                                  |
|--------------|------------------------------------------------------------------|
| Username     | Must contain an underscore (`_`) and be no more than 5 characters |
| Password     | Must contain at least 1 capital letter, 1 digit, 1 special character, and be ≥ 8 characters |
| Cell Number  | Must follow the format `+27` followed by 9 digits (12 total)     |
| Message      | Must be ≤ 250 characters                                         |

Unit Testing
The following tests were written in `MessageTest.java`:
- Valid and invalid message length
- Valid and invalid recipient cell numbers
- Valid message hash format

JSON Message Storage
- Messages can be **stored in a JSON file** named `messages.json` using the **Gson** library.
- Messages can be viewed in a scrollable text area using the GUI.

Java Class Breakdown

| File Name            | Purpose                                                   |
|----------------------|-----------------------------------------------------------|
| `LoginBackend.java`  | Handles validation for registration                       |
| `Message.java`       | Handles all message logic: ID, validation, hashing        |
| `MessageStorage.java`| Saves and loads messages to/from `messages.json`          |
| `QuickChatApp.java`  | Main `JFrame` GUI for sending, storing, and viewing messages |
| `MessageTest.java`   | Contains JUnit test cases for Part 2                      |

Sample Working Credentials for Registration

Use the following credentials to register successfully:

```text
Username: ab_c
Password: Passw0rd!
Cell:     +27831234567

 
