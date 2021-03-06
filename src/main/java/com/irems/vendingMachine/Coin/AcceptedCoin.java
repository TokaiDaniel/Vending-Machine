package com.irems.vendingMachine.Coin;

import java.util.stream.Stream;

public enum AcceptedCoin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final Integer value;

    AcceptedCoin(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static AcceptedCoin convertValueToCoin(int value) throws NoSuchCoinException {
        return AcceptedCoin.stream()
                .filter(coin -> coin.getValue().equals(value))
                .findFirst()
                .orElseThrow(NoSuchCoinException::new);
    }

    public static Stream<AcceptedCoin> stream() {
        return Stream.of(AcceptedCoin.values());
    }
}
