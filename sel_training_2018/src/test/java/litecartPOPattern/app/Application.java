package litecartPOPattern.app;

import litecartPOPattern.pages.CartPage;
import litecartPOPattern.pages.HomePage;
import litecartPOPattern.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    private WebDriver driver;

    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }

    public void addProductToCart(int index) {
        homePage.open().openProductByIndex(index);
        productPage.selectProductSize(1).addProductToCart().waitForCartToRefresh();
    }

    public void Checkout() {
        homePage.clickCheckout();
    }

    public boolean removeAllFromCart() {
        boolean isSuccess = true; // successful removal flag
        int itemsInTableCount; // number of items in table of products added to cart

        for(WebElement item: cartPage.getItemsInCart()){
            itemsInTableCount = cartPage.getItemsInTableCount(driver);
            cartPage.removeVisibleItem().waitForTableToUpdate();

            if (itemsInTableCount == cartPage.getItemsInTableCount(driver)){ // if number of items in table is not changed, then remove was not done

                System.out.println("remove was not successful");
                return isSuccess = false;
            }
        }
        return isSuccess;
    }
}
