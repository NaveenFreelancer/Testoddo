package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class TestBase {
	
	static String PRODUCT_NAME ;
	public final Float QUANTITY = 15f;
	
	static WebDriver driver;
	String url;
	final String URL = "https://codechallenge.odoo.com";
	Wait<WebDriver> wait;
	

	
	public static void initDriver() {
		String browserName = "chrome";
		switch(browserName.toUpperCase()) {
		
		case "CHROME":
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		}
		
	}
	
	
	
	public boolean waitUntilElementFound(By locator) {
		Boolean found = false;
		try {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(5));
		wait.pollingEvery(Duration.ofSeconds(1));
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		found = true;
	} catch (Exception e) {
		e.getStackTrace();
	}
		return found;
	}
	
	
	public  void click(WebElement element, String elementDetails) throws Exception {
		try {
			if(element.isEnabled())
			{
				element.click();
				
					}
		} catch (Exception e) {
			String msg = String.format("Click is not performed on %s element in %s page , Exception message : %s", elementDetails.split(",")[0], elementDetails.split(",")[1],e.getMessage());
			throw new Exception(msg);
		}

	}
	
	public  void sendKeys(WebElement element, String text, String elementDetails) throws InterruptedException, IOException {
		try {
			if(element.isEnabled())
			{
				element.clear();
				element.sendKeys(text);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void mouseOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.click();
		action.build().perform();
	}
	
	public boolean verifyPageLoadCompleted(Integer seconds) {
		boolean pageLoadedCompletely = false;
		JavascriptExecutor j = (JavascriptExecutor)driver;
		for (int i=0; i<seconds; i++){
	         try {
	            Thread.sleep(1000);
	         }catch (InterruptedException ex) {
	            System.out.println("Page has not loaded yet ");
	         }
	         //again check page state
	         if (j.executeScript("return document.readyState").toString().equals("complete")){
	            pageLoadedCompletely = true;
	            break;
	         }
	      }
		return pageLoadedCompletely;
	}
	

	
	
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	
	
	@BeforeTest
	public void launchURL() throws InterruptedException{
		initDriver();
		driver.get(URL);
		
	}
	
	@AfterTest
	public void tearDown() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
		
	}

}
