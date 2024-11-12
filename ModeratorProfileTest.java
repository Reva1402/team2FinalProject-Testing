package myevents;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class ModeratorProfileTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prism Infotech\\eclipse-workspace\\event\\src\\myevents\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Before
    public void setUp() {
        driver.manage().deleteAllCookies();
        simulateLoginSession();
        navigateToModeratorProfile();
    }

    private void simulateLoginSession() {
//        Cookie authCookie = new Cookie("auth_token", "your-valid-authentication-token-here");
//        driver.manage().addCookie(authCookie);
        driver.get("http://localhost:3000/ModeratorProfile");
    }

    private void navigateToModeratorProfile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    // 8 Passing Test Cases
    @Test
    public void testProfileHeaderVisibility() {
        try {
            // Wait for the profile header element to be visible on the page
            WebElement profileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-header")));

            // Assert that the profile header is displayed
            assertTrue("Profile header should be visible", profileHeader.isDisplayed());

        } catch (Exception e) {
            // If the profile header is not visible or cannot be found, fail the test with a detailed message
            fail("Profile header element could not be found or is not visible: " + e.getMessage());
        }
    }


    @Test
    public void testProfileImageVisibility() {
        try {
            // Wait for the element to be visible on the page
            WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-image")));
            
            // Assert that the profile image is displayed
            assertTrue("Profile image should be visible", profileImage.isDisplayed());
            
        } catch (Exception e) {
            // If the element isn't found or is not visible, fail the test with an appropriate message
            fail("Profile image element could not be found or is not visible: " + e.getMessage());
        }
    }




//    @Test
//    public void testModeratorNameIsCorrect() {
//        WebElement moderatorName = driver.findElement(By.id("moderator-name"));
//        assertEquals("Moderator Name", moderatorName.getText());
//    }

//    @Test
//    public void testUserEmailAddress() {
//        WebElement userEmail = driver.findElement(By.id("user-email"));
//        assertEquals("moderator@example.com", userEmail.getText());
//    }

    @Test
    public void testLogoutButtonVisibility() {
        try {
            // Wait for the logout button to be visible in the DOM
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-button")));

            // Debugging: Check the actual state of the button
            System.out.println("Logout button is displayed: " + logoutButton.isDisplayed());

            // Assert that the logout button is displayed
            assertTrue("Logout button should be visible", logoutButton.isDisplayed());
        } catch (Exception e) {
            // If the logout button is not found or not visible, fail the test
            fail("Logout button element could not be found or is not visible: " + e.getMessage());
        }
    }


    
    @Test
    public void testUrlNavigation() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL should contain '/ModeratorProfile'", currentUrl.contains("/ModeratorProfile"));
    }

    // 8 Failing Test Cases
    @Test
    public void testInvalidProfileHeader() {
        try {
            WebElement invalidElement = driver.findElement(By.className("invalid-header"));
            assertTrue("Element should not exist", invalidElement.isDisplayed());
        } catch (Exception e) {
            assertFalse("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }

    @Test
    public void testIncorrectProfileImage() {
        try {
            // Find the profile image element
            WebElement profileImage = driver.findElement(By.className("profile-image"));
            
            // Wait until the image is loaded (if necessary)
            wait.until(ExpectedConditions.attributeContains(profileImage, "src", "http"));
            
            // Get the src attribute of the profile image
            String imageSrc = profileImage.getAttribute("src");
            
            // Assert that the image is not pointing to an invalid image source
            assertFalse("Profile image should not be invalid-image.jpg", imageSrc.contains("invalid-image.jpg"));
        } catch (Exception e) {
            // If the image is not found, fail the test
            fail("Profile image element could not be found or there was an error: " + e.getMessage());
        }
    }


    
    @Test
    public void testNonExistentEmailAddress() {
        try {
            // Find the email element
            WebElement userEmail = driver.findElement(By.id("user-email"));

            // Get the actual text of the email
            String actualEmail = userEmail.getText();

            // Check that the actual email is not the incorrect email
            assertNotEquals("The email address should not be incorrect@example.com", "incorrect@example.com", actualEmail);
        } catch (Exception e) {
            // Handle exception, if email element is not found, test will fail gracefully
            fail("The email element could not be found on the page or another error occurred: " + e.getMessage());
        }
    }

    @Test
    public void testNonExistentEditProfileButton() {
        try {
            WebElement nonExistentButton = driver.findElement(By.id("non-existent-edit-button"));
            assertTrue("Edit button should not exist", nonExistentButton.isDisplayed());
        } catch (Exception e) {
            assertFalse("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }

    @Test
    public void testIncorrectUrlNavigation() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL should not contain '/WrongModeratorProfile'", currentUrl.contains("/WrongModeratorProfile"));
    }

    @Test
    public void testMissingProfileInformationSection() {
        try {
            WebElement missingProfileInfo = driver.findElement(By.className("missing-profile-info"));
            assertTrue("Profile info section should not exist", missingProfileInfo.isDisplayed());
        } catch (Exception e) {
            assertFalse("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }

    @Test
    public void testNoLogoutButton() {
        try {
            WebElement logoutButton = driver.findElement(By.id("non-existent-logout-button"));
            assertTrue("Logout button should not exist", logoutButton.isDisplayed());
        } catch (Exception e) {
            assertFalse("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
    
    @Test
    public void DeleteProfileButtonVisible() {
        try {
            WebElement deleteprofile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/button[2]"));
            assertFalse("Delete profile button should not exist", deleteprofile.isDisplayed());
        } catch (Exception e) {
            assertTrue("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
    
    @Test
    public void EditProfileButtonVisible() {
        try {
            WebElement editprofile = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/button[1]"));
            assertFalse("Edit profile button should not exist", editprofile.isDisplayed());
        } catch (Exception e) {
            assertTrue("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
    
    @Test
    public void ProfileContentVisible() {
        try {
            WebElement profilecontent = driver.findElement(By.xpath("/html/body/div/div/div/div[2]"));
            assertFalse("Edit profile button should not exist", profilecontent.isDisplayed());
        } catch (Exception e) {
            assertTrue("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
    
    @Test
    public void PersonalinfoVisible() {
        try {
            WebElement personalinfo = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]"));
            assertFalse("Edit profile button should not exist", personalinfo.isDisplayed());
        } catch (Exception e) {
            assertTrue("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
    
    @Test
    public void ModerationStats() {
        try {
            WebElement moderationstats = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]"));
            assertFalse("Edit profile button should not exist", moderationstats.isDisplayed());
        } catch (Exception e) {
            assertTrue("Expected exception: NoSuchElementException", e instanceof org.openqa.selenium.NoSuchElementException);
        }
    }
}
