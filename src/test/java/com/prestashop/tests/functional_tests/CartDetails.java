package com.prestashop.tests.functional_tests;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CartDetails extends TestBase {


    @Test
    public void cartDetails() {

        // Click on any product that is not on sale
        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();

        Random random = new Random();
        // this will give us numbers starting from 2 to 5
        int n = random.nextInt(4) + 2;

        // converting int to String
        String randomNum = Integer.toString(n);

        // Enter a random quantity between 2 and 5
        WebElement qty = driver.findElement(By.id("quantity_wanted"));
        qty.clear();
        qty.sendKeys(randomNum);

        // Select a different size
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText("M");

        // Click on add to cart
        driver.findElement(By.name("Submit")).click();

        // Verify confirmation message Product successfully added to your shopping cart
        String expectedMsg = "Product successfully added to your shopping cart";
        WebElement actualMsg = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']/h2"));
        actualMsg.click();

        String actualMessage = actualMsg.getText();

        System.out.println("Expected message: " + expectedMsg + " | Actual message: " + actualMessage);

        // Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@class='cross']")).click();

        // Click on the company logo
        driver.findElement(By.xpath("//img[@class='logo img-responsive']")).click();

        // Click on any product that is on sale
        driver.findElement(By.linkText("Printed Summer Dress")).click();

        // Enter a random quantity between 2 and 5
        WebElement quantity = driver.findElement(By.id("quantity_wanted"));
        quantity.clear();
        quantity.sendKeys(randomNum);

        // Select a different size
        Select size2 = new Select(driver.findElement(By.id("group_1")));
        size2.selectByVisibleText("L");

        // Click on add to cart
        driver.findElement(By.name("Submit")).click();

        // Verify confirmation message Product successfully added to your shopping cart
        String expMsg = "Product successfully added to your shopping cart";
        WebElement actMsg = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']/h2"));
        actMsg.click();

        String actMessage = actMsg.getText();

        System.out.println("Expected message: " + expMsg + " | Actual message: " + actMessage);

        // Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@class='cross']")).click();

        // Hover over the Cart icon
        WebElement cart = driver.findElement(By.xpath("//span[@class='ajax_cart_product_txt_s']"));
        actions.moveToElement(cart).perform();

        // Verify that correct total is displayed
        driver.findElement(By.xpath("//span[@class='price cart_block_total ajax_block_cart_total']")).isDisplayed();

        // Verify that total is correct based on the price and item count
        // of the products you added to cart. (Shipping is always $2)
        String qty1 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[1]/div/div[1]/span/span")).getText();
        System.out.println("Qty1 " + qty1);

        String price1 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[1]/div/span")).getText();
        price1 = price1.substring(1);
        System.out.println("Price1 " + price1);

        String qty2 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/div/div[1]/span/span")).getText();
        System.out.println("Qty2 " + qty2);

        String price2 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/div/span")).getText();
        price2 = price2.substring(1);
        System.out.println("Price2 " + price2);

        String totalPrice = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[1]")).getText();
        totalPrice = totalPrice.substring(1);
        System.out.println("Total " + totalPrice);

        double totalFirstItem = Double.parseDouble(price1);// / Double.parseDouble(qty1);
        double totalSecondItem = Double.parseDouble(price2);// / Double.parseDouble(qty2);
        System.out.println("Total first item: " + totalFirstItem);
        System.out.println("Total second item: " + totalSecondItem);

        // i had to convert it back to string because my totalPrice is String
        // and Assert was  comparing String with double
        double total = totalFirstItem + totalSecondItem + 2;
        String total2 = Double.toString(total);

        Assert.assertEquals(totalPrice,total2);










    }

}
