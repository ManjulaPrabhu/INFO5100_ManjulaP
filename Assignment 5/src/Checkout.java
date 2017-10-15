import java.util.Vector;

public class Checkout {
    Vector<DessertItem> dessertItemVector;
    int totalCost = 0;
    int totalTax = 0;
    String receipt;

    DessertShoppe dessertShoppeObject = new DessertShoppe();

    Checkout() {
        dessertItemVector = new Vector<>();
    }

    void enterItem(DessertItem dessertItemObject) {
        dessertItemVector.add(dessertItemObject);
    }

    int numberOfItems() {
        return dessertItemVector.size();
    }

    void clear() {
        totalCost = 0;
        totalTax = 0;
    }

    int totalCost() {
        totalCost = 0;
        for (DessertItem d : dessertItemVector) {
            totalCost += d.getCost();
        }
        return totalCost;
    }

    int totalTax() {
        totalTax = 0;
        for (DessertItem ds : dessertItemVector) {
            totalTax += Math.abs((ds.getCost() * dessertShoppeObject.TAX_RATE) % 100);
        }
        return totalTax;
    }

    public String toString() {

        double receiptWeight;
        float receiptRate;
        int count;
        String itemReceipt = "";
        receipt = "M&M Dessert Shoppe\n------------------\n\n";
        for (DessertItem di : dessertItemVector) {
            if (di.getClass().getName() == "Candy") {
                Candy candy = (Candy) di;
                receiptWeight = candy.weight;
                itemReceipt += receiptWeight + "lbs.@";
                receiptRate = Float.parseFloat(dessertShoppeObject.cents2dollarsAndCents(candy.price));
                itemReceipt += receiptRate + "/lb\n";
            } else if (di.getClass().getName() == "Cookie") {
                Cookie cookie = (Cookie) di;
                count = cookie.numberOfCookies;
                itemReceipt += count + "@";
                receiptRate = Float.parseFloat(dessertShoppeObject.cents2dollarsAndCents(cookie.price));
                itemReceipt += receiptRate + "/dz\n";
            } else if (di.getClass().getName() == "Sundae") {
                Sundae sundae = (Sundae) di;
                itemReceipt += sundae.toppingName + " Sundae with\n";
            }

//            DessertItem dessertItemObject .name=di.getName();
            itemReceipt += di.getName() + String.format("%5s", dessertShoppeObject.cents2dollarsAndCents(di.getCost())) + "\n";

        }
        receipt += itemReceipt + "\n\n" + String.format("%-51s", "Tax") + String.format("%5s", dessertShoppeObject.cents2dollarsAndCents(totalTax))
                + "\n" + String.format("%-51s", "Total Cost") + String.format("%5s", dessertShoppeObject.cents2dollarsAndCents(totalCost + totalTax));
        return receipt;
    }
}


