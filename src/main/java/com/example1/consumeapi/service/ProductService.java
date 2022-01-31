package com.example1.consumeapi.service;


import com.example1.consumeapi.model.Product;
import com.example1.consumeapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id)
    {
        return productRepository.findById(id);
    }

    public Product save(Product product)
    {
        return productRepository.save(product);
    }

    public void deleteById(Long id)
    {
        productRepository.deleteById(id);
    }


}
