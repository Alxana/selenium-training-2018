package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SortCountriesZones extends TestBase {

    private static int COUNTRY_COLUMN = 5;
    private static int COUNTRY_COLUMN_ZONES_PAGE = 3;
    private static int ZONES_COLUMN_NUM = 6;
    private static String TAG_NAME_LINK = "a";

    // get list of zones or countries from data table.
    // in: column number, tag name
    // out: list of elements

    private List<WebElement> getCountriesByColumn(int tdNum, String tagName){
        return driver.findElements(By.cssSelector(".dataTable tr.row td:nth-child(" + tdNum +") " + tagName));
    }

    // list of zones for selected country
    private List<WebElement> getZonesList(){
        return driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
    }

    //close zone and go to Countries
    private void closeZone(){
        driver.findElement(By.cssSelector("button[name=cancel")).click();
    }

    // check if zones are in alphabetical order on zones page
    private void checkZonesSort(int i, int td){
        String countryName = getCountriesByColumn(td, TAG_NAME_LINK).get(i).getText();
        getCountriesByColumn(td, TAG_NAME_LINK).get(i).click();

        if (isSortedAlphabetically(getZonesList())) {
            System.out.println("Geo Zones are sorted alphabetically for " + countryName);
        } else System.out.println("Geo Zones are not sorted alphabetically for " + countryName);
        closeZone();
    }

    private boolean isSortedAlphabetically(List<WebElement> listToCheck){

        ArrayList<String> obtainedList = new ArrayList<String>();
        ArrayList<String> sortedList = new ArrayList<String>();

        for (WebElement we: listToCheck){
            obtainedList.add(we.getText());
        }

        sortedList.addAll(obtainedList);
        Collections.sort(sortedList);

        return  sortedList.equals(obtainedList);
    }


    @Test //Zadanie 9-1a
    public void checkCountriesSort() {
        Login();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        assertTrue("Countries are not sorted alphabetically", isSortedAlphabetically(getCountriesByColumn(COUNTRY_COLUMN, TAG_NAME_LINK)));
        System.out.println("Countries are sorted alphabetically");

        LogoutAdmin();
    }

    @Test//Zadanie 9-1b
    public void checkZonesOfCountriesSort(){
        Login();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        for(int i=0; i<getCountriesByColumn(COUNTRY_COLUMN, "a").size(); i++) {

            if (!getCountriesByColumn(ZONES_COLUMN_NUM, "").get(i).getText().equals("0")) {
                checkZonesSort(i, COUNTRY_COLUMN);
            }
        }

        LogoutAdmin();
    }

    @Test //Zadanie 9-2
    public void checkZonesSort() {
        Login();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        for(int i=0; i<(getCountriesByColumn(COUNTRY_COLUMN_ZONES_PAGE, TAG_NAME_LINK)).size(); i++){
            checkZonesSort(i, COUNTRY_COLUMN_ZONES_PAGE);
        }
        LogoutAdmin();

    }
}
