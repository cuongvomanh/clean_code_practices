package Bank;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args){
        Bank bank = (Bank) Proxy.newProxyInstance(
                Bank.class.getClassLoader(),
                new Class[] {Bank.class},
                new BankProxyHandler(new BankImpl())
        );
        Collection<Account> accounts = bank.getAccounts();
        Arrays.stream(accounts.toArray()).forEach(System.out::println);
    }
}
