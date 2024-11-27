package UserProfile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class createevent {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("http://localhost:3000/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("nupurg1905@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("Nupur@123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]"))).click();

        //wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
        WebElement postaneventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/aside/button[2]")));
        postaneventButton.click();
        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/createevent"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testWelcomeMessage() {
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/nav/div")));
        String actualText = welcomeMessage.getText();
        assertTrue(actualText.startsWith("Hi, "), "Welcome message should start with 'Hi, '");
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
    void testLogoutButtonVisibility() {
       
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/nav/button")));
        
        
        assertTrue(logoutButton != null && logoutButton.isDisplayed(), "Logout button should be visible and displayed on the page");
    }
    @Test
    void testEventNameFieldIsVisible() {

        WebElement eventNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/label")));
        assertTrue(eventNameField.isDisplayed(), "Event name field should be visible.");
    }
    @Test
    void testEventDateFieldValidation() {

        WebElement eventDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[2]/label")));
        assertTrue(eventDateField.isDisplayed(), "Event date field should be visible.");

        
    }
    @Test
    void testEventLocationFieldIsVisible() {

        WebElement eventLocationField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[3]/label")));
        assertTrue(eventLocationField.isDisplayed(), "Event location field should be visible.");
    }
    @Test
    void testEventDescriptionFieldIsVisible() {

        WebElement eventDescriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[4]/label")));
        assertTrue(eventDescriptionField.isDisplayed(), "Event description field should be visible.");
    }
    @Test
    void testTicketPriceFieldIsVisible() {

        WebElement ticketPriceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[5]/label")));
        assertTrue(ticketPriceField.isDisplayed(), "Ticket price field should be visible.");
    }
    @Test
    void testUploadEventImageFieldIsVisible() {

        WebElement uploadImageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[6]/input")));
        assertTrue(uploadImageField.isDisplayed(), "Space to upload event images should be available.");
    }
    @Test
    void testCreateEventButtonIsClickable() {

        WebElement createEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/form/button")));
        assertTrue(createEventButton.isEnabled(), "Create Event button should be clickable.");
    }

    
    @Test
    void testFooterLinksVisibility() {
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer");

        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer");

        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer");

        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer");
    }
}