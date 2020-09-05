public class CinemaHallSchedule {

    private final String movieHour;

    private final CinemaHall cinemaHall;

    private final Seat[][] seats;

    public CinemaHallSchedule(String movieHour, CinemaHall cinemaHall) {
        this.movieHour = movieHour;
        this.cinemaHall = cinemaHall;
        this.seats = new Seat[cinemaHall.getSeatRows()][cinemaHall.getSeatColumns()];
        for (int i = 0; i < cinemaHall.getSeatRows(); i++) {
            for (int j = 0; j < cinemaHall.getSeatColumns(); j++) {
                seats[i][j] = new Seat();
            }
        }
    }

    public String getMovieHour() {
        return movieHour;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public boolean bookSeat(char row, int column) {
        if (seats[(int) row - 65][--column].isFree()) {
            seats[(int) row - 65][column].setFree(false);
            return true;
        }
        return false;
    }

    void printSeats() {
        // printing screen
        System.out.print("  "); // space before screen
        for (int i = 0; i < cinemaHall.getSeatColumns() - 1; i++) {
            System.out.print("---");
        }
        System.out.print("-\n\n");
        // printing numbers of seats
        for (int i = 1; i <= cinemaHall.getSeatColumns(); i++) {
            System.out.printf("%3d", i);
            //System.out.print(i + "  ");
        }
        System.out.println();
        // printing seats
        // F - free, X - taken
        char letter = 'A';
        for (Seat[] row : seats) {
            System.out.print(letter++ + " ");
            for (Seat col : row) {
                if (col.isFree()) {
                    System.out.print("F");
                } else {
                    System.out.print("X");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public boolean isFull() {
        for (Seat[] row : seats) {
            for (Seat col : row) {
                if (col.isFree()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "CinemaHallSchedule{" +
                "movieHour='" + movieHour + '\'' +
                ", cinemaHall=" + cinemaHall +
                '}';
    }
}
