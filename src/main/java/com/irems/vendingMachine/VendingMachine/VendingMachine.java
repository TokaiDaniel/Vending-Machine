package com.irems.vendingMachine.VendingMachine;

import com.irems.vendingMachine.Coin.AcceptedCoin;
import com.irems.vendingMachine.Product.Product;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Setter
@Getter
public class VendingMachine {

    private int balance = 0;
    private Product selectedProduct;
    private HashMap<Product,Integer> startingInventory;
    private HashMap<Product,Integer> currentInventory;

    public VendingMachine(HashMap<Product, Integer> startingInventory) {
        this.startingInventory = SerializationUtils.clone(startingInventory);
        this.currentInventory = SerializationUtils.clone(startingInventory);
    }

    public VendingMachine() {
        HashMap<Product,Integer> newInventory = new HashMap<>();
        newInventory.put(Product.COKE, 5);
        newInventory.put(Product.PEPSI, 4);
        newInventory.put(Product.SODA, 10);
        this.startingInventory = SerializationUtils.clone(newInventory);
        this.currentInventory = SerializationUtils.clone(newInventory);
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
        selectedProduct = null;
        return refund;
    }

    public Product buyProduct(){
        if (selectedProduct != null && selectedProduct.getPrice() <= balance && currentInventory.get(selectedProduct) >= 1) {
            Product broughtProduct = selectedProduct;
            balance -= selectedProduct.getPrice();
            upgradeInventory(broughtProduct);
            selectedProduct = null;
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

    public void resetOperation() {
        balance = 0;
        selectedProduct = null;
        HashMap<Product,Integer> newInventory = new HashMap<>();
        newInventory.put(Product.COKE, 5);
        newInventory.put(Product.PEPSI, 4);
        newInventory.put(Product.SODA, 10);
        this.startingInventory = SerializationUtils.clone(newInventory);
        this.currentInventory = SerializationUtils.clone(newInventory);
    }
}
