
import java.util.*;

public class ATM {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        while (true) {
            System.out.println("1.New User");
            System.out.println("2.Current User");
            System.out.println("3.Forgot Password");
            System.out.println("4.Exit");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Name, Age, Address and phoneNumber");
                    String name = in.next();
                    int age = in.nextInt();
                    String address = in.next();
                    String phoneNumber = in.next();
                    System.out.println("Username is:" + name);
                    System.out.println("You may set a password, Please enter a Password that you would like to use");
                    String passWord = in.next();
                    System.out.println("Account is Created, Account Number is:" + ATMOperations.createUser(name, age, address, phoneNumber, passWord));
                    break;
                case 2:
                    System.out.println("Enter your account Number");
                    String accountNumber = in.next();
                    System.out.println("Enter the Password");
                    passWord = in.next();
                    ATMOperations.login(accountNumber, passWord);
                    break;
                case 3:
                    System.out.println("Enter your Account Number, Age, Phone Number");
                    accountNumber = in.next();
                    age = in.nextInt();
                    phoneNumber = in.next();
                    ATMOperations.forgotPassword(accountNumber, age, phoneNumber);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter New or Current");
            }
        }
    }

}


