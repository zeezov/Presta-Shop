package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ContactUS extends TestBase {

    @Test
    public void contactUs() {

        // click on contact us link
        driver.findElement(By.linkText("Contact us")).click();

        // choose drop down box "Customer Service"
        Select select = new Select(driver.findElement(By.id("id_contact")));
        select.selectByIndex(1);

        // input email
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("cool@gmail.com");

        // order number
        WebElement orderNum = driver.findElement(By.id("id_order"));
        orderNum.sendKeys("PL1234");

        // upload file
        WebElement chooseFile = driver.findElement(By.id("fileUpload"));

        String pathToAFile = "/Users/nshaazizov/Desktop/flag.jpg";

        chooseFile.sendKeys(pathToAFile);

        // type message on the message box
        WebElement msg = driver.findElement(By.id("message"));
        msg.sendKeys("Yo, whats with my order? It is lost. I have paid $500000 for it");

        // submit
        driver.findElement(By.id("submitMessage")).click();

    }

}
