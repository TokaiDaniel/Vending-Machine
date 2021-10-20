package com.irems.vendingMachine.VendingMachine;

public enum Product {
    COKE(15),
    PEPSI(35),
    SODA(45);
    
    private final int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
