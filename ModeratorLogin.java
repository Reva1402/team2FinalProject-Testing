package ModeratorPanel;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModeratorLogin {
	 private WebDriver driver;
	    private WebDriverWait wait;

	    
	    @BeforeEach
	    public void setUp() throws InterruptedException {
	       
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @BeforeEach
	    public void navigateToLoginPage() {
	        driver.get("http://localhost:3000/login"); 
	    }

	        
	        
	       
	    
	    @Test
	    public void testInvalidLogin()throws InterruptedException {
	        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
	        WebElement passwordField = driver.findElement(By.id("password"));
	        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]"));

	        emailField.sendKeys("rachnaaulakh@gmail.com");
	        passwordField.sendKeys("12312311");
	        loginButton.click();
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        String alertMessage = alert.getText();
	        Assertions.assertEquals("Login failed. Please try again.", alertMessage);
	        alert.accept();
	    }
	    @Test
	    public void testSuccessfulLogin() {
	        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
	        WebElement passwordField = driver.findElement(By.id("password"));
	        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]"));

	        emailField.sendKeys("revathivooraboina@gmail.com");
	        passwordField.sendKeys("Admin@123");
	        loginButton.click();
	    }
	    @Test
	    public void testPasswordReset() {
	        WebElement forgotPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[3]")));
	        forgotPasswordButton.click();

	        WebElement resetEmailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/form/div/input")));
	        WebElement sendResetButton = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/button"));

	        resetEmailField.sendKeys("revathivooraboina@gmail.com");
	        sendResetButton.click();

	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        String alertMessage = alert.getText();
	        Assertions.assertEquals("Password reset email sent. Please check your inbox.", alertMessage);
	        alert.accept();
	    }
	    @Test
	    public void tesmodtsignupLinkIsVisible() {
	        WebElement signuupLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/a")));
	        assertTrue(signuupLink.isDisplayed(), "signup link should be visible.");
	    }
	    @Test
	    public void tesmodtservicesLinkIsVisible() {
	        WebElement servicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/ul/li[1]")));
	        assertTrue(servicesLink.isDisplayed(), "services link should be visible.");
	    }
	    @Test
	    public void testAboutfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement About = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[1]"));
	    	assertTrue(About.isDisplayed());

	    }
	   
	    @Test
	    public void testprivacyfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement privacy = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[2]"));
	    	assertTrue(privacy.isDisplayed());

	    }
	    @Test
	    public void testTermsfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement Terms = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[3]"));
	    	assertTrue(Terms.isDisplayed());

	    }
	    @Test
	    public void testContactfooterlink()throws InterruptedException{
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement contact = driver.findElement(By.xpath("/html/body/div/div/div/footer/ul/li[4]"));
	    	assertTrue(contact.isDisplayed());

	    }
	    

	
}
