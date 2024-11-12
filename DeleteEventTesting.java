package DeleteEvent;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class DeleteEventTesting {

    private static WebDriver driver;
    private static String baseUrl = "http://localhost:3000";
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUpClass() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Replace with the actual path
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
    }

    @Before
    public void setUp() {
        loginAndNavigateToMyEvents();
    }

    public void loginAndNavigateToMyEvents() {
        driver.get(baseUrl + "/login");

        // Wait for the email field, then input email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupurg1905@gmail.com");  // Replace with test email

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");  // Replace with test password

        // Wait for and click on the login button using CSS selector for compound classes
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.login-btn")));
        loginButton.click();

        // Wait for My Events link to be visible and click on it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[3]"))).click();
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Test Case 1: Verify Delete Button is Visible and Enabled
    @Test
    public void testDeleteButtonVisibleEnabled() {
        // Find delete buttons on the page
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div/div/button[3]"));
        
        if (!deleteButtons.isEmpty()) {
            WebElement deleteButton = deleteButtons.get(0);
            assertTrue("Delete button is not visible.", deleteButton.isDisplayed());
            assertTrue("Delete button is not enabled.", deleteButton.isEnabled());
        } else {
            System.out.println("No events available to check delete button visibility.");
        }
    }



    // Test Case 2: Verify Successful Event Deletion
    @Test
    public void testSuccessfulEventDeletion() {
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[contains(text(), 'Delete')]"));
        
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();

            // Verify that the event was deleted from the page
            List<WebElement> updatedDeleteButtons = driver.findElements(By.xpath("//button[contains(text(), 'Delete')]"));
            assertTrue("Event was not deleted.", updatedDeleteButtons.size() < deleteButtons.size());
        } else {
            System.out.println("No events available to delete.");
        }}

    // Test Case 3: Verify Canceling Event Deletion
    @Test
    public void testCancelEventDeletion() throws InterruptedException {
        // Locate all delete buttons
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[contains(text(), 'Delete')]"));
        
        if (!deleteButtons.isEmpty()) {
            // Click the first delete button
            deleteButtons.get(0).click();
            
            // Wait to observe the alert
            //Thread.sleep(50000000); // 2-second delay to observe the alert popup

            // Handle the alert by dismissing (canceling the deletion)
            Alert alert = driver.switchTo().alert();
            alert.dismiss();

            // Wait to observe the page after canceling
            //Thread.sleep(50000000000); // 2-second delay to observe the page after canceling deletion

            // Verify that the event was not deleted from the page
            List<WebElement> updatedDeleteButtons = driver.findElements(By.xpath("//button[contains(text(), 'Delete')]"));
            assertTrue("Event was deleted after canceling.", updatedDeleteButtons.size() == deleteButtons.size());
            
            // Additional wait to observe final state
            Thread.sleep(15000); // 2-second delay to observe the page after the test completes
        } else {
            System.out.println("No events available to test deletion cancelation.");
        }}
    

    // Test Case 4: Verify Alert Message Text on Delete Confirmation
    @Test
    public void testDeleteConfirmationMessage() throws InterruptedException {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Delete')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);

        Thread.sleep(2000); // Pause to allow the popup to appear

        try {
            Alert alert = driver.switchTo().alert();
            assertTrue("Incorrect alert text.", alert.getText().equals("Are you sure you want to delete this event?"));
            Thread.sleep(3000); // Observe the alert
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            WebElement modalText = driver.findElement(By.xpath("//*[contains(text(), 'Are you sure you want to delete this event?')]"));
            assertTrue("Confirmation modal did not appear or text is incorrect.", modalText.isDisplayed());
            Thread.sleep(3000); // Observe the modal
            driver.findElement(By.xpath("//button[contains(text(), 'Cancel') or contains(text(), 'No')]")).click();
        }
    }


    // Test Case 5: Verify Event is Removed from the List after Deletion
    
    @Test
    public void testEventRemovedFromListAfterDeletion() throws InterruptedException {
        List<WebElement> eventsBeforeDeletion = driver.findElements(By.className("event-card"));
        
        if (eventsBeforeDeletion.isEmpty()) {
            System.out.println("No events available to verify deletion.");
            return;
        }

        WebElement deleteButton = eventsBeforeDeletion.get(0).findElement(By.xpath(".//button[contains(text(), 'Delete')]"));

        // Scroll to and click the delete button using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", deleteButton);

        // Wait for and accept the alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        assertTrue("Unexpected alert text.", alert.getText().contains("Are you sure you want to delete this event?"));
        alert.accept();

        // Confirm event count decreased after deletion
        Thread.sleep(2000); // Wait briefly for deletion to complete
        List<WebElement> eventsAfterDeletion = driver.findElements(By.className("event-card"));
        assertTrue("Event was not removed from the list after deletion.", eventsAfterDeletion.size() < eventsBeforeDeletion.size());

        System.out.println("Event successfully deleted.");
        Thread.sleep(5000); // Optional delay to observe final state
    }
}