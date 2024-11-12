package UserProfile;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEditProfile{

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        // Initialize the WebDriver (ensure the path to chromedriver is set correctly)
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @BeforeEach
    public void navigateToEditProfile() {
        driver.get("http://localhost:3000/login");

        // Log in with the provided credentials
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupurg1905@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123456789");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        // Wait for the user homepage to load
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));

        // Click on the Profile link
        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//ul//li[1]")));
        profileLink.click();

        // Wait for navigation to the UserProfile page
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/UserProfile"));

        // Click on the Edit Profile button
        WebElement editProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Edit Profile')]")));
        editProfileLink.click();

        // Wait for navigation to the UserEditProfile page
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
        // Ensure we're not redirected to the login page
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the title element and verify its text
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/div")));
        assertEquals("Hi, User", titleElement.getText(), "The page title does not match the expected text.");
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"phoneNumber\"]")));
        assertTrue(phoneNumber.isDisplayed(), "Phone Number should be visible.");
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
    void testCountryIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country\"]")));
        assertTrue(country.isDisplayed(), "Country should be visible.");
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
  public void testInvalidEmailFormat() {
      // Navigate to the User Edit Profile page
      driver.get("http://localhost:3000/UserEditProfile");

      // Wait until the email field is visible and interactable
      WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
      
      // Clear the email field and enter an invalid email format
      emailField.clear();
      emailField.sendKeys("invalidEmail");

      // Click the Update Profile button to attempt submission
      WebElement updateButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
      updateButton.click();

      // Wait for the error message to appear
      WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']/following-sibling::span")));
      
      // Verify that the error message contains text indicating an invalid email format
      assertTrue(emailError.getText().contains("Invalid email format"), "The error message for invalid email format is not displayed.");
  }

    @Test
    public void testUpdateFirstAndLastName() {
        // Ensure we’re on the correct page and not redirected to login
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the first name and last name fields, clear existing values, and enter new values
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
        firstNameField.clear();
        lastNameField.clear();
        firstNameField.sendKeys("UpdatedFirstName");
        lastNameField.sendKeys("UpdatedLastName");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        // Wait for form submission to complete, ensuring we're still on UserEditProfile
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); // Adjust this based on actual behavior
        driver.get("http://localhost:3000/UserProfile");
    }

    @Test
    public void testUpdatePhoneNumber() {
        // Ensure we're on the correct page and not redirected to login
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the phone number field, clear existing value, and enter a new phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber")));
        phoneNumberField.clear();
        phoneNumberField.sendKeys("123456789");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        // Wait for the form submission to complete, remaining on UserEditProfile
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); // Adjust URL or condition based on actual behavior
        driver.get("http://localhost:3000/UserProfile");
    }
    @Test
    public void testUpdateAddress() {
        // Ensure we're on the correct page
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Update address
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address")));
        addressField.clear();
        addressField.sendKeys("123 New Street, Citytown");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        // Optionally wait for the page to confirm the form submission has processed
        wait.until(ExpectedConditions.urlContains("UserEditProfile"));  // Adjust URL or condition based on actual behavior
        driver.get("http://localhost:3000/UserProfile");
    }
    
    @Test
    public void testUpdateCountry() {
        // Ensure we’re not redirected to login
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the country field, clear existing value, and enter the new country
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        countryField.clear();
        countryField.sendKeys("USA");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        // Wait for form submission to complete, ensuring we're still on UserEditProfile
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); // Adjust this based on actual behavior
        driver.get("http://localhost:3000/UserProfile");
    }
    @Test
    public void testUpdateProvince() {
        // Ensure we’re not redirected to login
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the province field, clear existing value, and enter the new province
        WebElement provinceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("province")));
        provinceField.clear();
        provinceField.sendKeys("Ontario");

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();

        // Wait for form submission to complete, confirming we're still on UserEditProfile
        wait.until(ExpectedConditions.urlContains("UserEditProfile")); // Adjust this based on actual behavior
    }
    @Test
    public void testHeaderLinksVisibility() {
        // Ensure we're not redirected to the login page

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Verify that each header link is visible on the page
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
        // Ensure we're not redirected to the login page
        assertFalse(driver.getCurrentUrl().contains("login"), "Session lost; redirected to login.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Verify that each footer link is visible on the page
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

