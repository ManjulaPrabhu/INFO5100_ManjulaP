public class Sundae extends IceCream {
String toppingName;
int toppingCost;


Sundae(String name,int cost,String toppingName,int toppingCost) {
    super(name,cost);
    this.toppingName = toppingName;
    this.toppingCost = toppingCost;
}
int getCost(){
    return super.getCost() + toppingCost;

}
}
