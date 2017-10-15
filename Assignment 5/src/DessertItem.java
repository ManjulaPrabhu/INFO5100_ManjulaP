public abstract class DessertItem {
    String name;
    DessertShoppe dessertShoppe = new DessertShoppe();


    DessertItem(String name) {
        if (name.length() > dessertShoppe.MAX_ITEM_SIZE) {
            name = name.substring(0, dessertShoppe.MAX_ITEM_SIZE);
        }
        this.name = String.format("%-" + dessertShoppe.MAX_WIDTH + "s", name);
    }

    String getName() {
        return name;
    }

    abstract int getCost();
}



