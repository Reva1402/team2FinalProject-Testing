package AdminReg;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

public class Adminsignup {
    private static WebDriver driver;

    @BeforeAll
    static void setUpOnce() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\nupur\\FProject.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");  // replace with actual signup page URL
    }

    @AfterAll
    static void tearDownOnce() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAdminSignupValidData() {
        driver.get("http://localhost:3000/");

        driver.findElement(By.name("firstName")).sendKeys("Admin");
        driver.findElement(By.name("lastName")).sendKeys("User");
        driver.findElement(By.name("email")).sendKeys("admin@example.com");
        driver.findElement(By.name("mobilenumber")).sendKeys("1234567890");
        driver.findElement(By.name("password")).sendKeys("AdminPass123");
        driver.findElement(By.id("male")).click();
        driver.findElement(By.name("dateofbirth")).sendKeys("1980-01-01");
        driver.findElement(By.name("address")).sendKeys("123 Admin St");
        new Select(driver.findElement(By.name("country"))).selectByVisibleText("Canada");
        new Select(driver.findElement(By.name("province"))).selectByVisibleText("Ontario");
        new Select(driver.findElement(By.name("role"))).selectByVisibleText("Admin");

        driver.findElement(By.className("signup-btn-custom")).click();
        assertEquals("http://localhost:3000/", driver.getCurrentUrl());
    }

@Test
void testFirstNameField() {
    // Locate the First Name field and enter a value
    WebElement firstNameField = driver.findElement(By.name("firstName"));
    firstNameField.clear();
    firstNameField.sendKeys("TestFirstName");

    // Validate that the entered value is correct
    assertEquals("TestFirstName", firstNameField.getAttribute("value"));

    // Submit the form to check for any errors related to First Name
    driver.findElement(By.className("signup-btn-custom")).click();

    // Validate that no error message is displayed for the First Name field
    boolean firstNameError = driver.findElements(By.xpath("//*[contains(text(),'Please enter a valid First Name')]")).isEmpty();
    assertTrue(firstNameError, "First Name validation error detected unexpectedly.");
    
    System.out.println("First Name field passed validation checks.");
}


@Test
void testLastNameField() {
    // Locate the Last Name field and enter a value
    WebElement lastNameField = driver.findElement(By.name("lastName"));
    lastNameField.clear();
    lastNameField.sendKeys("TestLastName");

    // Validate that the entered value is correct
    assertEquals("TestLastName", lastNameField.getAttribute("value"));

    // Submit the form to check for any errors related to Last Name
    driver.findElement(By.className("signup-btn-custom")).click();

    // Validate that no error message is displayed for the Last Name field
    boolean lastNameError = driver.findElements(By.xpath("//*[contains(text(),'Please enter a valid Last Name')]")).isEmpty();
    assertTrue(lastNameError, "Last Name validation error detected unexpectedly.");
    
    System.out.println("Last Name field passed validation checks.");
}

@Test
void testEmailField() {
    WebElement email = driver.findElement(By.name("email"));
    email.sendKeys("test@example.com");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[contains(text(),'valid email')]")).isEmpty());

    email.clear();
    email.sendKeys("invalid-email");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[3]/input")).size() > 0);
}

@Test
void testMobileNumberField() {
    WebElement mobile = driver.findElement(By.name("mobilenumber"));
    mobile.sendKeys("1234567890");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[4]/input")).isEmpty());

    mobile.clear();
    mobile.sendKeys("123");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[4]/input")).size() > 0);
}
@Test
void testPasswordField() {
    WebElement password = driver.findElement(By.name("password"));
    password.sendKeys("ValidPass123");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[5]/input")).isEmpty());

    password.clear();
    password.sendKeys("123");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[5]/input")).size() > 0);
}

@Test
void testGenderField() {
    // Select Gender (Male, Female, or Other)
    WebElement genderMale = driver.findElement(By.id("male"));
    genderMale.click();
    assertTrue(genderMale.isSelected(), "Male option should be selected");

    WebElement genderFemale = driver.findElement(By.id("female"));
    genderFemale.click();
    assertTrue(genderFemale.isSelected(), "Female option should be selected");

    WebElement genderCustom = driver.findElement(By.id("custom"));
    genderCustom.click();
    assertTrue(genderCustom.isSelected(), "Custom option should be selected");

    System.out.println("Gender field selection passed for Male, Female, and Custom.");
}

@Test
void testRoleFieldAdmin() {
    // Select Role as Admin
    WebElement roleDropdown = driver.findElement(By.name("role"));
    roleDropdown.sendKeys("Admin"); // Assumes the dropdown is text-based or use Select class for options
    assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[7]/select")).isDisplayed(), "Admin role should be selected");

    System.out.println("Role field selection passed for Admin.");
}

@Test
void testDateOfBirthField() {
    WebElement dobField = driver.findElement(By.name("dateofbirth"));
    
    dobField.sendKeys("1990-01-01");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[8]/input")).isEmpty());

    dobField.clear();
    dobField.sendKeys("2025-01-01"); // Future date
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[8]/input")).size() > 0);
}

@Test
void testAddressField() {
    WebElement addressField = driver.findElement(By.name("address"));
    
    addressField.sendKeys("123 Main St");
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[9]/input")).isEmpty());

    addressField.clear();
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[9]/input")).size() > 0);
}

@Test
void testValidCountrySelection() {
    // Select a valid country from the dropdown
    WebElement countryDropdown = driver.findElement(By.name("country"));
    new Select(countryDropdown).selectByVisibleText("Canada");
    
    // Submit the form and check that no error appears for a valid country
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[10]/select")).isEmpty(),
               "Unexpected validation error for valid country selection.");
}

@Test
void testEmptyCountrySelection() {
    // Leave the Country field empty or select the placeholder option (if applicable)
    WebElement countryDropdown = driver.findElement(By.name("country"));
    new Select(countryDropdown).selectByVisibleText(""); // Assuming there's an empty option

    // Submit the form and check for validation error
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[10]/select,'Please select a valid country')]")).size() > 0,
               "Expected validation error for empty country selection was not detected.");
}

@Test
void testProfilePictureUploadValid() {
    // Locate the profile picture input and upload a valid image file
    WebElement profilePictureField = driver.findElement(By.name("profilepicture"));
    File profilePic = new File("C:\\Users\\nupur\\OneDrive\\Desktop.jpg"); // Path to a valid image
    profilePictureField.sendKeys(profilePic.getAbsolutePath());

    // Submit the form and check that no error appears for the uploaded image
    driver.findElement(By.className("signup-btn-custom")).click();
    assertTrue(driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[11]/input,'valid profile picture')]")).isEmpty(),
               "Unexpected validation error for valid profile picture upload.");
}
@Test
void testProfilePictureUploadEmpty() {
    // Leave the profile picture field empty
    driver.findElement(By.className("signup-btn-custom")).click();

    // Check for validation error indicating a missing profile picture
    assertTrue(driver.findElements(By.xpath("//*[contains(text(),'Please upload a profile picture')]")).size() > 0,
               "Expected validation error for missing profile picture was not detected.");
}


@Test
void testSignupWithMissingRequiredFields() {
    // Fill one field only to simulate missing required fields
    driver.findElement(By.name("firstName")).sendKeys("John");
    driver.findElement(By.className("signup-btn-custom")).click();

    // Check for validation error message
    assertTrue(driver.findElements(By.xpath("//*[contains(text(),'Please fill all required fields')]")).size() > 0,
               "Expected validation error for missing fields not detected.");
}

    @Test
    void testAlreadyHaveAccountLink() {
        // Wait for the "Already have an account?" link to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[12]/button[2], 'Already have an account?')]")));

        // Click the link and verify navigation to the login page
        loginLink.click();
        assertEquals("http://localhost:3000/login", driver.getCurrentUrl(), "Expected to navigate to the login page.");
    }

@Test
void testEventopiaLogo() {
    WebElement logo = driver.findElement(By.className("navbar-brand"));
    assertTrue(logo.isDisplayed(), "Logo should be visible.");
    logo.click();
    assertEquals("http://localhost:3000/", driver.getCurrentUrl(), "Should navigate to homepage.");
}

@Test
void testServicesLink() {
    WebElement servicesLink = driver.findElement(By.linkText("Services"));
    assertTrue(servicesLink.isDisplayed(), "Services link should be visible.");
    servicesLink.click();
    assertEquals("http://localhost:3000/services", driver.getCurrentUrl(), "Should navigate to Services page.");
}

@Test
void testLoginButton() {
    WebElement loginButton = driver.findElement(By.linkText("Login"));
    assertTrue(loginButton.isDisplayed(), "Login button should be visible.");
    loginButton.click();
    assertEquals("http://localhost:3000/login", driver.getCurrentUrl(), "Should navigate to Login page.");
}

@Test
void testCallUsNumberVisibility() {
    WebElement callUsNumber = driver.findElement(By.xpath("//*[contains(text(), 'Call us @ +1 123 456 789')]"));
    assertTrue(callUsNumber.isDisplayed(), "Call us number should be visible in the header.");
}


    @Test
    void testAdminSignupInvalidEmail() {
        driver.get("http://localhost:3000/");

        driver.findElement(By.name("firstName")).sendKeys("Admin");
        driver.findElement(By.name("lastName")).sendKeys("User");
        driver.findElement(By.name("email")).sendKeys("invalid-email");
        driver.findElement(By.name("password")).sendKeys("AdminPass123");
        new Select(driver.findElement(By.name("role"))).selectByVisibleText("Admin");

        driver.findElement(By.className("signup-btn-custom")).click();

        WebElement errorMessage = driver.findElement(By.className("error"));
        assertTrue(errorMessage.getText().contains("Please enter a valid email address"));
    }

    @Test
    void testAdminSignupShortPassword() {
        driver.get("http://yourwebsiteurl/signup");

        driver.findElement(By.name("firstName")).sendKeys("Admin");
        driver.findElement(By.name("lastName")).sendKeys("User");
        driver.findElement(By.name("email")).sendKeys("admin@example.com");
        driver.findElement(By.name("password")).sendKeys("123");
        new Select(driver.findElement(By.name("role"))).selectByVisibleText("Admin");

        driver.findElement(By.className("signup-btn-custom")).click();

        WebElement errorMessage = driver.findElement(By.className("error"));
        assertTrue(errorMessage.getText().contains("Password should be at least 6 characters long"));
    }
    }
