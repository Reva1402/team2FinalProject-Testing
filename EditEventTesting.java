package EditEventtesting;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class EditEventTesting {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String baseUrl = "http://localhost:3000";

    @BeforeClass
    public static void setUpClass() {
        // Set up WebDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set to 50 seconds
    }

    @Before
    public void setUp() {
        login();
        navigateToEditEventPage();
    }

    private void login() {
        driver.get(baseUrl + "/login");
        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("rachnaaulakh@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456789");

        // Click the login button
        driver.findElement(By.cssSelector(".btn.btn-primary.login-btn")).click();

        // Wait for redirection to the user homepage
        wait.until(ExpectedConditions.urlContains("userhomepage"));
    }

    private void navigateToEditEventPage() {
        // Navigate to "My Events" page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[3]"))).click();

        // Wait for the Edit button to become clickable and click it
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Edit')]")));
        editButton.click();

        // Confirm that we are on the edit page by checking for a unique element (e.g., event name field)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("eventName")));
    }
    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testEventDetailsDisplayedCorrectly() {
        // Check if form fields are correctly populated on the edit page
        WebElement eventName = driver.findElement(By.name("eventName"));
        WebElement eventDate = driver.findElement(By.name("eventDate"));
        WebElement eventLocation = driver.findElement(By.name("eventLocation"));
        WebElement eventDescription = driver.findElement(By.name("eventDescription"));
        WebElement ticketPrice = driver.findElement(By.name("ticketPrice"));

        assertTrue("Event Name is empty.", !eventName.getAttribute("value").isEmpty());
        assertTrue("Event Date is empty.", !eventDate.getAttribute("value").isEmpty());
        assertTrue("Event Location is empty.", !eventLocation.getAttribute("value").isEmpty());
        assertTrue("Event Description is empty.", !eventDescription.getText().isEmpty());
        assertTrue("Ticket Price is empty.", !ticketPrice.getAttribute("value").isEmpty());
    }

    @Test
    public void testUpdatingEventName() {
        WebElement eventName = driver.findElement(By.name("eventName"));
        eventName.clear();
        eventName.sendKeys("Updated Event Name");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
        WebElement popup = driver.findElement(By.className("popup"));
        assertTrue("Popup message did not appear after update.", popup.isDisplayed());
        assertTrue("Success message not displayed.", popup.getText().contains("Event updated successfully!"));
    }

    @Test
    public void testLogoutFunctionality() {
        driver.findElement(By.className("logout-btn")).click();
        wait.until(ExpectedConditions.urlContains("login"));
        assertTrue("User was not redirected to login page after logout.", driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void testImageUploadOptionExists() {
        // Wait for the image upload field to become visible on the page
        WebElement uploadField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='file']")));

        // Assert that the upload field is displayed, confirming its presence
        assertTrue("Image upload option is not present.", uploadField.isDisplayed());
    }

    @Test
    public void testRequiredFieldValidation() {
    	driver.findElement(By.name("eventName")).clear(); // Leave event name empty
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Confirm no validation error is displayed for eventName field
        assertTrue("Unexpected validation error for non-mandatory event name field.",
                   driver.findElements(By.className("error")).isEmpty());
    }

    @Test
    public void testFooterLinks() {
        WebElement aboutLink = driver.findElement(By.linkText("About"));
        aboutLink.click();
        wait.until(ExpectedConditions.urlContains("/about"));
        assertTrue("About page did not open.", driver.getCurrentUrl().contains("/about"));

        driver.navigate().back();
        WebElement privacyPolicyLink = driver.findElement(By.linkText("Privacy Policy"));
        privacyPolicyLink.click();
        wait.until(ExpectedConditions.urlContains("/privacypolicy"));
        assertTrue("Privacy Policy page did not open.", driver.getCurrentUrl().contains("/privacypolicy"));

        driver.navigate().back();
        WebElement termsLink = driver.findElement(By.linkText("Terms and Conditions"));
        termsLink.click();
        wait.until(ExpectedConditions.urlContains("/termsandconditions"));
        assertTrue("Terms and Conditions page did not open.", driver.getCurrentUrl().contains("/termsandconditions"));

        driver.navigate().back();
        WebElement contactUsLink = driver.findElement(By.linkText("Contact Us"));
        contactUsLink.click();
        wait.until(ExpectedConditions.urlContains("/contactus"));
        assertTrue("Contact Us page did not open.", driver.getCurrentUrl().contains("/contactus"));
    }

@Test
public void testUpdatingEventDateAndTime() {
    WebElement eventDate = driver.findElement(By.name("eventDate"));
    WebElement eventTime = driver.findElement(By.name("eventTime"));

    eventDate.clear();
    eventDate.sendKeys("2023-12-31"); // New test date
    eventTime.clear();
    eventTime.sendKeys("15:30");      // New test time

    driver.findElement(By.cssSelector("button[type='submit']")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
    WebElement popup = driver.findElement(By.className("popup"));
    assertTrue("Popup message did not appear after update.", popup.isDisplayed());
    assertTrue("Success message not displayed.", popup.getText().contains("Event updated successfully!"));
}
@Test
public void testUpdatingEventLocation() {
    WebElement eventLocation = driver.findElement(By.name("eventLocation"));
    eventLocation.clear();
    eventLocation.sendKeys("New Location Test");

    driver.findElement(By.cssSelector("button[type='submit']")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
    WebElement popup = driver.findElement(By.className("popup"));
    assertTrue("Popup message did not appear after update.", popup.isDisplayed());
    assertTrue("Success message not displayed.", popup.getText().contains("Event updated successfully!"));
}
    @Test
    public void testUnauthorizedAccessPopup() {
        driver.get("http://localhost:3000/editevent/9wsAo2hJcSYweNwLSD60vhIIYX23"); // Use actual unauthorized event ID

        // Wait for the popup and verify it contains the unauthorized message
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
        String messageText = popupMessage.getText();
        assertTrue("Unauthorized access popup not displayed or incorrect text.", 
                messageText.contains("You do not have permission to edit this event."));
    }

@Test
public void testEmptyTicketPriceValidation() {
	driver.findElement(By.name("ticketPrice")).clear(); // Leave ticket price empty
    driver.findElement(By.cssSelector("button[type='submit']")).click();

    // Verify no error message is displayed for the empty ticket price
    assertTrue("Unexpected error message for empty ticket price.",
               driver.findElements(By.className("error")).stream()
               .noneMatch(error -> error.getText().contains("Ticket Price is required")));
}
@Test
public void testUpdatingEventDescription() {
    WebElement eventDescription = driver.findElement(By.name("eventDescription"));
    eventDescription.clear();
    eventDescription.sendKeys("This is a test description update.");

    driver.findElement(By.cssSelector("button[type='submit']")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
    WebElement popup = driver.findElement(By.className("popup"));
    assertTrue("Popup message did not appear after update.", popup.isDisplayed());
    assertTrue("Success message not displayed.", popup.getText().contains("Event updated successfully!"));
}
@Test
public void testLogoutFromEditEventPage() {
    WebElement logoutButton = driver.findElement(By.className("logout-btn"));
    logoutButton.click();

    wait.until(ExpectedConditions.urlContains("login"));
    assertTrue("User was not redirected to login page after logout.", driver.getCurrentUrl().contains("/login"));
}
    

@Test
public void testHeaderNavigationLinks() {
    WebElement profileLink = driver.findElement(By.linkText("Profile"));
    profileLink.click();
    wait.until(ExpectedConditions.urlContains("/viewProfile"));
    assertTrue("Profile page did not open.", driver.getCurrentUrl().contains("/viewProfile"));
    driver.navigate().back();

    WebElement postEventLink = driver.findElement(By.linkText("Post An Event"));
    postEventLink.click();
    wait.until(ExpectedConditions.urlContains("/createevent"));
    assertTrue("Create Event page did not open.", driver.getCurrentUrl().contains("/createevent"));
    driver.navigate().back();

    WebElement myEventsLink = driver.findElement(By.linkText("My Events"));
    myEventsLink.click();
    wait.until(ExpectedConditions.urlContains("/myevents"));
    assertTrue("My Events page did not open.", driver.getCurrentUrl().contains("/myevents"));
}
}




