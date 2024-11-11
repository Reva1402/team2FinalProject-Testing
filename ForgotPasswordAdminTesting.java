package ForgotPasswordAdmin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;
import junit.framework.Assert;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ForgotPasswordAdminTesting {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");  // Replace with the path to your ChromeDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Navigate to the homepage
        driver.get("http://localhost:3000/");

        // Locate the login button on the homepage and click it to navigate to the login page
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/nav/ul/button")));
        loginButton.click();

        // Wait for the login page to load and verify URL
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl());

        // Verify that the login form is present on the login page
        WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-form")));
        assertTrue(loginForm.isDisplayed());

        // Locate and click the "Forgot Password" link to navigate to the reset password page
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot Password?"))); // Adjust if necessary
        forgotPasswordLink.click();

        // Wait for the reset password page to load and verify URL
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/forgot-password"));
        assertEquals("http://localhost:3000/forgot-password", driver.getCurrentUrl());

        // Verify that the reset password form is present on the reset password page
        WebElement resetPasswordForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("forgot-password-container")));
        assertTrue(resetPasswordForm.isDisplayed());
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testForgotPasswordPageTitle() {
        String expectedTitle = "Forgot Password";
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        assertEquals(expectedTitle, titleElement.getText());
    }
    @Test
    public void testEmailFieldPresence() {
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div/input"));
        assertTrue("Expected email field to be absent, but it was present.", !emailField.isDisplayed());  // Incorrect assertion
    }
    @Test
    public void testSendResetEmailButtonPresence() {
        // Use WebDriverWait to wait until the button is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Adjust XPath if necessary; alternatively, use a different locator strategy
            WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/form/button")));
            assertTrue(resetButton.isDisplayed());
        } catch (Exception e) {
            System.out.println("Element not found or not displayed: " + e.getMessage());
            throw e; // Rethrow the exception after logging if needed
        }

}
    @Test
    public void testSendResetEmailWithIncorrectSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/div/input")));
            emailField.sendKeys("md@gmail.com");

            WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("reset-button")));
            resetButton.click();

            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-message")));
            assertTrue(successMessage.isDisplayed());
            
            // Intentionally fail if the expected text does not match
            assertEquals("This is the wrong success message.", successMessage.getText());
            
        } catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
    @Test
    public void testSendResetEmailWithValidEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time to 20 seconds

        // Locate the email input field and enter a valid email address
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/div/input")));
        emailField.sendKeys("md@gmail.com");

        // Locate and click the reset button
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("reset-button")));
        resetButton.click();

        // Wait for the success message to appear by checking for specific text content
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/p[2]")));
        assertTrue("Success message should be displayed", successMessage.isDisplayed());
        
        // Intentionally incorrect expected text to make the test fail
        assertEquals("This is the wrong success message.", successMessage.getText());
    }
    @Test
    public void testSendResetEmailWithBlankEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Locate and click the reset button without entering an email
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("reset-button")));
        resetButton.click();

        try {
            // Wait for the error message to appear based on its text content
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/p[1]")));
            assertTrue("Error message should be displayed", errorMessage.isDisplayed());
            assertEquals("Please enter your email address.", errorMessage.getText());
        } catch (TimeoutException e) {
            // Print the page source if error message is not found for debugging
            System.out.println("Error message not found. Page source:\n" + driver.getPageSource());
            throw e;
        }
    }
    @Test
    public void testForgotPasswordHeadingIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Locate and check the "Forgot Password" heading
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        assertTrue(heading.isDisplayed());
        assertEquals("Forgot Password", heading.getText());
    }
    @Test
    public void testInstructionalTextIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement instructionText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
        assertTrue(instructionText.isDisplayed());
        assertEquals("Enter your email address to receive a password reset link.", instructionText.getText());
    }
    @Test
    public void testEmailInputFieldIsPresent() {
        WebElement emailInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        assertTrue("Expected email input field to be absent, but it was present.", !emailInputField.isDisplayed()); // Incorrect assertion
    }
}

