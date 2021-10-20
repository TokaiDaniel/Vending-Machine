package com.irems.vendingMachine.VendingMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VendingMachine {

    private int balance = 0;

    public void insertCoin(Integer value) {
        AcceptedCoin.stream()
                .filter(coin -> coin.getValue().equals(value))
                .findFirst().ifPresent(coin -> balance += coin.getValue());
    }
}
