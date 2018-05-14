package litecartPOPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[name='options[Size]'")
    List<WebElement> sizeSelect;

    @FindBy(css = "button[type=submit][name=add_cart_product]")
    WebElement addToCartButton;

    public ProductPage selectProductSize(int index) {
        if (sizeSelect.size() > 0) {
            new Select(sizeSelect.get(0)).selectByIndex(index);
        }

        return this;
    }

    public ProductPage addProductToCart() {
        addToCartButton.click();
        return this;
    }
}
