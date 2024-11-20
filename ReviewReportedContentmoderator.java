package Testingprojectfinal;
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

public class ReviewReportedContentmoderator {

	    private WebDriver driver;

	    @Before
	    public void setUp() throws InterruptedException {
	        
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/login");

	        // Login to the application
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("nupursinghal1991@gmail.com");

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("123456789");

	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Login')]")));
	        loginButton.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("moderator-page")));
	        driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[5]")).click();
	        wait.until(ExpectedConditions.urlContains("/ModeratorCommentManagement"));
	    }

	    @Test
	    public void testReportedCommentsTableLoaded() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/table")));
	        List<WebElement> rows = table.findElements(By.tagName("tr"));
	        assertTrue("Table should have more than just the header row", rows.size() > 1);
	        WebElement headerRow = rows.get(0);
	        assertTrue("Header should include 'Comment'", headerRow.getText().contains("Comment"));
	        assertTrue("Header should include 'Commenter First Name'", headerRow.getText().contains("Commenter First Name"));
	        assertTrue("Header should include 'Reason'", headerRow.getText().contains("Reason"));
	        assertTrue("Header should include 'Action'", headerRow.getText().contains("Action"));
	    }

	    @Test
	    public void testSearchComments() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/input")));
	        searchInput.sendKeys("spam");
	        WebElement table = driver.findElement(By.className("moderator-table"));
	        List<WebElement> rows = table.findElements(By.tagName("tr"));
	        assertTrue("Filtered rows should exist", rows.size() > 1);
	        for (int i = 1; i < rows.size(); i++) { 
	            WebElement row = rows.get(i);
	            assertTrue("Row should match search query", row.getText().toLowerCase().contains("spam"));
	        }
	    }

	    @Test
	    public void testDeleteComment() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/table")));
	        List<WebElement> rows = table.findElements(By.tagName("tr"));
	        assertTrue("There should be at least one comment in the table", rows.size() > 1);
	        WebElement deleteButton = rows.get(1).findElement(By.xpath("/html/body/div/div/div/table/tbody/tr[3]/td[4]/button"));
	        deleteButton.click();
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.accept();
	        List<WebElement> updatedRows = table.findElements(By.tagName("tr"));
	        assertEquals("Row count should decrease by 1 after deletion", rows.size() - 1, updatedRows.size());
	    }

	    @Test
	    public void testProfileNavigation() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[1]")));
	        profileButton.click();
	        wait.until(ExpectedConditions.urlContains("/ModeratorProfile"));
	        assertTrue("Should navigate to the Profile page", driver.getCurrentUrl().contains("/ModeratorProfile"));
	        driver.navigate().back();
	        wait.until(ExpectedConditions.urlContains("/ModeratorCommentManagement"));
	    }

	    @Test
	    public void testLogoutFunctionality() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[2]")));
	        logoutButton.click();
	        wait.until(ExpectedConditions.urlContains("/login"));
	        assertTrue("Should navigate to the Login page after logout", driver.getCurrentUrl().contains("/login"));
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


	    @After
	    public void tearDown() {
	      
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

	
	
