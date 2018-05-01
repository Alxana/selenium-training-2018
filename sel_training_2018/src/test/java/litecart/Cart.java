package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Cart {


    WebElement cartElement;

    public void waitForCartToRefresh(WebDriverWait wait){
        int counter = Integer.parseInt(this.getCartElement().getText()) + 1;
        wait.until(attributeToBe(this.getCartElement(), "textContent", Integer.toString(counter)));
    }

    public void Checkout(WebDriver driver) {
        driver.findElement(By.cssSelector("#cart a")).click();
    }

    public WebElement getCartElement() {
        return cartElement;
    }

    public void setCartElement(WebDriver driver) {
        this.cartElement = driver.findElement(By.cssSelector("#cart .quantity"));
    }

    public boolean removeAllFromCart(WebDriver driver, WebDriverWait wait) {
        boolean isSuccess = true; // successful removal flag
        int itemsInTableCount; // number of items in table of products added to cart
        WebElement itemsTable;
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".items .item"));


        for(WebElement item: itemsInCart){
            itemsInTableCount = getItemsInTableCount(driver);
            itemsTable = driver.findElement(By.cssSelector("#order_confirmation-wrapper"));

            driver.findElement(By.cssSelector(".items .item button[name=remove_cart_item]")).click(); //remove the first visible item

            wait.until(stalenessOf(itemsTable)); // waiting for table to refresh

            // if number of items in table is not changed, then remove was not done
            if (itemsInTableCount == getItemsInTableCount(driver)){

                System.out.println("remove was not successful");
                return isSuccess = false;
            }
        }
        return isSuccess;
    }

    private int getItemsInTableCount(WebDriver driver){
        return driver.findElements(By.cssSelector("#order_confirmation-wrapper table tr")).size() - 5;
    }
}
