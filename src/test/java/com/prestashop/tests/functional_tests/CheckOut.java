package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOut extends TestBase {

    @Test
    public void checkOut() {

        // search for t-shirt
        WebElement search = driver.findElement(By.id("search_query_top"));
        search.sendKeys("t-shirt" + Keys.ENTER);

        // verify page title contains search
        String expectedTitle = "Search";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);

        Assert.assertTrue(actualTitle.contains(expectedTitle));

        // add the item to the cart
        WebElement shirt = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img"));

        // to hower over the picture to get the button "Add to Cart"
        actions.moveToElement(shirt).perform();

        WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span"));
        addToCart.click();

        // click proceed to check out
        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.linkText("Proceed to checkout")).click();

        // long in again
        // enter your username
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("cool@gmail.com");

        // input password
        WebElement passInput = driver.findElement(By.id("passwd"));
        passInput.sendKeys("12345" + Keys.ENTER);



        // click on Proceed to Check out
        driver.findElement(By.xpath("//button[@name='processAddress']")).click();

        // select and agree terms of service box
        WebElement termBox = driver.findElement(By.id("cgv"));
        termBox.click();

        // proceed to check out
        driver.findElement(By.xpath("//button[@name='processCarrier']")).click();

        // chose payment option
        WebElement payment = driver.findElement(By.partialLinkText("Pay by bank wire"));
        payment.click();

        // confirm order
        driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();

        // verify that order was placed
        String expectedMsg = "Your order on My Store is complete.";
        String actualMsg = driver.findElement(By.xpath("//div[@class='box']/p/strong")).getText();

        System.out.println("Actual message: " + actualMsg + " | Expected message: " + expectedMsg);

        Assert.assertEquals(actualMsg,expectedMsg);

    }

}
