package com.example.clothing_sell_website.service.auth;

import com.example.clothing_sell_website.entity.Account;
import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Staff;
import com.example.clothing_sell_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));

        if (Objects.equals(account.getRole().getRoleId(), "0"))  //0 = staff
            {
                Staff staff = account.getStaff();
                if (staff == null) {
                    throw new UsernameNotFoundException("Không tìm thấy thông tin người dùng cho tài khoản: " + username);
                }
            }
            else if (Objects.equals(account.getRole().getRoleId(), "1")) // 1 = customer
            {
                Customer customer = account.getCustomer();
                if (customer == null) {
                    throw new UsernameNotFoundException("Không tìm thấy thông tin người dùng cho tài khoản: " + username);
                }
            }


        String role = account.getRole().getRoleName();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }
}
