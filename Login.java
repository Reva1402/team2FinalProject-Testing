package FinalProject;

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

class Login {
	private WebDriver driver;
	
	 
	 private void loadHomePage() {
	        driver.get("http://localhost:3000/login");
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
	    WebElement navbar = driver.findElement(By.xpath("/html/body/div/div/div/nav")); 
	    boolean checking = navbar.isSelected();
	    Assert.assertFalse(checking);
	}
	 
@Test
void testemail() {
	loadHomePage();
	  WebElement email = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/div[1]/input"));
	    boolean checking = email.isSelected();
	    Assert.assertTrue(checking);	
}

@Test
void testpassword() {
	loadHomePage();
	  WebElement password = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/div[2]/input"));
	    boolean checking = password.isSelected();
	    Assert.assertFalse(checking);
	
}

@Test
void testsubmitbutton() {
	 loadHomePage();
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/div[3]/button[1]"));
        submitButton.click();

        Assert.assertTrue(submitButton.isSelected());

	
}

	@Test
	void testforgotpasslinl() {
	    loadHomePage();
	    
	    WebElement forgotpass = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/div[3]/button[2]"));
	    boolean checking = forgotpass.isSelected();
	    Assert.assertNotNull(checking);
	}
	
	@Test
	void testsignuplinl() {
	    loadHomePage();
	    
	    WebElement signuplink = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/div[3]/button[3]"));
	    boolean checking = signuplink.isSelected();
	    Assert.assertNotNull(checking);
	}

    @Test
    void testPageNavigationToAbout() {
    	loadHomePage();

        WebElement aboutLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
        boolean checking = aboutLink.isSelected();
	    Assert.assertNotNull(checking);
    }
	
    @Test
    void testPageNavigationToPrivacyPolicy() {
    	loadHomePage();

        WebElement privacyPolicyLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
        boolean checking = privacyPolicyLink.isSelected();
	    Assert.assertNotNull(checking);
    }
    
    @Test
    void testPageNavigationToTnC() {
    	loadHomePage();

        WebElement TnCLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
        boolean checking = TnCLink.isSelected();
	    Assert.assertNotNull(checking);
    }
    
    @Test
    void testPageNavigationToContactUs() {
    	loadHomePage();

        WebElement ContactUsLink = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
        boolean checking = ContactUsLink.isSelected();
	    Assert.assertNotNull(checking);
    }	
	}
