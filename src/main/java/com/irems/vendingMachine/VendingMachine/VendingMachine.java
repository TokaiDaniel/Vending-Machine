package com.irems.vendingMachine.VendingMachine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@NoArgsConstructor
@Setter
@Getter
public class VendingMachine {

    private int balance = 0;
    private Product selectedProduct;
    private HashMap<Product,Integer> startingInventory = new HashMap<>();
    private HashMap<Product,Integer> currentInventory = new HashMap<>();

    public VendingMachine(HashMap<Product, Integer> startingInventory) {
        this.startingInventory = SerializationUtils.clone(startingInventory);
        this.currentInventory = SerializationUtils.clone(startingInventory);
    }

    public void insertCoin(AcceptedCoin coin) {
        balance += coin.getValue();
    }

    public void selectProduct(Product product) {
        selectedProduct = product;
    }

    public int refund() {
        int refund = balance;
        balance = 0;
        return refund;
    }

    public Product buyProduct(){
        if (selectedProduct.getPrice() <= balance) {
            Product broughtProduct = selectedProduct;
            balance -= selectedProduct.getPrice();
            selectedProduct = null;
            upgradeInventory(broughtProduct);
            return broughtProduct;
        }
        else {
            return null;
        }
    }

    public void upgradeInventory(Product boughtProduct) {
        currentInventory.forEach((product, numberOfUnits) -> {
            if (product.equals(boughtProduct)) {
                currentInventory.put(boughtProduct,numberOfUnits - 1);
            }
        });
    }

    public int reportConsumptionByProduct(Product product) {
        return startingInventory.get(product) - currentInventory.get(product);
    }

    public void resetOperation(HashMap<Product, Integer> newInventory) {
        balance = 0;
        selectedProduct = null;
        this.startingInventory = SerializationUtils.clone(newInventory);
        this.currentInventory = SerializationUtils.clone(newInventory);
    }
}
