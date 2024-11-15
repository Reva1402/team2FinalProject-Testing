package Testingprojectfinal;

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

class Adminusermanagement {

	private WebDriver driver;
	
	 
	 private void loadusermanagementPage() {
	        driver.get("http://localhost:3000/users");
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
		loadusermanagementPage();
	    
	    
	    WebElement navbar = driver.findElement(By.xpath("/html/body/div/div/div/header")); 
	    
	    
	    List<WebElement> navLinks = navbar.findElements(By.tagName("a")); 
	    
	    
	    assertEquals(2, navLinks.size(), "Navbar should contain 2 links");
	    
	    
	    assertEquals("Profile", navLinks.get(0).getText(), "First link should be 'Profile'");
	    assertEquals("logout", navLinks.get(1).getText(), "Second link should be 'logout'");
	}

	@Test
	void testProfileLink() {
		loadusermanagementPage();
	    
	    WebElement profile = driver.findElement(By.xpath("/html/body/div/div/div/header/nav/a"));
	    boolean checking = profile.isSelected();
	    Assert.assertFalse(checking);
	}

	@Test
	void testLogoutLink() {
		loadusermanagementPage ();
    
	    WebElement profile = driver.findElement(By.xpath("/html/body/div/div/div/header/nav/button"));
	    boolean checking = profile.isSelected();
	    Assert.assertNotNull(checking);
	}

	
	@Test
	void testtable() {
		loadusermanagementPage();
	    
	    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div/div[2]"));
	    boolean checking = table.isSelected();
	    Assert.assertNotNull(checking);
	}

	@Test
	void testViewButton() {
	    loadusermanagementPage();
	    
	    WebElement view = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[2]/td[3]/button[1]"));
	    boolean checking = view.isDisplayed() && view.isEnabled();
	    Assert.assertTrue(checking);
	}
	@Test
	void testeditButton() {
	    loadusermanagementPage();
	    
	    WebElement edit = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[2]/td[3]/button[2]"));
	    boolean checking = edit.isDisplayed() && edit.isEnabled();
	    Assert.assertTrue(checking);
	}
	@Test
	void testdeleteButton() {
	    loadusermanagementPage();
	    
	    WebElement delete = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[2]/td[3]/button[4]"));
	    boolean checking = delete.isDisplayed() && delete.isEnabled();
	    Assert.assertTrue(checking);
	}
	@Test
	void testsuspendButton() {
	    loadusermanagementPage();
	    
	    WebElement suspend = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[2]/td[3]/button[3]"));
	    boolean checking = suspend.isDisplayed() && suspend.isEnabled();
	    Assert.assertTrue(checking);
	}
	@Test
	void testsidebar() {
		loadusermanagementPage();
	    
	    WebElement sidebar = driver.findElement(By.xpath("/html/body/div/div/div/div[1]"));
	    boolean checking = sidebar.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testusermanagementlinl() {
		loadusermanagementPage();
	    
	    WebElement usermanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[1]"));
	    boolean checking = usermanage.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testmoderatormanagementlinl() {
		loadusermanagementPage();
	    
	    WebElement usermanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[2]"));
	    boolean checking = usermanage.isSelected();
	    Assert.assertNotNull(checking);
	}

	@Test
	void testsuspendedlinl() {
		loadusermanagementPage();
	    
	    WebElement suspended = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[3]"));
	    boolean checking = suspended.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testcontentlinl() {
		loadusermanagementPage();
	    
	    WebElement content = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[4]"));
	    boolean checking = content.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testsecuirtylinl() {
		loadusermanagementPage();
	    
	    WebElement security = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[5]"));
	    boolean checking = security.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testprivacy() {
		loadusermanagementPage();
	    
	    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[2]"));
	    boolean checking = privacy.isSelected();
	    Assert.assertNotNull(checking);
	}
	@Test
	void testterms() {
		loadusermanagementPage();
	    
	    WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[3]"));
	    boolean checking = terms.isSelected();
	    Assert.assertFalse(checking);
	}
	@Test
	void testcontactus() {
		loadusermanagementPage();
	    
	    WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/p[4]"));
	    boolean checking = contact.isSelected();
	    Assert.assertNotNull(checking);
	}
	}
//	
//
//
//}
