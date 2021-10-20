package com.irems.vendingMachine.VendingMachine;

import java.util.stream.Stream;

public enum Product {
    COKE(15, "Coke"),
    PEPSI(35, "Pepsi"),
    SODA(45, "Soda");
    
    private final int price;
    private final String name;

    Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static Stream<Product> stream() {
        return Stream.of(Product.values());
    }
}
