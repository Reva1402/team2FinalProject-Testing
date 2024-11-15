package UserProfile;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

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
        
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @BeforeEach
    public void navigateToEditProfile() {
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("rachnaaulakh@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));

        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//ul//li[1]")));
        profileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserProfile"));

        WebElement editProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Edit Profile')]")));
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
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/div")));
        String actualTitleText = titleElement.getText();

        System.out.println("Actual page title text: " + actualTitleText);

        assertTrue(actualTitleText.startsWith("Hi,"), "The page title matches with the expected greeting 'Hi,'.");


    }


    @Test
    void testFirstNameIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstName\"]")));
        assertTrue(firstName.isDisplayed(), "First Name should be visible.");
    }

    @Test
    void testLastNameIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lastName\"]")));
        assertTrue(lastName.isDisplayed(), "Last Name should be visible.");
    }

    @Test
    void testEmailIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
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
        WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gender\"]")));
        assertTrue(gender.isDisplayed(), "Gender should be visible.");
    }

    @Test
    void testDateOfBirthIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dateOfBirth\"]")));
        assertTrue(dob.isDisplayed(), "Date of Birth should be visible.");
    }

    @Test
    void testAddressIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"address\"]")));
        assertTrue(address.isDisplayed(), "Address should be visible.");
    }

    @Test
    void testProvinceIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement province = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"province\"]")));
        assertTrue(province.isDisplayed(), "Province should be visible.");
    }

    @Test
    void testProfileHeaderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/h2")));
        assertNotNull(profileHeader, "Profile header should not be null.");
        assertTrue(profileHeader.isDisplayed(), "Profile header should be displayed.");
    }

    @Test
    void testProfilePictureIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/div[11]/div/img")));
        assertNotNull(profileImage, "Profile image should not be null.");
        assertTrue(profileImage.isDisplayed(), "Profile image should be displayed.");
    }

    @Test
    void testEditProfileButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/button")));
        assertTrue(editProfileButton.isDisplayed(), "Edit Profile button should be displayed.");
    }

    @Test
    void testLogoutButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/nav/button")));
        assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed.");
    }

    @Test
    public void testUpdateFirstAndLastName() {
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
        firstNameField.clear();
        lastNameField.clear();
        firstNameField.sendKeys("UpdatedFirstName");
        lastNameField.sendKeys("UpdatedLastName");

       
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        wait.until(ExpectedConditions.urlContains("UserEditProfile")); 
        driver.get("http://localhost:3000/UserProfile");
    }

    @Test
    public void testUpdatePhoneNumber() {
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber")));
        phoneNumberField.clear();
        phoneNumberField.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); 
        driver.get("http://localhost:3000/UserProfile");
    }
    @Test
    public void testUpdateAddress() {
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address")));
        addressField.clear();
        addressField.sendKeys("123 New Street, Citytown");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

  
        wait.until(ExpectedConditions.urlContains("UserEditProfile"));  
        driver.get("http://localhost:3000/UserProfile");
    }
    
    @Test
    public void testUpdateCountry() {
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        countryField.clear();
        countryField.sendKeys("USA");

        
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); 
        driver.get("http://localhost:3000/UserProfile");
    }
    @Test
    public void testUpdateProvince() {
        try {
            
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException ignored) {
        }

       
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement provinceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("province")));
        provinceField.clear();
        provinceField.sendKeys("Ontario");

        
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/form/button")));
        submitButton.click();

        
        wait.until(ExpectedConditions.urlContains("UserEditProfile"));
    }
    @Test
    public void testHeaderLinksVisibility() {
        

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[1]")));
        assertTrue(profileLink.isDisplayed(), "Profile link should be visible.");

        WebElement myEventsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[2]")));
        assertTrue(myEventsLink.isDisplayed(), "My Events link should be visible.");

        WebElement postEventLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[3]")));
        assertTrue(postEventLink.isDisplayed(), "Post An Event link should be visible.");

        WebElement notificationsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[4]")));
        assertTrue(notificationsLink.isDisplayed(), "Notifications link should be visible.");

        WebElement followersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[5]")));
        assertTrue(followersLink.isDisplayed(), "Followers link should be visible.");
    }
    @Test
    public void testFooterLinksVisibility() {
        
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "About link should be visible.");

        WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link should be visible.");

        WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
        assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions link should be visible.");

        WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        assertTrue(contactUsLink.isDisplayed(), "Contact Us link should be visible.");
    }

}

