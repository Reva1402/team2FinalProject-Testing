package finaliteration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

class iteration1st {
	private WebDriver driver;
	
	 
	 private void loadHomePage() {
	        driver.get("http://localhost:3000/LoginPage");
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
	    loadHomePage();
	    
	    
	    WebElement navbar = driver.findElement(By.xpath("/html/body/div/div/h1")); 
	    boolean checking = navbar.isSelected();
	    Assert.assertFalse(checking);
	}
	    
	    
	  
	@Test
	void testuserportalLink() {
	    loadHomePage();
	    
	    WebElement userportal = driver.findElement(By.xpath("/html/body/div/div/div[1]/a[1]"));
	    boolean checking = userportal.isSelected();
	    Assert.assertFalse(checking);
	}
	@Test
	void testAdminportalLink() {
	    loadHomePage();
	    
	    WebElement adminportal = driver.findElement(By.xpath("/html/body/div/div/div[1]/a[2]"));
	    boolean checking = adminportal.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testmoderatorlinkBar() {
	    loadHomePage();
	    
	    WebElement moderatorportal = driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]"));
	    boolean checking = moderatorportal.isSelected();
	    Assert.assertNotNull(checking);
	}

@Test
void testsemail() {
	loadHomePage();
	  WebElement email = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/input"));
	    boolean checking = email.isSelected();
	    Assert.assertTrue(checking);
	
}
@Test
void testpassword() {
	loadHomePage();
	  WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/input"));
	    boolean checking = password.isSelected();
	    Assert.assertFalse(checking);
	
}
@Test
void testsubmitbutton() {
	 loadHomePage();
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        submitButton.click();

        // Assertion: Verify the radio button is selected
        Assert.assertTrue(submitButton.isSelected());

	
}


	@Test
	void testforgotpasslinl() {
	    loadHomePage();
	    
	    WebElement forgotpass = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a[1]"));
	    boolean checking = forgotpass.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testsignuplinl() {
	    loadHomePage();
	    
	    WebElement signuplink = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a[2]"));
	    boolean checking = signuplink.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testlinks() {
	    loadHomePage();
	    
	    WebElement links = driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
	    boolean checking = links.isSelected();
	    Assert.assertNull(checking);
	}
	}
	
//	@Test
//	void testprivacy() {
//	    loadHomePage();
//	    
//	    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/footer/div/a[2]"));
//	    boolean checking = privacy.isSelected();
//	    Assert.assertNull(checking);
//	}
//	@Test
//	void testterms() {
//	    loadHomePage();
//	    
//	    WebElement terms = driver.findElement(By.xpath("/html/body/div/div/footer/div/a[3]"));
//	    boolean checking = terms.isSelected();
//	    Assert.assertTrue(checking);
//	}
//	@Test
//	void testcontactus() {
//	    loadHomePage();
//	    
//	    WebElement contact = driver.findElement(By.xpath("/html/body/div/div/footer/div/a[4]"));
//	    boolean checking = contact.isSelected();
//	    Assert.assertNotNull(checking);
//	}
//	
	
	



	      