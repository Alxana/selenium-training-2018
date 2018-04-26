package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddNewProduct extends TestBase {

    String productName = "Teddy Bear";
    String code = "123450";
    String imagePath = "/src/test/resources/Brown-Teddy-Bear.jpg";
    String dateFrom = "2018-05-01";
    String dateTo = "2018-05-31";
    String quantity = "5";
//    String manufacturer = "ACME Corp.";
    String keywords = "toy, teddy, kid";
    String shortDescr = "Brown Teddy Bear";
    String descr = "Cute brown Teddy Bear for children";
    String purchasePrice = "5.00";
    String currency = "US Dollars";
    String price = "10.00";


    private void tabSwitcher(int i) {
        driver.findElements(By.cssSelector(".tabs .index li")).get(i).click();
    }

    private void datePicker(String selector, String value) {
        ((JavascriptExecutor) driver).executeScript(String.format("$('%s').val('%s');", selector, value));
    }

    private void submitForm() {
        driver.findElement(By.cssSelector("button[type=submit][name=save]")).click();
    }

    @Test
    public void NewProductCreation() {

        LoginAdmin();

        // Open Catalog
        driver.findElement(By.cssSelector("#box-apps-menu li#app-:nth-child(2) a")).click();

        //click Add New Product button
        driver.findElement(By.xpath("//*[@id='content']/div[1]/a[2]")).click();

        //Fill General tab info
        WebElement generalTab = driver.findElement(By.cssSelector("#tab-general"));

        // status = Enabled
        generalTab.findElements(By.cssSelector("input[type=radio][name=status]")).get(0).click();
        // name
        generalTab.findElement(By.cssSelector("input[type=text][name^=name")).sendKeys(productName);
        // code
        generalTab.findElement(By.cssSelector("input[type=text][name=code")).sendKeys(code);
        // product group = unisex
        generalTab.findElements(By.cssSelector("input[type=checkbox][name='product_groups[]'")).get(2).click();
        // quantity
        generalTab.findElement(By.cssSelector("input[type=number][name=quantity]")).sendKeys(quantity);

        // File upload
        String absFilePath = System.getProperty("user.dir") + imagePath;
        generalTab.findElement(By.cssSelector("input[type=file][name='new_images[]'")).sendKeys(absFilePath);

        // Data selection
        datePicker("input[name=date_valid_from]", dateFrom);
        datePicker("input[name=date_valid_to]", dateTo);

        //go to Information tab
        tabSwitcher(1);
        WebElement infoTab = driver.findElement(By.cssSelector("#tab-information"));

        //fill Information tab
        Select manufacturerSelect = new Select(infoTab.findElement(By.cssSelector("select[name=manufacturer_id]")));
        manufacturerSelect.selectByIndex(1);

        infoTab.findElement(By.cssSelector("input[name=keywords]")).sendKeys(keywords);
        infoTab.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys(shortDescr);
        infoTab.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(descr);

        //go to Prices tab
        tabSwitcher(3);
        WebElement pricesTab = driver.findElement(By.cssSelector("#tab-prices"));

        //fill Prices tab
        pricesTab.findElement(By.cssSelector("input[name=purchase_price")).clear();
        pricesTab.findElement(By.cssSelector("input[name=purchase_price")).sendKeys(purchasePrice);

        Select currencySelect = new Select(pricesTab.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        currencySelect.selectByVisibleText(currency);

        pricesTab.findElement(By.cssSelector("input[name^=prices]")).sendKeys(price);

        submitForm();

        // check if product is added
        List<WebElement> catalog = driver.findElements(By.cssSelector("form[name=catalog_form] .dataTable tr.row"));
        for (WebElement product : catalog) {
            if (product.getText().equals(productName)) {
                System.out.println("New product " + productName + " is added successfully");
                break;
            }
        }
    }
}
