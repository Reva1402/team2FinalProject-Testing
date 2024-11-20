package Testingprojectfinal;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class IssueWarningtoUsers {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up the WebDriver
        driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("http://localhost:3000/login");

        // Perform login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupursinghal1991@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();

        // Wait for the Moderator Home Page to load
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/ModeratorHomePage"));

        // Navigate to User Management via sidebar
        WebElement userManagementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/aside/a[3]"))); // Adjust the locator as needed
        userManagementLink.click();

        // Wait for the User Management page to load
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/ModeratorUserManagement"));
    }

    @Test
    public void testIssueWarning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the "Issue Warning" button for a user
        WebElement issueWarningButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/table/tbody/tr[1]/td[2]/button[3]"))); // Adjust locator if needed
        issueWarningButton.click();

        // Handle the alert box
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        assertTrue("Alert text does not match expected message", alertText.contains("Warning has been issued to the user"));
        alert.accept(); // Click "OK"
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

