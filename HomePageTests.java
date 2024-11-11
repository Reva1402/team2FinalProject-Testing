package hometesting;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

import java.time.Duration;

class HomePageTests {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
           
            driver.get("http://localhost:3000/userhomepage");
            System.out.println("Successfully connected to http://localhost:3000/userhomepage");
        } catch (Exception e) {
            System.err.println("Could not connect to http://localhost:3000/userhomepage. Ensure the server is running.");
            fail("Failed to connect to application server.");
        }
    }


    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Test Case 1: Verify User Greeting
    @Test
    void testUserGreeting() {
        try {
            
            WebElement greeting = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/nav")) .findElement(By.xpath("//*[@id=\"root\"]/div/div/nav/span"));       

            assertTrue(greeting.isDisplayed(), "Greeting should be visible");
            assertTrue(greeting.getText().contains("Hi, "), "Greeting should start with 'Hi, '");
        } catch (Exception e) {
            fail("User greeting element not found or incorrect selector: " + e.getMessage());
        }
    }

   



   
    @Test
    void testMyEventsLinkVisibleForLoggedInUser() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

           ]
            driver.get("http://localhost:3000/login"); 

          
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))); 
            emailField.sendKeys("r1@gmail.com"); 

            WebElement passwordField = driver.findElement(By.id("password")); 
            passwordField.sendKeys("AMRITSAR"); 

            
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]")); 
            loginButton.click();

           
            WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));

          
            WebElement myEventsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(., 'My Events')]")));

         
            assertTrue(myEventsLink.isDisplayed(), "'My Events' link should be visible for logged-in users.");

        } catch (Exception e) {
            fail("'My Events' link not found or incorrect selector: " + e.getMessage());
        }
    }



    

    @Test
    void testSearchBarFunctionality() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
  
        driver.get("http://localhost:3000/login");

    
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR"); 

      
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();

       
        WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-profile")));
        assertTrue(userProfile.isDisplayed(), "User should be logged in and see the user profile.");

        
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-bar")));

        
        searchBar.sendKeys("dance"); 
        searchBar.submit();

        WebElement resultsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));
        assertTrue(resultsSection.isDisplayed(), "Search results should be displayed after a query is submitted.");
    }
    
    @Test
    void testLogoutFunctionality() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutButton);
        logoutButton.click();
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "User should be redirected to login page after logging out.");
        System.out.println("Logout successful: Navigated back to login page.");
    }





   
   

    @Test
    void testProfileLinkNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("http://localhost:3000/login");

      
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");

    
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));
        WebElement profileLink = navbar.findElement(By.xpath("//li[contains(text(), 'Profile')]")); // Adjust if needed
        profileLink.click();
        wait.until(ExpectedConditions.urlContains("/userProfile"));
        assertTrue(driver.getCurrentUrl().contains("/userProfile"), "Should navigate to Profile page");
    }

    
    @Test
    void testCreateEventLinkNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));
        WebElement createEventLink = navbar.findElement(By.xpath("//li[contains(text(), 'Create An Event')]"));
        createEventLink.click();
        wait.until(ExpectedConditions.urlContains("/createevent"));
        System.out.println("Successfully navigated to Create Event page.");
    }


    @Test
    void testMyEventsLinkNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

      
        driver.get("http://localhost:3000/login");

      
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));
        WebElement myEventsLink = navbar.findElement(By.xpath("//li[contains(text(), 'My Events')]"));
        myEventsLink.click();
        wait.until(ExpectedConditions.urlContains("/events"));
        System.out.println("Successfully navigated to My Events page.");
    }


   
    @Test
    void testNotificationsLinkNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

     
        driver.get("http://localhost:3000/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
   
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));
        WebElement notificationsLink = navbar.findElement(By.xpath("//li[contains(text(), 'Notifications')]"));
        notificationsLink.click();
        wait.until(ExpectedConditions.urlContains("/notifications"));
        System.out.println("Successfully navigated to Notifications page.");
    }

    @Test
    void testFollowersLinkNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

 
        driver.get("http://localhost:3000/login");

    
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");

   
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar")));
        WebElement followersLink = navbar.findElement(By.xpath("//li[contains(text(), 'Followers')]"));
        followersLink.click();
        wait.until(ExpectedConditions.urlContains("/followers"));
        System.out.println("Successfully navigated to Followers page.");
    }
    

    @Test
    void testPrivacyPolicyLinkOnUserHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

   
        driver.get("http://localhost:3000/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();

    
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");
        WebElement privacyLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Privacy Policy")));
        privacyLink.click();
        System.out.println("Current URL after clicking Privacy Policy link: " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.urlContains("/privacy-policy"));
        assertTrue(driver.getCurrentUrl().contains("/privacy-policy"), "Should navigate to Privacy Policy page.");
    }
    
    @Test
    void testAboutLinkOnUserHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    
        driver.get("http://localhost:3000/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();

   
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");

      
        WebElement aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("About")));
        aboutLink.click();
        System.out.println("Current URL after clicking About link: " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.urlContains("/about"));
        assertTrue(driver.getCurrentUrl().contains("/about"), "Should navigate to About page.");
    }
    
    @Test
    void testContactUsLinkOnUserHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        
        driver.get("http://localhost:3000/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();

        
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");

     
        WebElement contactLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Contact Us")));
        contactLink.click();

    
        System.out.println("Current URL after clicking Contact Us link: " + driver.getCurrentUrl());

    
        wait.until(ExpectedConditions.urlContains("/contact"));
        assertTrue(driver.getCurrentUrl().contains("/contact"), "Should navigate to Contact Us page.");
    }
    
    @Test
    void testTermsAndConditionsLinkOnUserHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    
        driver.get("http://localhost:3000/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("r1@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("AMRITSAR");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginButton.click();

      
        wait.until(ExpectedConditions.urlContains("/userhomepage"));
        System.out.println("Login successful: Navigated to user homepage.");

    
        WebElement termsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Terms and Conditions")));
        termsLink.click();

     
        System.out.println("Current URL after clicking Terms and Conditions link: " + driver.getCurrentUrl());

       
        wait.until(ExpectedConditions.urlContains("/terms-and-conditions"));
        assertTrue(driver.getCurrentUrl().contains("/terms-and-conditions"), "Should navigate to Terms and Conditions page.");
    }



    @Test
    void testLikeButton() {
        try {
           
            WebElement likeButton = driver.findElement(By.className("like-btn")); 

           
            likeButton.click();
            assertFalse(likeButton.isEnabled(), "Like button should be disabled after clicking");
        } catch (Exception e) {
            fail("Like button not found or incorrect selector: " + e.getMessage());
        }
    }
    
    @Test
    void testUnlikeButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

       
            WebElement likeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("like-btn")));

    
            likeButton.click();

            WebElement unlikeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("unlike-btn")));

            unlikeButton.click();
      
            assertTrue(likeButton.isEnabled(), "Like button should be re-enabled after unliking");
            
            System.out.println("Unlike button test passed: Like button re-enabled after unliking.");
        } catch (Exception e) {
            fail("Unlike button not found or incorrect selector: " + e.getMessage());
        }
    }
    
   @Test
    void testAddComment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        try {
         
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

            
            WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[3]/form/input")));
            commentInput.sendKeys("superbB1.");

            // 
            WebElement submitCommentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[3]/form/button")));
            submitCommentButton.click();

            
            WebElement commentList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comments-list")));
            
           
            boolean isCommentPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("comments-list"), "superbB."));
            assertTrue(isCommentPresent, "The comment should appear in the comments list.");

            System.out.println("Comment successfully added and verified.");

        } catch (Exception e) {
            fail("Comment functionality failed or incorrect selector: " + e.getMessage());
        }
    }

    
    @Test
    void testReportComment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        try {
           
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");
            WebElement eventButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".attend-btn")));
            eventButton.click();
            System.out.println("Navigated to the specific event page.");

         
            WebElement commentList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comments-list")));
            List<WebElement> comments = commentList.findElements(By.tagName("li"));
            System.out.println("Number of comments found: " + comments.size());

            if (comments.isEmpty()) {
                fail("No comments found in the comments list.");
            }

            WebElement reportButton = comments.get(0).findElement(By.className("report-comment-btn"));
            reportButton.click();
            System.out.println("Clicked on report button for the first comment.");
            WebElement reportedText = comments.get(0).findElement(By.className("report-comment-btn"));
            wait.until(ExpectedConditions.textToBePresentInElement(reportedText, "Reported"));
            assertEquals("Reported", reportedText.getText(), "Comment should be marked as reported after clicking the report button.");

        } catch (Exception e) {
            System.out.println("Full page source:\n" + driver.getPageSource());
            fail("Report comment functionality failed: " + e.getMessage());
        }
    }
    
    @Test
    void testGiveAttendance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

            
            WebElement attendanceButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/button[1]")));
            attendanceButton.click();
            System.out.println("Clicked on 'Give Attendance' button for the event.");

           
            Boolean attendanceConfirmation = wait.until(ExpectedConditions.textToBePresentInElement(attendanceButton, "Attendance Given"));
            assertEquals("Attendance Given", attendanceButton.getText(), "The attendance button should confirm attendance after clicking.");

            System.out.println("Attendance successfully marked for the event.");

        } catch (Exception e) {
      
            fail("Give Attendance functionality failed: " + e.getMessage());
        }
    }

    @Test
    void testEventNameVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

           
            WebElement eventName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
            assertTrue(eventName.isDisplayed(), "Event name should be visible.");
            System.out.println("Event name is visible: " + eventName.getText());

        } catch (Exception e) {
           
            fail("Event name visibility test failed: " + e.getMessage());
        }
    }

    @Test
    void testEventDateVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

           
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

          
            WebElement eventDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
            assertTrue(eventDate.isDisplayed(), "Event date should be visible.");
            System.out.println("Event date is visible: " + eventDate.getText());

        } catch (Exception e) {
            System.out.println("Full page source:\n" + driver.getPageSource());
            fail("Event date visibility test failed: " + e.getMessage());
        }
    }
    
    @Test
    void testEventPictureVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

           
            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

         
            WebElement eventPicture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".event-image")));
            assertTrue(eventPicture.isDisplayed(), "Event picture should be visible.");
            System.out.println("Event picture is visible with source: " + eventPicture.getAttribute("src"));

        } catch (Exception e) {
            System.out.println("Full page source:\n" + driver.getPageSource());
            fail("Event picture visibility test failed: " + e.getMessage());
        }
    }



    
    

    @Test
    void testEventTimeVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
           
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

            WebElement eventTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".event-time")));
            assertTrue(eventTime.isDisplayed(), "Event time should be visible.");
            System.out.println("Event time is visible: " + eventTime.getText());

        } catch (Exception e) {
            System.out.println("Full page source:\n" + driver.getPageSource());
            fail("Event time visibility test failed: " + e.getMessage());
        }
    }

    @Test
    void testEventLocationVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
          
            driver.get("http://localhost:3000/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("r1@gmail.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("AMRITSAR");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            wait.until(ExpectedConditions.urlContains("/userhomepage"));
            System.out.println("Login successful: Navigated to user homepage.");

            WebElement eventLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
            assertTrue(eventLocation.isDisplayed(), "Event location should be visible.");
            System.out.println("Event location is visible: " + eventLocation.getText());

        } catch (Exception e) {
            System.out.println("Full page source:\n" + driver.getPageSource());
            fail("Event location visibility test failed: " + e.getMessage());
        }
    }

    










}
