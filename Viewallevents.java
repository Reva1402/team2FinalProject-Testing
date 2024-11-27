package AdminPanel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
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

class Viewallevents {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach 
    void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("madhavjariwala55@gmail.com");

        
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys("123456789");

        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();
        
       
        wait.until(ExpectedConditions.urlContains("/AdminDashboard"));
        
        
        WebElement allevents = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[5]")));
        allevents.click();
    }

    @AfterEach 
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testUserDashboardNavbar() {
        WebElement dashboardHeading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header")));
        assertNotEquals("Admin one", dashboardHeading.getText());
    }

    @Test
    void testProfileNavbar() {
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/a")));
        assertNotNull("Profile link should be visible", profile.getText());
    }

    @Test
    void testLogoutNavbar() {
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/header/div/button")));
        assertNotNull("Logout button should be visible", logout.getText());
    }

    @Test
    void testSidebar() {
        WebElement sidebar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/div")));
        assertNotEquals("Sidebar should not be empty", "", sidebar.getText());
    }

    @Test
    void testDashboardLink() {
        WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[1]"));
        assertTrue("Dashboard link should be visible", link.isDisplayed());
    }

    @Test
    void testUserManagementLink() {
        WebElement userManage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[2]"));
        assertTrue("User Management link should be visible", userManage.isDisplayed());
    }

    @Test
    void testModeratorManagementLink() {
        WebElement moderatorManage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[3]"));
        assertTrue("Moderator Management link should be visible", moderatorManage.isDisplayed());
    }

    @Test
    void testSuspendedManagementLink() {
        WebElement suspendedManage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[4]"));
        assertTrue("Suspended Management link should be visible", suspendedManage.isDisplayed());
    }

    @Test
    void testContentManagementLink() {
        WebElement contentManage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[5]"));
        assertTrue("Content Management link should be visible", contentManage.isDisplayed());
    }

    @Test
    void testSupportManagementLink() {
        WebElement supportManage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/a[6]"));
        assertTrue("Support Management link should be visible", supportManage.isDisplayed());
    }

    @Test
    void testAboutFooterLink() {
        WebElement about = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
        assertTrue("About footer link should be visible", about.isDisplayed());
    }

    @Test
    void testPrivacyFooterLink() {
        WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
        assertTrue("Privacy footer link should be visible", privacy.isDisplayed());
    }

    @Test
    void testTermsFooterLink() {
        WebElement terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
        assertTrue("Terms footer link should be visible", terms.isDisplayed());
    }

    @Test
    void testContactFooterLink() {
        WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
        assertTrue("Contact footer link should be visible", contact.isDisplayed());
    }

    @Test
    void testFeedDashboard() {
        WebElement feed = driver.findElement(By.xpath("/html/body/div/div/div/div/main/div"));
        assertTrue("Feed dashboard should be visible", feed.isDisplayed());
    }

    @Test
    void testViewButtonIsClickable() {
        WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[1]")));
        assertTrue("View button should be clickable", viewButton.isEnabled());
    }

    @Test
    void testSuspendButtonIsClickable() {
        WebElement suspendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[2]")));
        assertTrue("Suspend button should be clickable", suspendButton.isEnabled());
    }

    @Test
    void testDeleteButtonIsClickable() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/main/div/table/tbody/tr[1]/td[2]/button[3]")));
        assertTrue("Delete button should be clickable", deleteButton.isEnabled());
    }
}
