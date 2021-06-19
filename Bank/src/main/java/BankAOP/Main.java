package BankAOP;

public class Main {
    public static void main(String[] args){
        Bank bank = new Bank();
        bank.getAccounts().stream().forEach(System.out::println);
    }
}
