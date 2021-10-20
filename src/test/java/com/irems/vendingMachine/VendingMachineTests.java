package com.irems.vendingMachine;

import com.irems.vendingMachine.VendingMachine.Product;
import com.irems.vendingMachine.VendingMachine.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class VendingMachineTests {

	private VendingMachine underTest;
	int zeroBalance = 0;
	int penny = 1;
	int nickel = 5;
	int dime = 10;
	int quarter = 25;
	int sumOfCoins = penny + nickel + dime + quarter;

	@BeforeEach
	void setUp() {
		underTest = new VendingMachine();
	}

	@Test
	void acceptPennyCoin() {
		underTest.insertCoin(penny);
		assertEquals(penny, underTest.getBalance());
	}

	@Test
	void acceptNickelCoin() {
		underTest.insertCoin(nickel);
		assertEquals(nickel, underTest.getBalance());
	}

	@Test
	void acceptDimeCoin() {
		underTest.insertCoin(dime);
		assertEquals(dime, underTest.getBalance());
	}

	@Test
	void acceptQuarterCoin() {
		underTest.insertCoin(quarter);
		assertEquals(quarter, underTest.getBalance());
	}

	@Test
	void doNotAcceptUnknownCoin() {
		int coin = 7;
		underTest.insertCoin(coin);
		assertEquals(zeroBalance, underTest.getBalance());
	}

	@Test
	void doNotAcceptNegativeCoin() {
		int coin = -10;
		underTest.insertCoin(coin);
		assertEquals(zeroBalance, underTest.getBalance());
	}

	@Test
	void moreCoinsAcceptedInSuccession() {
		underTest.insertCoin(penny);
		underTest.insertCoin(nickel);
		underTest.insertCoin(dime);
		underTest.insertCoin(quarter);
		assertEquals(sumOfCoins, underTest.getBalance());
	}

	@Test
	void selectProductCoke() {
		String product = "Coke";
		underTest.selectProduct(product);
		assertEquals(Product.COKE, underTest.getSelectedProduct());
	}

	@Test
	void selectProductPepsi() {
		String product = "Pepsi";
		underTest.selectProduct(product);
		assertEquals(Product.PEPSI, underTest.getSelectedProduct());
	}

	@Test
	void selectProductSoda() {
		String product = "Soda";
		underTest.selectProduct(product);
		assertEquals(Product.SODA, underTest.getSelectedProduct());
	}

	@Test
	void changeSelectedProduct() {
		String soda = "Soda";
		underTest.selectProduct(soda);
		String coke = "Coke";
		underTest.selectProduct(coke);
		assertEquals(Product.COKE, underTest.getSelectedProduct());
	}

	@Test
	void wrongStringSelectedProduct() {
		String product = "Szoda";
		underTest.selectProduct(product);
		assertNull(underTest.getSelectedProduct());
	}

	@Test
	void refundGivesBackMoney() {
		underTest.insertCoin(penny);
		underTest.insertCoin(nickel);
		underTest.insertCoin(dime);
		underTest.insertCoin(quarter);
		assertEquals(sumOfCoins, underTest.refund());
	}

	@Test
	void refundSetsBalanceToZero() {
		int coin = 10;
		underTest.insertCoin(coin);
		underTest.refund();
		assertEquals(zeroBalance, underTest.getBalance());
	}
}