package com.AoI.Test;

import com.AoI.Page.Base;
import com.AoI.Page.LogInPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestData;

import java.io.IOException;
import java.time.Duration;

public class LogInPageTest extends Base {
    public WebDriver driver;
    private LogInPage logIn;
    public WebDriverWait wt;
    public String url = "https://mail.aol.com/";
    private static final Logger logger = LogManager.getLogger(LogInPageTest.class);
    String username = TestData.USERNAME;
    String password = TestData.PASSWORD;

    @BeforeMethod
    public void setUp() {
        driver = initializeDriver(); // Initialize the driver here
        logIn = new LogInPage(driver); // Initialize LogInPage with the driver
        wt = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait
    }

    @Test(priority = 0)
    public void verifyHomePage() throws IOException {
        try {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            wt.until(ExpectedConditions.elementToBeClickable(logIn.signInLink));
            WebElement getSignIn = logIn.getSignInLink();
            if (getSignIn.isDisplayed()) {
                getSignIn.click();
                logger.info("Successfully Clicked On SignIn Link");
            }
        } catch (Exception mainException) {
            TakeScreenshot("verifyLogInPage");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 1)
    public void verifySignIn() throws IOException {
        try {
            WebElement emailAddress = logIn.getEmail();
            wt.until(ExpectedConditions.visibilityOfElementLocated(logIn.email));
            WebElement signInPage = logIn.getSignInPage();
            wt.until(ExpectedConditions.visibilityOfElementLocated(logIn.email));
            String actualSignInText = signInPage.getText();

            if (actualSignInText.contains("Sign in")) {
                emailAddress.sendKeys(username);
                logger.info("Email has been Entered Successfully:- " + actualSignInText);
                WebElement nextButton = logIn.getNextButton();
                wt.until(ExpectedConditions.visibilityOfElementLocated(logIn.nextButton));
                nextButton.click();
            }

            WebElement getPassword = logIn.getPassword();
            wt.until(ExpectedConditions.visibilityOfElementLocated(logIn.password));
            WebElement passwordPage = logIn.getSignInPage();
            String actualPasswordText = passwordPage.getText();

            if (actualPasswordText.contains("Enter password")) {
                getPassword.sendKeys(password);
                logger.info("Password has been Entered Successfully:- " + actualPasswordText);
                WebElement nextButton = logIn.getNextButton();
                wt.until(ExpectedConditions.visibilityOfElementLocated(logIn.nextButton));
                nextButton.click();
            }
            WebElement composeButton = logIn.getComposeButton();
            wt.until(ExpectedConditions.visibilityOf(composeButton));

            if (composeButton.isDisplayed()) {
                logger.info("Login Successfully");
            } else {
                logger.info("Unable to LogIn");
            }

        } catch (Exception mainException) {
            TakeScreenshot("verifySignIn");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());

        }

    }
}
