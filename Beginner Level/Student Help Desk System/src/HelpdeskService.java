import java.util.ArrayList;

public class HelpdeskService {

    private ArrayList<Ticket> tickets;
    private int nextTicketId;

    public HelpdeskService() {

        tickets = new ArrayList<>();
        nextTicketId = 1001;
    }

    // Create ticket
    public Ticket createTicket(String title, String description,
                               Category category, Priority priority,
                               User user) {

        Ticket ticket = new Ticket(
                nextTicketId,
                title,
                description,
                category,
                priority,
                user
        );

        tickets.add(ticket);
        nextTicketId++;

        return ticket;
    }

    // View all tickets
    public void viewAllTickets() {

        if (tickets.isEmpty()) {

            System.out.println("\nNo tickets available.");
            return;
        }

        System.out.println("\n========== ALL TICKETS ==========");

        for (Ticket ticket : tickets) {
            ticket.displayTicket();
        }
    }

    // Search by ID
    public Ticket searchById(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }

        return null;
    }

    // Search by title
    public void searchByTitle(String title) {

        boolean found = false;

        for (Ticket ticket : tickets) {

            if (ticket.getTitle()
                    .toLowerCase()
                    .contains(title.toLowerCase())) {

                ticket.displayTicket();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo ticket found.");
        }
    }

    // Update title
    public boolean updateTitle(int ticketId, String newTitle) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        ticket.setTitle(newTitle);
        return true;
    }

    // Update description
    public boolean updateDescription(int ticketId,
                                     String newDescription) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        ticket.setDescription(newDescription);
        return true;
    }

    // Update category
    public boolean updateCategory(int ticketId,
                                  Category category) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        ticket.setCategory(category);
        return true;
    }

    // Update priority
    public boolean updatePriority(int ticketId,
                                  Priority priority) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        ticket.setPriority(priority);
        return true;
    }

    // Start ticket
    public boolean startTicket(int ticketId) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        if (ticket.getStatus() != TicketStatus.OPEN) {
            return false;
        }

        ticket.setStatus(TicketStatus.IN_PROGRESS);

        return true;
    }

    // Resolve ticket
    public boolean resolveTicket(int ticketId) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        if (ticket.getStatus() != TicketStatus.IN_PROGRESS) {
            return false;
        }

        ticket.setStatus(TicketStatus.RESOLVED);

        return true;
    }

    // Close ticket
    public boolean closeTicket(int ticketId) {

        Ticket ticket = searchById(ticketId);

        if (ticket == null) {
            return false;
        }

        if (ticket.getStatus() != TicketStatus.RESOLVED) {
            return false;
        }

        ticket.setStatus(TicketStatus.CLOSED);

        return true;
    }
}