package com.ktech.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {

    WebDriver driver;

    @BeforeTest
    public void launchBrowser()
    {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void launchWiki()
    {
        driver.get("https://www.google.com");

    }

    @Test
    public void launchGoogle()
    {
        driver.get("https://www.wikipedia.org/");

    }

    @AfterTest
    public void closeChrome()
    {
       // driver.quit();

    }

}
