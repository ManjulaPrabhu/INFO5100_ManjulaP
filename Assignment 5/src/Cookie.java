public class Cookie extends DessertItem {
    int numberOfCookies;
    int price;
    int totalCost;


    Cookie(String name, int numberOfCookies, int price) {
        super(name);
        this.price = price;
        this.numberOfCookies = numberOfCookies;
    }

    int getCost() {
        totalCost = (price / 12) * numberOfCookies;
        return totalCost;
    }

}
