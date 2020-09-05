import domain.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CINEMA_SHOW")
public class CinemaShow extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "movie_time")
    private String movieTime;

    @Transient
    private  CinemaHallSchedule cinemaHallSchedule;

    public CinemaShow() {
    }

    public CinemaShow(CinemaHallSchedule cinemaHallSchedule, Movie movie) {
        this.cinemaHallSchedule = cinemaHallSchedule;
        this.movie = movie;
        cinemaHall = cinemaHallSchedule.getCinemaHall();
        movieTime = cinemaHallSchedule.getMovieHour();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public CinemaHallSchedule getCinemaHallSchedule() {
        return cinemaHallSchedule;
    }

    @Override
    public String toString() {
        return "CinemaShow{" +
                "cinemaHall=" + cinemaHall +
                ", movie=" + movie +
                ", movieTime='" + movieTime + '\'' +
                ", cinemaHallSchedule=" + cinemaHallSchedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaShow that = (CinemaShow) o;
        return Objects.equals(cinemaHall, that.cinemaHall) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(movieTime, that.movieTime) &&
                Objects.equals(cinemaHallSchedule, that.cinemaHallSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaHall, movie, movieTime, cinemaHallSchedule);
    }
}
