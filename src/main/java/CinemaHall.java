import domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "CINEMA_HALL")
public class CinemaHall extends BaseEntity {

    @Column
    private  String size;

    @Column
    private int seatRows;

    @Column
    private  int seatColumns;

    public CinemaHall() {
    }

    public CinemaHall(String size) {
        this.size = size;
        switch (size) {
            case "small":
                seatRows=5;
                seatColumns=10;
                break;
            case "medium":
                seatRows=8;
                seatColumns=15;
                break;
            case "big":
                seatRows=10;
                seatColumns=20;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public int getSeatRows() {
        return seatRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "size='" + size + '\'' +
                ", seatRows=" + seatRows +
                ", seatColumns=" + seatColumns +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
