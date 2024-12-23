package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Account;
import com.example.clothing_sell_website.repository.AccountRepository;
import com.example.clothing_sell_website.service.admin.AccountService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(String username) {
        Optional<Account> accountOpt = accountRepository.findByUsername(username);
        return accountOpt.orElseThrow(
                () -> new RuntimeException("không tìm thấy tài khoản với username + " + username));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

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
