package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class RegisterNewUser extends TestBase {

    String FirstName = "Alexandra";
    String LastName = "Voronova";
    String Address = "Norfolk Str.5";
    String Postcode = "23455";
    String City = "Norfolk";
    String Country = "United States";
    String State = "Virginia";
    String Phone = "+1234567890";
    String Password = "my_password";
    SimpleDateFormat dateNow = new SimpleDateFormat("yyMMddhhmm");
    String Email = "alexandra.voronova" + dateNow.format(new Date()) + "@mail.by";

    WebElement CustomerForm;


    @Test
    public void Register(){

        OpenApp();

        // Open register form

        driver.findElement(By.xpath("//*[@id='box-account-login']/div/form/table/tbody/tr[5]/td")).click();
        assertTrue("Registration page was not open", driver.findElement(By.cssSelector("h1")).getText().equals("Create Account"));

        CustomerForm = driver.findElement(By.cssSelector("form[name=customer_form]"));

        // fill form
        CustomerForm.findElement(By.cssSelector("input[name=firstname]")).sendKeys(FirstName);
        CustomerForm.findElement(By.cssSelector("input[name=lastname]")).sendKeys(LastName);
        CustomerForm.findElement(By.cssSelector("input[name=address1]")).sendKeys(Address);
        CustomerForm.findElement(By.cssSelector("input[name=postcode]")).sendKeys(Postcode);
        CustomerForm.findElement(By.cssSelector("input[name=city]")).sendKeys(City);

        Select CountrySelect = new Select(CustomerForm.findElement(By.cssSelector("select[name=country_code]")));
        CountrySelect.selectByVisibleText(Country);

        Select StateSelect = new Select(CustomerForm.findElement(By.cssSelector("select[name=zone_code]")));
        StateSelect.selectByVisibleText(State);

        CustomerForm.findElement(By.cssSelector("input[name=email]")).sendKeys(Email);
        CustomerForm.findElement(By.cssSelector("input[name=phone]")).clear();
        CustomerForm.findElement(By.cssSelector("input[name=phone]")).sendKeys(Phone);

        CustomerForm.findElement(By.cssSelector("input[name=password]")).sendKeys(Password);
        CustomerForm.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(Password);

        //submit form
        driver.findElement(By.cssSelector("button[type=submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#notices-wrapper #notices")));
        String mes = getNoticeMessage();
        assertTrue("New account was not created: " + mes,
                mes.equals("Your customer account has been created."));
        System.out.println(mes);

        //Logout
        LogoutApp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#notices-wrapper #notices")));
        mes = getNoticeMessage();
        assertTrue(mes, mes.equals("You are now logged out."));
        System.out.println(mes);

        //Login
        LoginApp(Email, Password);
        mes = getNoticeMessage();
        assertTrue("Login failed: " + mes, mes.equals("You are now logged in as " + FirstName +" " + LastName +"."));
        System.out.println(mes);
    }
}
