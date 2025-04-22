import java.util.*;

public class BankingSystem {
    private HashMap<Integer, Double> accountBalances = new HashMap<>();
    private Queue<Integer> withdrawalQueue = new LinkedList<>();

    public void createAccount(int accountNumber, double initialBalance) {
        accountBalances.put(accountNumber, initialBalance);
    }

    public void requestWithdrawal(int accountNumber) {
        if (accountBalances.containsKey(accountNumber)) {
            withdrawalQueue.offer(accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    public void processWithdrawals(double amount) {
        while (!withdrawalQueue.isEmpty()) {
            int acc = withdrawalQueue.poll();
            double currentBalance = accountBalances.get(acc);
            if (currentBalance >= amount) {
                accountBalances.put(acc, currentBalance - amount);
                System.out.println("Withdrawal of $" + amount + " processed for Account " + acc);
            } else {
                System.out.println("Insufficient balance for Account " + acc);
            }
        }
    }

    public void displayAccountsSortedByBalance() {
        TreeMap<Double, List<Integer>> balanceMap = new TreeMap<>();

        for (Map.Entry<Integer, Double> entry : accountBalances.entrySet()) {
            int account = entry.getKey();
            double balance = entry.getValue();
            balanceMap.putIfAbsent(balance, new ArrayList<>());
            balanceMap.get(balance).add(account);
        }

        System.out.println("Accounts sorted by balance:");
        for (Map.Entry<Double, List<Integer>> entry : balanceMap.entrySet()) {
            double balance = entry.getKey();
            for (int account : entry.getValue()) {
                System.out.println("Account " + account + ": $" + balance);
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        bank.createAccount(1001, 5000);
        bank.createAccount(1002, 3000);
        bank.createAccount(1003, 7000);
        bank.createAccount(1004, 3000);

        bank.requestWithdrawal(1001);
        bank.requestWithdrawal(1002);
        bank.requestWithdrawal(1003);

        bank.processWithdrawals(1000);
        System.out.println();
        bank.displayAccountsSortedByBalance();
    }
}