package myevents;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class viewadminprofile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prism Infotech\\eclipse-workspace\\event\\src\\myevents\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    
    @Test
    public void testProfileLoading() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-card")));
        assertTrue(driver.findElement(By.className("profile-info")).isDisplayed(), "Profile info should be visible");
    }

    
    @Test
    public void testProfilePicturePresence() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-picture")));
        WebElement profilePicture = driver.findElement(By.className("profile-picture"));
        assertNotNull(profilePicture, "Profile picture should be present.");
    }

    
    @Test
    public void testEditButtonVisibility() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("edit-btn")));
        WebElement editButton = driver.findElement(By.className("edit-btn"));
        assertTrue(editButton.isDisplayed(), "Edit Profile button should be visible");
    }

    
    @Test
    public void testDeleteButtonVisibility() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-btn")));
        WebElement deleteButton = driver.findElement(By.className("delete-btn"));
        assertTrue(deleteButton.isDisplayed(), "Delete Profile button should be visible");
    }

    @Test
    public void testProfileDataNotEmpty() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-info")));
        WebElement profileInfo = driver.findElement(By.className("profile-info"));
        assertTrue(profileInfo.getText().contains("Name:"), "Profile info should not be empty");
    }

   
    @Test
    public void testInvalidAdminProfile() {
        driver.get("http://localhost:3000/invalidAdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-container")));
        String errorMessage = driver.findElement(By.tagName("body")).getText();
        assertTrue(errorMessage.contains("No profile data available"), "Invalid admin profile should show an error");
    }

    @Test
    public void testProfileLoadingWithInvalidUid() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        String errorMessage = driver.findElement(By.className("error-message")).getText();
        assertEquals("Admin profile not found or user is not an admin.", errorMessage);
    }

    @Test
    public void testMissingProfilePicture() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-info")));
        WebElement profilePicture = driver.findElement(By.className("profile-picture"));
        assertNull(profilePicture, "Profile picture should not be present when not available.");
    }

    @Test
    public void testNavigateToEditProfile() {
        driver.get("http://localhost:3000/AdminProfile");
        WebElement editButton = driver.findElement(By.className("edit-btn"));
        editButton.click();
        wait.until(ExpectedConditions.urlContains("/editProfile"));
        assertTrue(driver.getCurrentUrl().contains("/editProfile"), "Should navigate to the Edit Profile page.");
    }

    
    @Test
    public void testNavigateToDeleteProfile() {
        driver.get("http://localhost:3000/AdminProfile");
        WebElement deleteButton = driver.findElement(By.className("delete-btn"));
        deleteButton.click();
        wait.until(ExpectedConditions.urlContains("/deleteProfile"));
        assertTrue(driver.getCurrentUrl().contains("/deleteProfile"), "Should navigate to the Delete Profile page.");
    }

    @Test
    public void testErrorMessageWhenNoAdminRole() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        String errorMessage = driver.findElement(By.className("error-message")).getText();
        assertTrue(errorMessage.contains("User is not an admin"), "Error message for non-admin should be displayed");
    }

    @Test
    public void testProfileDataErrorWhenIncomplete() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-info")));
        WebElement profileInfo = driver.findElement(By.className("profile-info"));
        assertTrue(profileInfo.getText().contains("Email:") && profileInfo.getText().contains("Name:"), "Profile info should contain all required fields.");
    }

    @Test
    public void testPageNotFoundForInvalidURL() {
        driver.get("http://localhost:3000/nonExistingProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-404")));
        assertTrue(driver.findElement(By.className("error-404")).isDisplayed(), "Page should display a 404 error for invalid URLs");
    }

    @Test
    public void testMissingFieldsOnAdminProfile() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-info")));
        WebElement profileInfo = driver.findElement(By.className("profile-info"));
        assertFalse(profileInfo.getText().contains("Address:") && profileInfo.getText().contains("Mobile Number:"), "Missing fields in admin profile should cause an error");
    }

    @Test
    public void testEmptyProfilePage() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-card")));
        driver.findElement(By.className("profile-info")).clear();
        assertTrue(driver.getPageSource().contains("No profile data available."), "Empty profile page should show error");
    }

    @Test
    public void testProfilePageForUnauthorizedAccess() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-redirect")));
        assertTrue(driver.findElement(By.className("login-redirect")).isDisplayed(), "Unauthorized users should be redirected to the login page.");
    }
    @Test
    public void testProfilePageDoesNotLoadIfUserIsNotLoggedIn() {
        driver.get("http://localhost:3000/AdminProfile");
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "User should be redirected to login page when not logged in.");
    }

    @Test
    public void testProfileDeletionAndRedirection() {
        driver.get("http://localhost:3000/AdminProfile");
        WebElement deleteButton = driver.findElement(By.className("delete-btn"));
        deleteButton.click();
        driver.findElement(By.id("confirm-delete-btn")).click();
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "After profile deletion, the user should be redirected to the login page.");
    }

    @Test
    public void testNavigateBetweenEditAndProfilePages() {
        driver.get("http://localhost:3000/adminProfile");
        WebElement editButton = driver.findElement(By.className("edit-btn"));
        editButton.click();
        wait.until(ExpectedConditions.urlContains("/editProfile"));
        assertTrue(driver.getCurrentUrl().contains("/editProfile"), "Should navigate to Edit Profile page.");
        driver.get("http://localhost:3000/adminProfile");
        assertTrue(driver.getCurrentUrl().contains("/adminProfile"), "Should navigate back to the Admin Profile page.");
    }

    @Test
    public void testInvalidProfileUrl() {
        driver.get("http://localhost:3000/nonExistingProfile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-404")));
        assertTrue(driver.findElement(By.className("error-404")).isDisplayed(), "Should display 404 error when navigating to non-existing page.");
    }
}
