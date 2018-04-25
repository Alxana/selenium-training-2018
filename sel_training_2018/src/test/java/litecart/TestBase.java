package litecart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String username ="admin";
    private String pass = "admin";

    @Before
    public void start(){
        driver = new ChromeDriver();
//      driver = new FirefoxDriver();
//      driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    public void LoginAdmin(){
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"notices\"]/div[2]"), "You are now logged in as admin" ));
    }

    public void LogoutAdmin(){
        driver.findElement(By.cssSelector("#sidebar .header a[title=Logout]")).click();
    }

    public void OpenApp(){
        driver.get("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
    }

    public void LoginApp(String username, String pass){
        driver.findElement(By.cssSelector("form[name=login_form] input[name=email]")).sendKeys(username);
        driver.findElement(By.cssSelector("form[name=login_form] input[name=password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type=submit][name=login]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#notices-wrapper #notices")));
    }

    public void LogoutApp(){
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
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

    public String getNoticeMessage(){
        return driver.findElement(By.cssSelector("#notices-wrapper #notices")).getText();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
