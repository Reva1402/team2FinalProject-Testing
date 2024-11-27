package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactUs {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
       
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
        driver.get("http://localhost:3000/login");

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupurg1905@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Nupur@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));

        
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/footer/ul/li[4]")));
        contactUsLink.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/contactus"));
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        }

    @Test
    public void testContactUsTitleVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactUsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/h2")));
        assertTrue(contactUsTitle.isDisplayed());
    }

    @Test
    public void testNameFieldVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div[1]/label")));
        assertTrue(nameField.isDisplayed());
    }

    @Test
    public void testEmailFieldVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div[2]/label")));
        assertTrue(emailField.isDisplayed());
    }

    @Test
    public void testMessageBoxVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div[3]/textarea")));
        assertTrue(messageBox.isDisplayed());
    }

    @Test
    public void testSendButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/button")));
        assertTrue(sendButton.isDisplayed());
    }

    @Test
    public void testWriteMessageAndVerifyPopup() {
        
        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div[3]/textarea"))); 
        String message = "This is a test message for the contact form.";
        messageBox.sendKeys(message);

        
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/button"))); 
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

       
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertEquals("Your message has been sent successfully!", alertText);

        
        alert.accept();
    }





}

