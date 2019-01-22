package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLoginTests extends TestBase {

    @Test
    public void positiveLogin() {

        clickSingIn();

        // enter your username
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("cool@gmail.com");

        // verify email input is correct
        String expectedEmail = "cool@gmail.com";
        String actualEmail = driver.findElement(By.id("email")).getAttribute("value");

        System.out.println("Expected email: " + expectedEmail + " | Actual email: " + actualEmail);

        Assert.assertEquals(actualEmail,expectedEmail);

        // input password
        WebElement passInput = driver.findElement(By.id("passwd"));
        passInput.sendKeys("12345");

        // verify password input is correct
        String expectedPass = "12345";
        String actualPass = driver.findElement(By.id("passwd")).getAttribute("value");

        System.out.println("Expected password: " + expectedPass + " | Actual password: " + actualPass);

        Assert.assertEquals(actualPass,expectedPass);

        // click on log in button
        WebElement signIn = driver.findElement(By.id("SubmitLogin"));
        signIn.click();

    }

}
