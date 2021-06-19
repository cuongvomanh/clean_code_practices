package Bank;

import BankAOP.Bank;

public class Account {
    private String name;
    private String location;
    private Bank bank;

    public Account(String name, String location) {
        this.name = name;
        this.location = location;
    }
    @Override
    public String toString(){
        return String.format("Name: %s, Location: %s", this.name, this.location);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
