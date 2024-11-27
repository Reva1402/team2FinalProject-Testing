package UserProfile;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class EditEvents {

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

        
        WebElement myeventsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/aside/button[3]")));
        myeventsButton.click();

        
        WebElement myeventseditButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/button[2]")));
        myeventseditButton.click();

        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("http://localhost:3000/editevent"), "Failed to navigate to Edit Event page");
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWelcomeMessage() {
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/nav")));
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
    void testupdateEventButtonIsClickable() {

        WebElement createEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/form/button")));
        assertTrue(createEventButton.isEnabled(), "Create Event button should be clickable.");
    }
    
    @Test
    void testUpdateEventName() {
        
        WebElement eventNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventName")));

        
        eventNameField.clear();
        eventNameField.sendKeys("Updated Event Name1");

        
        WebElement updateEventButton = driver.findElement(By.xpath("//*[text()='Update Event']"));

        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateEventButton);
        updateEventButton.click();

        
        wait.until(ExpectedConditions.urlContains("/editevent"));

        
        WebElement updatedEventField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventName")));
        assertEquals("Updated Event Name1", updatedEventField.getAttribute("value"), "Event name should be updated correctly.");
    }
    
    @Test
    void testUpdateEventDate() {
        
        WebElement eventDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventDate")));

        
        String newEventDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        eventDateField.clear();
        eventDateField.sendKeys(newEventDate);

        
        WebElement updateButton = driver.findElement(By.xpath("//*[text()='Update Event']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

        
        wait.until(ExpectedConditions.urlContains("/editevent"));

        
        String updatedDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventDate"))).getAttribute("value");

        
        assertEquals(newEventDate, updatedDate, "Event date should be updated to the new valid date.");
    }
    
    @Test
    void testUpdateEventLocation() {
        
        WebElement locationField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventLocation")));

        
        String newLocation = "ontario";
        locationField.clear();
        locationField.sendKeys(newLocation);

        
        WebElement updateButton = driver.findElement(By.xpath("//*[text()='Update Event']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

        
        wait.until(ExpectedConditions.urlContains("/editevent"));

        
        WebElement updatedLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventLocation")));
        assertEquals(newLocation, updatedLocation.getAttribute("value"), "Event location should be updated correctly.");
    }
    
    @Test
    void testUpdateEventDescription() {
        String newDescription = "Updated event description";
        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventDescription")));
        descriptionField.clear();
        descriptionField.sendKeys(newDescription);

        
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Update Event']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", updateButton);

        
        WebElement updatedDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventDescription")));
        assertEquals(newDescription, updatedDescription.getAttribute("value"), "Event description should be updated correctly.");
    }

    @Test
    void testUpdateEventPrice() {
        String newPrice = "99.99";

        
        WebElement priceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ticketPrice")));
        priceField.clear();
        priceField.sendKeys(newPrice);

        
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Update Event']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", updateButton);

        
        WebElement updatedPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ticketPrice")));
        assertEquals(newPrice, updatedPrice.getAttribute("value"), "Event price should be updated correctly.");
    }

    @Test
    void testUpdateEventImage() {
        
        WebElement imageSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Event Image']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", imageSection);

        
        WebElement fileInputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[6]/inputx`")));

        
        String imagePath = "D:\\Final Project\\pajamaparty1.jpg"; 
        fileInputField.sendKeys(imagePath);

        
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Update Event']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

        
        WebElement updatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'pajamaparty1.jpg')]")));
        assertTrue(updatedImage.isDisplayed(), "Event image should be updated correctly.");
    }

}





