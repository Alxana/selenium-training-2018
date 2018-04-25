package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoThroughAdminMenu extends TestBase {

    private List<WebElement> getMainCategories(){
        return driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
    }

    private List<WebElement> getSubCategories(WebElement mainCategory){
        return mainCategory.findElements(By.cssSelector("li[id^=doc]:not(.selected)"));
    }

    private void checkHeader(){
        if (areElementsPresent(driver, By.cssSelector("h1"))){
            System.out.println(driver.findElement(By.cssSelector("h1")).getText());}
        else System.out.println("No header");
    }


    @Test
    public void GoThroughAdminMenu(){

        LoginAdmin();

        for (int i=0; i<getMainCategories().size(); i++){
            getMainCategories().get(i).click();
            checkHeader();

            for(int j=0; j<getSubCategories(getMainCategories().get(i)).size(); j++) {
                getSubCategories(getMainCategories().get(i)).get(j).click();
                checkHeader();
            }

        }
}
}
