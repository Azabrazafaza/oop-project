package users;

import enums.Teacheros;
import enums.UrgencyLevel;
import uni.Course;
import service.Func;
import service.Request;

import java.util.List;
import java.util.Vector;
import java.util.Scanner;
public abstract class Dean extends Teacher {

//    public Dean() {
//    }

    public Vector<Request> requests = new Vector<>();

    public Dean(int id,
                String username,
                String password,
                String name,
                int employeeId,
                String teacherID,
                Teacheros title,
                List<Course> coursesTaught,
                double rating
    )
    {
        super(id, username, password, name, title, coursesTaught, rating);




    }

    public void signRequest() {
        System.out.println("All requests are signed.");
        requests.clear();
    }

    public void viewRequest() {
        System.out.println("Requests:");
        for (Request r : requests) {
            System.out.println("- " + r.getSender() + ": " + r.getUrgencyLevel());
        }
    }

    public void addRequest(Vector<String> validRecipients) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sender name: ");
        String senderName = scanner.nextLine();

        Employee sender = Employee.findByName(senderName);
        if (sender == null) {
            System.out.println("Sender not found.");
            return;
        }
        System.out.print("Enter recipient type: ");
        String recipientName = scanner.nextLine();

        Employee recipient = Employee.findByName(recipientName);
        if (recipient == null) {
            System.out.println("Recipient not found. Request not added.");
            return;
        }

        System.out.print("Enter request urgency level (LOW, MEDIUM, HIGH): ");
        String urgencyLevelInput = scanner.nextLine();
        UrgencyLevel urgencyLevel;

        try {
            urgencyLevel = UrgencyLevel.valueOf(urgencyLevelInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid urgency level. Request not added.");
            return;
        }
        System.out.print("Enter request description: ");
        String report = scanner.nextLine();

        Request newRequest = new Request(sender, recipient, report, urgencyLevel);
        requests.add(newRequest);

        System.out.println("Request added.");
    }
}