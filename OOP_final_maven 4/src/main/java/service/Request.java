package service;

import enums.RequestType;
import enums.TicketStatus;
import enums.UrgencyLevel;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;
import users.Employee;
import users.User;

import java.time.LocalDateTime;
@Getter
@Setter
public class Request {

    private RequestType type;
    public Employee sender;
    public Employee recipient;
    public String report;
    private LocalDateTime timestamp;
    private UrgencyLevel urgencyLevel;
    private TicketStatus status;

    public Request(Employee sender, Employee recipient, String report,  UrgencyLevel urgencyLevel) {
        this.sender = sender;
        this.recipient = recipient;
        this.report = report;
        this.timestamp = LocalDateTime.now();
        this.urgencyLevel = urgencyLevel;
        this.type = RequestType.NOTSIGNED;
    }
    public void signRequest() {
        this.type = RequestType.SIGNED;
        System.out.println("Request signed by Dean");
    }

    public String formatRequest() {
        return "From: " + sender + "\nTo: " + recipient + "\nMessage: " + report + "\nSent: " + timestamp;
    }
    @Override
    public String toString(){
        return formatRequest();
    }

}
