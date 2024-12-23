package users;

import database.DatabaaseeActions;
import lombok.Getter;
import lombok.Setter;
import service.Message;
import service.Request;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.List;
import java.util.Objects;

public abstract class Employee extends User {
    @Setter
    @Getter
    protected List<Message> messages;
    private static final List<Employee> allEmployees = new ArrayList<>();
    public Employee(int id,
                    String username,
                    String password,
                    String name
    ) {
        super(id, username, password, name);
        allEmployees.add(this);
//        this.messages = DatabaaseeActions.findAll("Message");
    }
    public static Employee findByName(String name) {
        for (Employee employee : allEmployees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        return null;
    }
    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient name: ");
        String recipientName = scanner.nextLine();
        Employee recipient = Employee.findByName(recipientName);
        if (recipient == null) {
            System.out.println("Recipient not found. Message not sent.");
            return;
        }
        System.out.print("Enter your message: ");
        String content = scanner.nextLine();
        Message message = new Message(this, recipient, content);
        recipient.receiveMessage(message);
        DatabaaseeActions.save("Message", message);
        System.out.println("Message sent to " + recipient.getName());
    }


    public void receiveMessage(Message message){
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
        System.out.println("Received message from "+ message.getSender().getName());
    }
    public void viewReceivedMessages(){

        System.out.println("Messages: ");

        for(Message message : messages){
            System.out.println((message.formatMessage()));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return  super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    public abstract void viewSignedRequest(List<Request> requests);

    @Override
    public String toString() {
        return super.toString() ;
    }
}