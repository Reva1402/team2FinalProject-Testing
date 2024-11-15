package Testingprojectfinal;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;



class LikeandComments {

	 private WebDriver driver;
	    private WebDriverWait wait;

	    @Before
	    public void setUp() {
	        
	        driver = new ChromeDriver();
	        
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        driver.get("http://localhost:3000/login");

	        driver.findElement(By.id("email")).sendKeys("rachnaaulakh@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123456789");
	        
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
	    public void testLikeButton() {
	        
	        WebElement likeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[4]/button[1]")));
	        likeButton.click();

	        assertTrue("Like button should be disabled after clicking", !likeButton.isEnabled());
	    }

	    @Test
	    public void testUnlikeButtonVisibility() {
	        
	        WebElement unlikeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[5]/button[2]")));

	        assertTrue("Unlike button should be visible", unlikeButton.isDisplayed());
	    }
	    
	    @Test
	    public void testUnlikeButtonIsClickable() {
	        System.out.println(driver.getPageSource());

	        try {
	            
	            WebElement unlikeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[5]/button[2]")));

	            Actions actions = new Actions(driver);
	            actions.moveToElement(unlikeButton).perform();

	            unlikeButton = wait.until(ExpectedConditions.elementToBeClickable(unlikeButton));
	            unlikeButton.click();

	            WebElement likeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[5]/button[1]")));
	            
	            assertTrue("Like button should be visible after unliking", likeButton.isDisplayed() && likeButton.isEnabled());

	        } catch (Exception e) {
	            System.out.println("Exception occurred: " + e.getMessage());
	            throw e;
	        }}

	    @Test
	    public void testPostComment() {
	        
	        WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[4]/div[2]/form/input")));
	        commentInput.sendKeys("This is a test comment");

	        WebElement postCommentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[4]/div[2]/form/button")));
	        postCommentButton.click();

	        WebElement postedComment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[4]/div[2]/ul/li[8]")));
	        assertTrue("Comment should be posted and visible", postedComment.isDisplayed());
	    }

	   
	    }


