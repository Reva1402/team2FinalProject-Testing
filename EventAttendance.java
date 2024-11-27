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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class EventAttendance {

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
	    public void testAttendEvent() {
	        
	        WebElement event = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div[1]"))); 
	        event.click();

	        
	        WebElement attendEventButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div[1]/button[4]"))); 
	        attendEventButton.click();
	        wait.until(ExpectedConditions.urlContains("http://localhost:3000/attendevent/"));

	        
	        WebElement yesRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @value='yes']"))); 
	        yesRadioButton.click();

	        
	    }
	    @Test
	    public void testMyScheduleUpcomingEvents() {
	       
	        WebElement myScheduleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/aside/button[5]"))); 
	        myScheduleButton.click();

	        boolean isMySchedulePageLoadedAndUpcomingEventsVisible = wait.until(ExpectedConditions.urlToBe("http://localhost:3000/mySchedule")) &&
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Upcoming Events')]"))).isDisplayed();

	        
	        assertTrue("The My Schedule page should load and the 'Upcoming Events' section should be visible", isMySchedulePageLoadedAndUpcomingEventsVisible);
	    }
	    @Test
	    void testAboutLinkVisibility() {
	        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[1]")));
	        assertTrue(aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer.");
	    }

	    @Test
	    void testPrivacyPolicyLinkVisibility() {
	        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[2]")));
	        assertTrue(privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer.");
	    }

	    @Test
	    void testTermsAndConditionsLinkVisibility() {
	        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[3]")));
	        assertTrue(termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer.");
	    }

	    @Test
	    void testContactUsLinkVisibility() {
	        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/footer/ul/li[4]")));
	        assertTrue(contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer.");
	    }



	    }


