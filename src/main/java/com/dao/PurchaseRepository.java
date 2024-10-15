package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Purchase;
import com.entity.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByOrderByPurchaseDateDesc();
    List<Purchase> findByUser(User user);
}
