package FinalProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

class CreateEventTest {
    private WebDriver driver;

    private void loadCreateEventPage() {
        driver.get("http://localhost:3000/createevent");
    }

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testNavbar() {
        loadCreateEventPage();
        WebElement navbar = driver.findElement(By.xpath("/html/body/div/div/div/nav"));
        boolean checking = navbar.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testEventNameField() {
        loadCreateEventPage();
        WebElement eventName = driver.findElement(By.xpath("/html/body/div/div/div/form/div[1]/input"));
        boolean checking = eventName.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testEventDateField() {
        loadCreateEventPage();
        WebElement eventDate = driver.findElement(By.xpath("/html/body/div/div/div/form/div[2]/input"));
        boolean checking = eventDate.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testEventTimeField() {
        loadCreateEventPage();
        WebElement eventTime = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/input"));
        boolean checking = eventTime.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testLocationField() {
        loadCreateEventPage();
        WebElement location = driver.findElement(By.xpath("/html/body/div/div/div/form/div[4]/input"));
        boolean checking = location.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testTicketPriceField() {
        loadCreateEventPage();
        WebElement ticketPrice = driver.findElement(By.xpath("/html/body/div/div/div/form/div[6]/input"));
        boolean checking = ticketPrice.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testEventImageUploadField() {
        loadCreateEventPage();
        WebElement eventImageUpload = driver.findElement(By.xpath("/html/body/div/div/div/form/div[7]/input"));
        boolean checking = eventImageUpload.isDisplayed();
        Assert.assertTrue(checking);
    }

    @Test
    void testPageNavigationToAbout() {
    	loadCreateEventPage();

        WebElement aboutLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
        boolean checking = aboutLink.isSelected();
	    Assert.assertNotNull(checking);
    }
	
    @Test
    void testPageNavigationToPrivacyPolicy() {
    	loadCreateEventPage();

        WebElement privacyPolicyLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
        boolean checking = privacyPolicyLink.isSelected();
	    Assert.assertNotNull(checking);
    }
    
    @Test
    void testPageNavigationToTnC() {
    	loadCreateEventPage();

        WebElement TnCLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
        boolean checking = TnCLink.isSelected();
	    Assert.assertNotNull(checking);
    }
    
    @Test
    void testPageNavigationToContactUs() {
    	loadCreateEventPage();

        WebElement ContactUsLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
        boolean checking = ContactUsLink.isSelected();
	    Assert.assertNotNull(checking);
    }
    
}
