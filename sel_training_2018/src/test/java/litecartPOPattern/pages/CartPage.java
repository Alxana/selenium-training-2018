package litecartPOPattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".items .item")
    List<WebElement> itemsInCart;

    @FindBy(css = ".items .item button[name=remove_cart_item]")
    WebElement removeCartItem;

    @FindBy(css = "#order_confirmation-wrapper table tr")
    List<WebElement> itemsInTable;

    public List<WebElement> getItemsInCart() {
        return itemsInCart;
    }

    public CartPage removeVisibleItem() {
        removeCartItem.click();
        return this;
    }

    public void waitForTableToUpdate() {
        wait.until(stalenessOf(driver.findElement(By.cssSelector("#order_confirmation-wrapper"))));
    }

    public int getItemsInTableCount(){
        return itemsInTable.size() - 5;
    }
}
