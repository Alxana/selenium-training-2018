package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.remove;

public class Product {

    private String name;
    private String regularPrice;
    private String regularPriceColor;
    private String regularPriceStyle;
    private String regularPriceFont;

    private String campaignPrice;
    private String campaignPriceColor;
    private String campaignPriceStyle;
    private String campaignPriceFont;

    private boolean isCampaignPriceBigger;

    // initialize product on Main page
    public void initProductMainPage(WebElement prod){
        setName(prod.findElement(By.cssSelector(".name")).getText());
        initPrices(prod);
    }

    // Initialize product on product detail page
    public void initProductDetailPage(WebElement prod){
        setName(prod.findElement(By.cssSelector("h1.title")).getText());
        initPrices(prod);
    }

    // Init price-related parameters
    private void initPrices(WebElement prod){
        setRegularPrice(prod.findElement(By.cssSelector(".regular-price")).getText());
        setRegularPriceColor(prod.findElement(By.cssSelector(".regular-price")).getCssValue("color"));
        setRegularPriceStyle(prod.findElement(By.cssSelector(".regular-price")).getTagName());
        setRegularPriceFont(prod.findElement(By.cssSelector(".regular-price")).getCssValue("font-size"));
        setCampaignPrice(prod.findElement(By.cssSelector(".campaign-price")).getText());
        setCampaignPriceColor(prod.findElement(By.cssSelector(".campaign-price")).getCssValue("color"));
        setCampaignPriceStyle(prod.findElement(By.cssSelector(".campaign-price")).getTagName());
        setCampaignPriceFont(prod.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size"));
        isCampaignPriceBigger();
    }

    public boolean isCampaignPriceBigger(){
        String str1 = remove(campaignPriceFont, "px");
        String str2 = remove(regularPriceFont, "px");
        if (Double.parseDouble(str1)>Double.parseDouble(str2)) {
            isCampaignPriceBigger = true;
        }
        else isCampaignPriceBigger=false;
        return isCampaignPriceBigger;
    }

    private String parseColor(String color){
        color = remove(color, "rgba(");
        color = remove(color, "rgb(");
        String[] colors = color.substring(0, color.length() - 1 ).split(",");
        int r = Integer.parseInt(colors[0].trim());
        int g = Integer.parseInt(colors[1].trim());
        int b = Integer.parseInt(colors[2].trim());
//        System.out.println(r + " " + g + " " + b);
        if (r==g && g==b){return "grey";}
        else if (r>0 && g==0 && g==b){return "red";}
        else return color;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return isCampaignPriceBigger() == product.isCampaignPriceBigger() &&
                Objects.equals(name, product.name) &&
                Objects.equals(regularPrice, product.regularPrice) &&
                Objects.equals(getRegularPriceColor(), product.getRegularPriceColor()) &&
                Objects.equals(getRegularPriceStyle(), product.getRegularPriceStyle()) &&
                Objects.equals(campaignPrice, product.campaignPrice) &&
                Objects.equals(getCampaignPriceColor(), product.getCampaignPriceColor()) &&
                Objects.equals(getCampaignPriceStyle(), product.getCampaignPriceStyle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regularPrice, getRegularPriceColor(), getRegularPriceStyle(), campaignPrice, getCampaignPriceColor(), getCampaignPriceStyle(), isCampaignPriceBigger());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void setRegularPriceColor(String regularPriceColor) {
        this.regularPriceColor = parseColor(regularPriceColor);
    }

    public void setRegularPriceStyle(String regularPriceStyle) {
        this.regularPriceStyle = regularPriceStyle;
    }

    public void setRegularPriceFont(String regularPriceFont) {
        this.regularPriceFont = regularPriceFont;
    }

    public void setCampaignPrice(String campaignPrice) {
        this.campaignPrice = campaignPrice;
    }

    public void setCampaignPriceColor(String campaignPriceColor) {
        this.campaignPriceColor = parseColor(campaignPriceColor);
    }

    public void setCampaignPriceStyle(String campaignPriceStyle) {
        this.campaignPriceStyle = campaignPriceStyle;
    }

    public void setCampaignPriceFont(String campaignPriceFont) {
        this.campaignPriceFont = campaignPriceFont;
    }

    public String getRegularPriceColor() {
        return regularPriceColor;
    }

    public String getRegularPriceStyle() {
        return regularPriceStyle;
    }

    public String getCampaignPriceColor() {
        return campaignPriceColor;
    }

    public String getCampaignPriceStyle() {
        return campaignPriceStyle;
    }

    @Override
    public String toString() {
        return "Product{ name = " + name +
                ", regularPrice = " + regularPrice +
                ", regularPriceColor = " + regularPriceColor +
                ", regularPriceStyle = " + regularPriceStyle +
                ", regularPriceFont = " + regularPriceFont +
                ", campaignPrice = " + campaignPrice +
                ", campaignPriceColor = " + campaignPriceColor +
                ", campaignPriceStyle = " + campaignPriceStyle +
                ", campaignPriceFont = " + campaignPriceFont +
                ", isCampaignPriceBigger = " + isCampaignPriceBigger +
                '}';
    }
}
