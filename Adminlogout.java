package AdminPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Adminlogout {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--force-device-scale-factor=0.7");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("http://localhost:3000/login");

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("madhavjariwala55@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456789");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]"))).click();

        
        wait.until(ExpectedConditions.urlContains("/AdminDashboard"));
    }

    @Test
    void testLogoutButtonIsVisible() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/header/div/button")));
        assertTrue("Logout button should be visible", logoutButton.isDisplayed());
    }

    @Test
    void testLogoutButtonIsClickable() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/header/div/button")));
        assertTrue("Logout button should be clickable", logoutButton.isEnabled());
    }

    @Test
    void testLogoutFunctionality() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/header/div/button")));
        logoutButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));

        
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        
        if (driver != null) {
            driver.quit();
        }
    }
}
