/**
 * The Card class consists of methods that define the attributes of a Card object and display them.
 */
public class Card {
    int num;
    int suit;

    Card(int num, int suit) {
        this.num = num;
        this.suit = suit;
    }

    void display() {
        switch (num) {
            case 2:
                System.out.print("2");
                break;
            case 3:
                System.out.print("3");
                break;
            case 4:
                System.out.print("4");
                break;
            case 5:
                System.out.print("5");
                break;
            case 6:
                System.out.print("6");
                break;
            case 7:
                System.out.print("7");
                break;
            case 8:
                System.out.print("8");
                break;
            case 9:
                System.out.print("9");
                break;
            case 10:
                System.out.print("10");
                break;
            case 11:
                System.out.print("Jack");
                break;
            case 12:
                System.out.print("Queen");
                break;
            case 13:
                System.out.print("King");
                break;
            case 14:
                System.out.print("Ace");
                break;
        }

        switch (suit) {
            case 0:
                System.out.println(" of clubs");
                break;
            case 1:
                System.out.println(" of diamonds");
                break;
            case 2:
                System.out.println(" of hearts");
                break;
            case 3:
                System.out.println(" of spades");
                break;
        }

    }

    int getSuit() {
        return suit;

    }

    int getNum() {
        return num;
    }


}
