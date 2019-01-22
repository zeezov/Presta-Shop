package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorMessageValidationFirstName extends TestBase {

    /*
    1.Open browser
    2. Go to http://automationpractice.com/index.php
    3. Click Sign in link
    4. Enter new valid email to the email field
    5. Click on Create Account
    6. Fill all the required steps except for first name
    7. Click on Register
    8.Verify that error message "first name is required." is displayed
     */

    @Test
    public void ErrorMessageValidationFirstName() throws InterruptedException {

//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Click Sign in link

        clickSingIn();

        // 4. Enter new valid email to the email field
        // 5. Click on Create Account

        WebElement createEmail = driver.findElement(By.id("email_create"));
        createEmail.sendKeys(EMAIL + Keys.ENTER);

        Thread.sleep(5000);

        // 6. Fill all the required steps except for first name
        // 7. Click on Register

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

        // 8.Verify that error message "first name is required." is displayed


        String expectedMsg = "firstname is required.";

        String actualMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();

        Assert.assertEquals(actualMsg,expectedMsg);

    }

}
