public class User {
    static String name;
    static int age;
    static String address;
    static String phoneNumber;
    static String accountNumber;
    static String passWord;

    User(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    User(String name, int age, String address, String phoneNumber, String passWord, String accountNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.passWord = passWord;
    }

}
