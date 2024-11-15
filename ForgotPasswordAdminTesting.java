package ForgotPasswordAdmin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordAdminTesting {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        driver.get("http://localhost:3000/login");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[1]/form/div[3]/button[3]")));
        
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
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetEmail")));
        assertTrue("Email field should be visible", emailField.isDisplayed());
    }

    @Test
    public void testSendResetEmailButtonPresence() {
        
        WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/form/button")));
        assertTrue("Send Reset Email button should be visible", resetButton.isDisplayed());
    }

@Test
public void testBlankEmailFieldValidation() {
    
    WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Send Reset Email']")));

    WebElement emailField = driver.findElement(By.id("resetEmail"));
    emailField.clear();

    resetButton.click();
    String activeElementId = driver.switchTo().activeElement().getAttribute("id");
    assertEquals("resetEmail", activeElementId);

    assertTrue("Email field should be required", emailField.getAttribute("required") != null);
}

    @Test
    public void testForgotPasswordModalCloseButton() {
        
        WebElement forgotPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[1]/form/div[3]/button[3]")));
        forgotPasswordButton.click();

        
        WebElement forgotPasswordModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/h2")));
        assertTrue(forgotPasswordModal.isDisplayed(), "Forgot Password modal should be visible");

        
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/h4")));
        closeButton.click();

        
        wait.until(ExpectedConditions.invisibilityOf(forgotPasswordModal));
    }


}


