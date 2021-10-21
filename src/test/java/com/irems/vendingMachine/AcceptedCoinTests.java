package com.irems.vendingMachine;

import com.irems.vendingMachine.VendingMachine.AcceptedCoin;
import com.irems.vendingMachine.VendingMachine.NoSuchCoinException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AcceptedCoinTests {
    int penny = 1;
    int nickel = 5;
    int dime = 10;
    int quarter = 25;

    @Test
    void convertValueToCoinAcceptsPennyCoin() throws NoSuchCoinException {
        assertEquals(penny, AcceptedCoin.convertValueToCoin(penny).getValue());
    }

    @Test
    void convertValueToCoinAcceptsPennyNickel() throws NoSuchCoinException {
        assertEquals(nickel, AcceptedCoin.convertValueToCoin(nickel).getValue());
    }

    @Test
    void convertValueToCoinAcceptsDimeCoin() throws NoSuchCoinException {
        assertEquals(dime, AcceptedCoin.convertValueToCoin(dime).getValue());
    }

    @Test
    void convertValueToCoinAcceptsQuarterCoin() throws NoSuchCoinException {
        assertEquals(quarter, AcceptedCoin.convertValueToCoin(quarter).getValue());
    }

    @Test
    void convertValueToCoinDoNotAcceptUnknownCoin() {
        int coin = 7;
        assertThrows(NoSuchCoinException.class, () -> AcceptedCoin.convertValueToCoin(coin));
    }

    @Test
    void convertValueToCoinDoNotAcceptNegativeCoin() {
        int coin = -10;
        assertThrows(NoSuchCoinException.class, () -> AcceptedCoin.convertValueToCoin(coin));
    }
}
