package litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginAdminFF {

    private WebDriver driver;
    private WebDriverWait wait;
    private String username ="admin";
    private String pass = "admin";

    @Before
    public void start(){
        // --- Nightly ---
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files (x86)\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);

        // --- old schema for Selenium v < 3.3 ---
//      DesiredCapabilities caps = new DesiredCapabilities();
//      caps.setCapability(FirefoxDriver.MARIONETTE, false);
//      driver = new FirefoxDriver(caps);

        // --- new schema ---
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Login(){
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"notices\"]/div[2]"), "You are now logged in as admin" ));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
