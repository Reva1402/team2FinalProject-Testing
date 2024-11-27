package AdminPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteEvents {
    private WebDriver driver;

    @Before
    public void setUp() {
        
        driver = new ChromeDriver();

        
        driver.get("http://localhost:3000/login");

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("madhavjariwala55@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminDashboard"));

        WebElement contentmanagementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/aside/a[5]")));
        contentmanagementLink.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Admincontentmanagement"));
    }

    @Test
    public void testDeleteEventconfirm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[3]")));
        deleteButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("First Alert Text: " + alertText);

        assertTrue("First alert text does not match expected message", alertText.contains("Are you sure you want to delete this event?"));

        alert.accept();

        Alert successAlert = wait.until(ExpectedConditions.alertIsPresent());
        String successAlertText = successAlert.getText();
        System.out.println("Second Alert Text: " + successAlertText);

        assertTrue("Second alert text does not match expected message", successAlertText.contains("Event deleted successfully"));

        successAlert.accept();
    }

    	
    	
    	@Test
    	public void testDeleteEventCancel() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	    WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[3]")));
    	    deleteButton.click();

    	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    	    String alertText = alert.getText();
    	    System.out.println("Alert Text: " + alertText);

    	    assertTrue("Alert text does not match expected message", alertText.contains("Are you sure you want to delete this event?"));

    	    alert.dismiss();

    	    WebElement eventRow = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[1]"));
    	    assertNotNull("User row should still exist", eventRow);
    	}
    
    @Test
    public void testDeleteRandomEventAndCheckRowCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        List<WebElement> rowsBefore = driver.findElements(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr"));
        int rowCountBefore = rowsBefore.size();
        assertTrue("No events found to delete", rowCountBefore > 0);

        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[3]")));
        deleteButton.click();

        
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        
        Alert successAlert = wait.until(ExpectedConditions.alertIsPresent());
        successAlert.accept();

        
        List<WebElement> rowsAfter = wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
            By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr"), rowCountBefore));
        int rowCountAfter = rowsAfter.size();

        
        assertTrue("Row count did not decrease after deletion", rowCountAfter < rowCountBefore);
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



    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



