package com.example.clothing_sell_website.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.repository.TypeRepository;
import com.example.clothing_sell_website.service.customer.TypeService;

@org.springframework.stereotype.Service
public class TypeServiceCusImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepo;

    @Override
    public List<Type> getAllType() {
        return typeRepo.findAll();
    }
}
