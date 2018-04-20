package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckStickers extends LoginAdmin {

    List<WebElement> products;
    List<WebElement> sticker;
    WebElement name;

    private List<WebElement> getAllProducts(){
        return driver.findElements(By.cssSelector(".product.column.shadow.hover-light"));
    }
    @Test
    public void CheckStickers(){

        LoginApp();

        products = getAllProducts();

        for(WebElement product: products){
            name = product.findElement(By.className("name"));
            sticker = product.findElements(By.cssSelector("div[class^=sticker]"));
            if (sticker.size() != 1){
                if (sticker.size()==0) {
                    System.out.println("No sticker available for " + name.getText());
                }
                else System.out.println("More than 1 sticker available for " + name.getText());
            }
            else System.out.println( name.getText() + " has 1 sticker");
        }
    }
}
