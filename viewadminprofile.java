package AdminPanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminProfile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
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

        
        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/header/div/a")));
        profileLink.click();
    }

    @AfterEach
    public void tearDown() {
        
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testProfileDashboardNavbar() {
        WebElement profileHead = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header")));
        assertEquals("Admin one", profileHead.getText());
    }

    @Test
    public void testProfileNavbar() {
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/a")));
        assertNotNull(profile.getText(), "Profile link should be visible.");
    }

    @Test
    public void testLogoutNavbar() {
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/button")));
        assertNotNull(logout.getText(), "Logout button should be visible.");
    }

    @Test
    public void testSidebar() {
        WebElement sidebar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/aside")));
        assertNotEquals("", sidebar.getText(), "Sidebar should not be empty.");
    }

    @Test
    public void testDashboardLink() {
        WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[1]"));
        assertTrue(link.isDisplayed(), "Dashboard link should be visible.");
    }

    @Test
    public void testUserManagementLink() {
        WebElement userManage = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[2]"));
        assertTrue(userManage.isDisplayed(), "User Management link should be visible.");
    }

    @Test
    public void testModeratorManagementLink() {
        WebElement moderatorManage = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[3]"));
        assertTrue(moderatorManage.isDisplayed(), "Moderator Management link should be visible.");
    }

    @Test
    public void testSuspendedManagementLink() {
        WebElement suspendedManage = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[4]"));
        assertTrue(suspendedManage.isDisplayed(), "Suspended Management link should be visible.");
    }

    @Test
    public void testContentManagementLink() {
        WebElement contentManage = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[5]"));
        assertTrue(contentManage.isDisplayed(), "Content Management link should be visible.");
    }

    @Test
    public void testSupportManagementLink() {
        WebElement supportManage = driver.findElement(By.xpath("/html/body/div/div/div/aside/a[6]"));
        assertTrue(supportManage.isDisplayed(), "Support Management link should be visible.");
    }

    @Test
    public void testAboutFooterLink() {
        WebElement about = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
        assertTrue(about.isDisplayed(), "About footer link should be visible.");
    }

    @Test
    public void testPrivacyFooterLink() {
        WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
        assertTrue(privacy.isDisplayed(), "Privacy footer link should be visible.");
    }

    @Test
    public void testTermsFooterLink() {
        WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
        assertTrue(terms.isDisplayed(), "Terms footer link should be visible.");
    }

    @Test
    public void testContactFooterLink() {
        WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
        assertTrue(contact.isDisplayed(), "Contact footer link should be visible.");
    }

    @Test
    public void testFeedDashboard() {
        WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div"));
        assertTrue(feed.isDisplayed(), "Feed dashboard should be visible.");
    }

    @Test
    public void testEditButtonIsDisplayed() {
        WebElement editButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/button"));
        assertTrue(editButton.isDisplayed(), "Edit button should be visible.");
    }
}
