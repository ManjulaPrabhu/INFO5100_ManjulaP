import java.io.*;
import java.util.*;

public class ATMOperations {

    public static final String ATM_FILES = "C:/Users/USER/Desktop/INFO_5100/ATMFiles/";
    static int ATM_AVAILABLE_AMOUNT = 50000;
    static int TRANSACTION_FEE = 5;
    static Scanner in = new Scanner(System.in);
    static User userSessionObject;

    protected static void forgotPasswordHelper(String accountNumber, int age, String phoneNumber, String newPassword) {
        String line;
        FileReader freader = null;
        FileWriter fwriter = null;
        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/UserData.txt");
            freader = new FileReader(fileObject);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                if (line.contains(accountNumber) && line.contains(Integer.toString(age)) && line.contains(phoneNumber)) {
                    fwriter = new FileWriter(fileObject, false);
                    fwriter.write(line);
                    fwriter.write("\r\nPassword:" + newPassword + ";");
                    System.out.println("Password has been successfully updated");
                    break;
                } else {
                    System.out.println("Please enter valid data");
                }
            }

        } catch (IOException ie) {
            System.err.println("There was error in reading from the file" + ie.getMessage());
        } finally {
            try {
                if (freader != null) {

                    freader.close();
                    fwriter.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    static boolean passwordHelper(String accountNumber, String password) {
        String line;
        String checkPassword = "";
        FileReader freader;
        File fileObject = new File(ATM_FILES + accountNumber + "/UserData.txt");
        try {
            freader = new FileReader(fileObject);

            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                checkPassword = line;
            }
            String subStringToCheck = checkPassword.substring(checkPassword.indexOf(":") + 1, checkPassword.indexOf(";"));

            if (subStringToCheck.equals(password)) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    protected static void login(String accountNumber, String passWord) {
        if (passwordHelper(accountNumber, passWord)) {
            System.out.println("LOGIN is Successful");
            userSessionObject = new User(accountNumber);
            DisplayOptionsToTheUser();
        } else {
            System.out.println("Please check your credentials LOGIN Failed");
        }

    }

    protected static void DisplayOptionsToTheUser() {

        int functionToPerform;
        while (true) {
            System.out.println("1.Change Password");
            System.out.println("2.Check Available Balance");
            System.out.println("3.Withdrawal");
            System.out.println("4.Deposit");
            System.out.println("5.View Recent Transactions");
            System.out.println("6.Exit");
            System.out.println("Enter the function to perform");
            functionToPerform = in.nextInt();

            switch (functionToPerform) {
                case 1:
                    changePassword(userSessionObject.accountNumber);
                    break;
                case 2:
                    System.out.println("CURRENT BALANCE IS:" + checkBalance(userSessionObject.accountNumber));
                    break;
                case 3:
                    withdrawal(userSessionObject.accountNumber);
                    break;
                case 4:
                    deposit(userSessionObject.accountNumber);
                    break;
                case 5:
                    viewTransactions(userSessionObject.accountNumber);
                    break;
                case 6:
                    System.exit(0);
            }
        }

    }

    protected static String viewTransactions(String accountNumber) {
        List<String> recentTransactionsList = new ArrayList<>();
        String line;
        int lineCount = 0;
        int itemCount = 0;
        String stringToBeAddedToList;

        FileReader freader = null;

        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/TransactionData.txt");
            freader = new FileReader(fileObject);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                if (lineCount >= 1) {
                    stringToBeAddedToList = line.substring(line.indexOf(";") + 1, line.lastIndexOf(";"));
                    recentTransactionsList.add(stringToBeAddedToList);
                }
                lineCount++;
            }
            StringBuilder builder = new StringBuilder();
            System.out.println("Your most Recent Transactions are:");
            Collections.reverse(recentTransactionsList);

            for (String transaction : recentTransactionsList) {
                if (itemCount < 10) {
                    builder.append(transaction);
                    builder.append("\n");
                    itemCount++;
                } else {
                    break;
                }

            }
            System.out.println(builder.toString());
            return builder.toString();
        } catch (IOException e) {
            System.err.println("There was error in writing the file" + e.getMessage());
        } finally {
            try {
                if (freader != null) {

                    freader.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        return null;
    }


    protected static void deposit(String accountNumber) {

        System.out.println("The Current Amount in ATM Machine is" + ATM_AVAILABLE_AMOUNT);
        System.out.println("Enter the amount to deposit");
        int depositAmount = in.nextInt();
        depositHelper(accountNumber, depositAmount);

    }

    static void depositHelper(String accountNumber, int depositAmount) {
        FileReader freader;
        FileWriter fwriter = null;

        String line;
        String amountUpdate = "";
        String balanceToBeUpdated = "";
        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/TransactionData.txt");
            freader = new FileReader(fileObject);
            fwriter = new FileWriter(fileObject, true);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                amountUpdate = line;
            }
            if (amountUpdate.contains("CURRENT BALANCE:")) {
                balanceToBeUpdated = amountUpdate.substring(amountUpdate.indexOf(":") + 1, amountUpdate.indexOf(";"));
                balanceToBeUpdated = Integer.toString(Integer.parseInt(balanceToBeUpdated) + depositAmount - TRANSACTION_FEE);
                fwriter.write("\r\nCURRENT BALANCE:" + balanceToBeUpdated + ";");
                fwriter.write("DEPOSIT:" + Integer.toString(depositAmount) + ";");
                System.out.println("Deposit has been done successfully");
                ATM_AVAILABLE_AMOUNT = ATM_AVAILABLE_AMOUNT + depositAmount + TRANSACTION_FEE;
            }

        } catch (IOException ie) {
            System.err.println("There was error in writing the file" + ie.getMessage());
        } finally {
            try {
                if (fwriter != null) {

                    fwriter.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }

    }

    protected static void withdrawal(String accountNumber) {
        System.out.println("The Current Amount in ATM Machine is" + ATM_AVAILABLE_AMOUNT);
        System.out.println("Enter the amount to be withdrawn");
        int withdrawalAmount = in.nextInt();
        withdrawalHelper(accountNumber, withdrawalAmount);
    }

    static boolean withdrawalHelper(String accountNumber, int withdrawalAmount) {
        String line;
        String amountUpdate = "";
        String balanceToBeUpdated;
        FileReader freader;
        FileWriter fwriter = null;
        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/TransactionData.txt");
            freader = new FileReader(fileObject);
            fwriter = new FileWriter(fileObject, true);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                amountUpdate = line;
            }
            if (amountUpdate.contains("CURRENT BALANCE:")) {
                balanceToBeUpdated = amountUpdate.substring(amountUpdate.indexOf(":") + 1, amountUpdate.indexOf(";"));
                if (ATM_AVAILABLE_AMOUNT > 5000 && Integer.parseInt(balanceToBeUpdated) >= withdrawalAmount) {
                    balanceToBeUpdated = Integer.toString(Integer.parseInt(balanceToBeUpdated) - withdrawalAmount - TRANSACTION_FEE);
                    fwriter.write("\r\nCURRENT BALANCE:" + balanceToBeUpdated + ";");
                    fwriter.write("WITHDRAWAL:" + Integer.toString(withdrawalAmount) + ";");
                    System.out.println("Withdrawal has been done successfully");
                    ATM_AVAILABLE_AMOUNT = ATM_AVAILABLE_AMOUNT - withdrawalAmount + TRANSACTION_FEE;
                    return true;
                } else {
                    System.out.println("There is no sufficient funds in the Account");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("There was error in writing the file" + e.getMessage());
        } finally {
            try {
                if (fwriter != null) {

                    fwriter.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }

        return false;
    }

    protected static String checkBalance(String accountNumber) {
        String line;
        String balanceToCheck = "";
        FileReader freader = null;
        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/TransactionData.txt");
            freader = new FileReader(fileObject);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                balanceToCheck = line;
            }
            if (balanceToCheck.contains("CURRENT BALANCE:")) {
                return balanceToCheck.substring(balanceToCheck.indexOf(":") + 1, balanceToCheck.indexOf(";"));

            }
        } catch (IOException e) {
            System.err.println("There was error in displaying the file" + e.getMessage());
        } finally {
            try {
                if (freader != null) {

                    freader.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        return "0";
    }

    private static void changePassword(String accountNumber) {
        String line;
        int changeFlag = 0;
        FileReader freader;
        FileWriter fwriter = null;
        String updatedLine = "";
        System.out.println("Enter old Password");
        String checkPassWord = in.next();
        try {
            File fileObject = new File(ATM_FILES + accountNumber + "/UserData.txt");
            freader = new FileReader(fileObject);
            BufferedReader br = new BufferedReader(freader);
            while ((line = br.readLine()) != null) {
                if (line.contains("NAME:")) {
                    updatedLine = line;
                    continue;
                } else if (line.contains(checkPassWord)) {
                    System.out.println("Enter new Password");
                    String newPassWord = in.next();
                    fwriter = new FileWriter(fileObject, false);
                    fwriter.write(updatedLine);
                    fwriter.write("\r\nPASSWORD:" + newPassWord + ";");
                    System.out.println("Password reset Successfully");
                    changeFlag = 1;
                    break;
                }
            }
            if (changeFlag == 0) {
                System.out.println("Please enter correct Password");
            }
        } catch (IOException e) {
            System.err.println("There was error in writing the file" + e.getMessage());
        } finally {
            try {
                if (fwriter != null) {

                    fwriter.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    static String createUser(String name, int age, String address, String phoneNumber, String passWord) {
        String accountNumber = UUID.randomUUID().toString();
        FileWriter fwriter = null;
        FileWriter fwriter2 = null;
        try {
            File dirObject = new File(ATM_FILES + accountNumber);
            if (dirObject.exists()) {
                return "User Account already present.";
            }
            dirObject.mkdirs();
            File fileObject = new File(ATM_FILES + accountNumber + "/UserData.txt");
            fwriter = new FileWriter(fileObject, true);
            fwriter.write("NAME:" + name + "; ");
            fwriter.write("AGE:" + age + "; ");
            fwriter.write("ADDRESS:" + address + "; ");
            fwriter.write("PHONE NUMBER:" + phoneNumber + "; ");

            fwriter.write("ACCOUNT NUMBER:" + accountNumber + "; ");
            fwriter.write("\r\nPASSWORD:" + passWord + ";");
            File fileObjectUserAccount = new File(ATM_FILES + accountNumber + "/TransactionData.txt");
            fwriter2 = new FileWriter(fileObjectUserAccount, true);
            fwriter2.write("ACCOUNT NUMBER" + accountNumber + " ");
            fwriter2.write("CURRENT BALANCE:" + 0 + ";");
            fwriter2.write(" DEPOSIT:" + 0 + ";");
            fwriter2.write(" WITHDRAW:" + 0 + ";");
            userSessionObject = new User(name, age, address, phoneNumber, passWord, accountNumber);
        } catch (IOException e) {
            System.err.println("There was error in writing the file" + e.getMessage());
        } finally {

            try {
                if (fwriter != null && fwriter2 != null) {
                    fwriter.close();
                    fwriter2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userSessionObject.accountNumber;

    }

    public static void forgotPassword(String accountNumber, int age, String phoneNumber) {
        System.out.println("Please enter a new Password to be reset");
        forgotPasswordHelper(accountNumber, age, phoneNumber, in.next());
    }
}

