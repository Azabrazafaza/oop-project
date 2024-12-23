package users;

import database.DatabaaseeActions;
import enums.TicketStatus;
import service.Request;

import java.util.List;

public class TechSupporter extends Employee {

    private List<Request> tickets;

    public TechSupporter(int id, String username, String password, String name) {
        super(id, username, password, name);
    }


    public void acceptTicket(Request ticket) {
        ticket.setStatus(TicketStatus.ACCEPTED);
    }

    public void rejectTicket(Request ticket) {
        ticket.setStatus(TicketStatus.REJECTED);
    }

    public List<Request> getTicketsByStatus(TicketStatus status) {
//        List<Request> filteredTickets = new ArrayList<Request>();
//        for (Request ticket : tickets) {
//            if (ticket.getStatus() == status) {
//                filteredTickets.add(ticket);
//            }
//        }
//        return filteredTickets;
        return (List<Request>) DatabaaseeActions.findById("TeechSupportRquests",-1);
    }
}
