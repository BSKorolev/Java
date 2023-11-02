import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.next();

                    if (isAdminAuthenticated(adminPassword)) {
                        ticketSystem.adminMenu();
                    } else {
                        System.out.println("Invalid administrator password");
                    }

                    break;
                case 2:
                    ticketSystem.userMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private static boolean isAdminAuthenticated(String password) {
        return password.equals("admin123");
    }
}
