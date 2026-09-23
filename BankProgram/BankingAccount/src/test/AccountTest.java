// Written by Christopher Eichert
package test;

import main.Account;
import main.InsufficientFundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setup(){
        account = new Account();
    }

    @Test
    @DisplayName("Account Deposit")
    public void givenEmptyAccount_attemptDeposit_thenTrue(){
        account.deposit(100);

        assertEquals(100, account.getBalance());
    }

    @Test
    @DisplayName("Account Withdraw")
    public void givenNonEmptyAccount_attemptWithdraw_thenTrue(){
        account.deposit(100);
        var withdrawnAmount = account.withdraw(100);

        assertEquals(100, withdrawnAmount);
        assertEquals(0, account.getBalance());
    }

    /// Referenced this Stack Overflow forum for asserting exceptions
    /// https://stackoverflow.com/questions/40268446/how-to-assert-an-exception-is-thrown-with-junit-5
    @Test
    @DisplayName("Empty Account Transfer")
    public void givenEmptyAccount_attemptTransfer_thenException(){
        var targetAccount = new Account();

        assertThrows(InsufficientFundException.class, () -> {
            account.transferMoney(targetAccount, 10);
        });
    }

    /// Happy path
    @Test
    @DisplayName("Non-Empty Account Transfer")
    public void givenNonEmptyAccount_attemptTransfer_thenTrue(){
        account.deposit(100);
        var targetAccount = new Account();

        try {
            account.transferMoney(targetAccount, 100);
        } catch (InsufficientFundException e) {
            fail(e);
        }

        assertEquals(0, account.getBalance());
        assertEquals(100, targetAccount.getBalance());
    }

    @Test
    @DisplayName("Non-Empty account Insufficient Fund Withdraw")
    public void givenNonEmptyAccount_attemptWithdraw_thenFalse(){
        account.deposit(10);
        var withdrawnAmount = account.withdraw(100);

        assertEquals(0, withdrawnAmount);
    }

    @Test
    @DisplayName("Non-Empty Account Negative Withdraw")
    @Disabled("Waiting on bug fix #22")
    public void givenNonEmptyAccount_attemptNegativeWithdraw_thenException(){
        account.deposit(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1));
    }
}