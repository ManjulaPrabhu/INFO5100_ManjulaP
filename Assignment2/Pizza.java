import java.lang.*;
import java.io.*;
public class Pizza {

    String pizza_type;
    float unit_price = 0, loyalty_points = 0;


    public static void main(String[] args) {

        Pizza p1 = new Pizza();
        Pizza p2 = new Pizza("jalapenos", 6.0f, 5.0f);
        Pizza p3 = new Pizza("onions", 5.0f);

    }
       public Pizza()
        {
            this.pizza_type = "Tomato";
            this.unit_price = 7.0f;
            this.loyalty_points = 6.0f;
	    System.out.println(pizza_type);
	    System.out.println(unit_price);
            System.out.println(loyalty_points);
        }

       public Pizza(String pizza_type, float unit_price, float loyalty_points)
        {
            this.pizza_type = pizza_type;
            this.unit_price = unit_price;
            this.loyalty_points = loyalty_points;

            System.out.println(pizza_type);
            System.out.println(unit_price);
            System.out.println(loyalty_points);
        }

        public Pizza(String pizza_type, float unit_price)
        {
            this.pizza_type = pizza_type;
            this.unit_price = unit_price;
            System.out.println(pizza_type);
            System.out.println(unit_price);
        }


}