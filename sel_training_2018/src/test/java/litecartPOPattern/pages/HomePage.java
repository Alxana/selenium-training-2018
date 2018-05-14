package litecartPOPattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HomePage extends Page{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage open(){
        driver.get("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        return this;
    }

    @FindBy(css = ".content .products .product")
    List<WebElement> products;

    public void openProductByIndex(int index) {
        products.get(0).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-product.box")));
    }
}
