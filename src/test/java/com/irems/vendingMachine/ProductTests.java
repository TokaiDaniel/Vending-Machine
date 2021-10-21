package com.irems.vendingMachine;


import com.irems.vendingMachine.VendingMachine.NoSuchProductException;
import com.irems.vendingMachine.VendingMachine.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTests {

    @Test
    void selectProductCoke() throws NoSuchProductException {
        assertEquals(Product.COKE, Product.convertStringToProduct("Coke"));
    }

    @Test
    void selectProductPepsi() throws NoSuchProductException {
        assertEquals(Product.PEPSI, Product.convertStringToProduct("Pepsi"));
    }

    @Test
    void selectProductSoda() throws NoSuchProductException {
        assertEquals(Product.SODA, Product.convertStringToProduct("Soda"));
    }

    @Test
    void wrongStringSelectedProduct() {
        assertThrows(NoSuchProductException.class, () -> Product.convertStringToProduct("Szoda"));
    }

}
