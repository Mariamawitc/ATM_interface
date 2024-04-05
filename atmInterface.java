import java.util.*;


class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}


class User {
    private String userId;
    private String userPin;
    private List<Transaction> transactionHistory;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}


class ATM {
    private Map<String, User> users;

    public ATM() {
        users = new HashMap<>();

        users.put("user1", new User("user1", "1234"));
        users.put("user2", new User("user2", "5678"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        System.out.print("Enter user pin: ");
        String userPin = scanner.nextLine();

        if (authenticateUser(userId, userPin)) {
            System.out.println("Authentication successful!");
            showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
      

    }

    private boolean authenticateUser(String userId, String userPin) {
        User user = users.get(userId);
        if (user != null && user.getUserPin().equals(userPin)) {
            return true;
        }
        return false;
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        
    }

    private void viewTransactionHistory() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        User user = users.get(userId);
        if (user != null) {
            System.out.println("\nTransaction History for User: " + userId);
            List<Transaction> transactions = user.getTransactionHistory();
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
                }
            }
        } else {
            System.out.println("User not found.");
        }
       
    }

    private void performWithdrawal() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        User user = users.get(userId);
        if (user != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();

            
            System.out.println("Withdrawal of $" + amount + " successful for User: " + userId);
            user.getTransactionHistory().add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("User not found.");
        }
       
    }

    private void performDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        User user = users.get(userId);
        if (user != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();

          
            System.out.println("Deposit of $" + amount + " successful for User: " + userId);
            user.getTransactionHistory().add(new Transaction("Deposit", amount));
        } else {
            System.out.println("User not found.");
        }
     
    }

    private void performTransfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source user id: ");
        String sourceUserId = scanner.nextLine();

        User sourceUser = users.get(sourceUserId);
        if (sourceUser != null) {
            System.out.print("Enter destination user id: ");
            String destinationUserId = scanner.nextLine();

            User destinationUser = users.get(destinationUserId);
            if (destinationUser != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();

                
                System.out.println("Transfer of $" + amount + " successful from User: " + sourceUserId + " to User: " + destinationUserId);
                sourceUser.getTransactionHistory().add(new Transaction("Transfer to " + destinationUserId, amount));
                destinationUser.getTransactionHistory().add(new Transaction("Transfer from " + sourceUserId, amount));
            } else {
                System.out.println("Destination user not found.");
            }
        } else {
            System.out.println("Source user not found.");
        }
       
    }
}


public class atmInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}