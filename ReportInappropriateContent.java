package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ReportInappropriateContent {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--force-device-scale-factor=0.7"); 

        
        driver = new ChromeDriver(options);

        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        driver.get("http://localhost:3000/login");

        
        driver.findElement(By.id("email")).sendKeys("nupurg1905@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Nupur@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testReportEventButtonVisibility() {
        
        WebElement reportEventButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        assertTrue("Report Event button should be visible", reportEventButton.isDisplayed());
    }

    @Test
    public void testReportCommentButtonVisibility() {
       
        WebElement reportCommentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/div[2]/ul/li/span[2]/button")));
        assertTrue("Report Comment button should be visible", reportCommentButton.isDisplayed());
    }
    @Test
    public void testReportedByVisibility() {
        
        WebElement reportEventButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));

        
        reportEventButton.click();

        
        boolean isReportedByVisible = wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/")) &&
                                      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/h1"))).isDisplayed();

        
        assertTrue("Report Event button click should navigate to the correct page and 'Reported by' field should be visible", isReportedByVisible);
    }

    @Test
    public void testEventNameVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement eventName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/p[1]/strong")));
        assertTrue("Event name field should be visible", eventName.isDisplayed());
    }
    @Test
    public void testEventCreatedByVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement eventCreatedBy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/p[2]/strong")));
        assertTrue("Event created by field should be visible", eventCreatedBy.isDisplayed());
    }

    @Test
    public void testReasonForReportingVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement reasonForReporting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/label[1]")));
        assertTrue("Reason for reporting field should be visible", reasonForReporting.isDisplayed());
    }

    @Test
    public void testMessageBoxVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/textarea")));
        assertTrue("Message box should be visible", messageBox.isDisplayed());
    }

    @Test
    public void testSubmitReportButtonVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement submitReportButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/button[1]")));
        assertTrue("Submit report button should be visible", submitReportButton.isDisplayed());
    }

    @Test
    public void testCancelButtonVisibility() {
        WebElement reportEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]")));
        reportEventButton.click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/button[2]")));
        assertTrue("Cancel button should be visible", cancelButton.isDisplayed());
    }
    @Test
    public void testSubmitReportAfterSelectingReason() {
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/button[3]"))).click();
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/reportContent/"));

        
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reason")))).selectByVisibleText("Spam");

        
        WebElement submitReportButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/button[1]")));
        submitReportButton.click();

        
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception e) {
            throw new AssertionError("Alert was not handled.", e);
        }

        
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/button[1]")));
        assertTrue("Pop-up should be visible after handling the alert", popup.isDisplayed());
    }





    
}
