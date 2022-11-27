package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends TestBase {
	
	
	@FindBy(xpath="//span[text()='Inventory Overview']/ancestor::ol[@class='breadcrumb']")
	WebElement inventoryOverview;
	
	@FindBy(css="button[title='Create record']")
	WebElement btnCreate;
	
	@FindBy(css="input[name='name']")
	WebElement tbName;
	
	@FindBy(css="button[title='Save record']")
	WebElement btnSave;
	
	@FindBy(css="button[title='Edit record']")
	WebElement btnEdit;
	
	@FindBy(css="button[name='action_update_quantity_on_hand']")
	WebElement btnUpdateQuantity;
	
	@FindBy(css="input[name='inventory_quantity']")
	WebElement tbCountedQuantity;
	
	@FindBy(css="a[title='Home menu']")
	WebElement menu;
	
	
	

	
	public InventoryPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateTo(String menu, String subMenu) throws Exception {
		isElementPresent(inventoryOverview);
		WebElement btnMenu = driver.findElement(By.cssSelector("button[title='"+menu+"']"));
		mouseOverElement(btnMenu);
		WebElement hlsubMenu = driver.findElement(By.xpath("//a[text()='"+subMenu+"']"));
		click(hlsubMenu,getClass().getName()+", Product Hyper link");
	}
	
	public void createNewProduct() throws Exception {
		waitUntilElementFound(By.xpath("//span[text()='Products']/ancestor::ol[@class='breadcrumb']"));
		click(btnCreate, getClass().getName()+", Create Button");
		waitUntilElementFound(By.xpath("//span[text()='New']/ancestor::ol[@class='breadcrumb']")); 
		PRODUCT_NAME = "item" + System.currentTimeMillis();
		sendKeys(tbName,PRODUCT_NAME,getClass().getName()+", Name input box");
		click(btnSave, getClass().getName()+", Save Button");
		waitUntilElementFound(By.xpath("//span[text()='"+PRODUCT_NAME+"']/ancestor::ol[@class='breadcrumb']"));
	}
	
	public void updateQuantity(Float quantity) throws Exception {
		click(btnUpdateQuantity, getClass().getName()+", Update Quantity Button");
		waitUntilElementFound(By.xpath("//span[text()='Update Quantity']/ancestor::ol[@class='breadcrumb']"));
		click(driver.findElement(By.xpath("//button[contains(text(),'Create')]")), getClass().getName()+", Create Button");
		waitUntilElementFound(By.cssSelector("button[title='Save record']"));
		sendKeys(tbCountedQuantity, Float.toString(quantity),getClass().getName()+", Name input box");
		click(btnSave, getClass().getName()+", Save Button");
	}
	
	public void navigateToMenuPage() throws Exception {
		click(driver.findElement(By.xpath("//button[contains(text(),'Create')]")), getClass().getName()+", Create Button");
		click(menu,getClass().getName()+", Application Icon");
	}

}
