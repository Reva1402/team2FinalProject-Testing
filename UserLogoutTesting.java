package event;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserLogoutTesting {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Navigate to the login page
        driver.get("http://localhost:3000/login");
        
        // Enter email and password, then submit the form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("rachnaaulakh@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456789");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();

        // Verify that user is redirected to the user homepage
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
        assertEquals("http://localhost:3000/userhomepage", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogoutButtonIsVisible() {
        // Check if the logout button is visible
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Logout']")));
        assertTrue("Logout button should be visible", logoutButton.isDisplayed());
    }

    @Test
    public void testLogoutButtonIsClickable() {
        // Check if the logout button is clickable
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        assertTrue("Logout button should be clickable", logoutButton.isEnabled());
    }

    @Test
    public void testLogoutFunctionality() {
        // Click the logout button and verify redirection to login page
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        logoutButton.click();

        // Verify that user is redirected to the login page
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }
}
