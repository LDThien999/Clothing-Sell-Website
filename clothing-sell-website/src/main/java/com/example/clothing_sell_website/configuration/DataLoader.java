package com.example.clothing_sell_website.configuration;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.clothing_sell_website.entity.Account;
import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Role;
import com.example.clothing_sell_website.entity.Staff;
import com.example.clothing_sell_website.repository.AccountRepository;
import com.example.clothing_sell_website.repository.CustomerRepository;
import com.example.clothing_sell_website.repository.RoleRepository;
import com.example.clothing_sell_website.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    Role.builder().roleId("0").roleName("staff").build(),
                    Role.builder().roleId("1").roleName("customer").build()));
        }

        if (customerRepository.count() == 0) {
            customerRepository.saveAll(List.of(Customer.builder()
                    .customerId("0001")
                    .name("Nguyen Van An")
                    .phoneNum("0123456789")
                    .email("vanan@gmail.com")
                    .creditNum("0111222333")
                    .build()));
        }

        if (staffRepository.count() == 0) {
            staffRepository.saveAll(List.of(Staff.builder()
                    .staffId("1001")
                    .name("Tran Ngoc Ba")
                    .phoneNum("0123321123")
                    .email("ngocba@gmail.com")
                    .sex("male")
                    .status(true)
                    .build()));
        }

        if (accountRepository.count() == 0) {
            Optional<Customer> customerOptional = customerRepository.findById("KH001");
            if (customerOptional.isPresent()) {
                accountRepository.saveAll(List.of(Account.builder()
                        .username("customer01")
                        .password("$2y$10$MG6jaQZk49xYO95xMxt6UO5pk.sA1htpEZppNcKY7iRudengtwzmO")
                        .customer(customerOptional.get())
                        .role(roleRepository.findByRoleId("1"))
                        .build()));
            }
            Optional<Staff> staffOptional = staffRepository.findById("NV001");
            if (staffOptional.isPresent()) {
                accountRepository.saveAll(List.of(Account.builder()
                        .username("admin")
                        .password("$2y$10$MG6jaQZk49xYO95xMxt6UO5pk.sA1htpEZppNcKY7iRudengtwzmO")
                        .staff(staffOptional.get())
                        .role(roleRepository.findByRoleId("0"))
                        .build()));
            }
        }
    }
}
