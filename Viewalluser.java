package AdminPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Viewalluser {

	

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("madhavjariwala55@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();
       
       
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminDashboard"));

      
        WebElement userlink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[2]")));
        userlink.click();
        
    }
    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testuserdashboardnavbar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardHeading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header")));
        assertEquals("Admin one", dashboardHeading.getText());
    }
    @Test
    public void testprofilenavbar()throws InterruptedException { 
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/a")));
        assertNotNull("profile", profile.getText());
    }
    @Test
    public void testlogoutnavbar() throws InterruptedException{ 
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/button")));
        assertNotNull("logout clickable", logout.getText());
    }
    @Test
    public void testsidebar()throws InterruptedException { 
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sidebar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[1]")));
        assertNotEquals("sidebar visible", sidebar.getText());
    }
    @Test
    public void testdashboardlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[1]"));
    	assertNotNull(link.isDisplayed());

    }
    @Test
    public void testusermanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement usermanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[2]"));
    	assertNotNull(usermanage.isDisplayed());

    }
    @Test
    public void testmoderatormanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement moderatormanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[3]"));
    	assertNotNull(moderatormanage.isDisplayed());

    }
    @Test
    public void testsuspendedmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement suspendedmanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[4]"));
    	assertNotNull(suspendedmanage.isDisplayed());

    }
    @Test
    public void testcontentdmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement contentdmanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[5]"));
    	assertNotNull(contentdmanage.isDisplayed());

    }
    @Test
    public void testsupportdmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement supportdmanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/a[6]"));
    	assertNotNull(supportdmanage.isDisplayed());

    }
    @Test
    public void testsecuritymanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement securitymanage = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/h4/a"));
    	assertNotNull(securitymanage.isDisplayed());
    }

    
   @Test
    public void testAboutfooterlink()throws InterruptedException{
   	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   	WebElement About = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
   	assertNotNull(About.isDisplayed());

  }
    @Test
   public void testprivacyfooterlink()throws InterruptedException{
   	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   	WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
   	assertNotNull(privacy.isDisplayed());
   }
   @Test
   public void testTermsfooterlink()throws InterruptedException{
   	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  	WebElement Terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
  	assertNotNull(Terms.isDisplayed());

    }
    @Test
   public void testContactfooterlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
    	assertNotNull(contact.isDisplayed());

    }
    @Test
    public void testfeeddashboardr()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table"));
    	assertNotNull(feed.isDisplayed());

    }
    @Test
   public void testAddButtonIsDisplayed()throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement AddButton = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/button"));
       assertTrue(AddButton.isDisplayed(), "Add button should be visible.");
    }
}



   