class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 0;

    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Setting balance to 0.");
            initialDeposit = 0;
        }
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount + " into account " + accountNumber);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds in account " + accountNumber);
            return;
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + " from account " + accountNumber);
    }

    public double checkBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        System.out.println("==================================");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("==================================");
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Alice", 1000);
        accounts[1] = new BankAccount("Bob", 500);
        accounts[2] = new BankAccount("Charlie", 2000);

        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].withdraw(2500);

        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
        System.out.println("Alice’s Balance (Instance variable): " + accounts[0].checkBalance());
    }
}
