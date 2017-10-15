public class Candy extends DessertItem {
    double weight;
    int price;
    int totalCost;

    Candy(String name, double weight, int price) {
        super(name);
        this.price = price;
        this.weight = weight;
    }

    int getCost() {
        totalCost = (int) (Math.ceil(weight * price));
        return totalCost;
    }

}
