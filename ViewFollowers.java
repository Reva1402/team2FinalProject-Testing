package UserProfile;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class ViewFollowers {

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

       
        WebElement followersButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/aside/button[4]")));
        followersButton.click();

        
        
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        } 
    }
        @Test
        public void testFollowersVisibility() {
            
            WebElement followersSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/h3[1]"))); // Replace "followers" with the actual id or locator

            
            assertTrue("Followers section should be visible", followersSection.isDisplayed());
        }
        @Test
        public void testFollowingVisibility() {
            
            WebElement followersSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/h3[2]"))); // Replace "followers" with the actual id or locator

            
            assertTrue("Followers section should be visible", followersSection.isDisplayed());
        }
        @Test
        public void testFirstNameFieldVisibility() {
            
            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/table[1]/thead/tr/th[1]"))); // Replace with actual locator

            
            assertTrue("First Name field should be visible", firstNameField.isDisplayed());
        }
        @Test
        public void testEmailFieldVisibility() {
            
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/table[1]/thead/tr/th[2]"))); // Replace with actual locator

            
            assertTrue("Email field should be visible", emailField.isDisplayed());
        }
        @Test
        public void testActionButtonVisibility() {
            
            WebElement actionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/table[1]/thead/tr/th[3]"))); // Replace with actual locator

            
            assertTrue("Action button should be visible", actionButton.isDisplayed());
        }
        @Test
        void testSidebarMenuVisibility() {
            
            WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/aside")));

            
            List<WebElement> menuItems = sidebar.findElements(By.tagName("button")); 

            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Profile")), "'Profile' menu item should be visible");
            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Post An Event")), "'Post an Event' menu item should be visible");
            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Events")), "'My Events' menu item should be visible");
            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Followers")), "'Followers' menu item should be visible");
            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Schedule")), "'My Schedule' menu item should be visible");
            assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Filter Events")), "'Filter Events' menu item should be visible");
        }

        @Test
        public void testFooterLinksVisibility() {
            
            

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            
            WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
            assertTrue(aboutLink.isDisplayed(), "About link should be visible.");

            WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
            assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link should be visible.");

            WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
            assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions link should be visible.");

            WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
            assertTrue(contactUsLink.isDisplayed(), "Contact Us link should be visible.");
        }





    
}