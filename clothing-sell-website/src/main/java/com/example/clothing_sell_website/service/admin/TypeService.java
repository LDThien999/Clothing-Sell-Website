package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Type;

public interface TypeService {
    public List<Type> getTypes();

    public Type findTypeById(String id);

    public Type save(Type type);

    public void delete(String id);
}
