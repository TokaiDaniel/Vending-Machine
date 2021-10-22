package com.irems.vendingMachine.VendingMachine;

import com.irems.vendingMachine.Coin.AcceptedCoin;
import com.irems.vendingMachine.Coin.NoSuchCoinException;
import com.irems.vendingMachine.Product.NoSuchProductException;
import com.irems.vendingMachine.Product.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class VendingMachineController {

    private final VendingMachine vendingMachine;

    @PostMapping("insert-coin/{value}")
    public void insertCoin(@PathVariable(value = "value") int value, HttpServletResponse response) {
        response.setStatus(200);
        try {
            AcceptedCoin coin = AcceptedCoin.convertValueToCoin(value);
            vendingMachine.insertCoin(coin);
        } catch (NoSuchCoinException exception) {
            exception.printStackTrace();
        }
    }

    @PostMapping("select-product/{product-name}")
    public void selectProduct(@PathVariable("product-name") String productName, HttpServletResponse response) {
        response.setStatus(200);
        try {
            Product selectedProduct = Product.convertStringToProduct(productName);
            vendingMachine.selectProduct(selectedProduct);
        } catch (NoSuchProductException exception) {
            exception.printStackTrace();
        }
    }

    @GetMapping("refund")
    public int refund(HttpServletResponse response) {
        response.setStatus(200);
        return vendingMachine.refund();
    }

    @GetMapping("buy-product")
    public Map<String, Object> buyProduct(HttpServletResponse response) {
        response.setStatus(200);
        HashMap<String, Object> jsonResponse = new HashMap<>();
        Product boughtProduct = vendingMachine.buyProduct();
        if (boughtProduct != null) {
            jsonResponse.put("product", boughtProduct);
            int remainingChange = vendingMachine.refund();
            jsonResponse.put("remainingChange", remainingChange);
        }
        else {
            jsonResponse.put("message", "Problem with buying product");
        }
        return jsonResponse;
    }

    @GetMapping("report-consumption/{product-name}")
    public Map<String, Object> reportConsumptionByProduct(@PathVariable("product-name") String productName, HttpServletResponse response) {
        response.setStatus(200);
        HashMap<String, Object> jsonResponse = new HashMap<>();
        try {
            Product product = Product.convertStringToProduct(productName);
            int consumptionOfProduct = vendingMachine.reportConsumptionByProduct(product);
            jsonResponse.put("product", product);
            jsonResponse.put("consumption of the product", consumptionOfProduct);
            return jsonResponse;
        } catch (NoSuchProductException exception) {
            exception.printStackTrace();
            jsonResponse.put("message", "No such product found!");
            return jsonResponse;
        }
    }

    @PostMapping("reset-operation")
    public void resetOperation(HttpServletResponse response) {
        response.setStatus(200);
        vendingMachine.resetOperation();
    }

    @GetMapping("vending-machine")
    public Object VendingMachine(HttpServletResponse response) {
        response.setStatus(200);
        return vendingMachine;
    }
}
