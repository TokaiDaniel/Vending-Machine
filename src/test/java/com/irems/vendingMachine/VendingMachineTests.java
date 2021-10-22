package com.irems.vendingMachine;

import com.irems.vendingMachine.Coin.AcceptedCoin;
import com.irems.vendingMachine.Product.Product;
import com.irems.vendingMachine.VendingMachine.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class VendingMachineTests {

	private VendingMachine underTest;
	private HashMap<Product,Integer> startingInventory = new HashMap<>();
	int zeroBalance = 0;
	AcceptedCoin penny = AcceptedCoin.PENNY;
	AcceptedCoin nickel = AcceptedCoin.NICKEL;
	AcceptedCoin dime = AcceptedCoin.DIME;
	AcceptedCoin quarter = AcceptedCoin.QUARTER;
	int sumOfCoins = penny.getValue() + nickel.getValue() + dime.getValue() + quarter.getValue();
	Product coke = Product.COKE;
	Product pepsi = Product.PEPSI;
	Product soda = Product.SODA;

	@BeforeEach
	void setUp() {
		startingInventory.put(coke, 5);
		startingInventory.put(pepsi, 4);
		startingInventory.put(soda, 10);
		underTest = new VendingMachine(startingInventory);
	}

	@Test
	void acceptPennyCoin() {
		underTest.insertCoin(penny);
		assertEquals(penny.getValue(), underTest.getBalance());
	}

	@Test
	void acceptDimeCoin() {
		underTest.insertCoin(dime);
		assertEquals(dime.getValue(), underTest.getBalance());
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
	void changeSelectedProduct() {
		underTest.selectProduct(soda);
		underTest.selectProduct(coke);
		assertEquals(coke, underTest.getSelectedProduct());
	}

	@Test
	void refundGivesBackMoney() {
		underTest.setBalance(sumOfCoins);
		assertEquals(sumOfCoins, underTest.refund());
	}

	@Test
	void refundSetsBalanceToZero() {
		underTest.setBalance(sumOfCoins);
		underTest.refund();
		assertEquals(zeroBalance, underTest.getBalance());
	}

	@Test
	void buyProductEnoughBalanceReturnsProduct() {
		underTest.insertCoin(quarter);
		underTest.selectProduct(coke);
		assertEquals(coke, underTest.buyProduct());
	}

	@Test
	void buyProductNotEnoughBalanceReturnsNull() {
		underTest.insertCoin(dime);
		underTest.selectProduct(coke);
		assertNull(underTest.buyProduct());
	}

	@Test
	void buyProductAfterBuyingTheCorrectBalanceRemains() {
		underTest.insertCoin(dime);
		underTest.insertCoin(quarter);
		underTest.selectProduct(coke);
		underTest.buyProduct();
		assertEquals(dime.getValue() + quarter.getValue() - coke.getPrice(), underTest.getBalance());
	}

	@Test
	void buyProductAfterBuyingTheSelectedProductIsSetToNull() {
		underTest.insertCoin(quarter);
		underTest.selectProduct(coke);
		underTest.buyProduct();
		assertNull(underTest.getSelectedProduct());
	}

	@Test
	void upgradeInventoryAfterBuyingAProduct() {
		underTest.upgradeInventory(coke);
		int reducedInventory = startingInventory.get(coke) - 1;
		assertEquals(reducedInventory, underTest.getCurrentInventory().get(coke));
	}

	@Test
	void reportConsumptionByProductNoProductBought() {
		int boughtProducts = 0;
		assertEquals(boughtProducts, underTest.reportConsumptionByProduct(coke));
	}

	@Test
	void reportConsumptionByProductTwoProductBought() {
		int boughtProducts = 2;
		underTest.upgradeInventory(coke);
		underTest.upgradeInventory(coke);
		assertEquals(boughtProducts, underTest.reportConsumptionByProduct(coke));
	}

	@Test
	void reportConsumptionByProductOtherProductsNotEffected() {
		int boughtProducts = 0;
		underTest.upgradeInventory(coke);
		underTest.upgradeInventory(coke);
		assertEquals(boughtProducts, underTest.reportConsumptionByProduct(soda));
	}

	@Test
	void resetOperationBalance() {
		underTest.insertCoin(dime);
		underTest.resetOperation(startingInventory);
		assertEquals(zeroBalance, underTest.getBalance());
	}

	@Test
	void resetOperationBalanceDefaultInventory() {
		underTest.insertCoin(dime);
		underTest.resetOperation();
		assertEquals(zeroBalance, underTest.getBalance());
	}

	@Test
	void resetOperationSelectedProduct() {
		underTest.selectProduct(coke);
		underTest.resetOperation(startingInventory);
		assertNull(underTest.getSelectedProduct());
	}

	@Test
	void resetOperationSelectedProductDefaultInventory() {
		underTest.selectProduct(coke);
		underTest.resetOperation();
		assertNull(underTest.getSelectedProduct());
	}

	@Test
	void resetOperationInventory() {
		underTest.upgradeInventory(coke);
		underTest.upgradeInventory(coke);
		underTest.resetOperation(startingInventory);
		assertEquals(0, underTest.reportConsumptionByProduct(coke));
	}

	@Test
	void resetOperationInventoryDefaultInventory() {
		underTest.upgradeInventory(coke);
		underTest.upgradeInventory(coke);
		underTest.resetOperation();
		assertEquals(0, underTest.reportConsumptionByProduct(coke));
	}
}