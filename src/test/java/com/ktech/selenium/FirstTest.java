package com.ktech.selenium;


import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class FirstTest
{
    private static final String SEARCH_BOX = "//input[@name ='search']";
    private static final String SUBMIT_BTN = "//button[@type='submit']";
    private static final String SEARCH_URL = "https://en.wikipedia.org/wiki/Google";


    @Test
    public void seleniumMethods()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver(options); // instantiating ChromeDriver.

        //get methods in Selenium.
        driver.get("https://www.wikipedia.org/"); // opens Wikipedia home page.
        driver.findElement(By.xpath(SEARCH_BOX)).sendKeys("Google"); // finds the search textbox in the Wikipedia
        driver.findElement(By.xpath(SUBMIT_BTN)).click(); // clicks the submit button.
        Assert.assertTrue(driver.getCurrentUrl().equals(SEARCH_URL)); // gets the current URL.
        System.out.println(driver.getTitle().equals("WIKIPEDIA")); // gets Page Title
        System.out.println(driver.getPageSource()); // gets the page source.

        //navigate methods in Selenium.
        driver.navigate().to("https://www.wikipedia.org/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        //find methods in Selenium.

        System.out.println("Page Title : "+ driver.findElement(By.xpath("//*[contains(@class,'-title-main')]")).getText());
       List<WebElement> textAppearance = driver.findElements(By.xpath("//div[@id='skin-client-prefs-vector-feature-custom-font-size']//child::div[@class='cdx-radio']"));
       for(WebElement element : textAppearance)
       {
           if(element.getText().equals("Large"))
           {
               element.click();
           }
       }

       //Switch methods usage
       try {
           driver.switchTo().alert();// there is no alert in wikipedia page so this is expected to fail.
           driver.switchTo().frame(1); // there is no frame in wikipedia.
           String currentWindow = driver.getWindowHandle();
           Set<String> windows = driver.getWindowHandles(); // there is only one window in wikipedia, so driver won't switch
           for(String window: windows)
           {
               if(!window.equals(currentWindow))
               {
                   driver.switchTo().window(window);
               }
           }
       }
       catch (NoAlertPresentException | NoSuchFrameException e)
       {
           System.out.println("Alert is not present");
       }

       //driver manage methods in Selenium

        driver.manage().window().maximize();
        driver.manage().window().minimize();

        //waits in Selenium
             //implicit wait







    }


}