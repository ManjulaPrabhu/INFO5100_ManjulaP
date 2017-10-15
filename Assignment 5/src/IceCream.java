public class IceCream extends DessertItem {
    int totalCost;

    IceCream(String name, int cost) {
        super(name);
        this.totalCost = cost;
    }

    int getCost() {
        return totalCost;
    }
}
