package UserProfile;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class FollowUnfollowUsers {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        driver.manage().window().maximize();

        
        driver.get("http://localhost:3000/login");

     
        driver.findElement(By.id("email")).sendKeys("nupurg1905@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Nupur@123");
        driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")).click();        
        
    }

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        } 
    }
    @Test
    public void testFollowUser() {
        // Wait for the search bar and enter the user's name
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/nav/input"))); // Update XPath as needed
        searchBar.sendKeys("Madhav");

        // Scroll search button into view and click it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/nav/button[1]"))); // Update XPath as needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchButton);
        searchButton.click();

        // Wait for search results to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Follow') and @data-username='Madhav']"))); // Update locator if needed

        // Scroll to the Follow button
        WebElement followButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Follow') and @data-username='Madhav']"))); // Update locator if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", followButton);

        // Click the Follow button
        followButton.click();

        // Add optional success validation
        //System.out.println("Follow button clicked successfully.");
    }


    }


