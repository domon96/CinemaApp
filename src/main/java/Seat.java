public class Seat {

    private boolean isFree = true;

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Seat{" +
                ", isFree=" + isFree +
                '}';
    }


}
