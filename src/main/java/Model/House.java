package Model;

public class House {
    private int numberOfHouse = 0;
    private boolean isHotel = false;

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public boolean isHotel() {
        return isHotel;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public void setHotel(boolean hotel) {
        isHotel = hotel;
    }
}
