package ModeratorPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueWarningtoUsers {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("http://localhost:3000/login");

        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("revathivooraboina@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Admin@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/ModeratorHomePage"));

        
        WebElement userManagementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/aside/a[3]")));
        userManagementLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/ModeratorUserManagement"));
    }

    @Test
    public void testIssueWarning() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement issueWarningButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/table/tbody/tr[2]/td[4]/button[3]"))); 
        issueWarningButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        assertTrue("Alert text does not match expected message", alertText.contains("Warning has been issued to the user"));
        alert.accept(); 
    }
    
    @Test
    public void testSidebarVisibility() {
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside")));
        assertTrue(sidebar.isDisplayed(), "Sidebar should be visible.");
    }

    @Test
    public void testDashboardMenuVisibility() {
        WebElement dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[1]")));
        assertTrue(dashboardMenu.isDisplayed(), "'Dashboard' menu item should be visible.");
    }

    @Test
    public void testFeedMenuVisibility() {
        WebElement feedMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[2]")));
        assertTrue(feedMenu.isDisplayed(), "'Feed' menu item should be visible.");
    }

    @Test
    public void testUserManagementMenuVisibility() {
        WebElement userManagementMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[3]")));
        assertTrue(userManagementMenu.isDisplayed(), "'User Management' menu item should be visible.");
    }

    @Test
    public void testEventManagementMenuVisibility() {
        WebElement eventManagementMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[4]")));
        assertTrue(eventManagementMenu.isDisplayed(), "'Event Management' menu item should be visible.");
    }

    @Test
    public void testCommentManagementMenuVisibility() {
        WebElement commentManagementMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/aside/a[5]")));
        assertTrue(commentManagementMenu.isDisplayed(), "'Comment Management' menu item should be visible.");
    }
    
//    private boolean isElementVisible(By locator) {
//        try {
//            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }



    @Test
    public void testAboutLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "About link should be visible.");
    }

    @Test
    public void testPrivacyPolicyLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link should be visible.");
    }

    @Test
    public void testTermsAndConditionsLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
        assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions link should be visible.");
    }

    @Test
    public void testContactUsLinkVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        assertTrue(contactUsLink.isDisplayed(), "Contact Us link should be visible.");
    }



    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

