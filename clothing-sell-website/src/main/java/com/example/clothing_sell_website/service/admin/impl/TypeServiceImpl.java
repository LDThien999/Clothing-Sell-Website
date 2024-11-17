package com.example.clothing_sell_website.service.admin.impl;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.repository.BrandRepository;
import com.example.clothing_sell_website.repository.TypeRepository;
import com.example.clothing_sell_website.service.admin.BrandService;
import com.example.clothing_sell_website.service.admin.TypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TypeServiceImpl implements TypeService {
    TypeRepository typeRepository;
    @Override
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type findTypeById(String id) {
        return typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy loại với id là " + id));
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void delete(String id) {
        typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy loại này"));
        typeRepository.deleteById(id);
    }
}
