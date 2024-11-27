package AdminPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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

public class ViewDashboardAnalytics {

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
        wait.until(ExpectedConditions.urlContains("/AdminDashboard"));
    }

    @Test
    public void testdashboardnavbar()throws InterruptedException { 
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardHeading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header")));
        assertEquals("Welcome Admin", dashboardHeading.getText());
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
        WebElement sidebar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/aside")));
        assertNotEquals("sidebar visible", sidebar.getText());
    }
    @Test
    public void testdashboardlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[1]"));
    	assertTrue(link.isDisplayed());

    }
    @Test
    public void testusermanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement usermanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[2]"));
    	assertTrue(usermanage.isDisplayed());

    }
    @Test
    public void testmoderatormanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement moderatormanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[3]"));
    	assertTrue(moderatormanage.isDisplayed());

    }
    @Test
    public void testsuspendedmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement suspendedmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[4]"));
    	assertTrue(suspendedmanage.isDisplayed());

    }
    @Test
    public void testcontentdmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement contentdmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[5]"));
    	assertTrue(contentdmanage.isDisplayed());

    }
    @Test
    public void testsupportdmanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement supportdmanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/a[6]"));
    	assertTrue(supportdmanage.isDisplayed());

    }
    @Test
    public void testsecuritymanagementlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement securitymanage = driver.findElement(By.xpath("/html/body/div/div/div/div/aside/h4/a"));
    	assertTrue(securitymanage.isDisplayed());

    }
    @Test
    public void testAboutfooterlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement About = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
    	assertTrue(About.isDisplayed());

    }
   
    @Test
    public void testprivacyfooterlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
    	assertTrue(privacy.isDisplayed());

    }
    @Test
    public void testTermsfooterlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement Terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
    	assertTrue(Terms.isDisplayed());

    }
    @Test
    public void testContactfooterlink()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
    	assertTrue(contact.isDisplayed());

    }
    @Test
    public void testfeeddashboardr()throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[1]"));
    	assertNotNull(feed.isDisplayed());

    }
    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }}
