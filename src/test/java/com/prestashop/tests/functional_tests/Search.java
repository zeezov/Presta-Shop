package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Search extends TestBase {

    @Test
    public void search() {

        // search for t-shirt
        WebElement search = driver.findElement(By.id("search_query_top"));
        search.sendKeys("t-shirt" + Keys.ENTER);

        // verify page title contains search
        String expectedTitle = "Search";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);

        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

}
