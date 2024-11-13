package admindashboard;







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

	class Adminhome {

		private WebDriver driver;
		
		 
		 private void loadusermanagementPage() {
		        driver.get("http://localhost:3000/Admins");
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
		    
		    
		    WebElement navbar = driver.findElement(By.xpath("/html/body/div/div[2]/header")); 
		    
		    
		    List<WebElement> navLinks = navbar.findElements(By.tagName("a")); 
		    
		    
		    assertEquals(2, navLinks.size(), "Navbar should contain 2 links");
		    
		    
		    assertEquals("Profile", navLinks.get(0).getText(), "First link should be 'Profile'");
		    assertEquals("logout", navLinks.get(1).getText(), "Second link should be 'logout'");
		}
		@Test
		void testProfileLink() {
			loadusermanagementPage();
		    
		    WebElement profile = driver.findElement(By.xpath("/html/body/div/div[2]/header/div/a"));
		    boolean checking = profile.isSelected();
		    Assert.assertFalse(checking);
		}
		@Test
		void testLogoutLink() {
			loadusermanagementPage ();
		    
		    WebElement logout = driver.findElement(By.xpath("/html/body/div/div[2]/header/div/button"));
		    boolean checking = logout.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testsearchLink() {
			loadusermanagementPage ();
		    
		    WebElement search = driver.findElement(By.xpath("/html/body/div/div[2]/header/div/input"));
		    boolean checking = search.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testtable() {
			loadusermanagementPage();
		    
		    WebElement table = driver.findElement(By.xpath("/html/body/div/div[2]/div/div"));
		    boolean checking = table.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testsidebar() {
			loadusermanagementPage();
		    
		    WebElement view = driver.findElement(By.xpath("/html/body/div/div[2]/div/aside"));
		    boolean checking = view.isSelected();
		}
		   
		@Test
		void testaboutlink() {
			loadusermanagementPage();
		    
		    WebElement about = driver.findElement(By.xpath("/html/body/div/div[2]/footer/div/a[1]"));
		    boolean checking = about.isSelected();
		    Assert.assertNotNull(checking);
		} 
		 
		@Test
		void testprivacylink() {
			loadusermanagementPage();
		    
		    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div[2]/footer/div/a[2]"));
		    boolean checking = privacy.isSelected();
		    Assert.assertTrue(checking);
		}
		
		@Test
		void testtermslinl() {
			loadusermanagementPage();
		    
		    WebElement terms = driver.findElement(By.xpath("/html/body/div/div[2]/footer/div/a[3]"));
		    boolean checking = terms.isSelected();
		    Assert.assertNotNull(checking);
		}
		@Test
		void testcontact() {
			loadusermanagementPage();
		    
		    WebElement privacy = driver.findElement(By.xpath("/html/body/div/div[2]/footer/div/a[4]"));
		    boolean checking = privacy.isSelected();
		    Assert.assertNull(checking);
		}
		
		
	



}
