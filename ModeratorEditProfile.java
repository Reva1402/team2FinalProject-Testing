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
class ModeratorEditProfile {


	    private static WebDriver driver;
	    private static WebDriverWait wait;

	    @BeforeAll
	    public static void setUp() {
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    }

	    @BeforeEach
	    public void navigateToModeratorProfile() {
	        driver.get("http://localhost:3000/login");

	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	        emailField.sendKeys("nupursinghal1991@gmail.com");

	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("123456789");

	        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
	        loginButton.click();

	        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/moderatorhomepage"));

	        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//ul//li[1]")));
	        profileLink.click();

	        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/moderatorProfile"));
	    }

	    @AfterAll
	    public static void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @Test
	    void testPageHeaderTitle() {
	        WebElement headerTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Moderator Profile')]")));
	        assertTrue(headerTitle.isDisplayed(), "The page header title should be displayed.");
	    }

	    @Test
	    void testEditButtonIsDisplayed() {
	        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Edit Profile')]")));
	        assertTrue(editButton.isDisplayed(), "Edit Profile button should be displayed.");
	    }

	    @Test
	    void testDeleteButtonIsDisplayed() {
	        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete Profile')]")));
	        assertTrue(deleteButton.isDisplayed(), "Delete Profile button should be displayed.");
	    }

	    @Test
	    void testPersonalInfoFieldsAreVisible() {
	        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'First Name')]/following-sibling::p")));
	        assertTrue(firstNameField.isDisplayed(), "First Name should be visible.");

	        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Last Name')]/following-sibling::p")));
	        assertTrue(lastNameField.isDisplayed(), "Last Name should be visible.");

	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Email')]/following-sibling::p")));
	        assertTrue(emailField.isDisplayed(), "Email should be visible.");

	        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Phone')]/following-sibling::p")));
	        assertTrue(phoneField.isDisplayed(), "Phone should be visible.");
	    }

	    @Test
	    void testModerationStatsAreVisible() {
	        WebElement roleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Role')]/following-sibling::p")));
	        assertTrue(roleField.isDisplayed(), "Role should be visible.");

	        WebElement joinDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Join Date')]/following-sibling::p")));
	        assertTrue(joinDateField.isDisplayed(), "Join Date should be visible.");

	        WebElement actionsField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Actions This Month')]/following-sibling::p")));
	        assertTrue(actionsField.isDisplayed(), "Actions This Month should be visible.");
	    }

	    @Test
	    void testEditProfileFunctionality() {
	        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Edit Profile')]")));
	        editButton.click();

	        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
	        firstNameInput.clear();
	        firstNameInput.sendKeys("UpdatedFirstName");

	        WebElement lastNameInput = driver.findElement(By.name("lastName"));
	        lastNameInput.clear();
	        lastNameInput.sendKeys("UpdatedLastName");

	        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
	        saveButton.click();

	        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Profile updated successfully.')]")));
	        assertTrue(confirmationMessage.isDisplayed(), "Confirmation message should be displayed.");
	    }

	    @Test
	    void testDeleteProfileFunctionality() {
	        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete Profile')]")));
	        deleteButton.click();

	        try {
	            driver.switchTo().alert().accept();
	        } catch (NoAlertPresentException e) {
	            fail("Delete confirmation alert was not displayed.");
	        }

	        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
	        assertTrue(driver.getCurrentUrl().contains("login"), "User should be redirected to login page after deleting the profile.");
	    }
	}

	

}
