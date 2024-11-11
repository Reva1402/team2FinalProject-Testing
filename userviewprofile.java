package myevents;


import org.junit.jupiter.api.AfterEach;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class userviewprofile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prism Infotech\\eclipse-workspace\\event\\src\\myevents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    
    

    @Test
    public void testUserProfilePageLoads() {
        System.out.println("Opening user profile page...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement profileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-header")));
            assertTrue(profileHeader.isDisplayed(), "Profile page header is not visible");

            WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-img")));
            assertTrue(profileImage.isDisplayed(), "Profile image is not visible");

            WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'First Name:')]")));
            assertTrue(nameElement.isDisplayed(), "User's first name is not visible");

            WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Email:')]")));
            assertTrue(emailElement.isDisplayed(), "User's email is not visible");
        } catch (Exception e) {
            System.out.println("Error during testUserProfilePageLoads: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testLogout() {
        System.out.println("Testing logout functionality...");
        driver.get("http://localhost:3000/userprofile");

        try {

            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
            logoutButton.click();

            WebElement loginPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-header")));
            assertTrue(loginPageHeader.isDisplayed(), "Login page is not displayed after logout");
        } catch (Exception e) {
            System.out.println("Error during testLogout: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testEditProfileNavigation() {
        System.out.println("Testing edit profile navigation...");


        driver.get("http://localhost:3000/userprofile");

        try {
            // Wait for and click on the "Edit Profile" button
            WebElement editProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("edit-profile-btn")));
            editProfileButton.click();

            WebElement editProfileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("edit-profile-header")));
            assertTrue(editProfileHeader.isDisplayed(), "Edit profile page did not load");
        } catch (Exception e) {
            System.out.println("Error during testEditProfileNavigation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testProfileImageDisplayed() {
        System.out.println("Testing profile image visibility...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-img")));
            assertTrue(profileImage.isDisplayed(), "Profile image is not visible");
        } catch (Exception e) {
            System.out.println("Error during testProfileImageDisplayed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testFirstNameIsDisplayed() {
        System.out.println("Testing first name display...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'First Name:')]")));
            assertTrue(firstNameElement.isDisplayed(), "First name is not visible");
        } catch (Exception e) {
            System.out.println("Error during testFirstNameIsDisplayed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

//    @Test
//    public void testNonExistentFieldNotFound() {
//        System.out.println("Testing non-existent field visibility...");
//
//        driver.get("http://localhost:3000/userprofile");
//
//        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
//        });
//    }
    
    @Test
    public void testLogoutDoesNotRedirectToProfile() {
        System.out.println("Testing logout does not redirect to profile...");

        driver.get("http://localhost:3000/userprofile");
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
        logoutButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertNotEquals("http://localhost:3000/userprofile", currentUrl, "User was redirected to the profile page after logout");
    }

   
    @Test
    public void testIncorrectProfilePicture() {
        System.out.println("Testing incorrect profile picture...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-img")));
            String profileImageSrc = profileImage.getAttribute("src");
            System.out.println("Profile Image Src: " + profileImageSrc);

            assertFalse(profileImageSrc.contains("incorrect-path.png"), "Profile image source should not be 'incorrect-path.png'");

            assertFalse(profileImageSrc.isEmpty(), "Profile image src is empty");

            assertTrue(profileImageSrc.startsWith("http"), "Profile image src is invalid or doesn't start with 'http'");
            Boolean isImageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && arguments[0].naturalHeight !== 0;", profileImage);

            assertTrue(isImageLoaded, "Profile image is not loaded properly or is broken");

        } catch (Exception e) {
            System.out.println("Expected failure for incorrect profile picture: " + e.getMessage());
        }
    }

    @Test
    public void testProfilePageNotAccessible() {
        System.out.println("Testing profile page access...");

        assertThrows(Exception.class, () -> {
            driver.get("http://localhost:3000/userprofile");
        }, "Profile page should not be accessible for unauthenticated users");
    }
    
    @Test
    public void testInvalidEmailFormat() {
        System.out.println("Testing invalid email format...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement editProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("edit-profile-btn")));
            editProfileButton.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            emailField.sendKeys("invalid-email"); // Invalid email format

            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("save-btn")));
            saveButton.click();
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-msg")));
            fail("Invalid email format did not trigger error message: " + errorMessage.getText());

        } catch (Exception e) {
            System.out.println("Expected failure for invalid email format: " + e.getMessage());
        }
    }
    
    

    @Test
    public void testProfilePictureUploadFails() {
        System.out.println("Testing profile picture upload...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement changeProfilePictureButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("change-profile-pic-btn")));
            changeProfilePictureButton.click();

            WebElement uploadField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("profilePicUpload")));
            uploadField.sendKeys("C:\\path\\to\\invalid-image.txt");

            // Click the save button to upload
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("save-profile-pic-btn")));
            saveButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-msg")));
            fail("Profile picture upload failed without error message: " + errorMessage.getText());

        } catch (Exception e) {
            System.out.println("Expected failure for profile picture upload: " + e.getMessage());
        }
    }
    
    

    @Test
    public void testInvalidProfileEdit() {
        System.out.println("Testing invalid profile edit...");

        driver.get("http://localhost:3000/userprofile");

        try {
            WebElement editProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("edit-profile-btn")));
            editProfileButton.click();

            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
            firstNameField.sendKeys("12345"); // Invalid first name

            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("save-btn")));
            saveButton.click();
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-msg")));
            fail("Invalid profile edit did not trigger error: " + errorMessage.getText());
        } catch (Exception e) {
            System.out.println("Expected failure for invalid profile edit: " + e.getMessage());
        }
    }
    
//    @Test
//    public void testDelete() {
//        System.out.println("Testing logout functionality...");
//        driver.get("http://localhost:3000/userprofile");
//
//        try {
//
//            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
//            logoutButton.click();
//
//            WebElement loginPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-header")));
//            assertTrue(loginPageHeader.isDisplayed(), "Login page is not displayed after logout");
//        } catch (Exception e) {
//            System.out.println("Error during testLogout: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    

    @Test
    public void testDeleteButton() {
       
        driver.get("http://localhost:3000/userprofile"); 

       
        WebElement deleteButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/button[2]"));

        
        deleteButton.click();

    
        try {
            Thread.sleep(2000);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("deletedUserPage"));  
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertTrue(alertText.contains("User deleted"));
            alert.accept();
        } catch (NoAlertPresentException e) {
            fail("Alert was not shown after deletion");
        }

    
    }
    
    private void assertFalse(boolean contains, String string) {
		// TODO Auto-generated method stub
		
	}

	@AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}
