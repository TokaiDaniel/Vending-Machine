package com.irems.vendingMachine.VendingMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Setter
@Getter
public class VendingMachine {

    private int balance = 0;

    private Product selectedProduct;

    public void insertCoin(Integer value) {
        AcceptedCoin.stream()
                .filter(coin -> coin.getValue().equals(value))
                .findFirst().ifPresent(coin -> balance += coin.getValue());
    }

    public void selectProduct(String nameOfProduct) {
        Product.stream()
                .filter(product -> product.getName().equals(nameOfProduct))
                .findFirst().ifPresent(product -> selectedProduct = product);
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
            return broughtProduct;
        }
        else {
            return null;
        }
    }
}
