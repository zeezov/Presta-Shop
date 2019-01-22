package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WONegativeLoginTests extends TestBase {

    // log in with empty email

    @Test
    public void emptyEmailTest() {

        // click sing in
        clickSingIn();

        // input password
        WebElement passInput = driver.findElement(By.id("passwd"));
        passInput.sendKeys("12345");

        // click on log in button
        WebElement signIn = driver.findElement(By.id("SubmitLogin"));
        signIn.click();

        // verify error message: "An email address required."

        String expectedMsg = "An email address required.";
        String actualMsg = driver.findElement(By.xpath("//div[@id='center_column']/div/ol/li")).getText();

        System.out.println("Expected message: " + expectedMsg + " | Actual message " + actualMsg);

        Assert.assertEquals(actualMsg,expectedMsg);

    }

    // log in with empty password

    @Test
    public void emptyPasswordTest() {

        // sing in
        clickSingIn();

        // enter your username
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("cool@gmail.com");

        // click on log in button
        WebElement signIn = driver.findElement(By.id("SubmitLogin"));
        signIn.click();

        // verify error message: "Password is required."

        String expectedMsg = "Password is required.";
        String actualMsg = driver.findElement(By.xpath("//li[.='Password is required.']")).getText();

        System.out.println("Expected message: " + expectedMsg + " | Actual message " + actualMsg);

        Assert.assertEquals(actualMsg,expectedMsg);

    }


}
