package Testingprojectfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

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

public class ReportedEventManagement {
	private WebDriver driver;

	 private void loadeventmoderatorPage() {
	        driver.get("http://localhost:3000/moderatorhomepage");
	    }

	@BeforeEach
	void setUp() throws Exception {
		
		        driver = new ChromeDriver();
		        loadeventmoderatorPage();
	}

	@AfterEach
	void tearDown() throws Exception {
		  if (driver != null) {
	            driver.quit();
	}
	}
	@Test
	void testtable() {
		
	    
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/table"));
	    boolean checking = table.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testViewButton() {
	
	    
	    WebElement view = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[1]"));
	    boolean checking = view.isSelected();
	}
	   
	@Test
	void testwarningbutton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[2]")
	    ));
	    boolean checking = warning.isSelected();
	    Assert.assertNotNull(checking);
	}
	 
	@Test
	void testsuspendbutton() {
	    
	    WebElement suspend = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[3]"));
	    boolean checking = suspend.isSelected();
	    Assert.assertTrue(checking);
	}
	@Test
	void testremovebutton() {
	    
	    WebElement remove = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[4]"));
	    boolean checking = remove.isSelected();
	    Assert.assertTrue(checking);
	}
    @Test
    void testSidebarLinks() {
        
        List<WebElement> sidebarLinks = driver.findElements(By.xpath("//aside[@class='sidebar']//a"));
        assertEquals(5, sidebarLinks.size(), "Sidebar should contain 5 links");

        // Validate each link's text
        assertEquals("User Management", sidebarLinks.get(0).getText(), "First link should be 'User Management'");
        assertEquals("Moderator Management", sidebarLinks.get(1).getText(), "Second link should be 'Moderator Management'");
        assertEquals("Suspended Resources", sidebarLinks.get(2).getText(), "Third link should be 'Suspended Resources'");
        assertEquals("Content Management", sidebarLinks.get(3).getText(), "Fourth link should be 'Content Management'");
        assertEquals("Security Rules", sidebarLinks.get(4).getText(), "Fifth link should be 'Security Rules'");
    }
    @Test
	void testAboutlinl() {
		
	    
	    WebElement about = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[1]"));
	    boolean checking = about.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testprivacy() {
		
	    
	    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[2]"));
	    boolean checking = privacy.isSelected();
	    Assert.assertNull(checking);
	}
	@Test
	void testterms() {
		
	    
	    WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[3]"));
	    boolean checking = terms.isSelected();
	    Assert.assertFalse(checking);
	}
	@Test
	void testcontactus() {
		
	    
	    WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[4]"));
	    boolean checking = contact.isSelected();
	    Assert.assertNotNull(checking);
	}
	

}
