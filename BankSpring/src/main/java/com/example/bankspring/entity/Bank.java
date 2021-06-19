package com.example.bankspring.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "BANKS")
public class Bank {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

//    @Embeddable
//    public class Address {
//        protected String streetAddr1;
//        protected String streetAddr2;
//        protected String city;
//        protected String state;
//        protected String zipCode;
//    }

//    @Embedded
//    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="bank")
    private Collection<Account> accounts = new ArrayList<Account>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addAccount(Account account){
        account.setBank(this);
        accounts.add(account);
    }
    public Collection<Account> getAccounts(){
        return accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
