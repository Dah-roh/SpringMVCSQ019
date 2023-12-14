package com.decagon.springmvcsq019.repositories;

import com.decagon.springmvcsq019.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product, Long>{
    List<Product> findByCategories(String categories);
    List<Product> findByIdIn(List<Long> ids);

    List<Product> findByProductName(String productName);
}
