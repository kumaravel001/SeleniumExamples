package com.ktech.selenium;


import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

public class FirstTest {
    private static final String SEARCH_BOX = "//input[@name ='search']";
    private static final String SUBMIT_BTN = "//button[@type='submit']";
    private static final String SEARCH_URL = "https://en.wikipedia.org/wiki/Google";

    static WebDriver driver;


   // @Test
    public void seleniumMethods() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver(options); // instantiating ChromeDriver.

        //get methods in Selenium.
        driver.get("https://www.wikipedia.org/"); // opens Wikipedia home page.
        driver.findElement(By.xpath(SEARCH_BOX)).sendKeys("Google"); // finds the search textbox in the Wikipedia
        driver.findElement(By.xpath(SUBMIT_BTN)).click(); // clicks the submit button.
        Assert.assertEquals(driver.getCurrentUrl(),SEARCH_URL); // gets the current URL.
        System.out.println(driver.getTitle().equals("WIKIPEDIA")); // gets Page Title
        System.out.println(driver.getPageSource()); // gets the page source.


        //navigate methods in Selenium.
        //driver.navigate().to("https://www.wikipedia.org/");
        driver.navigate().back();
        driver.navigate().forward(); // moving forward to the previous page to continue actions.
        driver.navigate().refresh();

        //find methods in Selenium.
        Thread.sleep(Duration.ofSeconds(3));
        System.out.println("Page Title : " + driver.findElement(By.xpath("//*[contains(@class,'-title-main')]")).getText());
        List<WebElement> textAppearance = driver.findElements(By.xpath("//div[@id='skin-client-prefs-vector-feature-custom-font-size']//child::div[@class='cdx-radio']"));
        for (WebElement element : textAppearance) {
            if (element.getText().equals("Large")) {
                element.click();
            }
        }

        //Switch methods usage
        try {
            driver.switchTo().alert();// there is no alert in wikipedia page so this is expected to fail.
            driver.switchTo().frame(1); // there is no frame in wikipedia.
            String currentWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles(); // there is only one window in wikipedia, so driver won't switch
            for (String window : windows) {
                if (!window.equals(currentWindow)) {
                    driver.switchTo().window(window);
                }
            }
        } catch (NoAlertPresentException | NoSuchFrameException e) {
            System.out.println("Alert is not present");
        }

        //driver manage methods in Selenium

        driver.manage().window().maximize();
        driver.manage().window().minimize();

        //waits in Selenium
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // driver waits throughout execution for 10 seconds.
        driver.navigate().refresh();
        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //driver will wait 10seconds before timeout.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='//upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/250px-Googleplex_HQ_%28cropped%29.jpg']")));
        //Explicit Wait will wait until given condition becomes true or throws exception if element is not visible within given timeout.

        //FluentWait - We can configure polling period and ignor the exceptions

        FluentWait wait1 = new FluentWait<WebDriver>(driver);
        wait1.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='//upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/250px-Googleplex_HQ_%28cropped%29.jpg']")));


    }

    @Test
    public void standardSeleniumFunctionalities() throws InterruptedException, IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver(options); // instantiating ChromeDriver.
        driver.get("https://en.wikipedia.org/wiki/Google");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        //scrolling using JavaScript Executor.

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement element = driver.findElement(By.xpath("//img[@src='//upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/250px-Googleplex_HQ_%28cropped%29.jpg']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        //Actions Class

        Actions actions = new Actions(driver);
        Action action = actions.clickAndHold().contextClick().doubleClick().moveToElement(element).moveByOffset(1,1).keyDown(Keys.CONTROL).keyUp(Keys.CONTROL).sendKeys(element,Keys.TAB).build();
        action.perform();

        //Taking Screenshot

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scrnFile = screenshot.getScreenshotAs(OutputType.FILE);
        Instant dateTime = Instant.now();
        String date = dateTime.toString().substring(0,10);
        FileUtils.copyFile(scrnFile,new File("src/test/resources/ScreenShots/"+date+"/"+dateTime+".png"));

       //Reading and Writing Excel
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/TestData_PB.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        for(int i =0; i <rowCount; i++)
        {
            Row row = sheet.getRow(i);
            int colCount = row.getLastCellNum();
            for(int j=0; j<colCount;j++)
            {
                String data = row.getCell(i).getStringCellValue();
            }
        }

        // Writing an Excel

        FileInputStream fileInputStream1 = new FileInputStream("src/test/resources/TestData_PB.xlsx");
        Workbook workbook1 = new XSSFWorkbook(fileInputStream1);
        workbook1.createSheet("TestData");
        Sheet sheet1 = workbook1.getSheet("TestData");
        for(int i=0;i<5;i++)
        {
            sheet1.createRow(i);
            Row row = sheet1.getRow(i);
            for(int j =0; j<5;j++)
            {
                row.createCell(j).setCellValue("test");
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("src/test/resources/TestData_PB.xlsx");
        workbook1.write(fileOutputStream);



    }


}