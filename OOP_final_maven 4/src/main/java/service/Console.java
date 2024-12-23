package service;

import database.DatabaaseeActions;
import users.*;
import uni.Course;
import uni.Marks;
import service.Request;
import enums.UrgencyLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Console {
    private final List<String> buffer;
    private final Scanner scanner;

    public Console() {
        this.buffer = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void bufferMessage(String message) {
        buffer.add(message);
    }

    public void printBuffered(String language) {
        for (String message : buffer) {
            System.out.println(translate(message, language));
        }
        buffer.clear();
    }

    private String translate(String text, String language) {
        switch (language.toLowerCase()) {
            case "ru":
                return "[RU] " + text;
            case "kz":
                return "[KZ] " + text;
            case "en":
            default:
                return text;
        }
    }

    public String readInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    public void run() {
        bufferMessage("Welcome to the University System!");
        printBuffered("en");

        User loggedInUser = login();
        if (loggedInUser != null) {
            handleUserFunctions(loggedInUser);
        } else {
            bufferMessage("Login failed. Exiting the system.");
            printBuffered("en");
        }
    }

    private User login() {
        bufferMessage("Please enter your username:");
        printBuffered("en");
        String username = readInput("");

        Optional<User> userOptional = DatabaaseeActions.findAll("Users")
                .stream()
                .filter(obj -> obj instanceof User && ((User) obj).getUsername().equals(username))
                .map(obj -> (User) obj)
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            bufferMessage("Please enter your password:");
            printBuffered("en");
            String password = readInput("");

            if (user.getPassword().equals(password)) {
                bufferMessage("Login successful. Welcome, " + user.getName() + "!");
                printBuffered("en");
                return user;
            } else {
                bufferMessage("Incorrect password.");
                printBuffered("en");
            }
        } else {
            bufferMessage("User not found.");
            printBuffered("en");
        }
        return null;
    }

    private void handleUserFunctions(User user) {
        if (user instanceof Admin) {
            handleAdminFunctions((Admin) user);
        } else if (user instanceof Dean) {
            handleDeanFunctions((Dean) user);
        } else if (user instanceof Manager) {
            handleManagerFunctions((Manager) user);
        } else if (user instanceof Teacher) {
            handleTeacherFunctions((Teacher) user);
        } else if (user instanceof Student) {
            handleStudentFunctions((Student) user);
        } else if (user instanceof TechSupporter) {
            handleTechSupporterFunctions((TechSupporter) user);
        } else {
            bufferMessage("Unknown user role. Exiting.");
            printBuffered("en");
        }
    }

    private void handleAdminFunctions(Admin admin) {
        boolean running = true;
        while (running) {
            bufferMessage("Admin Menu:");
            bufferMessage("1. Create User");
            bufferMessage("2. Update User");
            bufferMessage("3. Remove User");
            bufferMessage("4. See All Users");
            bufferMessage("5. See Log Files");
            bufferMessage("6. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    admin.createUser();
                    bufferMessage("User created successfully.");
                    break;
                case "2":
                    int updateUserId = Integer.parseInt(readInput("Enter user ID to update:"));
                    String newUsername = readInput("Enter new username:");
                    String newPassword = readInput("Enter new password:");
                    String newName = readInput("Enter new name:");
                    User updatedUser = new User(updateUserId, newUsername, newPassword, newName);
                    admin.updateUser(updatedUser);
                    bufferMessage("User updated successfully.");
                    break;
                case "3":
                    int removeUserId = Integer.parseInt(readInput("Enter user ID to remove:"));
                    User userToRemove = new User(removeUserId, null, null, null);
                    admin.removeUser(userToRemove);
                    bufferMessage("User removed successfully.");
                    break;
                case "4":
                    bufferMessage("All Users:");
                    admin.seeAllUsers();
                    break;
                case "5":
                    bufferMessage("Log Files:");
                    admin.seeLogFiles();
                    break;
                case "6":
                    bufferMessage("Exiting admin menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    private void handleDeanFunctions(Dean dean) {
        boolean running = true;
        while (running) {
            bufferMessage("Dean Menu:");
            bufferMessage("1. View Requests");
            bufferMessage("2. Sign Request");
            bufferMessage("3. Add Request");
            bufferMessage("4. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    bufferMessage("Requests:");
                    bufferMessage(dean.requests.toString());
                    break;
                case "2":
                    bufferMessage("Signing a request.");
                    dean.signRequest();
                    break;
                case "3":
                    bufferMessage("Adding a new request.");
                    dean.addRequest();
                    break;
                case "4":
                    bufferMessage("Exiting dean menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    private void handleManagerFunctions(Manager manager) {
        boolean running = true;
        while (running) {
            bufferMessage("Manager Menu:");
            bufferMessage("1. Approve Registration");
            bufferMessage("2. Manage Teachers");
            bufferMessage("3. Manage Students");
            bufferMessage("4. Manage News");
            bufferMessage("5. View Request Messages");
            bufferMessage("6. Create Report");
            bufferMessage("7. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    bufferMessage("Approving student registration.");
                    String studentName = readInput("Enter student name:");
                    String courseName = readInput("Enter course name:");
                    break;
                case "2":
                    bufferMessage("Managing teachers.");
                    break;
                case "3":
                    bufferMessage("Managing students.");
                    break;
                case "4":
                    bufferMessage("Managing news.");
                    break;
                case "5":
                    bufferMessage("Viewing request messages.");
                    manager.viewRequestMessages();
                    break;
                case "6":
                    bufferMessage("Creating report.");
                    manager.createReport();
                    break;
                case "7":
                    bufferMessage("Exiting manager menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    private void handleTeacherFunctions(Teacher teacher) {
        boolean running = true;
        while (running) {
            bufferMessage("Teacher Menu:");
            bufferMessage("1. View Courses");
            bufferMessage("2. View Marks");
            bufferMessage("3. Grade Student");
            bufferMessage("4. Send Request");
            bufferMessage("5. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    bufferMessage("Courses:");
                    teacher.viewCourses();
                    break;
                case "2":
                    bufferMessage("Viewing marks.");
                    break;
                case "3":
                    bufferMessage("Grading a student.");
                    break;
                case "4":
                    bufferMessage("Sending a request.");
                    break;
                case "5":
                    bufferMessage("Exiting teacher menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    private void handleStudentFunctions(Student student) {
        boolean running = true;
        while (running) {
            bufferMessage("Student Menu:");
            bufferMessage("1. View Marks");
            bufferMessage("2. View Courses");
            bufferMessage("3. Add Course");
            bufferMessage("4. Drop Course");
            bufferMessage("5. Rate Teacher");
            bufferMessage("6. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    bufferMessage("Viewing marks.");
                    student.viewMarks();
                    break;
                case "2":
                    bufferMessage("Courses:");
                    bufferMessage(student.viewCourses());
                    break;
                case "3":
                    bufferMessage("Adding a course.");
                    break;
                case "4":
                    bufferMessage("Dropping a course.");
                    break;
                case "5":
                    bufferMessage("Rating a teacher.");
                    break;
                case "6":
                    bufferMessage("Exiting student menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    private void handleTechSupporterFunctions(TechSupporter techSupporter) {
        boolean running = true;
        while (running) {
            bufferMessage("Tech Support Menu:");
            bufferMessage("1. Accept Ticket");
            bufferMessage("2. Reject Ticket");
            bufferMessage("3. View Tickets by Status");
            bufferMessage("4. Exit");
            printBuffered("en");

            String choice = readInput("Enter your choice:");
            switch (choice) {
                case "1":
                    bufferMessage("Accepting ticket.");
                    break;
                case "2":
                    bufferMessage("Rejecting ticket.");
                    break;
                case "3":
                    bufferMessage("Viewing tickets by status.");
                    break;
                case "4":
                    bufferMessage("Exiting tech support menu.");
                    running = false;
                    break;
                default:
                    bufferMessage("Invalid choice. Try again.");
            }
            printBuffered("en");
        }
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.run();
    }
}

