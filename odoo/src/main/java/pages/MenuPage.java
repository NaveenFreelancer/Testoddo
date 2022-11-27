package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MenuPage extends TestBase{
	
	@FindBy(xpath="//div[text()='Inventory']/..")
	WebElement hlInventory;
	
	@FindBy(xpath="//div[text()='Manufacturing']/..")
	WebElement hlManufacturing;
	

	
	public MenuPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToInventory() throws Exception{
		click(hlInventory, " Inventory Link, "+getClass().getName());
	}
	
	public void navigateToManufacturing() throws Exception{
		click(hlManufacturing, " Manufacturing Link, "+getClass().getName());
	}


}
