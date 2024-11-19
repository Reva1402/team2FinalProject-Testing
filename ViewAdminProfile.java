package event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

class ViewAdminProfile {

	private WebDriver driver;
	  private static WebDriverWait wait;
	
	 
	 private void loadvieweventfeed() {
	        driver.get("http://localhost:3000/AdminProfile");
	    }

	@BeforeEach
	void setUp() throws Exception {
		
		        driver = new ChromeDriver();
		        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		        loadvieweventfeed();
	}

	@AfterEach
	void tearDown() throws Exception {
		  if (driver != null) {
	            driver.quit();
	}
	}

	@Test
	void testprofileLink() {
		loadvieweventfeed();
	    
	    WebElement profileform = driver.findElement(By.xpath("/html/body/div/div/div"));
	    boolean checking = profileform.isSelected();
	    Assert.assertFalse(checking);
	}
    @Test
    void testAdminProfileTitle() {
    	 loadvieweventfeed();
        WebElement titleElement =  driver.findElement(By.xpath("/html/head/title"));
        String actualTitleText = titleElement.getText();
        assertFalse(actualTitleText.contains("Admin Profile"), "Admin profile title should be displayed.");
    }
    @Test
    void testEditProfileButtonIsDisplayed() {
    	 loadvieweventfeed();
        WebElement editButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/button[1]"));
        assertTrue(editButton.isDisplayed(), "Edit Profile button should be visible.");
    }
    @Test
    void testdeleteButtonIsDisplayed() {
    	 loadvieweventfeed();
        WebElement deleteButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/button[2]"));
        assertTrue(deleteButton.isDisplayed(), "DELETE Profile button should be visible.");
    }
}
    


