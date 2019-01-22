package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class accountInformation extends TestBase {

    String fullNameTop;

    @Test
    public  void signIn() {

//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("http://automationpractice.com/index.php");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            /*
            2.Click Sign in link
            3.Login using valid username and password
            4.Verify that title contains My account
            5.Verify that account holder full name is displayed next to the Sign out link
             */

            // 1. Click Sign in link
        driver.findElement(By.linkText("Sign in")).click();

            // 2. Login using valid username and password

        WebElement email = driver.findElement(By.id("email"));
        WebElement pass = driver.findElement(By.id("passwd"));

        email.sendKeys("nshaazizov@yahoo.com");
        pass.sendKeys("080387");

        // click on submit
        driver.findElement(By.id("SubmitLogin")).click();

        fullNameTop = driver.findElement(By.xpath("//a[@class='account']//span")).getText();

    }

    @Test
    public void loginMyAccount() {

        // 3. Verify that title contains My account

        String expectedTitle = "My account";
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.contains(expectedTitle));

        // 4. Verify that account holder full name is displayed next to the Sign out link

        String expectedName = "Na Zim";

        System.out.println("Expected name: " + expectedName + " | Actual name: " + fullNameTop);

        Assert.assertEquals(expectedName,fullNameTop);

    }

    @Test
    public void loginMyPersonalInformation() {

        /*
        6.Click on My personal information button
        7.Verify title contains Identity
        8.Verify that first name and last name matches the full name on top
        9.Click on Save button
        10.Verify error message “The password you entered is incorrect.
        11.Click on Back to your account
        12.Verify that title contains My account
         */

        // 1. Click on My personal information button

        driver.findElement(By.xpath("//a[@title='Information']//span")).click();

        // 2. Verify title contains Identity

        String expectedTitle = "Identity";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));

        // 3. Verify that first name and last name matches the full name on top

        WebElement firstName = driver.findElement(By.id("firstname"));
        String inputFirstName = firstName.getAttribute("value");

        WebElement lastName = driver.findElement(By.id("lastname"));
        String inputLastName = lastName.getAttribute("value");

        String inputFullName = inputFirstName + " " + inputLastName;

        System.out.println("Expected full name: " + inputFullName + " | Actual full name: " + fullNameTop);

        Assert.assertEquals(fullNameTop,inputFullName);

        // 4. Click on Save button
        driver.findElement(By.name("submitIdentity")).click();

        // 5. Verify error message “The password you entered is incorrect.

        String expectedError = "The password you entered is incorrect.";
        String actualError = driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li")).getText();

        System.out.println("Expected error: " + expectedError + " | Actual error: " + actualError);

        Assert.assertEquals(actualError,expectedError);

        // 6. Click on Back to your account

        driver.findElement(By.linkText("Back to your account")).click();

        // 7. Verify that title contains My account

        String expected = "My account";
        Assert.assertTrue(driver.getTitle().contains(expected));

    }

    @Test
    public void loginMyAddresses() {

        /*
        13.Click on My addresses
        14.Click on Add a new address
        15.Verify that first name and last name matches the full name on top
        16.Delete the first name
        17.Click save
        18.Verify error message “first name is required.”
         */

        // 1. Click on My addresses

        driver.findElement(By.linkText("My addresses")).click();

        // 2. Click on Add a new address

        driver.findElement(By.linkText("Add a new address")).click();

        // 3. Verify that first name and last name matches the full name on top

        WebElement firstName = driver.findElement(By.id("firstname"));
        String inputFirstName = firstName.getAttribute("value");

        WebElement lastName = driver.findElement(By.id("lastname"));
        String inputLastName = lastName.getAttribute("value");

        String inputFullName = inputFirstName + " " + inputLastName;

        System.out.println("Expected full name: " + inputFullName + " | Actual full name: " + fullNameTop);

        Assert.assertEquals(fullNameTop,inputFullName);

        // 4. Delete the first name

        firstName.clear();

        // 5. Click save

        driver.findElement(By.id("submitAddress")).click();

        // 6. Verify error message “first name is required.”

        String expectedError = "firstname is required.";
        String actualError = driver.findElement(By.xpath("//div[@class='alert alert-danger']//ol//li[1]")).getText();

        System.out.println("Expected error: " + expectedError + " | Actual error: " + actualError);

        Assert.assertEquals(actualError,expectedError);

    }



}
