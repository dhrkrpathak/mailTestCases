package com.AoI.Page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class Base {

    public static WebDriver driver = null;
    public static ChromeOptions chromeOptions = new ChromeOptions();

    @BeforeMethod
    public WebDriver initializeDriver() {

        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--disable-notifications");

        if (driver == null) {
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
        return driver;

    }

    public static WebDriver getDriver() {
        return driver;

    }

    // Function to Take screenshot
    public static void TakeScreenshot(String FileName) throws IOException {
        // Creating instance of File
        File File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(File, new File("Error ScreenShot:- " + FileName + ".jpeg"));
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        Reporter.log("=====Browser Session End=====", true);
        System.out.println("Window closed ");

    }


}

