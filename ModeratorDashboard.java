package Testingprojectfinal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;



public class ModeratorDashboard {

	private WebDriver driver;

	    @Before
	    public void setUp() throws InterruptedException {
	       
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/login");
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("nupursinghal1991@gmail.com");

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("123456789");

	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Login')]")));
	        loginButton.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("moderator-page")));
	    }

	    @Test
	    public void testDashboardLoads() {
	        WebElement dashboardElement = driver.findElement(By.className("moderator-page"));
	        assertTrue("Dashboard should be visible", dashboardElement.isDisplayed());
	        List<WebElement> sections = dashboardElement.findElements(By.xpath("/html/body/div/div/div/div/aside"));
	        assertTrue("Dashboard should contain multiple sections", sections.size() > 0);
	    }

	    @Test
	    public void testNavigationLinks() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        WebElement userManagementLink = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[3]"));
	        userManagementLink.click();
	        wait.until(ExpectedConditions.urlContains("/moderatordashboard"));
	        assertTrue("Should navigate to User Management page", driver.getCurrentUrl().contains("/ModeratorUserManagement"));

	        driver.navigate().back();
	        wait.until(ExpectedConditions.urlContains("/moderatordashboard"));

	    
	        WebElement eventManagementLink = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[4]"));
	        eventManagementLink.click();
	        wait.until(ExpectedConditions.urlContains("/ModeratorEventManagement"));
	        assertTrue("Should navigate to Event Management page", driver.getCurrentUrl().contains("/ModeratorEventManagement"));
	    }

	    @Test
	    public void testSearchFunctionality() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        
	        WebElement searchInput = driver.findElement(By.xpath("/html/body/div/div/div/nav/input"));
	        searchInput.sendKeys("user");

	       
	        WebElement reportedUsersSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2/a[contains(text(), 'Reported Users')]")));
	        assertTrue("Reported Users section should be visible", reportedUsersSection.isDisplayed());

	       
	        searchInput.clear();
	        Thread.sleep(1000);

	       
	        searchInput.sendKeys("event");
	        WebElement reportedEventsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2/a[contains(text(), 'Reported Events')]")));
	        assertTrue("Reported Events section should be visible", reportedEventsSection.isDisplayed());
	    }
	    @Test
	    public void testFooterLinks() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/div/a[1]")));
	        aboutLink.click();
	        assertTrue("Should navigate to About page", driver.getCurrentUrl().contains("/about"));
	        driver.navigate().back();
	        WebElement privacyPolicyLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/div/a[2]"));
	        privacyPolicyLink.click();
	        assertTrue("Should navigate to Privacy Policy page", driver.getCurrentUrl().contains("/privacy"));
	        driver.navigate().back();
	        WebElement termsLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/div/a[3]"));
	        termsLink.click();
	        assertTrue("Should navigate to Terms and Conditions page", driver.getCurrentUrl().contains("/terms"));
	        driver.navigate().back();
	        WebElement contactUsLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/div/a[4]"));
	        contactUsLink.click();
	        assertTrue("Should navigate to Contact Us page", driver.getCurrentUrl().contains("/contactus"));
	        driver.navigate().back();
	    }

	    @Test
	    public void testProfileNavigation() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[1]")));
	        profileButton.click();
	        wait.until(ExpectedConditions.urlContains("/ModeratorProfile"));
	        assertTrue("Should navigate to the Profile page", driver.getCurrentUrl().contains("/ModeratorProfile"));
	        driver.navigate().back();
	        wait.until(ExpectedConditions.urlContains("/moderatordashboard"));
	    }

	    @Test
	    public void testLogoutFunctionality() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[2]")));
	        logoutButton.click();

	        wait.until(ExpectedConditions.urlContains("/login"));
	        assertTrue("Should navigate to the Login page after logout", driver.getCurrentUrl().contains("/login"));
	    }

	    @After
	    public void tearDown() {
	       
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

