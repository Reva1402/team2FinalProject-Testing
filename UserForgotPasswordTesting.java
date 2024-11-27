package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class UserForgotPasswordTesting {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--force-device-scale-factor=0.7"); 

        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        driver.get("http://localhost:3000/login");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[3]")));
        
        Actions actions = new Actions(driver);
        actions.moveToElement(forgotPasswordLink).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/h2")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testResetPasswordTitle() {
       
        WebElement resetPasswordTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/h2")));
        assertEquals("Reset Password", resetPasswordTitle.getText());
    }
    @Test
    public void testEmailFieldPresence() {
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/form/div/input")));
        assertTrue("Email field should be visible", emailField.isDisplayed());
    }

    @Test
    public void testSendResetEmailButtonPresence() {
        
        WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/form/button")));
        assertTrue("Send Reset Email button should be visible", resetButton.isDisplayed());
    }

    @Test
    public void testResetEmailFieldValidation() {
        
        WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/form/button"))); // Replace with actual locator

        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/form/div/input"))); // Replace with actual locator

        emailField.clear();

        
        resetButton.click();

        
        boolean isFieldBlank = emailField.getAttribute("value").isEmpty();
        assertTrue("Please fill the required field", isFieldBlank);
    }



@Test
public void testPasswordResetEmailSentMessage() {
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetEmail"))).sendKeys("nupurg1905@gmail.com");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/form/button"))).click();

    org.openqa.selenium.Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    assertEquals("Password reset email sent. Please check your inbox.", alert.getText());
    alert.accept();  
}

}


