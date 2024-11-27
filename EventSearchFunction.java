package UserProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class EventSearchFunction {
    private WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        
        driver = new ChromeDriver();
        
        
        driver.get("http://localhost:3000/login");
        
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("nupurg1905@gmail.com");

        driver.findElement(By.id("password")).sendKeys("Nupur@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/form/div[3]/button[1]")));
        loginButton.click();
    }

    @Test
    public void testSearchAndOpenEvent() throws InterruptedException { 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/nav/input"))); 
        searchBar.sendKeys("location check");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/nav/button[1]")));
        searchButton.click();
        

        WebElement searchResultContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/div/div/div"))); 

        searchResultContainer.click();

        WebElement eventTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/h1"))); 

        System.out.println("Event Title Text: " + eventTitle.getText());

        assertTrue(eventTitle.getText().toLowerCase().contains("location check"));
    }

    @After
    public void tearDown() throws InterruptedException {
       
        Thread.sleep(2000);

        if (driver != null) {
            driver.quit();
        }
    }
}

