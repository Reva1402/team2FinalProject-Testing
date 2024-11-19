package event;

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

class userViewprofile {

	private WebDriver driver;
	
	 
	 private void loadviewprofile() {
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
		
		loadviewprofile();
		    
		    
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
			loadviewprofile();
		    
		    WebElement profile = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[1]"));
		    boolean checking = profile.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testpostaneventLink() {
			loadviewprofile();
		    
		    WebElement postanevent = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[2]"));
		    boolean checking = postanevent.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testmyeventsLink() {
			loadviewprofile();
		    
		    WebElement myevents = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[3]"));
		    boolean checking = myevents.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testnotificationLink() {
			loadviewprofile();
		    
		    WebElement notification = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[4]"));
		    boolean checking = notification.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testfollowersLink() {
			loadviewprofile();
		    
		    WebElement followers = driver.findElement(By.xpath("/html/body/div/div/div/nav/ul/li[5]"));
		    boolean checking = followers.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testlogoutLink() {
			loadviewprofile();
		    WebElement logout = driver.findElement(By.xpath("/html/body/div/div/div/nav/button"));
		    boolean checking = logout.isSelected();
		    Assert.assertNotNull(checking);
		}
	
		@Test
		void testeditbutton() {
			loadviewprofile();
		        WebElement editbutton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/button[2]"));
		        editbutton.click();

		        Assert.assertNotNull(editbutton.isSelected());
         }
		@Test
		void testdeletebutton() {
			loadviewprofile();
		        WebElement deletebutton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/button[3]"));
		        deletebutton.click();

		        Assert.assertNotNull(deletebutton.isSelected());
         }
		@Test
		void testAboutlinl() {
			loadviewprofile();
		    
		    WebElement about = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
		    boolean checking = about.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testprivacy() {
			loadviewprofile();
		    
		    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
		    boolean checking = privacy.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testterms() {
			loadviewprofile();
		    
		    WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
		    boolean checking = terms.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testcontactus() {
			loadviewprofile();
		    
		    WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
		    boolean checking = contact.isSelected();
		    Assert.assertNotNull(checking);
		}
		
}
