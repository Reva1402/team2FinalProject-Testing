package UserProfile;

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

public class UserEditProfile{

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void navigateToEditProfile() {
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupurg1905@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Nupur@123");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));

        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/aside/button[1]")));
        profileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserProfile"));

        WebElement editProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div/button[1]")));
        editProfileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserEditProfile"));
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

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/nav")));
        String actualTitleText = titleElement.getText();

        System.out.println("Actual page title text: " + actualTitleText);

        assertTrue(actualTitleText.startsWith("Hi,"), "The page title matches with the expected greeting 'Hi,'.");


    }


    @Test
    void testFirstNameIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[1]")));
        assertTrue(firstName.isDisplayed(), "First Name should be visible.");
    }

    @Test
    void testLastNameIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[2]/label")));
        assertTrue(lastName.isDisplayed(), "Last Name should be visible.");
    }

    @Test
    void testEmailIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[3]/label")));
        assertTrue(email.isDisplayed(), "Email should be visible.");
    }

    @Test
    void testPhoneNumberIsDisplayed() {
        
        try { driver.switchTo().alert().dismiss(); } catch (NoAlertPresentException ignored) {}

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber")))
            .isDisplayed(), "Phone Number should be visible.");
    }

    @Test
    void testGenderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/div[6]/label")));
        assertTrue(gender.isDisplayed(), "Gender should be visible.");
    }

    @Test
    void testAddressIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[5]/label")));
        assertTrue(address.isDisplayed(), "Address should be visible.");
    }

    @Test
    void testProvinceIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement province = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[8]/label")));
        assertTrue(province.isDisplayed(), "Province should be visible.");
    }

    @Test
    void testProfileHeaderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/h2")));
        assertNotNull(profileHeader, "Profile header should not be null.");
        assertTrue(profileHeader.isDisplayed(), "Profile header should be displayed.");
    }

    @Test
    void testProfilePictureIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[9]/label")));
        assertNotNull(profileImage, "Profile image should not be null.");
        assertTrue(profileImage.isDisplayed(), "Profile image should be displayed.");
    }

    @Test
    void testUpdateProfileButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement updateProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/form/button")));
        assertTrue(updateProfileButton.isDisplayed(), "Edit Profile button should be displayed.");
    }

    @Test
    void testLogoutButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/nav/button")));
        assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed.");
    }

    @Test
    void testUpdateFirstAndLastName() {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[1]/input")));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[2]/input")));
        firstNameField.clear();
        lastNameField.clear();
        firstNameField.sendKeys("UpdatedFirstName");
        lastNameField.sendKeys("UpdatedLastName");

        
        WebElement updateProfileButton = driver.findElement(By.xpath("//*[text()='Update Profile']"));

        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", updateProfileButton);

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserProfile"));
    }
    
    @Test
    void testUpdateAddress() {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[5]/input")));
        addressField.clear();
        addressField.sendKeys("456 Updated Address, NewCity");

        
        WebElement updateProfileButton = driver.findElement(By.xpath("//*[text()='Update Profile']"));

        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", updateProfileButton);

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserProfile"));
    }
    


    @Test
    void testSidebarMenuVisibility() {
        
        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/aside")));

        
        List<WebElement> menuItems = sidebar.findElements(By.tagName("button")); 

        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Profile")), "'Profile' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Post An Event")), "'Post an Event' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Events")), "'My Events' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Followers")), "'Followers' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("My Schedule")), "'My Schedule' menu item should be visible");
        assertTrue(menuItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase("Filter Events")), "'Filter Events' menu item should be visible");
    }

    @Test
    public void testFooterLinksVisibility() {
        
        

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "About link should be visible.");

        WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link should be visible.");

        WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[3]")));
        assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions link should be visible.");

        WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/footer/ul/li[4]")));
        assertTrue(contactUsLink.isDisplayed(), "Contact Us link should be visible.");
    }

}

