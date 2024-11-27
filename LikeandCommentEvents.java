package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class LikeandCommentEvents {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        
        driver = new ChromeDriver();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("http://localhost:3000/login");

        driver.findElement(By.id("email")).sendKeys("nupurg1905@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Nupur@123");
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
        loginButton.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userhomepage"));
    }
    
    @After
    public void tearDown() {
        
        if (driver != null) {
            driver.quit();
        }}

    @Test
    public void testLikeButtonIsVisible() {

        WebElement likeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/button[1]")));
        assertTrue("Like button should be visible", likeButton.isDisplayed());
    }

    @Test
    public void testUnlikeButtonVisibility() {
        
        WebElement unlikeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/button[2]")));

        assertTrue("Unlike button should be visible", unlikeButton.isDisplayed());
    }
    
    @Test
    public void testLikeButtonTextChangesToLiked() {
    
        WebElement likeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/button[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", likeButton);

        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        
      
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likeButton);

     
        wait.until(ExpectedConditions.textToBePresentInElement(likeButton, "Liked"));

     
        assertEquals("Liked", likeButton.getText(), "Button text should change to 'Liked' after clicking.");
    }






    @Test
    public void testPostComment() {
        
        WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/input")));
        commentInput.sendKeys("This is a test comment");

        WebElement postCommentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/button")));
        postCommentButton.click();

        WebElement postedComment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/div/div/div[2]/ul/li")));
        assertTrue("Comment should be posted and visible", postedComment.isDisplayed());
    }

   
    }
