package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {



    @Test
    public void registration() {

        clickSingIn();

        WebElement createEmail = driver.findElement(By.id("email_create"));
        createEmail.sendKeys("cool@gmail.com" + Keys.ENTER);

        // find and select mr button
        WebElement maleRadioButton = driver.findElement(By.id("id_gender1"));
        maleRadioButton.click();

        // verify that mr radio button is clicked
        System.out.println(maleRadioButton.isSelected());

        //enter first name
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        firstName.sendKeys("Zee");

        // enter last name
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        lastName.sendKeys("Zov");

        // input pass
        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys("12345");

        // chose date of birth

        // this is for day
        Select day = new Select(driver.findElement(By.id("days")));
        day.selectByIndex(8);

        // this is for month
        Select month = new Select(driver.findElement(By.id("months")));
        month.selectByIndex(3);

        // this is for year
        Select year = new Select(driver.findElement(By.id("years")));
        year.selectByValue("1987");

        // Input address
        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("1220 Hell Ave");

        // city
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Los Angeles");

        // state
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText("California");

        // zip code
        WebElement zip = driver.findElement(By.id("postcode"));
        zip.sendKeys("90000");

        // phone number
        WebElement phoneNum = driver.findElement(By.id("phone_mobile"));
        phoneNum.sendKeys("111-222-3333" + Keys.ENTER);

    }



    @Test
    public void registration2() throws InterruptedException {

        clickSingIn();

        WebElement createEmail = driver.findElement(By.id("email_create"));
        createEmail.sendKeys(EMAIL + Keys.ENTER);

        Thread.sleep(5000);

        //Verify that that email link displays current email
        String expectedEmail = EMAIL;

        String email = driver.findElement(By.id("email")).getAttribute("value");

        System.out.println("Expected Email: " + expectedEmail + " | Actual Email: " + email);

        Assert.assertEquals(email,expectedEmail);

        // find and select mr button
        WebElement maleRadioButton = driver.findElement(By.id("id_gender1"));
        maleRadioButton.click();


        //enter first name
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        firstName.sendKeys(NAME);

        // enter last name
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        lastName.sendKeys(LASTNAME);

        // input pass
        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys(PASSWORD);

        // chose date of birth

        // this is for day
        Select day = new Select(driver.findElement(By.id("days")));
        day.selectByIndex(DAYS);

        // this is for month
        Select month = new Select(driver.findElement(By.id("months")));
        month.selectByIndex(MONTH);

        // this is for year
        Select year = new Select(driver.findElement(By.id("years")));
        year.selectByValue(YEAR+ "");

        // Input address
        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys(ADDRESS1);

        // city
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys(CITY);

        // state
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText(STATE);

        // zip code
        WebElement zip = driver.findElement(By.id("postcode"));
        zip.sendKeys(ZIPCODE.substring(0,5));

        // phone number
        WebElement phoneNum = driver.findElement(By.id("phone_mobile"));
        phoneNum.sendKeys(PHONENUMBER + Keys.ENTER);

        System.out.println("Name: " + NAME + " Last Name: " + LASTNAME + " Email: " + EMAIL);

        // Verify that correct first and last name is displayed correctly on top right
        String expectedName = NAME+" "+LASTNAME;
        String fullNameTop = driver.findElement(By.xpath("//a[@class='account']//span")).getText();

        Assert.assertEquals(expectedName,fullNameTop);

        //Click on My personal information button
        driver.findElement(By.xpath("//a[@title='Information']//span")).click();

        // Verify that personal information is displayed correctly

        String inputName = driver.findElement(By.id("firstname")).getAttribute("value");
        String inputLastName = driver.findElement(By.id("lastname")).getAttribute("value");
        String inputEmail = driver.findElement(By.id("email")).getAttribute("value");
        String inputDay = driver.findElement(By.id("days")).getAttribute("value");
        String inputMonth = driver.findElement(By.id("months")).getAttribute("value");
        String inputYear = driver.findElement(By.id("years")).getAttribute("value");

        // we need to parse inputDay, inputMonth, inputYear Strings into int
        int parsedDay = Integer.parseInt(inputDay);
        int parsedMonth = Integer.parseInt(inputMonth);
        int parsedYear = Integer.parseInt(inputYear);

        Assert.assertEquals(inputName,NAME);
        Assert.assertEquals(inputLastName,LASTNAME);
        Assert.assertEquals(inputEmail,EMAIL);
        Assert.assertEquals(parsedDay,DAYS); // also (inputDay,DAYS+"")
        Assert.assertEquals(parsedMonth,MONTH); // also (inputMonth,MONTH+"")
        Assert.assertEquals(parsedYear,YEAR); // also (inputYear,YEAR+"")

        // Click on Back to your account
        driver.findElement(By.linkText("Back to your account")).click();

        //Click on My addresses
        driver.findElement(By.linkText("My addresses")).click();

        // verify that address information is displayed correctly
        String expName = NAME;
        String actName = driver.findElement(By.xpath("//span[@class='address_name']")).getText();

        Assert.assertEquals(actName,expName);

        String expLastName = LASTNAME;
        String actLastName = driver.findElement(By.xpath("(//span[@class='address_name'])[2]")).getText();

        Assert.assertEquals(actLastName,expLastName);

        String expAddress = ADDRESS1;
        String actAddress = driver.findElement(By.xpath("//span[@class='address_address1']")).getText();

        Assert.assertEquals(actAddress,expAddress);

        String expCity = CITY;
        String actCity = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 address']//li[5]//span[1]")).getText();
                                                // removing the colon (,) at the end of city
        Assert.assertEquals(actCity.substring(0,actCity.length()-1),expCity);

        String expState = STATE;
        String actState = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 address']//li[5]//span[2]")).getText();

        Assert.assertEquals(actState,expState);

        String expZip = ZIPCODE.substring(0,5);
        String actZip = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 address']//li[5]//span[3]")).getText();

        Assert.assertEquals(actZip,expZip);

        String expPhone = PHONENUMBER;
        String actPhone = driver.findElement(By.xpath("//span[@class='address_phone_mobile']")).getText();

        Assert.assertEquals(actPhone,expPhone);

        // Click on sign out link

        driver.findElement(By.linkText("Sign out")).click();

        // Name: Adele Last Name: Hamill Email: janae.daniel@hotmail.com Pass: 12345

        // Login using the email and password if the current user

        clickSingIn();

        // input email
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(EMAIL);

        // input password
        WebElement passInput = driver.findElement(By.id("passwd"));
        passInput.sendKeys(PASSWORD + Keys.ENTER);

        // Verify that correct first and last name is displayed correctly on top right
        String expectedName2 = NAME+" "+LASTNAME;
        String fullNameTop2 = driver.findElement(By.xpath("//a[@class='account']//span")).getText();

        Assert.assertEquals(expectedName2,fullNameTop2);
        
    }

}
