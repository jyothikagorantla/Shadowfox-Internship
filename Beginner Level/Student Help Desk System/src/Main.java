import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static HelpdeskService helpdesk = new HelpdeskService();

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       STUDENT HELPDESK SYSTEM");
        System.out.println("========================================");

        System.out.println("\nEnter your details");

        String name = readNonEmptyString("Enter your name: ");
        String email = readNonEmptyString("Enter your email: ");

        User user = new User(1, name, email);

        int choice;

        do {

            displayMenu();

            choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    createTicket(user);
                    break;

                case 2:
                    helpdesk.viewAllTickets();
                    break;

                case 3:
                    searchTicket();
                    break;

                case 4:
                    updateTicket();
                    break;

                case 5:
                    startTicket();
                    break;

                case 6:
                    resolveTicket();
                    break;

                case 7:
                    closeTicket();
                    break;

                case 8:
                    System.out.println(
                            "\nThank you for using Student Helpdesk System."
                    );
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    System.out.println(
                            "Please select a number from 1 to 8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }

    public static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");

        System.out.println("1. Create Ticket");
        System.out.println("2. View All Tickets");
        System.out.println("3. Search Ticket");
        System.out.println("4. Update Ticket");
        System.out.println("5. Start Ticket");
        System.out.println("6. Resolve Ticket");
        System.out.println("7. Close Ticket");
        System.out.println("8. Exit");

        System.out.println("========================================");
    }

    public static void createTicket(User user) {

        System.out.println("\n========== CREATE TICKET ==========");

        String title =
                readNonEmptyString("Enter ticket title: ");

        String description =
                readNonEmptyString("Enter ticket description: ");

        Category category = selectCategory();

        Priority priority = selectPriority();

        Ticket ticket = helpdesk.createTicket(
                title,
                description,
                category,
                priority,
                user
        );

        System.out.println("\nTicket created successfully!");
        System.out.println(
                "Your Ticket ID is: " + ticket.getTicketId()
        );
        System.out.println(
                "Status: " + ticket.getStatus()
        );
    }

    public static void searchTicket() {

        System.out.println("\n========== SEARCH TICKET ==========");

        System.out.println("1. Search by Ticket ID");
        System.out.println("2. Search by Title");

        int choice = readInteger("Enter choice: ");

        if (choice == 1) {

            int id = readInteger("Enter Ticket ID: ");

            Ticket ticket = helpdesk.searchById(id);

            if (ticket == null) {

                System.out.println("\nTicket not found.");

            } else {

                ticket.displayTicket();
            }

        } else if (choice == 2) {

            String title =
                    readNonEmptyString("Enter title to search: ");

            helpdesk.searchByTitle(title);

        } else {

            System.out.println("\nInvalid choice.");
        }
    }

    public static void updateTicket() {

        System.out.println("\n========== UPDATE TICKET ==========");

        int id = readInteger("Enter Ticket ID: ");

        Ticket ticket = helpdesk.searchById(id);

        if (ticket == null) {

            System.out.println("\nTicket not found.");
            return;
        }

        if (ticket.getStatus() == TicketStatus.CLOSED) {

            System.out.println(
                    "\nClosed tickets cannot be updated."
            );
            return;
        }

        System.out.println("\nCurrent ticket details:");
        ticket.displayTicket();

        System.out.println("\nWhat do you want to update?");

        System.out.println("1. Title");
        System.out.println("2. Description");
        System.out.println("3. Category");
        System.out.println("4. Priority");

        int choice = readInteger("Enter choice: ");

        boolean updated = false;

        switch (choice) {

            case 1:

                String title =
                        readNonEmptyString("Enter new title: ");

                updated =
                        helpdesk.updateTitle(id, title);

                break;

            case 2:

                String description =
                        readNonEmptyString(
                                "Enter new description: "
                        );

                updated =
                        helpdesk.updateDescription(
                                id,
                                description
                        );

                break;

            case 3:

                Category category = selectCategory();

                updated =
                        helpdesk.updateCategory(
                                id,
                                category
                        );

                break;

            case 4:

                Priority priority = selectPriority();

                updated =
                        helpdesk.updatePriority(
                                id,
                                priority
                        );

                break;

            default:

                System.out.println("\nInvalid choice.");
                return;
        }

        if (updated) {

            System.out.println(
                    "\nTicket updated successfully."
            );

        } else {

            System.out.println(
                    "\nTicket could not be updated."
            );
        }
    }

    public static void startTicket() {

        System.out.println("\n========== START TICKET ==========");

        int id = readInteger("Enter Ticket ID: ");

        Ticket ticket = helpdesk.searchById(id);

        if (ticket == null) {

            System.out.println("\nTicket not found.");
            return;
        }

        if (ticket.getStatus() != TicketStatus.OPEN) {

            System.out.println(
                    "\nTicket cannot be started."
            );

            System.out.println(
                    "Current status: " + ticket.getStatus()
            );

            return;
        }

        boolean result =
                helpdesk.startTicket(id);

        if (result) {

            System.out.println(
                    "\nTicket is now IN_PROGRESS."
            );

        } else {

            System.out.println(
                    "\nUnable to start ticket."
            );
        }
    }

    public static void resolveTicket() {

        System.out.println("\n========== RESOLVE TICKET ==========");

        int id = readInteger("Enter Ticket ID: ");

        Ticket ticket = helpdesk.searchById(id);

        if (ticket == null) {

            System.out.println("\nTicket not found.");
            return;
        }

        if (ticket.getStatus()
                != TicketStatus.IN_PROGRESS) {

            System.out.println(
                    "\nTicket cannot be resolved."
            );

            System.out.println(
                    "Current status: " + ticket.getStatus()
            );

            System.out.println(
                    "Ticket must be IN_PROGRESS before resolving."
            );

            return;
        }

        boolean result =
                helpdesk.resolveTicket(id);

        if (result) {

            System.out.println(
                    "\nTicket resolved successfully."
            );

            System.out.println(
                    "Status: RESOLVED"
            );

        } else {

            System.out.println(
                    "\nUnable to resolve ticket."
            );
        }
    }

    public static void closeTicket() {

        System.out.println("\n========== CLOSE TICKET ==========");

        int id = readInteger("Enter Ticket ID: ");

        Ticket ticket = helpdesk.searchById(id);

        if (ticket == null) {

            System.out.println("\nTicket not found.");
            return;
        }

        if (ticket.getStatus()
                != TicketStatus.RESOLVED) {

            System.out.println(
                    "\nTicket cannot be closed."
            );

            System.out.println(
                    "Current status: " + ticket.getStatus()
            );

            System.out.println(
                    "Ticket must be RESOLVED before closing."
            );

            return;
        }

        boolean result =
                helpdesk.closeTicket(id);

        if (result) {

            System.out.println(
                    "\nTicket closed successfully."
            );

            System.out.println(
                    "Status: CLOSED"
            );

        } else {

            System.out.println(
                    "\nUnable to close ticket."
            );
        }
    }

    public static Category selectCategory() {

        while (true) {

            System.out.println("\nSelect Category:");

            System.out.println("1. Technical");
            System.out.println("2. Academic");
            System.out.println("3. Account");
            System.out.println("4. Other");

            int choice =
                    readInteger("Enter choice: ");

            switch (choice) {

                case 1:
                    return Category.TECHNICAL;

                case 2:
                    return Category.ACADEMIC;

                case 3:
                    return Category.ACCOUNT;

                case 4:
                    return Category.OTHER;

                default:

                    System.out.println(
                            "Invalid category. Please try again."
                    );
            }
        }
    }

    public static Priority selectPriority() {

        while (true) {

            System.out.println("\nSelect Priority:");

            System.out.println("1. Low");
            System.out.println("2. Medium");
            System.out.println("3. High");

            int choice =
                    readInteger("Enter choice: ");

            switch (choice) {

                case 1:
                    return Priority.LOW;

                case 2:
                    return Priority.MEDIUM;

                case 3:
                    return Priority.HIGH;

                default:

                    System.out.println(
                            "Invalid priority. Please try again."
                    );
            }
        }
    }

    public static String readNonEmptyString(
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }

    public static int readInteger(
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}