package machine.coffee.model;

public class MachineResources {
    private int money;
    private int water;
    private int coffeeBeans;
    private int disposalCup;
    private int milk;

    public MachineResources(int money, int water, int coffeeBeans, int disposalCup, int milk) {
        this.money = money;
        this.water = water;
        this.coffeeBeans = coffeeBeans;
        this.disposalCup = disposalCup;
        this.milk = milk;
    }

    public void updateStatus(Coffee coffee) {
        this.money += coffee.getPrice();
        this.water -= coffee.getWater();
        this.coffeeBeans -= coffee.getCoffeeBeans();
        this.disposalCup -= 1; // always use 1 cup
        this.milk -= coffee.getMilk();
    }

    public void updateStatus(MachineResources status) {
        this.water += status.water;
        this.coffeeBeans += status.coffeeBeans;
        this.disposalCup += status.disposalCup;
        this.milk += status.milk;
        this.money = status.money;
    }

    public void withdraw() {
        money = 0;
    }

    @Override
    public String toString() {
        String dollarSign = "$";
        if (money == 0)
            dollarSign = "";
        return String.format("The coffee machine has:\n%d of water\n%d of milk\n%d of coffee beans" +
                "\n%d of disposable cups\n%s%d of money", water, milk, coffeeBeans, disposalCup, dollarSign, money);
    }

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getDisposalCup() {
        return disposalCup;
    }

    public int getMilk() {
        return milk;
    }
}
