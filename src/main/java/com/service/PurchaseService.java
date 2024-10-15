package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PurchaseRepository;
import com.entity.Purchase;
import com.entity.User;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase); 
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAllByOrderByPurchaseDateDesc();
    }
    
    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseRepository.findByUser(user);
    }
}
