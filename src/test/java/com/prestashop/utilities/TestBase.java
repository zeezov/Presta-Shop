package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    Faker faker = new Faker();

    public WebDriver driver;
    public Actions actions;

    public final String EMAIL = faker.internet().emailAddress();
    public final String NAME = faker.name().firstName();
    public final String LASTNAME = faker.name().lastName();
    public final String PASSWORD = faker.internet().password();
    public final int DAYS = faker.number().numberBetween(1, 31);
    public final int MONTH = faker.number().numberBetween(1, 12);
    public final int YEAR = faker.number().numberBetween(1930, 2000);
    public final String ADDRESS1 = faker.address().streetAddress();
    public final String ADDRESS2 = faker.address().secondaryAddress();
    public final String CITY = faker.address().city();
    public final String STATE = faker.address().state();
    public final String ZIPCODE = faker.address().zipCode();
    public final String STATEABBR = faker.address().stateAbbr();
    public final String PHONENUMBER = faker.phoneNumber().cellPhone();
    public final String COMPANY = faker.company().name();
    public final double RANDOMNUMBER = Math.random();
    public final String US = "United States";

    public final String CREDITCARD = faker.finance().creditCard();




    public final void INFO() {
        System.out.println("Email: " + EMAIL);
        System.out.println("Name: " + NAME);
        System.out.println("Last Name: " + LASTNAME);
        System.out.println("Password: " + PASSWORD);
        System.out.println("Day: " + DAYS + ", Month: " + MONTH + ", Year: " + YEAR);
        System.out.println("Address 1: " + ADDRESS1 + ", Address 2: " + ADDRESS2);
        System.out.println("City: " + CITY);
        System.out.println("State: " + STATE);
        System.out.println("Zip code: " + ZIPCODE);
        System.out.println("State abbreviation: " + STATEABBR);
        System.out.println("Phone number: " + PHONENUMBER);
        System.out.println("Company name: " + COMPANY);
        System.out.println("Double Number: " + RANDOMNUMBER);
        System.out.println("County: " + US);
    }

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void accessories() {

        actions = new Actions(driver);

    }

    public void clickSingIn() {

        driver.findElement(By.linkText("Sign in")).click();
    }

    @AfterMethod
    public void tearDown() {
       // driver.quit();
    }





}
