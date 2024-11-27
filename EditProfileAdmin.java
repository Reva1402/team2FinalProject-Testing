package AdminPanel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditProfileAdmin{

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--force-device-scale-factor=0.7"); 

        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
       
    }

    @BeforeEach
    public void navigateToEditProfile() {
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("madhavjariwala55@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminDashboard"));

        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/header/div/a")));
        profileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminProfile"));

        WebElement editProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[2]/button")));
        editProfileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminEditProfile"));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/header/h1")));
        String actualTitleText = titleElement.getText();

        
        System.out.println("Actual page title text: " + actualTitleText);

     
        assertTrue(actualTitleText.contains("Welcome Admin"), 
                   "The page title should contain 'Welcome Admin'.");
    }


    @Test
    void testPhoneNumberIsDisplayed() {
        
        try { driver.switchTo().alert().dismiss(); } catch (NoAlertPresentException ignored) {}

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.name("mobileNumber")))
            .isDisplayed(), "Phone Number should be visible.");
    }

    @Test
    void testAddressIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[2]/label")));
        assertTrue(address.isDisplayed(), "Address should be visible.");
    }

    
    @Test
    void testProfilePictureIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[3]/label")));
        assertNotNull(profileImage, "Profile image should not be null.");
        assertTrue(profileImage.isDisplayed(), "Profile image should be displayed.");
    }

    @Test
    void testSaveProfileButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement SaveProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/button")));
        assertTrue(SaveProfileButton.isDisplayed(), "Edit Profile button should be displayed.");
    }

    @Test
    void testLogoutButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/header/div/button")));
        assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed.");
    }

    @Test
    void testUpdateMobileNumber() {
        
        WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[1]/input")));
        mobileNumberField.clear();
        mobileNumberField.sendKeys("5146279083");

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Profile']"))).click();

        
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);

        
        alert.accept();
    }

    
    @Test
    void testUpdateAddress() {
        
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div[2]/input"))); // Adjust XPath to the correct address field location
        addressField.clear();
        addressField.sendKeys("cegep cremaize");

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save Profile']"))).click();

        
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);

        
        alert.accept();
    }

    


    @Test
    void testSidebarMenuVisibility() {
        
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/aside")));

        
        String[] expectedItems = {
            "Dashboard", "User Management", "Moderator Management",
            "Suspended Resources", "Content Management", "Support Management", "Security Rules"
        };

        for (String item : expectedItems) {
            assertTrue(
                sidebar.getText().contains(item),
                "'" + item + "' menu item should be visible"
            );
        }
    }

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


}

