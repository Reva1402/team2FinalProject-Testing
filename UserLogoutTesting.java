package UserProfile;

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
        
        
        driver.get("http://localhost:3000/login");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("nupurg1905@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("Nupur@123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/form/div[3]/button[1]"))).click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
        assertEquals("http://localhost:3000/userhomepage", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogoutButtonIsVisible() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Logout']")));
        assertTrue("Logout button should be visible", logoutButton.isDisplayed());
    }

    @Test
    public void testLogoutButtonIsClickable() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        assertTrue("Logout button should be clickable", logoutButton.isEnabled());
    }

    @Test
    public void testLogoutFunctionality() {
        
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
        logoutButton.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }
}
