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

class DeleteEvent {

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

       
        WebElement myeventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/aside/button[3]")));
        myeventButton.click();

        
        assertEquals("http://localhost:3000/MyEvents", driver.getCurrentUrl(), "Failed to navigate to UserProfile page");
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    void testDeleteButton() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/button[3]")));
        assertNotNull(deleteButton, "Delete button should be visible");
        deleteButton.click();
    }
    @Test
    void testDeleteEventAlert() throws InterruptedException {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/button[3]")));
        deleteButton.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        TimeUnit.SECONDS.sleep(2); 

        
        String alertText = driver.switchTo().alert().getText();
        assertEquals("Are you sure you want to delete this event?", alertText, "Alert text should match the expected message.");

        
        driver.switchTo().alert().accept();
    }

    @Test
    void testDeleteEventAlertCancel() throws InterruptedException  {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/button[3]")));
        deleteButton.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        TimeUnit.SECONDS.sleep(2); 
        
        String alertText = driver.switchTo().alert().getText();

        
        assertEquals("Are you sure you want to delete this event?", alertText, "Alert text should match expected message");

        
        driver.switchTo().alert().dismiss(); 

        assertEquals("http://localhost:3000/MyEvents", driver.getCurrentUrl(), "User should remain on the Event page after clicking Cancel");
    }


    @Test
    void testWelcomeMessage() {
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/div")));
        String actualText = welcomeMessage.getText();
        assertTrue(actualText.startsWith("Hi, "), "Welcome message should start with 'Hi, '");
    }
    @Test
    void testLogoutButtonVisibility() {
       
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/nav/button")));
        
        
        assertTrue(logoutButton != null && logoutButton.isDisplayed(), "Logout button should be visible and displayed on the page");
    }
    @Test
    void testFooterLinksVisibility() {
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer");

        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer");

        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer");

        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer");
    }
    @Test
    void testProfileLink() {
        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[1]")));
        profileLink.click();
        assertEquals("http://localhost:3000/UserProfile", driver.getCurrentUrl(), "Should navigate to User Profile page");
    }

    @Test
    void testCreateAnEventLink() {
        WebElement createEventLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[2]")));
        createEventLink.click();
        assertEquals("http://localhost:3000/createevent", driver.getCurrentUrl(), "Should navigate to Create Event page");
    }

    @Test
    void testMyEventsLink() {
        WebElement myEventsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[3]")));
        myEventsLink.click();
        assertEquals("http://localhost:3000/MyEvents", driver.getCurrentUrl(), "Should navigate to My Events page");
    }
    @Test
    void testFollowersLink() {
        WebElement myScheduleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[4]")));
        myScheduleLink.click();
        assertEquals("http://localhost:3000/followers", driver.getCurrentUrl(), "Should navigate to My Schedule page");
    }

    @Test
    void testMyScheduleLink() {
        WebElement myScheduleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[5]")));
        myScheduleLink.click();
        assertEquals("http://localhost:3000/mySchedule", driver.getCurrentUrl(), "Should navigate to My Schedule page");
    }
   
    @Test
    void testFiltereventsLink() {
        WebElement myScheduleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/aside/button[6]")));
        myScheduleLink.click();
        assertEquals("http://localhost:3000/filterEvents", driver.getCurrentUrl(), "Should navigate to My Schedule page");
    }
    @Test
    void testEventDeleted() {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/button[3]")));
        deleteButton.click();

        
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='eventCard'][contains(., 'Event Name')]")));

        
        List<WebElement> events = driver.findElements(By.xpath("//div[@class='eventCard'][contains(., 'Event Name')]"));
        assertTrue(events.isEmpty(), "The event should no longer be visible after deletion.");
    }

    

}
