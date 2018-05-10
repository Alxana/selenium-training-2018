package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBrowserLogs extends TestBase {

    private List<WebElement> getProducts() {
        return driver.findElements(By.cssSelector(".dataTable .row td:nth-child(3) a[href*='product_id']"));
    }


    @Test
    public void CheckLogs() {
        LoginAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        WebElement ducksCatalog = driver.findElement(By.cssSelector(".dataTable .fa-folder"));

        if (ducksCatalog.getText().equals("Rubber Ducks")) {ducksCatalog.click();}

        for (int i=0; i<getProducts().size(); i++) {
            getProducts().get(i).click();
            wait.until(driver -> driver.getTitle().contains("Edit Product"));
            driver.manage().logs().get("browser").forEach( l -> System.out.println("new log: " + l));
            driver.findElement(By.cssSelector("button[name=cancel]")).click();
            wait.until(driver -> driver.getTitle().contains("Catalog"));
        }
    }
}
