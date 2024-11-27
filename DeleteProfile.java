package ModeratorPanel;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class DeleteProfile {

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
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testDeleteButton() {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[2]")));
        assertNotNull(deleteButton, "Delete button should be visible");
        deleteButton.click();
    
}

    @Test
    void testDeleteProfileAlert() throws InterruptedException {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[2]")));
        deleteButton.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        TimeUnit.SECONDS.sleep(2); 

        
        String alertText = driver.switchTo().alert().getText();

        
        assertEquals("Are you sure you want to delete your profile? This action cannot be undone.", alertText, "Alert text should match expected message");

        
        driver.switchTo().alert().accept(); 
        
    }
    @Test
    void testDeleteProfileAlertCancel() throws InterruptedException  {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[2]")));
        deleteButton.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        TimeUnit.SECONDS.sleep(2); 
        
        String alertText = driver.switchTo().alert().getText();

        
        assertEquals("Are you sure you want to delete your profile? This action cannot be undone.", alertText, "Alert text should match expected message");

        
        driver.switchTo().alert().dismiss(); 

        assertEquals("http://localhost:3000/ModeratorProfile", driver.getCurrentUrl(), "User should remain on the Profile page after clicking Cancel");
    }


    @Test
    void testNavbarVisibility() {
      
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/h2")));
        
        
        assertTrue(navbar != null && navbar.isDisplayed(), "Navbar should be visible and displayed on the page");
    }
    @Test
    void testLogoutButtonVisibility() {
       
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/button[2]")));
        
        
        assertTrue(logoutButton != null && logoutButton.isDisplayed(), "Logout button should be visible and displayed on the page");
    }
    @Test
    void testAboutLinkVisibility() {
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink != null && aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer.");
    }

    @Test
    void testPrivacyPolicyLinkVisibility() {
        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink != null && privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer.");
    }

    @Test
    void testTermsAndConditionsLinkVisibility() {
        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink != null && termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer.");
    }

    @Test
    void testContactUsLinkVisibility() {
        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink != null && contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer.");
    }

    @Test
    public void testSidebarVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside")));
        assertTrue(sidebar.isDisplayed(), "Sidebar should be visible.");
    }

    @Test
    public void testDashboardLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard")));
        assertTrue(dashboardLink.isDisplayed(), "Dashboard link should be visible in the sidebar.");
    }

    @Test
    public void testFeedLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement feedLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feed")));
        assertTrue(feedLink.isDisplayed(), "Feed link should be visible in the sidebar.");
    }

    @Test
    public void testUserManagementLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
        assertTrue(userManagementLink.isDisplayed(), "User Management link should be visible in the sidebar.");
    }

    @Test
    public void testEventManagementLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement eventManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Event Management")));
        assertTrue(eventManagementLink.isDisplayed(), "Event Management link should be visible in the sidebar.");
    }

    @Test
    public void testCommentManagementLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement commentManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Comment Management")));
        assertTrue(commentManagementLink.isDisplayed(), "Comment Management link should be visible in the sidebar.");
    }

    

}
