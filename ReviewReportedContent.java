package ModeratorPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
class ReviewReportedContent {

	 private WebDriver driver;
	    private WebDriverWait wait;

	 @BeforeEach   // Correct annotation for JUnit 5
	    public void setUp() throws InterruptedException {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/login");

	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        // Waiting for email field to be visible and entering value
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("revathivooraboina@gmail.com");

	        // Waiting for password field and entering value
	        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	        passwordField.sendKeys("Admin@123");

	        // Waiting for login button to be clickable and clicking it
	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
	        loginButton.click();
	        
	        // Waiting until URL contains "/AdminDashboard"
	        wait.until(ExpectedConditions.urlContains("/ModeratorHomePage"));
	        
	        // Waiting for profile link to be visible and clicking it
	        WebElement usereventmanage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[4]")));
	        usereventmanage.click();
	        WebElement userviewmanage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/table/tbody/tr[1]/td[5]/button[1]")));
	        userviewmanage.click();
	    }
	 @Test
	    public void testprofiledashboardnavbar() throws InterruptedException {
	        // Use the existing wait object from setUp() instead of creating a new one
	        WebElement profilehead = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/nav")));
	        
	        // Assertion to verify the profile header text
	        assertEquals("Moderator one", profilehead.getText());
	    }

	    @Test
	    public void testprofilenavbar()throws InterruptedException { 
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/button[1]")));
	        assertNotNull("profile", profile.getText());
	    }
	    @Test
	    public void testlogoutnavbar() throws InterruptedException{ 
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/button[2]")));
	        assertNotNull("logout clickable", logout.getText());
	    }
	   
	    @Test
	    public void testAboutfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement About = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
	    	assertNotNull(About.isDisplayed());

	    }
	   
	    @Test
	    public void testprivacyfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
	    	assertNotNull(privacy.isDisplayed());

	    }
	    @Test
	    public void testTermsfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement Terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
	    	assertNotNull(Terms.isDisplayed());

	    }
	    @Test
	    public void testContactfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
	    	assertNotNull(contact.isDisplayed());

	    }
	    @Test
	    public void testfeeddashboardr()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div"));
	    	assertNotNull(feed.isDisplayed());

	
	}



}

	

