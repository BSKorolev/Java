import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicketSystem {
    private List<Cinema> cinemas;

    public TicketSystem() {
        cinemas = new ArrayList<>();
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a cinema");
            System.out.println("2. Add a hall");
            System.out.println("3. Add a movie");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addCinema();
                    break;
                case 2:
                    addHall();
                    break;
                case 3:
                    addMovie();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public void userMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View movie schedule");
            System.out.println("2. Buy ticket");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        viewSchedule();
                        break;
                    case 2:
                        buyTicket();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
            }
        }
    }


    public void addCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cinema name: ");
        String name = scanner.nextLine();

        Cinema cinema = new Cinema(name);
        cinemas.add(cinema);
        System.out.println("Cinema added");
    }

    public void addHall() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        System.out.print("Enter hall name: ");
        String hallName = scanner.nextLine();
        System.out.print("Enter number of rows: ");
        int numRows = scanner.nextInt();
        int[] numSeatsPerRow = new int[numRows];

        for (int numRow = 0; numRow < numRows; numRow++) {
            System.out.print("Enter number of seats in row " + (numRow + 1) + ": ");
            numSeatsPerRow[numRow] = scanner.nextInt();
        }

        Cinema cinema = getCinema(cinemaName);

        if (cinema != null) {
            Hall hall = new Hall(hallName, numRows, numSeatsPerRow);
            cinema.addHall(hall);
            System.out.println("Hall added");
        } else {
            System.out.println("Cinema not found");
        }
    }

    public void addMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        System.out.print("Enter hall name: ");
        String hallName = scanner.nextLine();
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter session time: ");
        String time = scanner.nextLine();

        Cinema cinema = getCinema(cinemaName);

        if (cinema != null) {
            Hall hall = cinema.getHall(hallName);

            if (hall != null) {
                Movie movie = new Movie(movieName);
                Session session = new Session(movie, time);
                hall.addSession(session);
                System.out.println("Movie added");
            } else {
                System.out.println("Hall not found");
            }
        } else {
            System.out.println("Cinema not found");
        }
    }

    public void viewSchedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        System.out.print("Enter hall name: ");
        String hallName = scanner.nextLine();

        Cinema cinema = getCinema(cinemaName);

        if (cinema != null) {
            Hall hall = cinema.getHall(hallName);

            if (hall != null) {
                List<Session> sessions = hall.getSessions();

                if (sessions.size() > 0) {
                    System.out.println("Session schedule:");

                    for (Session session : sessions) {
                        Movie movie = session.getMovie();
                        System.out.println("Movie: " + movie.getName());
                        System.out.println("Time: " + session.getTime());
                        System.out.println("Status: " + movie.getStatus());
                        System.out.println();
                        hall.displaySeatStatus();
                    }
                } else {
                    System.out.println("Sessions not found");
                }
            } else {
                System.out.println("Hall not found");
            }
        } else {
            System.out.println("Cinema not found");
        }
    }

    public void buyTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cinema name: ");
        String cinemaName = scanner.nextLine();
        System.out.print("Enter hall name: ");
        String hallName = scanner.nextLine();
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter session time: ");
        String time = scanner.nextLine();

        Cinema cinema = getCinema(cinemaName);

        if (cinema != null) {
            Hall hall = cinema.getHall(hallName);

            if (hall != null) {
                Session session = hall.getSession(movieName, time);

                if (session != null) {
                    if (session.getMovie().getStatus().equals("Available")) {
                        System.out.print("Enter row number: ");
                        int row = scanner.nextInt();
                        System.out.print("Enter seat number: ");
                        int seat = scanner.nextInt();

                        if (hall.isSeatAvailable(row, seat)) {
                            hall.bookSeat(row, seat);
                            System.out.println("Ticket bought");
                            session.getMovie().setStatus("Unavailable");
                        } else {
                            System.out.println("Seat not available");
                        }
                    } else {
                        System.out.println("Movie is not available");
                    }
                } else {
                    System.out.println("Session not found");
                }
            } else {
                System.out.println("Hall not found");
            }
        } else {
            System.out.println("Cinema not found");
        }
    }

    private Cinema getCinema(String cinemaName) {
        for (Cinema cinema : cinemas) {
            if (cinema.getName().equals(cinemaName)) {
                return cinema;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();
        ticketSystem.adminMenu();
    }
}

class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public Hall getHall(String hallName) {
        for (Hall hall : halls) {
            if (hall.getName().equals(hallName)) {
                return hall;
            }
        }
        return null;
    }
}

class Hall {
    private String name;
    private List<Session> sessions;
    private int numRows;
    private int[] numSeatsPerRow;
    private boolean[][] seats;

    public Hall(String name, int numRows, int[] numSeatsPerRow) {
        this.name = name;
        this.numRows = numRows;
        this.numSeatsPerRow = numSeatsPerRow;
        this.seats = new boolean[numRows][];
        for (int i = 0; i < numRows; i++) {
            this.seats[i] = new boolean[numSeatsPerRow[i]];
        }
        this.sessions = new ArrayList<>();
    }

    public boolean isSeatAvailable(int row, int seat) {
        if (row >= 1 && row <= numRows && seat >= 1 && seat <= numSeatsPerRow[row - 1]) {
            return !seats[row - 1][seat - 1];
        }
        return false;
    }

    public void bookSeat(int row, int seat) {
        if (row >= 1 && row <= numRows && seat >= 1 && seat <= numSeatsPerRow[row - 1]) {
            seats[row - 1][seat - 1] = true;
        }
    }

    public String getName() {
        return name;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void displaySeatStatus() {
        System.out.println("Seat status:");
        for (int row = 1; row <= numRows; row++) {
            for (int seat = 1; seat <= numSeatsPerRow[row - 1]; seat++) {
                if (seats[row - 1][seat - 1]) {
                    System.out.println("Row " + row + ", Seat " + seat + ": Occupied");
                } else {
                    System.out.println("Row " + row + ", Seat " + seat + ": Free");
                }
            }
        }
    }

    public Session getSession(String movieName, String time) {
        for (Session session : sessions) {
            if (session.getMovie().getName().equals(movieName) && session.getTime().equals(time)) {
                return session;
            }
        }
        return null;
    }
}

class Movie {
    private String name;
    private String status;

    public Movie(String name) {
        this.name = name;
        this.status = "Available";
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String unavailable) {
        return status;
    }
}

class Session {
    private Movie movie;
    private String time;

    public Session(Movie movie, String time) {
        this.movie = movie;
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }
}