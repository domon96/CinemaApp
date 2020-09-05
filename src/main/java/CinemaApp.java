import dao.HibernateUtil;
import domain.BaseEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CinemaApp {

    public static void main(String[] args) {

        // beginning of hibernate app
        SessionFactory sessionFactory = createSessionFactory();
        //SessionFactory sessionFactory = createSessionFactory(Movie.class, CinemaHall.class, CinemaShow.class, Client.class, PaymentsHistory.class);

        HibernateUtil movieDao = new HibernateUtil(sessionFactory.createEntityManager(), Movie.class);
        HibernateUtil cinemaHallDao = new HibernateUtil(sessionFactory.createEntityManager(), CinemaHall.class);
        HibernateUtil filmShowDao = new HibernateUtil(sessionFactory.createEntityManager(), CinemaShow.class);
        HibernateUtil clientDao = new HibernateUtil(sessionFactory.createEntityManager(), Client.class);
        HibernateUtil paymentsHistoryDao = new HibernateUtil(sessionFactory.createEntityManager(), PaymentsHistory.class);

        Movie movie1 = new Movie("zootopia");
        Movie movie2 = new Movie("wonder woman");
        Movie movie3 = new Movie("black widow");
        Movie movie4 = new Movie("once upon a time in hollywood");

        CinemaHall smallCinemaHall = new CinemaHall("small");
        CinemaHall mediumCinemaHall = new CinemaHall("medium");
        CinemaHall bigCinemaHall = new CinemaHall("big");

        CinemaHallSchedule cinemaHallSchedule1 = new CinemaHallSchedule("12:00", smallCinemaHall);
        CinemaHallSchedule cinemaHallSchedule2 = new CinemaHallSchedule("15:00", smallCinemaHall);
        CinemaHallSchedule cinemaHallSchedule3 = new CinemaHallSchedule("20:00", smallCinemaHall);
        CinemaHallSchedule cinemaHallSchedule4 = new CinemaHallSchedule("13:30", mediumCinemaHall);
        CinemaHallSchedule cinemaHallSchedule5 = new CinemaHallSchedule("17:00", mediumCinemaHall);
        CinemaHallSchedule cinemaHallSchedule6 = new CinemaHallSchedule("19:30", mediumCinemaHall);
        CinemaHallSchedule cinemaHallSchedule7 = new CinemaHallSchedule("10:00", bigCinemaHall);
        CinemaHallSchedule cinemaHallSchedule8 = new CinemaHallSchedule("16:00", bigCinemaHall);
        CinemaHallSchedule cinemaHallSchedule9 = new CinemaHallSchedule("21:00", bigCinemaHall);

        CinemaShow cinemaShow1 = new CinemaShow(cinemaHallSchedule1, movie1);
        CinemaShow cinemaShow2 = new CinemaShow(cinemaHallSchedule2, movie2);
        CinemaShow cinemaShow3 = new CinemaShow(cinemaHallSchedule3, movie4);
        CinemaShow cinemaShow4 = new CinemaShow(cinemaHallSchedule4, movie3);
        CinemaShow cinemaShow5 = new CinemaShow(cinemaHallSchedule5, movie2);
        CinemaShow cinemaShow6 = new CinemaShow(cinemaHallSchedule6, movie2);
        CinemaShow cinemaShow7 = new CinemaShow(cinemaHallSchedule7, movie1);
        CinemaShow cinemaShow8 = new CinemaShow(cinemaHallSchedule8, movie2);
        CinemaShow cinemaShow9 = new CinemaShow(cinemaHallSchedule9, movie3);

        Client client1 = new Client("Adam", "Kowalski", "adam.kowalski@gmail.com", "adamkowalski");
        Client client2 = new Client("Marek", "Nowak", "marek.nowak@gmail.com", "mareknowak");

        // DAO
        movieDao.create(movie1);
        movieDao.create(movie2);
        movieDao.create(movie3);
        movieDao.create(movie4);

        cinemaHallDao.create(smallCinemaHall);
        cinemaHallDao.create(mediumCinemaHall);
        cinemaHallDao.create(bigCinemaHall);

        filmShowDao.create(cinemaShow1);
        filmShowDao.create(cinemaShow2);
        filmShowDao.create(cinemaShow3);
        filmShowDao.create(cinemaShow4);
        filmShowDao.create(cinemaShow5);
        filmShowDao.create(cinemaShow6);
        filmShowDao.create(cinemaShow7);
        filmShowDao.create(cinemaShow8);
        filmShowDao.create(cinemaShow9);

        clientDao.create(client1);
        clientDao.create(client2);

        List<Movie> movies = movieDao.findAll();
        List<CinemaShow> cinemaShowList = filmShowDao.findAll();
        List<Client> clients = clientDao.findAll();

        // end of hibernate app

        // main sequence
        Scanner scanner = new Scanner(System.in);
        int step;
        Client loggedClient = null;
        System.out.println("Welcome to the best cinema!");

        try {
            do {
                System.out.println("What would you like to do?");
                System.out.println("1. Buy tickets");
                System.out.println("2. Show movies info");
                System.out.println("3. Sign in");
                System.out.println("4. Sign up");
                System.out.println("5. Check account balance and history of payments");
                System.out.println("6. Add money to your account");
                System.out.println("7. Log out");
                System.out.println("Exit");
                step = scanner.nextInt();
                switch (step) {
                    case 1:
                        // buy tickets

                        // show titles
                        System.out.println("Choose movie");
                        showMovieTitles(movies);
                        int movieId = scanner.nextInt();
                        // show hours
                        System.out.println("Choose hour of chosen movie");
                        List<Integer> showsId = showMovieHours(cinemaShowList, movieId);
                        CinemaShow chosenShow = cinemaShowList.get(showsId.get(scanner.nextInt() - 1) - 1);
                        // if chosen movie has full cinema hall
                        if (chosenShow.getCinemaHallSchedule().isFull()) {
                            System.out.println("Sorry, cinema hall is full");
                        } else {
                            // choose number of tickets
                            System.out.println("Choose number of tickets");
                            int numberOfTickets = scanner.nextInt();
                            // choose tickets type (normal, reduced)
                            boolean ticketsQuantityOK;
                            int normalTickets;
                            int reducedTickets;
                            do {
                                System.out.println("Choose tickets type");
                                System.out.println("Normal tickets quantity:");
                                normalTickets = scanner.nextInt();
                                System.out.println("Reduced tickets quantity:");
                                reducedTickets = scanner.nextInt();
                                ticketsQuantityOK = normalTickets + reducedTickets == numberOfTickets;
                                if (!ticketsQuantityOK || normalTickets < 0 || reducedTickets < 0) {
                                    System.out.println("Wrong tickets quantity");
                                }
                            } while (!ticketsQuantityOK);
                            // choose seats
                            System.out.println("Choose available seat on cinema hall");
                            System.out.println("F - free seat, X - taken seat");
                            chosenShow.getCinemaHallSchedule().printSeats();
                            System.out.println("First type row, then enter, then type column");
                            int reservedSeats = 0;
                            while (reservedSeats < numberOfTickets) {
                                scanner.nextLine();
                                char row = scanner.nextLine().toUpperCase().charAt(0);
                                int col = scanner.nextInt();
                                if (chosenShow.getCinemaHallSchedule().bookSeat(row, col)) {
                                    reservedSeats++;
                                } else {
                                    System.out.println("Seat already taken");
                                }
                            }
                            // pay
                            int bill = calculateBill(normalTickets, reducedTickets);
                            if (loggedClient != null) {
                                if (loggedClient.getAccountBalance() - bill < 0) {
                                    System.out.println("Sorry, you don't have enough money on your account");
                                    System.out.println("Amount to pay at cinema checkout is : " + bill);
                                } else {
                                    loggedClient.pay(bill);
                                    clientDao.update();
                                    paymentsHistoryDao.create(new PaymentsHistory(loggedClient, -bill));
                                    System.out.println("You just paid " + bill + " from your account");
                                }
                            } else {
                                System.out.println("Amount to pay at cinema checkout is : " + bill);
                            }
                        }
                        System.out.println();
                        break;
                    case 2:
                        // show movies
                        for (Movie movie : movies) {
                            movie.showInfo();
                        }
                        System.out.println();
                        break;
                    case 3:
                        // sign in
                        clients = clientDao.findAll();
                        if (loggedClient != null) {
                            System.out.println("You are already logged in, " + loggedClient.getName());
                        } else {
                            System.out.println("Type your email:");
                            scanner.nextLine();
                            String email = scanner.nextLine();
                            System.out.println("Type your password:");
                            String password = scanner.nextLine();
                            for (Client client : clients) {
                                if (client.getEmail().equals(email) && client.getPassword().equals(password)) {
                                    loggedClient = client;
                                    System.out.println("Hello " + loggedClient.getName());
                                }
                            }
                            if (loggedClient == null) {
                                System.out.println("Sorry, you are not in our data base, please sign up");
                            }
                        }
                        System.out.println();
                        break;
                    case 4:
                        // sign up
                        if (loggedClient != null) {
                            System.out.println("You already have an account");
                        } else {
                            System.out.println("Name: ");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            System.out.println("Last name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("E-mail: ");
                            String email = scanner.nextLine();
                            boolean passwordOK = false;
                            String password;
                            do {
                                System.out.println("Password: ");
                                password = scanner.nextLine();
                                System.out.println("Repeat password: ");
                                passwordOK = scanner.nextLine().equals(password);
                                if (!passwordOK) {
                                    System.out.println("Wrong password, try again");
                                }
                            } while (!passwordOK);
                            // new client
                            clients = clientDao.findAll();
                            if (clients.contains(new Client(name, lastName, email, password))) {
                                System.out.println("Sorry, this account already exists");
                            } else {
                                clientDao.create(new Client(name, lastName, email, password));
                                System.out.println("New account was created");
                            }
                        }
                        System.out.println();
                        break;
                    case 5:
                        // check history of payments
                        if (loggedClient != null) {
                            System.out.println("Current account balance is: " + loggedClient.getAccountBalance());
                            List<PaymentsHistory> paymentsHistoryList = paymentsHistoryDao.findAll();
                            for (PaymentsHistory paymentsHistory : paymentsHistoryList) {
                                if (paymentsHistory.getClientId() == loggedClient.getId()) {
                                    System.out.println(paymentsHistory);
                                }
                            }
                        } else {
                            System.out.println("Sorry, you are not logged in");
                        }
                        System.out.println();
                        break;
                    case 6:
                        // add money to account
                        if (loggedClient != null) {
                            System.out.println("How much money do you want to deposit?");
                            int amount = scanner.nextInt();
                            loggedClient.addMoney(amount);
                            clientDao.update();
                            paymentsHistoryDao.create(new PaymentsHistory(loggedClient, amount));
                            System.out.println("Current balance = " + loggedClient.getAccountBalance());
                        } else {
                            System.out.println("Sorry, you are not logged in");
                        }
                        System.out.println();
                        break;
                    case 7:
                        // log out
                        if (loggedClient != null) {
                            loggedClient = null;
                            System.out.println("You are logged out\n");
                        } else {
                            System.out.println("First you have to be logged in");
                        }
                        System.out.println();
                        break;
                }
            } while (step > 0 && step <= 7);
        } catch (InputMismatchException exception) {}
        finally {
            System.out.println("Bye, bye");
        }

        close(sessionFactory, movieDao, cinemaHallDao, filmShowDao, clientDao, paymentsHistoryDao);
    }

    private static void close(SessionFactory sessionFactory, HibernateUtil... hibernateUtils) {
        for (HibernateUtil hibernateUtil : hibernateUtils) {
            hibernateUtil.close();
        }
        sessionFactory.close();
    }

//    private static SessionFactory createSessionFactory(Object... objects) {
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//        for (Object object : objects) {
//            configuration = configuration.addAnnotatedClass(object.getClass());
//        }
//        return configuration.buildSessionFactory();
//    }

    private static SessionFactory createSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(CinemaHall.class)
                .addAnnotatedClass(CinemaShow.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(PaymentsHistory.class)
                .buildSessionFactory();
    }

    private static void showMovieTitles(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.printf("%d. %s\n", movie.getId(), movie.getTitle());
        }
    }

    private static List<Integer> showMovieHours(List<CinemaShow> cinemaShowList, int movieId) {
        int i = 1;
        List<Integer> showsId = new LinkedList<>();
        for (CinemaShow cinemaShow : cinemaShowList) {
            if (cinemaShow.getMovie().getId() == movieId) {
                System.out.printf("%d. %s\n", i, cinemaShow.getMovieTime());
                showsId.add(cinemaShow.getId());
                i++;
            }
        }
        return showsId;
    }

    private static int calculateBill(int normalTickets, int reducedTickets) {
        return normalTickets * 20 + reducedTickets * 10;
    }
}
