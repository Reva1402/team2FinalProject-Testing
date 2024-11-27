package UserProfile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class userlogin {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("http://localhost:3000/login");


    }

    @AfterEach
    void tearDown() {
        driver.quit();
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
    public void testSignupVisibility() {
        
        WebElement signupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Signup')]"))); 

        
        assertTrue("The 'Signup' element should be visible", signupElement.isDisplayed());
    }
    
    @Test
    public void testEmailFieldVisibility() {
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[1]/input"))); 

        
        assertTrue("The 'Email' field should be visible", emailField.isDisplayed());
    }
    
    @Test
    public void testPasswordFieldVisibility() {
        
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[2]/input"))); 

        
        assertTrue("The 'Password' field should be visible", passwordField.isDisplayed());
    }
    @Test
    public void testLoginButtonVisibility() {
        
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Login')]"))); 

        
        assertTrue("The 'Login' button should be visible", loginButton.isDisplayed());
    }

    @Test
    public void testNewHereVisibilityAndNavigation() {
        
        WebElement newHereElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/form/div[3]/button[2]"))); 

        
        newHereElement.click();

        
        boolean isRedirectedToHomePage = wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        assertTrue("The 'New Here' link should redirect to 'http://localhost:3000/'", isRedirectedToHomePage);
    }
    @Test
    public void testForgotPasswordVisibilityAndResetPasswordText() {
        
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/form/div[3]/button[3]"))); 

        
        forgotPasswordLink.click();

        
        WebElement resetPasswordText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/h2"))); 
        assertTrue("Reset password text should be visible after clicking 'Forgot Password'", resetPasswordText.isDisplayed());
    }
    @Test
    public void testLoginAndRedirection() {
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))); 
        emailField.sendKeys("nupurg1905@gmail.com");

        
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
        passwordField.sendKeys("Nupur@123");

        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]"))); 
        loginButton.click();

        
        boolean isRedirectedToUserHomepage = wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
        assertTrue("Login should redirect to the user homepage at 'http://localhost:3000/userhomepage'", isRedirectedToUserHomepage);
    }







}
