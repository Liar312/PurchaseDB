package ru.victor.springmvc.purchasedb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.victor.springmvc.purchasedb.model.Purchase;
import ru.victor.springmvc.purchasedb.repositories.PurchaseRepository;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    @PostMapping
    public void storePurchase(
            @RequestBody Purchase purchase
    ){
        purchaseRepository.storePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> findPurchase(){
        return purchaseRepository.findAllPurchases();
    }

}
