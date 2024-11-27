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

public class ModeratorviwProfile {
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
	        WebElement profilelink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[1]")));
	        profilelink.click();
	    }

	    @Test
	    public void testprofiledashboardnavbar() throws InterruptedException {
	        // Use the existing wait object from setUp() instead of creating a new one
	        WebElement profilehead = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/nav")));
	        
	        // Assertion to verify the profile header text
	        assertEquals("Admin one", profilehead.getText());
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
	    public void testsidebar()throws InterruptedException { 
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement sidebar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/aside")));
	        assertNotEquals("sidebar visible", sidebar.getText());
	    }
	    @Test
	    public void testdashboardlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[1]"));
	    	assertNotNull(link.isDisplayed());

	    }
	    @Test
	    public void testfeedmanagementlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement feedmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[2]"));
	    	assertNotNull(feedmanage.isDisplayed());

	    }
	    @Test
	    public void testusermanagementlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement usermanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[3]"));
	    	assertNotNull(usermanage.isDisplayed());

	    }
	    @Test
	    public void testeventmanagementlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement eventmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[4]"));
	    	assertNotNull(eventmanage.isDisplayed());

	    }
	    @Test
	    public void testcontentdmanagementlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement contentdmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[5]"));
	    	assertNotNull(contentdmanage.isDisplayed());

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
	    	WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div/div"));
	    	assertNotNull(feed.isDisplayed());

	    }
	    @Test
	    public void testeditButtonIsDisplayed()throws InterruptedException {
	     	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement editButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/button[1]"));
	        assertTrue(editButton.isDisplayed(), "edit button should be visible.");
	     }
	    @Test
	    public void testdeleteButtonIsDisplayed()throws InterruptedException {
	     	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement deletetButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/button[2]"));
	        assertTrue(deletetButton.isDisplayed(), "delete button should be visible.");
	     }
	}




