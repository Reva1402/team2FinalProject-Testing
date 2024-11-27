package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class UserSignup {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--force-device-scale-factor=0.7"); 

        
        driver = new ChromeDriver(options);

        
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        
        driver.get("http://localhost:3000");

       
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testNavbarWithEventopiaIsVisible() {
        
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[contains(@class, 'navbar')]"))); 

        
        assertTrue("Navbar should be visible and contain the text 'Eventopia'", navbar.isDisplayed() && navbar.getText().contains("Eventopia"));
    }
    @Test
    public void testServicesButtonVisibilityAndNavigation() {
        
        WebElement servicesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[1]"))); 

        
        servicesButton.click();
        boolean isServicesPageLoaded = driver.getCurrentUrl().equals("http://localhost:3000/services");

        assertTrue("The 'Services' button should be visible and navigate to 'http://localhost:3000/services'", isServicesPageLoaded);
    }
    @Test
    public void testCallNumberVisibility() {
        
        WebElement callNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[2]/button")));

        
        assertTrue("The 'Call Number' should be visible", callNumber.isDisplayed());
    }
    @Test
    public void testNameFieldVisibility() {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[1]/input")));
        assertTrue("Name field should be visible", nameField.isDisplayed());
    }
    @Test
    public void testLastNameFieldVisibility() {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[2]/input"))); 
        assertTrue("Last Name field should be visible", lastNameField.isDisplayed());
    }
    @Test
    public void testEmailFieldVisibility() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[3]/input"))); 
        assertTrue("Email field should be visible", emailField.isDisplayed());
    }
    @Test
    public void testMobileNumberFieldVisibility() {
        WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[4]/input"))); 
        assertTrue("Mobile Number field should be visible", mobileNumberField.isDisplayed());
    }
    
    @Test
    public void testPasswordFieldVisibility() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[5]/input"))); 
        assertTrue("Password field should be visible", passwordField.isDisplayed());
    }
    @Test
    public void testGenderRadioButtonsVisibility() {
        WebElement genderRadioButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio' and @name='gender']"))); 
        assertTrue("Gender radio button should be visible", genderRadioButton.isDisplayed());
    }
    @Test
    public void testDateOfBirthFieldVisibility() {
        WebElement dobField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[8]/input"))); 
        assertTrue("Date of Birth field should be visible", dobField.isDisplayed());
    }
    @Test
    public void testAddressFieldVisibility() {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[9]/input"))); 
        assertTrue("Address field should be visible", addressField.isDisplayed());
    }
    @Test
    public void testCountryDropdownVisibility() {
        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[10]/select"))); 
        assertTrue("Country dropdown should be visible", countryDropdown.isDisplayed());
    }
    @Test
    public void testImageUploadOptionVisibility() {
        WebElement imageUpload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[11]/input"))); 
        assertTrue("Image upload option should be visible", imageUpload.isDisplayed());
    }
    @Test
    public void testSignupButtonVisibility() {
        WebElement signupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[3]/div/form/div[12]/button"))); 
        assertTrue("Signup button should be visible", signupButton.isDisplayed());
    }
    @Test
    public void testAlreadyHaveAccountLinkRedirection() {
        WebElement alreadyHaveAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Already have an account?"))); 
        alreadyHaveAccountLink.click();
        boolean isRedirectedToLogin = wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        assertTrue("Already have an account link should redirect to the login page", isRedirectedToLogin);
    }

    @Test
    public void testFooterLinksVisibility() {
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[1]")));
        assertTrue(aboutLink.isDisplayed(), "'About' link should be visible and displayed in the footer");

        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[2]")));
        assertTrue(privacyLink.isDisplayed(), "'Privacy Policy' link should be visible and displayed in the footer");

        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[3]")));
        assertTrue(termsLink.isDisplayed(), "'Terms and Conditions' link should be visible and displayed in the footer");

        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/footer/ul/li[4]")));
        assertTrue(contactLink.isDisplayed(), "'Contact Us' link should be visible and displayed in the footer");
    }
    }