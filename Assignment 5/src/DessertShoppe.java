public class DessertShoppe {
    double TAX_RATE=2.0;
    int MAX_ITEM_SIZE=50;
    int MAX_WIDTH=MAX_ITEM_SIZE+1;


    String cents2dollarsAndCents(int cents){
        return String.valueOf(((float)cents/100));

    }
}
