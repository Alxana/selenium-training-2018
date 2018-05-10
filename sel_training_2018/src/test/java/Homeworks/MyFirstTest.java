package Homeworks;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;


import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
 //               Files.copy(tempFile, new File("screen.png"));
                Files.copy(tempFile, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }

    @Before
    public void start(){
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(cap);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void getBrowserLogs() {
        driver.get("http://selenium2.ru");
        System.out.println(driver.manage().logs().getAvailableLogTypes());
//        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
        driver.manage().logs().get("performance").forEach(l -> System.out.println(l));

    }

    @Test
    public void MyFirstTest(){
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
//        driver.findElement(By.name("btnK")).click();
        driver.findElement(By.name("_btnK")).click(); //bad locator
        wait.until(titleIs("webdriver - Пошук Google"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

