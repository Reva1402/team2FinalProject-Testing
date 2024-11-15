package Testingprojectfinal;



import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Admindash {


	
	    private static WebDriver driver;
	    private static WebDriverWait wait;

	    @BeforeAll
	    public static void setUp() {
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    }

	    @BeforeEach
	    public void navigateToAdminDashboard() {
	        driver.get("http://localhost:3000/login");

	        // Log in with the provided credentials
	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("madhavjariwala55@gmail.com");

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("123456789");

	        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
	        loginButton.click();

	        // Wait for the admin dashboard to load
	        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/AdminDashboard"));

	        // Verify that we're on the admin dashboard page
	        assertTrue(driver.getCurrentUrl().contains("admindashboard"), "Admin dashboard page is not loaded.");
	    }

	    @AfterAll
	    public static void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @Test
	    public void testAdminDashboardTitle() {
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header//h1")));
	        String actualTitleText = titleElement.getText();
	        System.out.println("Actual page title text: " + actualTitleText);

	        // Check if the title text starts with "Admin One"
	        assertTrue(actualTitleText.startsWith("Admin One"), "The page title matches with the expected greeting 'Admin One'.");
	    }

//	    @Test
//	    public void testSearchBarIsVisible() {
//	        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-bar")));
//	        assertTrue(searchBar.isDisplayed(), "Search bar should be visible.");
//	    }
//
//	    @Test
//	    public void testProfileLinkIsVisible() {
//	        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-link")));
//	        assertTrue(profileLink.isDisplayed(), "Profile link should be visible.");
//	    }
//
//	    @Test
//	    public void testLogoutButtonIsVisible() {
//	        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout-btn")));
//	        assertTrue(logoutButton.isDisplayed(), "Logout button should be visible.");
//	    }
//
//	    @Test
//	    public void testSectionsForUserManagement() {
//	        WebElement userManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='User Management']")));
//	        assertTrue(userManagementSection.isDisplayed(), "User Management section should be visible.");
//	    }
//
//	    @Test
//	    public void testSectionsForModeratorManagement() {
//	        WebElement moderatorManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='Moderator Management']")));
//	        assertTrue(moderatorManagementSection.isDisplayed(), "Moderator Management section should be visible.");
//	    }
//
//	    @Test
//	    public void testSectionsForEventManagement() {
//	        WebElement eventManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='Event Management']")));
//	        assertTrue(eventManagementSection.isDisplayed(), "Event Management section should be visible.");
//	    }
//
//	    @Test
//	    public void testLogoutFunctionality() {
//	        // Click the logout button
//	        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
//	        logoutButton.click();
//
//	        // Wait for the login page to appear
//	        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
//	        assertTrue(driver.getCurrentUrl().contains("login"), "Failed to log out and navigate to the login page.");
//	    }
//
//	    @Test
//	    public void testAdminSidebarLinks() {
//	        WebElement userManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside//a[contains(text(), 'User Management')]")));
//	        assertTrue(userManagementLink.isDisplayed(), "User Management sidebar link should be visible.");
//
//	        WebElement moderatorManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside//a[contains(text(), 'Moderator Management')]")));
//	        assertTrue(moderatorManagementLink.isDisplayed(), "Moderator Management sidebar link should be visible.");
//
//	        WebElement suspendedResourcesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside//a[contains(text(), 'Suspended Resources')]")));
//	        assertTrue(suspendedResourcesLink.isDisplayed(), "Suspended Resources sidebar link should be visible.");
//
//	        WebElement contentManagementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside//a[contains(text(), 'Content Management')]")));
//	        assertTrue(contentManagementLink.isDisplayed(), "Content Management sidebar link should be visible.");
//
//	        WebElement securityRulesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside//a[contains(text(), 'Security Rules')]")));
//	        assertTrue(securityRulesLink.isDisplayed(), "Security Rules sidebar link should be visible.");
//	    }
//
//	    @Test
//	    public void testFooterLinksVisibility() {
//	        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//a[contains(text(), 'About')]")));
//	        assertTrue(aboutLink.isDisplayed(), "About footer link should be visible.");
//
//	        WebElement privacyPolicyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//a[contains(text(), 'Privacy Policy')]")));
//	        assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy footer link should be visible.");
//
//	        WebElement termsAndConditionsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//a[contains(text(), 'Terms and Conditions')]")));
//	        assertTrue(termsAndConditionsLink.isDisplayed(), "Terms and Conditions footer link should be visible.");
//
//	        WebElement contactUsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//a[contains(text(), 'Contact Us')]")));
//	        assertTrue(contactUsLink.isDisplayed(), "Contact Us footer link should be visible.");
//	    }
//	    
//	    @Test
//	    public void testDashboardContentVisibility() {
//	        WebElement userManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='User Management']")));
//	        assertTrue(userManagementSection.isDisplayed(), "User Management section should be visible on the dashboard.");
//
//	        WebElement moderatorManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='Moderator Management']")));
//	        assertTrue(moderatorManagementSection.isDisplayed(), "Moderator Management section should be visible on the dashboard.");
//
//	        WebElement eventManagementSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'management-section')]//h2[text()='Event Management']")));
//	        assertTrue(eventManagementSection.isDisplayed(), "Event Management section should be visible on the dashboard.");
//	    }
	}
