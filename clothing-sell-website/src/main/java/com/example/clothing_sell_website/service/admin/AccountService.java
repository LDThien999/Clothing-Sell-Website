package com.example.clothing_sell_website.service.admin;

import com.example.clothing_sell_website.entity.Account;

import java.util.List;

public interface AccountService {
    Account getAccountById(String username);
    //    public List<Type> getTypes();

    Account getAccountByUsername(String username);
    List<Account> getStaffAccounts();
    List<Account> getCustomerAccounts();

    //    public Type save(Type type);
    //
    //    public void delete(String id);
}
