package pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage extends TestBase{
	
	@FindBy(css="#login")
	WebElement inputEmail;
	
	@FindBy(css="#password")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnLogIn;

	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void doLogin() throws Exception{
		verifyPageLoadCompleted(2);
		sendKeys(inputEmail, "user@codechallenge.app","Email ,"+getClass().getName());
		sendKeys(inputPassword, "@sp1r3app", "Password ,"+getClass().getName());
		click(btnLogIn,"Login Button , "+getClass().getName());
		
	}

}
