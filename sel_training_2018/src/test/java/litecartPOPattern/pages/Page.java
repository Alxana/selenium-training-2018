package litecartPOPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#cart .quantity")
    WebElement cartQuantity;

    @FindBy(css = ".fa-home")
    WebElement homeButton;

    @FindBy(css = "#cart a")
    WebElement checkoutLink;

    public WebElement getCartQuantity() {return cartQuantity;}

    public void waitForCartToRefresh(){
        int counter = Integer.parseInt(cartQuantity.getText()) + 1;
        wait.until(attributeToBe(this.getCartQuantity(), "textContent", Integer.toString(counter)));
    }

    public WebElement getHomeButton() {return homeButton;}

    public void clickCheckout() {checkoutLink.click();}


}
