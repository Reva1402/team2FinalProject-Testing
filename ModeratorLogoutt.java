package ModeratorPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

public class ModeratorLogoutt {

	
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
        
        
    }
    @Test
    public void testLogoutButtonIsVisible() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[2]")));
        assertTrue("Logout button should be visible", logoutButton.isDisplayed());
    }

    @Test
    public void testLogoutButtonIsClickable() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/nav/button[2]")));
        assertTrue("Logout button should be clickable", logoutButton.isEnabled());
    }

    @Test
    public void testLogoutFunctionality() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/nav/button[2]")));
        logoutButton.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }
}




