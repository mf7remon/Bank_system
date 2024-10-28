
abstract class BankAccount {
    protected double balance;

    public BankAccount(double blnc) {
        this.balance = blnc;
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public abstract void applyYearlyCharge();
    public abstract void withdraw(double amount);

    public double getLoan(double loanRate) {
        return balance * loanRate;
    }

    public void displayBalance() {
        System.out.println("Balance is : " + balance);
    }
}

class SavingAccount extends BankAccount {
    private static double CHARGE_RATE = 0.05;
    private static double LOAN_RATE = 0.50;
    private static double MIN_BALANCE_PERCENT = 0.02;

    public SavingAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void applyYearlyCharge() {
        balance -= balance * CHARGE_RATE;
        System.out.println("Yearly charge applied to Savings Account.");
    }

    @Override
    public void withdraw(double amount) {
        double minBalance = balance * MIN_BALANCE_PERCENT;
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrawn from Savings Account: " + amount);
        } else {
            System.out.println("Insufficient funds in Savings Account for this withdrawal.");
        }
    }
}

class CurrentAccount extends BankAccount {
    private static final double CHARGE_RATE = 0.10;
    private static final double LOAN_RATE = 0.70;
    private static final double MIN_BALANCE_PERCENT = 0.05;

    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void applyYearlyCharge() {
        balance -= balance * CHARGE_RATE;
        System.out.println("Yearly charge applied to Current Account.");
    }

    @Override
    public void withdraw(double amount) {
        double minBalance = balance * MIN_BALANCE_PERCENT;
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrawn from Current Account: " + amount);
        } else {
            System.out.println("Insufficient funds in Current Account for this withdrawal.");
        }
    }
}

public class Bank_system {
    public static void main(String[] args) {
        BankAccount savingAccount = new SavingAccount(5000.0);
        BankAccount currentAccount = new CurrentAccount(5000.0);

        savingAccount.deposit(2000);
        savingAccount.withdraw(1000);
        savingAccount.applyYearlyCharge();
        System.out.println("Loan on Savings Account: " + savingAccount.getLoan(0.50));
        savingAccount.displayBalance();

        currentAccount.deposit(1000);
        currentAccount.withdraw(500);
        currentAccount.applyYearlyCharge();
        System.out.println("Loan on Current Account: " + currentAccount.getLoan(0.70));
        currentAccount.displayBalance();
    }
}
