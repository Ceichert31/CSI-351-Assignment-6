// Written by Christopher Eichert
package main;

public class Account {
    private double _balance;

    public Account(){
        _balance = 0.0;
    }

    /// Adds money to the balance
    public void deposit(double amount){
        // Prevent depositing negative amounts
        if (amount <= 0){
            // Eventually throw exception here
            System.out.println("Cannot deposit a zero or negative amount");
            return;
        }
        _balance += amount;
    }

    /// Subtracts money from balance and returns it
    public double withdraw(double amount){

        // If not enough in balance to withdraw, return 0.0
        var resultingBalance = _balance - amount;

        if (amount < 0){
            throw new IllegalArgumentException("Invalid amount entered.");
        }

        if (resultingBalance < 0){
            System.out.println("Insufficient funds.");
            return 0.0;
        }

        // Apply withdrawal and return amount
        _balance = resultingBalance;
        return amount;
    }

    /// Returns the current account balance
    public double getBalance(){
        return _balance;
    }

    /// Transfers money from this account to another
    public void transferMoney(Account toAccount, double amount) throws InsufficientFundException {
        if (getBalance() < amount){
            throw new InsufficientFundException("Insufficient funds. Cancelling transfer.");
        }

        var withdrawnAmount = withdraw(amount);
        toAccount.deposit(withdrawnAmount);
    }
}
