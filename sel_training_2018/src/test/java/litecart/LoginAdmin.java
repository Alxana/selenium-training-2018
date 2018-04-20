package litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginAdmin {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String username ="admin";
    private String pass = "admin";

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public void Login(){
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"notices\"]/div[2]"), "You are now logged in as admin" ));
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
            }
        }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean areElementsPresent(WebElement el, By locator) {
        return el.findElements(locator).size() > 0;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
