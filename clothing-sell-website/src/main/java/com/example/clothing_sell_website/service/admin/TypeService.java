package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Type;

public interface TypeService {
    List<Type> getTypes();

    Type findTypeById(String id);

    Type save(Type type);

    void delete(String id);
}
