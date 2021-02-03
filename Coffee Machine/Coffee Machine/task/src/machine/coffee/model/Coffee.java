package machine.coffee.model;

public enum Coffee {
    ESPRESSO(250,0,16,4),
    CAPPUCCINO(200,100,12,6),
    LATTE(350,75,20,7);

    private final int water;
    private final int milk;
    private final int coffeeBeans;
    private final int price;

    Coffee(int water, int milk, int coffeeBeans, int price) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getPrice() {
        return price;
    }
}
