package litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginAdminIE {

    private WebDriver driver;
    private WebDriverWait wait;
    private String username ="admin";
    private String pass = "admin";

    @Before
    public void start(){
        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Login(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pass);
//        driver.findElement(By.name("login")).click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",  driver.findElement(By.name("login")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"notices\"]/div[2]"), "You are now logged in as admin" ));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
