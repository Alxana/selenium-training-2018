package litecart;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartTest extends TestBase {

    int productNum = 3;
    Cart cart = new Cart();

    private void addFirstProductToCart() throws InterruptedException {

        // add first product to cart
        driver.findElements(By.cssSelector(".content .products .product")).get(0).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-product.box")));

        // select Size if this element presents
        if(isElementPresent(driver, By.cssSelector("select[name='options[Size]'"))){
            Select sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]'")));
            sizeSelect.selectByIndex(1);
        }

        cart.setCartElement(driver);

        // click Add to Cart button
        driver.findElement(By.cssSelector("button[type=submit][name=add_cart_product]")).click();
    }



    @Test
    public void CartTest() throws InterruptedException {
        OpenApp();

        for (int i=0; i<productNum; i++) {

            addFirstProductToCart();

            cart.waitForCartToRefresh(wait);

            // go to homepage
            driver.findElement(By.cssSelector(".fa-home")).click();
        }

        cart.Checkout(driver);

        assertTrue(cart.removeAllFromCart(driver, wait));
        System.out.println("All items removed");
    }

}
