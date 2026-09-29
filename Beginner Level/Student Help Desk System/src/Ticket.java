public class Ticket {

    private int ticketId;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private TicketStatus status;
    private User user;

    public Ticket(int ticketId, String title, String description,
                  Category category, Priority priority, User user) {

        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.user = user;
        this.status = TicketStatus.OPEN;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void displayTicket() {

        System.out.println("----------------------------------------");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Category    : " + category);
        System.out.println("Priority    : " + priority);
        System.out.println("Status      : " + status);
        System.out.println("User        : " + user.getName());
        System.out.println("Email       : " + user.getEmail());
        System.out.println("----------------------------------------");
    }
}