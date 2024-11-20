package event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class Eventattendanceuser {
	public class PreventSimultaneousAttendance {

	    private WebDriver driver;

	    @Before
	    public void setUp() {
	        // Set up the WebDriver and navigate to the event page
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/login");

	        // Log in as a test user
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("user@check.com");

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("password123");

	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Login')]")));
	        loginButton.click();

	        // Wait until the user is redirected to the homepage
	        wait.until(ExpectedConditions.urlContains("/userhomepage"));

	        // Navigate to a specific event page
	        driver.get("attendevent/zmmIf3nuJTVqWH74Uylo"); // Replace with the actual event ID
	    }

	    @Test
	    public void testPreventSimultaneousAttendance() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Ensure the page loads correctly
	        WebElement eventTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
	        assertNotNull("Event title should be visible", eventTitle);

	        // Select "Yes" to attend the event
	        WebElement yesRadioButton = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/label[1]/input"));
	        yesRadioButton.click();

	        // Submit attendance
	        yesRadioButton.sendKeys(Keys.RETURN);

	        // Wait for the alert and validate conflict detection
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        String alertText = alert.getText();
	        assertTrue("Alert should mention overlapping attendance", alertText.contains("already attending an event"));
	        alert.accept();

	        // Ensure the user is redirected back to the homepage
	        wait.until(ExpectedConditions.urlContains("/userhomepage"));
	        assertEquals("http://localhost:3000/userhomepage", driver.getCurrentUrl());
	    }

	    @After
	    public void tearDown() {
	        // Close the browser
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}
}
