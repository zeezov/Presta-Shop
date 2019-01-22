package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class productInformationPrice extends TestBase {

    WebElement product;
    WebElement price;
    String productPrice;
    String productName;

    // this method created to click the first product on Home Page
    public void homePageProduct() {

        product = driver.findElement(By.linkText("Faded Short Sleeve T-shirts"));
        productName = product.getText();

        price = driver.findElement(By.xpath("(//span[@class='price product-price'])[2]"));
        productPrice = price.getText();

        product.click();

    }

    @Test
    public void confirmPrice() {

        homePageProduct();

        //product.click();

        String productPageName = driver.findElement(By.xpath("//div[@class='pb-center-column col-xs-12 col-sm-4']//h1")).getText();
        String productPagePrice = driver.findElement(By.id("our_price_display")).getText();

        System.out.println("Homepage product name: " + productName + " | Product page Name: " + productPageName);
        System.out.println("Homepage product price: " + productPrice + " | Product page Price: " + productPagePrice);

        Assert.assertEquals(productPageName, productName);
        Assert.assertEquals(productPagePrice, productPrice);


    }

    @Test
    public void productDetails() {

        // Product information details:
        // Verify that default qty is 1
        // verify that default size is S
        // verify that size options are S,M,L

        homePageProduct();

        //product.click();


        // 1. verify that default quantity is 1
        WebElement qty = driver.findElement(By.id("quantity_wanted"));
        String expectedQty = "1";
        String actualQty = qty.getAttribute("value"); // this to find the number inside the box

        System.out.println("Expected default qty: " + expectedQty + " Actual default qty: " + actualQty);
        Assert.assertEquals(expectedQty, actualQty);

        // 2. verify that default size is S
        WebElement size = driver.findElement(By.id("group_1"));

        // we use Select object to access drop down menu
        Select select = new Select(size);

        String expectedSize = "S";
                                    // this will get the first selected option on dropdown
        String actualSize = select.getFirstSelectedOption().getText();

        System.out.println("Expected default size: " + expectedSize + " Actual default size: " + actualSize);
        Assert.assertEquals(expectedSize, actualSize);

        // 3. verify that size options are S,M,L

        // We need this so we can store S,M,L
        List<String> expectedSizeOptions = new ArrayList<>();

        expectedSizeOptions.add("S");
        expectedSizeOptions.add("M");
        expectedSizeOptions.add("L");
                                            // this is to get options from drop down box
        List<WebElement> actualSizeOptions = select.getOptions();

        for (int i = 0; i < actualSizeOptions.size(); i++) {

            System.out.println("Expected size options: " + expectedSizeOptions.get(i) +
                    " Actual size options: " + actualSizeOptions.get(i).getText());
            Assert.assertEquals(expectedSizeOptions.get(i),actualSizeOptions.get(i).getText());
        }

    }

    @Test
    public void productInformationAddToCart() {

        /*
        Product information–Add to cart:
        7.Click on Add to cart
        8.Verify confirmation message “Product successfully added to your shopping cart”
        9.that default quantity is 1
        10.Verify that default size is S
        11.Verify that same name and price displayed as on the home page
         */

        homePageProduct();
        //product.click();

        // 1. Click on Add to cart
        driver.findElement(By.xpath("//span[.='Add to cart']")).click();

        // 2. Verify confirmation message “Product successfully added to your shopping cart”
        WebElement actualMessage = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2"));
        actualMessage.click();

        String actualMsg = actualMessage.getText();
        String expectedMessage = "Product successfully added to your shopping cart";

        actualMessage.click();

        System.out.println("Expected confirmation message: " +  expectedMessage + "\n" +
                "Actual confirmation message: " + actualMsg);

        Assert.assertEquals(actualMsg,expectedMessage);

        // 3. Verify that default quantity is 1

        WebElement defaultQty = driver.findElement(By.id("layer_cart_product_quantity"));
        String expectedQty = "1";
        String actualQty = defaultQty.getText();

        System.out.println("Expected qty: " + expectedQty + " Actual qty: " + actualQty);
        Assert.assertEquals(expectedQty, actualQty);

        // 4. Verify that default size is S

        String expectedSize = "S";
        WebElement defaultSize = driver.findElement(By.id("layer_cart_product_attributes")); // Orange, S

        String actualSize = defaultSize.getText(); // Orange, S

        System.out.println("Expected size: " + expectedSize + " Actual size: " + actualSize);

        Assert.assertEquals(actualSize.substring(actualSize.indexOf("S")), expectedSize);


        // 5. Verify that same name and price displayed as on the home page

        String cartName = driver.findElement(By.id("layer_cart_product_title")).getText();
        String cartPrice = driver.findElement(By.id("layer_cart_product_price")).getText();

        System.out.println("Homepage product name: " + productName + " | Cart product name: " + cartName);
        System.out.println("Homepage product price: " + productPrice + " | Cart product price: " + cartPrice);

        Assert.assertEquals(productName,cartName);
        Assert.assertEquals(productPrice,cartPrice);

    }

}
