package com.irems.vendingMachine;

import com.irems.vendingMachine.VendingMachine.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VendingMachineTests {

	private VendingMachine underTest;

	@BeforeEach
	void setUp() {
		underTest = new VendingMachine();
	}

	@Test
	void acceptPennyCoin() {
		int coin = 1;
		underTest.insertCoin(coin);
		assertEquals(coin, underTest.getBalance());
	}

	@Test
	void acceptNickelCoin() {
		int coin = 5;
		underTest.insertCoin(coin);
		assertEquals(coin, underTest.getBalance());
	}

	@Test
	void acceptDimeCoin() {
		int coin = 10;
		underTest.insertCoin(coin);
		assertEquals(coin, underTest.getBalance());
	}

	@Test
	void acceptQuarterCoin() {
		int coin = 25;
		underTest.insertCoin(coin);
		assertEquals(coin, underTest.getBalance());
	}

	@Test
	void doNotAcceptUnknownCoin() {
		int coin = 7;
		underTest.insertCoin(coin);
		int zero = 0;
		assertEquals(zero, underTest.getBalance());
	}

	@Test
	void doNotAcceptNegativeCoin() {
		int coin = -7;
		underTest.insertCoin(coin);
		int zero = 0;
		assertEquals(zero, underTest.getBalance());
	}


}
