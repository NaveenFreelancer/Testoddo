package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ManufacturingPage extends TestBase{
	
	
	
	String orderName = "";
	
	@FindBy(css="button[title='Create record']")
	WebElement btnCreate;
	
	@FindBy(css="div[name='product_id'] input")
	WebElement tbProductName;
	
	@FindBy(css="input[name='qty_producing']")
	WebElement tbProductQuantity;
	
	@FindBy(xpath="//label[text()='Responsible']/../following-sibling::td//input")
	WebElement tbUser;
	
	@FindBy(xpath="//div[@name='date_planned_start']/ancestor::td")
	WebElement dateScheduled;
	
	@FindBy(xpath="//span[text()='Confirm']/..")
	WebElement btnConfirm;
	
	@FindBy(css="button[title ='Current state']")
	WebElement currentstatus;
	
	@FindBy(xpath="//span[text() ='Mark as Done']/ancestor::button[@class='btn btn-primary']")
	WebElement btnMarkAsDone;
	
	@FindBy(xpath="//label[text()='Quantity']/../following-sibling::td//span[@name='qty_producing']")
	WebElement txtQuantity;
	
	@FindBy(css=".modal-footer button.btn-primary")
	WebElement modalAcceptButton; 
	
	@FindBy(css="button[title='Save record']")
	WebElement btnSave;
	
	@FindBy(css="span[name='name']")
	WebElement txtorderName;
	
	@FindBy(css="a[name='product_id'] span")
	WebElement textProductName;

	@FindBy(css="span[name='date_planned_start']")
	WebElement textScheduledDate;
	
	@FindBy(css="a[name='user_id'] span")
	WebElement textResposible;
	
	@FindBy(css="span[name='qty_producing']")
	WebElement textQuantity;
	
	
	String CONFIRMATION_TEXT = "There are no components to consume. Are you still sure you want to continue?";

	public ManufacturingPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void createOrder() throws Exception {
		waitUntilElementFound(By.xpath("//span[text()='Manufacturing Orders']/ancestor::ol[@class='breadcrumb']"));
		click(driver.findElement(By.xpath("//button[contains(text(),'Create')]")), getClass().getName()+", Create Button");
		waitUntilElementFound(By.xpath("//span[text()='New']/ancestor::ol[@class='breadcrumb']")); 
		sendKeys(tbProductName, PRODUCT_NAME, getClass().getName()+", Product name");
		waitUntilElementFound(By.xpath("//a[text()='"+PRODUCT_NAME+"']"));
		click(driver.findElement(By.xpath("//a[text()='"+PRODUCT_NAME+"']")), getClass().getName()+", Product");
		click(btnConfirm, getClass().getName()+", Confirm button");
		waitUntilElementFound(By.xpath("//span[text() ='Mark as Done']/ancestor::button[@class='btn btn-primary']"));
		click(tbProductQuantity, getClass().getName()+", Product quantity");
		sendKeys(tbProductQuantity, Double.toString(QUANTITY), getClass().getName()+", Product name");
		orderName = txtorderName.getText();
		
		System.out.println("Date >> "+ dateScheduled.getText());
		
		click(btnMarkAsDone, getClass().getName()+", Mark As Done Button");
		waitUntilElementFound(By.cssSelector(".modal-content"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".modal-body")).getText(),CONFIRMATION_TEXT, "" );
		click(modalAcceptButton, getClass().getName()+", Modal Accept Button");
		waitUntilElementFound(By.xpath("//button[@title ='Current state' and contains(text(),'Done')]"));
		click(btnSave, getClass().getName()+", Save Button");
		waitUntilElementFound(By.cssSelector("button[title='Edit record']"));
	}
	
	public void verifyOrderDetails() {
		Assert.assertEquals(txtorderName.getText(), orderName,"Order name is not same");
		Assert.assertEquals(textProductName.getText().trim(), PRODUCT_NAME,"Prodoct name is not same");
		Assert.assertEquals(Float.parseFloat(textQuantity.getText().trim()), (float)QUANTITY,"Quantity unit is not same");
		
	}
}
