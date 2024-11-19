package event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

class Userdeleteprofile {
	private WebDriver driver;
	
	 
	 private void loaduserprofile() {
	        driver.get("http://localhost:3000/UserProfile");
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
		
		loaduserprofile();
		    
		    
		    WebElement navbar = driver.findElement(By.xpath("/html/body/div/div/div/nav")); 
		    
		    
		    List<WebElement> navLinks = navbar.findElements(By.tagName("a")); 
		    
		    
		    assertEquals(2, navLinks.size(), "Navbar should contain 6 links");
		    
		    
		    assertEquals("profile", navLinks.get(0).getText(), "First link should be 'profile'");
		    assertEquals("postanevent", navLinks.get(1).getText(), "Second link should be 'postanevent'");
		    assertEquals("myevents", navLinks.get(1).getText(), "Second link should be 'myevents'");
		    assertEquals("notification", navLinks.get(1).getText(), "Second link should be 'notification'");
		    assertEquals("followers", navLinks.get(1).getText(), "Second link should be 'followers'");
		    assertEquals("logout", navLinks.get(1).getText(), "Second link should be 'logout'");
		}
		@Test
		void testprofileLink() {
			loaduserprofile();
		    
		    WebElement profile = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[1]"));
		    boolean checking = profile.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testpostaneventLink() {
			loaduserprofile();
		    
		    WebElement postanevent = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[2]"));
		    boolean checking = postanevent.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testmyeventsLink() {
			loaduserprofile();
		    
		    WebElement myevents = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[3]"));
		    boolean checking = myevents.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testnotificationLink() {
			loaduserprofile();
		    
		    WebElement notification = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[4]"));
		    boolean checking = notification.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testfollowersLink() {
			loaduserprofile();
		    
		    WebElement followers = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[5]"));
		    boolean checking = followers.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testlogoutLink() {
			loaduserprofile();
		    WebElement logout = driver.findElement(By.xpath("/html/body/div/div/div/nav/button"));
		    boolean checking = logout.isSelected();
		    Assert.assertNotNull(checking);
		}
		
	
		  @Test
		    void testdeletebutton() {
			  loaduserprofile();
			        WebElement deleteprofile = driver.findElement(By.xpath("/html/body/div/div/div/div/div/button[2]"));
			        deleteprofile.click();
			        Assert.assertTrue(deleteprofile.isSelected());
		    
		    }
		@Test
		void testAboutlinl() {
			loaduserprofile();
		    
		    WebElement about = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
		    boolean checking = about.isSelected();
		    Assert.assertNotNull(checking);
		}
	
		@Test
		void testprivacy() {
			loaduserprofile();
		    
		    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
		    boolean checking = privacy.isSelected();
		    Assert.assertNull(checking);
		}
		@Test
		void testterms() {
			loaduserprofile();
		    
		    WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
		    boolean checking = terms.isSelected();
		    Assert.assertTrue(checking);
		}
		@Test
		void testcontactus() {
			loaduserprofile();
		    
		    WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
		    boolean checking = contact.isSelected();
		    Assert.assertNotNull(checking);
		}
		
}



