package Model;

public class Land {
    private String name;

    private int price;
    private int priority;

    public Land(String name, int location, int priority) {
        this.name = name;
        this.price = location;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
