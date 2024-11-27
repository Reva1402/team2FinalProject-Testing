package UserProfile;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class Userdeleteprofile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        driver.manage().window().maximize();

        
        driver.get("http://localhost:3000/login");

     
        driver.findElement(By.id("email")).sendKeys("nupurg1905@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Nupur@123");
        driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")).click();

       
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/aside/button[1]")));
        profileButton.click();

        
        assertEquals("http://localhost:3000/UserProfile", driver.getCurrentUrl(), "Failed to navigate to UserProfile page");
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

        assertEquals("http://localhost:3000/UserProfile", driver.getCurrentUrl(), "User should remain on the Profile page after clicking Cancel");
    }


    @Test
    void testNavbarVisibility() {
      
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/div")));
        
        
        assertTrue(navbar != null && navbar.isDisplayed(), "Navbar should be visible and displayed on the page");
    }
    @Test
    void testLogoutButtonVisibility() {
       
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/nav/button")));
        
        
        assertTrue(logoutButton != null && logoutButton.isDisplayed(), "Logout button should be visible and displayed on the page");
    }
    @Test
    void testFooterLinksVisibility() {
        
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink != null && aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer");

       
        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink != null && privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer");

        
        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink != null && termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer");

       
        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink != null && contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer");
    }
    @Test
    void testSidebarMenuVisibility() {
        
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside")));

        
        List<WebElement> menuItems = sidebar.findElements(By.tagName("button")); 

        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Profile")), "'Profile' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Post An Event")), "'Post an Event' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Events")), "'My Events' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Followers")), "'Followers' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Schedule")), "'My Schedule' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Filter Events")), "'Filter Events' menu item should be visible");
    }
    

}
