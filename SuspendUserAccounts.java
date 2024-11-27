package AdminPanel;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuspendUserAccounts {


	    private WebDriver driver;

	    @Before
	    public void setUp() throws InterruptedException {
	        
	       
	        driver = new ChromeDriver();
	        
	        driver.get("http://localhost:3000/login");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("madhavjariwala55@gmail.com"); 

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("123456789");

	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Login')]")));
	        loginButton.click();

	        wait.until(ExpectedConditions.urlContains("/AdminDashboard"));
	        driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[4]")).click();
	        wait.until(ExpectedConditions.urlContains("/suspendedresources"));
	    }

	    @Test
	    public void testSuspendedUsersTableLoaded() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]")));
	        List<WebElement> rows = table.findElements(By.tagName("tr"));
	        assertTrue("Suspended Users table should have rows", rows.size() > 1); 
	    }

	    @Test
	    public void testReactivateUser() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        WebElement reactivateButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("/html/body/div/div/div/div[2]/table[1]/tbody/tr/td[3]/button")));
	        reactivateButton.click();

	        
	        wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert

	        
	    }


	    @Test
	    public void testSuspendedEventsTableLoaded() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]")));

 
	    }

	    @Test
	    public void testReactivateEvent() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        WebElement reactivateButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("/html/body/div/div/div/div[2]/table[2]/tbody/tr/td[2]/button")));
	        reactivateButton.click();

	       
	        wait.until(ExpectedConditions.alertIsPresent()).accept(); 


	    }


	    @Test
	    public void testNavbarNavigation() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        WebElement profileLink = driver.findElement(By.xpath("/html/body/div/div/div/header/div/a"));
	        profileLink.click();
	        wait.until(ExpectedConditions.urlContains("/AdminProfile"));
	        assertTrue("Should navigate to Profile page", driver.getCurrentUrl().contains("/AdminProfile"));

	        
	        driver.navigate().back();
	        wait.until(ExpectedConditions.urlContains("/suspendedresources"));
	    }
	    @Test
	    public void testAboutLinkVisibility() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
	        assertTrue(aboutLink.isDisplayed(), "About link should be visible.");
	    }

	    @Test
	    public void testPrivacyPolicyLinkVisibility() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
	        assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link should be visible.");
	    }

	    @Test
	    public void testTermsAndConditionsLinkVisibility() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
	        assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions link should be visible.");
	    }

	    @Test
	    public void testContactUsLinkVisibility() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
	        assertTrue(contactUsLink.isDisplayed(), "Contact Us link should be visible.");
	    }

	    

	    @Test
	    public void testSidebarLinks() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        assertTrue("Sidebar is not visible", 
	                   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]"))).isDisplayed());
	        String[] links = {"Dashboard", "User Management", "Moderator Management", "Suspended Resources", "Content Management", "Support Management", "Security Rules"};
	        for (String link : links) {
	            assertTrue(link + " link is not visible", driver.findElement(By.linkText(link)).isDisplayed());
	        }
	    }

	        

	    @After
	    public void tearDown() {
	        
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


