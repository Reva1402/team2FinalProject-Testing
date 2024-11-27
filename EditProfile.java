package ModeratorPanel;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class EditProfile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() throws Exception {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--force-device-scale-factor=0.7");

        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("http://localhost:3000/login");

       
        driver.findElement(By.id("email")).sendKeys("revathivooraboina@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Admin@123");
        driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")).click();

        
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/nav/button[1]")));
        profileButton.click();

        
        assertEquals("http://localhost:3000/ModeratorProfile", driver.getCurrentUrl(), "Failed to navigate to UserProfile page");
        
        WebElement editProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[1]"))); 
        editProfileButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/moderatoreditprofile"));
        assertEquals("http://localhost:3000/moderatoreditprofile", driver.getCurrentUrl(), "Failed to navigate to Edit Profile page");
    
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testPageTitle() {
       

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/h2")));
        String actualTitleText = titleElement.getText();

        System.out.println("Actual page title text: " + actualTitleText);

        assertTrue(actualTitleText.startsWith("Welcome, Moderator"), "The page title matches with the expected greeting 'Welcome, Moderator'.");


    }


    @Test
    void testPhoneNumberIsDisplayed() {
        
        try { driver.switchTo().alert().dismiss(); } catch (NoAlertPresentException ignored) {}

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.name("mobileNumber")))
            .isDisplayed(), "Phone Number should be visible.");
    }

    @Test
    void testAddressIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[2]/label")));
        assertTrue(address.isDisplayed(), "Address should be visible.");
    }

    
    @Test
    void testProfilePictureIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[3]/label")));
        assertNotNull(profileImage, "Profile image should not be null.");
        assertTrue(profileImage.isDisplayed(), "Profile image should be displayed.");
    }

    @Test
    void testSaveProfileButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement SaveProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/button")));
        assertTrue(SaveProfileButton.isDisplayed(), "Edit Profile button should be displayed.");
    }

    @Test
    void testLogoutButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/button[2]")));
        assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed.");
    }

    @Test
    void testUpdateMobileNumber() {
        
        WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[1]/input")));
        mobileNumberField.clear();
        mobileNumberField.sendKeys("5146279083");

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Profile']"))).click();

        
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);

        
        alert.accept();
    }

    
    @Test
    void testUpdateAddress() {
        
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[2]/input"))); 
        addressField.clear();
        addressField.sendKeys("Rue Berri");

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Profile']"))).click();

        
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);

        
        alert.accept();
    }

 
    @Test
    void testAboutLinkVisibility() {
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink != null && aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer.");
    }

    @Test
    void testPrivacyPolicyLinkVisibility() {
        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink != null && privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer.");
    }

    @Test
    void testTermsAndConditionsLinkVisibility() {
        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink != null && termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer.");
    }

    @Test
    void testContactUsLinkVisibility() {
        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink != null && contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer.");
    }

    @Test
    void testSidebarVisibility() {
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/aside")));
        assertTrue(sidebar.isDisplayed(), "Sidebar should be visible.");
    }

    @Test
    void testDashboardLinkVisibility() {
        WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard")));
        assertTrue(dashboardLink.isDisplayed(), "Dashboard link should be visible in the sidebar.");
    }

    @Test
    void testFeedLinkVisibility() {
        WebElement feedLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feed")));
        assertTrue(feedLink.isDisplayed(), "Feed link should be visible in the sidebar.");
    }

    @Test
    void testUserManagementLinkVisibility() {
        WebElement userManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
        assertTrue(userManagementLink.isDisplayed(), "User Management link should be visible in the sidebar.");
    }

    @Test
    void testEventManagementLinkVisibility() {
        WebElement eventManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Event Management")));
        assertTrue(eventManagementLink.isDisplayed(), "Event Management link should be visible in the sidebar.");
    }

    @Test
    void testCommentManagementLinkVisibility() {
        WebElement commentManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Comment Management")));
        assertTrue(commentManagementLink.isDisplayed(), "Comment Management link should be visible in the sidebar.");
    }

    
}

