package Bank;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class BankProxyHandler implements InvocationHandler {
    private Bank bank;

    public BankProxyHandler(Bank bank){
        this.bank = bank;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("getAccounts")){
            bank.setAccounts(getAccountsFromDatabase());
            return bank.getAccounts();
        } else if (methodName.equals("setAccounts")){
            bank.setAccounts((Collection<Account>) args[0]);
            setAccountsToDatabase(bank.getAccounts());
            return null;
        } else {
            return null;
        }
    }
    protected Collection<Account> getAccountsFromDatabase(){
        return new ArrayList<Account>(){{
            add(new Account("cuongvm12", "Ba Trieu"));
            add(new Account("cuongvomanh", "Tran Duy Hung"));
        }};
    }
    protected void setAccountsToDatabase(Collection<Account> accounts){

    }

}
