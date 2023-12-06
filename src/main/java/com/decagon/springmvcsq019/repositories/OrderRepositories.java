package com.decagon.springmvcsq019.repositories;

import com.decagon.springmvcsq019.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositories extends JpaRepository<Order, Long> {
}
