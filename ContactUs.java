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

public class ContactUs {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up the WebDriver
        driver = new ChromeDriver();
        
        // Navigate to login page
        driver.get("http://localhost:3000/login");

        // Perform login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("user@check.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();

        // Wait for the user homepage to load
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));

        // Navigate to the footer and click "Contact Us"
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        contactUsLink.click();

        // Wait for the Contact Us page to load
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
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/label")));
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
    public void testSendMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Locate the message box and enter the message
        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/form/div[3]/textarea")));
        messageBox.sendKeys("This is a test message.");

        // Locate the send button and click it
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/form/button")));
        sendButton.click();

        // Handle the alert box and verify its text
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().contains("Your message has been sent successfully"));
        alert.accept();
    }

}

