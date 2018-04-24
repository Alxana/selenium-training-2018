package litecart;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class CheckProductPageCorrectness extends TestBase {

    public Product productOnMainPage = new Product();
    public Product productOnDetailPage = new Product();



    @Test
    public void ProductPageCorrectnessTest(){
        LoginApp();

        WebElement firstCampaignProduct = driver.findElement(By.cssSelector("#box-campaigns .product"));
        productOnMainPage.initProductMainPage(firstCampaignProduct);

        System.out.println(productOnMainPage);

        assertTrue("Regular price on main page product is not grey!", productOnMainPage.getRegularPriceColor().equals("grey"));
        assertTrue("Regular price on main page product is not crossed out!", productOnMainPage.getRegularPriceStyle().equals("s"));
        assertTrue("Campaign price on main page product color is not red!", productOnMainPage.getCampaignPriceColor().equals("red"));
        assertTrue("Campaign price on main page product is not bold!", productOnMainPage.getCampaignPriceStyle().equals("strong"));
        assertTrue("Campaign price on main page product is not bigger than regular price!", productOnMainPage.isCampaignPriceBigger());
        System.out.println("Product on main page is correctly formatted");

//        firstCampaignProduct.click();

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        WebElement firstCampaignProductDetail = driver.findElement(By.cssSelector("#box-product"));
        productOnDetailPage.initProductDetailPage(firstCampaignProductDetail);

        System.out.println(productOnDetailPage);

        assertTrue("Regular price on details page product is not grey!", productOnDetailPage.getRegularPriceColor().equals("grey"));
        assertTrue("Regular price on details page product is not crossed out!", productOnDetailPage.getRegularPriceStyle().equals("s"));
        assertTrue("Campaign price on details page product color is not red!", productOnDetailPage.getCampaignPriceColor().equals("red"));
        assertTrue("Campaign price on details page product is not bold!", productOnDetailPage.getCampaignPriceStyle().equals("strong"));
        assertTrue("Campaign price on details page product is not bigger than regular price!", productOnDetailPage.isCampaignPriceBigger());
        System.out.println("Product on detail page is correctly formatted");

        assertTrue("Product on Main page is not the same that on a Detail page", productOnMainPage.equals(productOnDetailPage));
        System.out.println("Correct Product page is open.");


    }


}