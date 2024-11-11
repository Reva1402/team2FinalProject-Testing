package AdminLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminLoginTesting {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("http://localhost:3000/"); // Adjust the URL as necessary
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Test Case 1: Verify Login Page Loads and Elements Are Present
    @Test
    public void testLoginPageElementsPresence() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.text-center")));
        assertEquals("Login", title.getText());

        WebElement emailInput = driver.findElement(By.id("email"));
        assertTrue(emailInput.isDisplayed());

        WebElement passwordInput = driver.findElement(By.id("password"));
        assertTrue(passwordInput.isDisplayed());

        WebElement loginButton = driver.findElement(By.className("login-btn"));
        assertTrue(loginButton.isDisplayed());

        WebElement forgotPasswordButton = driver.findElement(By.xpath("//button[contains(text(), 'Forgot Password?')]"));
        assertTrue(forgotPasswordButton.isDisplayed());
    }

    // Test Case 2: Login with Valid Admin Credentials
    @Test
    public void testLoginWithValidAdminCredentials() {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("admin@example.com"); // Replace with valid admin credentials

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("adminpassword");

        WebElement loginButton = driver.findElement(By.className("login-btn"));
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("/admin")); // Wait for admin page
        assertEquals("http://localhost:3000/admin", driver.getCurrentUrl()); // Update URL if needed
    }

    // Test Case 3: Login with Invalid Email Format
    @Test
    public void testLoginWithInvalidEmailFormat() {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("invalid-email");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("password");

        WebElement loginButton = driver.findElement(By.className("login-btn"));
        loginButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Login failed. Please try again.", alert.getText());
        alert.accept();
    }

    // Test Case 4: Forgot Password Modal with Valid Email
    @Test
    public void testForgotPasswordModalAndResetEmail() {
        WebElement forgotPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Forgot Password?')]")));
        forgotPasswordButton.click();

        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        assertEquals("Reset Password", modalTitle.getText());

        WebElement resetEmailInput = driver.findElement(By.id("resetEmail"));
        resetEmailInput.sendKeys("user@example.com");

        WebElement resetButton = driver.findElement(By.className("reset-btn"));
        resetButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Password reset email sent. Please check your inbox.", alert.getText());
        alert.accept();
    }

    // Test Case 5: Forgot Password Modal with Invalid Email Format
    @Test
    public void testForgotPasswordWithInvalidEmailFormat() {
        WebElement forgotPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Forgot Password?')]")));
        forgotPasswordButton.click();

        WebElement resetEmailInput = driver.findElement(By.id("resetEmail"));
        resetEmailInput.sendKeys("invalid-email");

        WebElement resetButton = driver.findElement(By.className("reset-btn"));
        resetButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Failed to send password reset email. Please try again.", alert.getText());
        alert.accept();
    }

    // Test Case 6: Login with Empty Fields
    @Test
    public void testLoginWithEmptyFields() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("login-btn")));
        loginButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Login failed. Please try again.", alert.getText());
        alert.accept();
    }

    // Test Case 7: Login with Correct Email and Incorrect Password
    @Test
    public void testLoginWithCorrectEmailAndIncorrectPassword() {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("user@example.com"); // Replace with valid test email

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("wrongpassword");

        WebElement loginButton = driver.findElement(By.className("login-btn"));
        loginButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Login failed. Please try again.", alert.getText());
        alert.accept();
    }
}
