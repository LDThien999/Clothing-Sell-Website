package com.example.clothing_sell_website.service.admin.impl;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Account;
import com.example.clothing_sell_website.repository.AccountRepository;
import com.example.clothing_sell_website.service.admin.AccountService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).get();
    }

    @Override
    public List<Account> getStaffAccounts() {
        return accountRepository.getStaffAccounts();
    }

    @Override
    public List<Account> getCustomerAccounts() {
        return accountRepository.getCustomerAccounts();
    }
}
