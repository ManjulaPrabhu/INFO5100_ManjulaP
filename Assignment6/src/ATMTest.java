//import org.junit.jupiter.api.Test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ATMTest {
    public static final String NEW_PASSWORD = "1234";
    private static String ACCOUNT_NUMBER;
    public static final String TEST_ADDRESS = "TestAddress";
    public static final int AGE = 25;
    public static final String PHONE_NUMBER = "123455678";
    public static final String TEST_PASSWORD = "testpassword";
    public static final String WRONG_PASSWORD = "wrongPassword";
    public static final int DEPOSIT_AMOUNT = 2000;
    private static String TEST_USER_NAME = "TestUser";


    @Test
    public void test1CreateUserSuccess() throws IOException, InterruptedException {
        ACCOUNT_NUMBER = ATMOperations.createUser(TEST_USER_NAME, AGE, TEST_ADDRESS, PHONE_NUMBER, TEST_PASSWORD);
        Thread.sleep(1000);
        File fileObject = new File("C:/Users/USER/Desktop/INFO_5100/ATMFiles/" + ACCOUNT_NUMBER + "/UserData.txt");
        FileReader freader = new FileReader(fileObject);
        BufferedReader br = new BufferedReader(freader);
        String line = "";
        String newLine;
        while ((newLine = br.readLine()) != null) {
            line = line.concat(newLine);
        }
        Assert.assertTrue(line.contains(TEST_USER_NAME));
        Assert.assertTrue(line.contains(Integer.toString(AGE)));
        Assert.assertTrue(line.contains(TEST_ADDRESS));
        Assert.assertTrue(line.contains(TEST_PASSWORD));
        Assert.assertTrue(line.contains(PHONE_NUMBER));
    }

    @Test
    public void test2UserLoginSuccess() {
        Assert.assertTrue(ATMOperations.passwordHelper(ACCOUNT_NUMBER, TEST_PASSWORD));

    }

    @Test
    public void test3UserLoginFailure() {
        Assert.assertFalse(ATMOperations.passwordHelper(ACCOUNT_NUMBER, WRONG_PASSWORD));

    }

    @Test
    public void test4CheckBalanceSuccess() throws IOException, InterruptedException {
        ATMOperations.depositHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT);
        Thread.sleep(1000);
        Assert.assertEquals(ATMOperations.checkBalance(ACCOUNT_NUMBER), Integer.toString(DEPOSIT_AMOUNT - ATMOperations.TRANSACTION_FEE));
        // This operation brings the balance to 0 for running future tests.
        ATMOperations.withdrawalHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT - 2 * ATMOperations.TRANSACTION_FEE);
    }

    @Test
    public void test5DepositSuccess() throws InterruptedException {
        ATMOperations.depositHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT);
        Thread.sleep(1000);
        Assert.assertEquals(ATMOperations.checkBalance(ACCOUNT_NUMBER), Integer.toString(DEPOSIT_AMOUNT - ATMOperations.TRANSACTION_FEE));

    }

    @Test
    public void test6WithdrawalSuccess() throws InterruptedException {
        ATMOperations.withdrawalHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT - 2 * ATMOperations.TRANSACTION_FEE);
        Thread.sleep(1000);
        Assert.assertEquals(ATMOperations.checkBalance(ACCOUNT_NUMBER), "0");

    }

    @Test
    public void test7WithdrawalFailure() throws InterruptedException {
        ATMOperations.depositHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT);
        Assert.assertFalse(ATMOperations.withdrawalHelper(ACCOUNT_NUMBER, 2 * DEPOSIT_AMOUNT - 2 * ATMOperations.TRANSACTION_FEE));
        Thread.sleep(1000);
        //Reset Balance
        ATMOperations.withdrawalHelper(ACCOUNT_NUMBER, DEPOSIT_AMOUNT - 2 * ATMOperations.TRANSACTION_FEE);
    }

    @Test
    public void test8ViewTransactions() {
        String transactions = ATMOperations.viewTransactions(ACCOUNT_NUMBER);
        Assert.assertNotNull(transactions);
        Assert.assertTrue(transactions.contains("DEPOSIT") && transactions.contains("WITHDRAWAL"));
    }

    @Test
    public void test9ForgotPasswordSuccess() {
        ATMOperations.forgotPasswordHelper(ACCOUNT_NUMBER, AGE, PHONE_NUMBER, NEW_PASSWORD);
        Assert.assertTrue(ATMOperations.passwordHelper(ACCOUNT_NUMBER, NEW_PASSWORD));
    }

    @Test
    public void test9ForgotPasswordFailure() {
        ATMOperations.forgotPasswordHelper(ACCOUNT_NUMBER, AGE, PHONE_NUMBER, NEW_PASSWORD);
        Assert.assertFalse(ATMOperations.passwordHelper(ACCOUNT_NUMBER, WRONG_PASSWORD));
    }

}
