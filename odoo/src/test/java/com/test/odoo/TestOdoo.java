package com.test.odoo;

import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import pages.ManufacturingPage;
import pages.MenuPage;
import pages.TestBase;

public class TestOdoo extends TestBase {
	
	@Test
	void test() throws Exception {
		LoginPage loginPage = new LoginPage();
		MenuPage menuPage = new MenuPage();
		InventoryPage inventoryPage = new InventoryPage();
		ManufacturingPage manufacturingPage = new ManufacturingPage();
		loginPage.doLogin();
		menuPage.navigateToInventory();
		inventoryPage.navigateTo("Products","Products");
		inventoryPage.createNewProduct();
		inventoryPage.updateQuantity(QUANTITY);
		inventoryPage.navigateToMenuPage();
		menuPage.navigateToManufacturing();
		manufacturingPage.createOrder();
		manufacturingPage.verifyOrderDetails();
	}

}
