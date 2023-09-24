package com.AoI.Test;

import com.AoI.Page.Base;
import com.AoI.Page.LogInPage;
import com.AoI.Page.composeMailPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.TestData;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static utils.TestData.filePath;

public class composeMailPageTest extends Base {
    public WebDriver driver;
    public LogInPage logIn;
    public composeMailPage composeMail;
    private static final Logger logger = LogManager.getLogger(composeMailPageTest.class);
    public WebDriverWait wt;
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    String enteredBodyText = null;

    @BeforeMethod
    public void setUp() {
        driver = Base.getDriver();
        logIn = new LogInPage(driver);
        composeMail = new composeMailPage(driver);
        wt = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test(priority = 0)
    public void clickGoToInbox() throws IOException {
        try {
            WebElement goToElement = composeMail.getGoToInbox();
            wt.until(ExpectedConditions.visibilityOfElementLocated(composeMail.goToInbox));
            if (goToElement.isDisplayed()) {
                goToElement.click();
                logger.info("Successfully Redirect to Mail Listing");
            } else {
                logger.info("Go to Inbox section is not displayed. Performing alternative action...");
                clickComposeBtn();
            }
        } catch (Exception mainException) {
            TakeScreenshot("clickComposeBtn");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            System.err.println("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 1)
    public void clickComposeBtn() throws IOException {
        try {
            WebElement composeButton = logIn.getComposeButton();
            wt.until(ExpectedConditions.visibilityOf(composeButton));
            if (composeButton.isDisplayed()) {
                composeButton.click();
                logger.info("Compose Email Modal is Open");
            }

        } catch (Exception mainException) {
            TakeScreenshot("clickComposeBtn");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 2)
    @Parameters("recipientEmail")
    public void setRecipientEmail() throws IOException {
        try {
            String recipientEmail = TestData.USERNAME;
            WebElement recipientField = composeMail.getSenderEmailAddress();
            wt.until(ExpectedConditions.elementToBeClickable(recipientField));
            if (recipientField.isDisplayed()) {
                recipientField.clear();
                recipientField.sendKeys(recipientEmail);
                logger.info("Successfully Enter the Sender Email Address:- " + recipientEmail);
            }
        } catch (Exception mainException) {
            TakeScreenshot("setRecipientEmail");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 3)
    public void setSubject() throws IOException {
        try {
            String emailSubject = TestData.subject;
            WebElement subjectField = composeMail.getSubject();
            wt.until(ExpectedConditions.elementToBeClickable(subjectField));
            if (subjectField.isDisplayed()) {
                subjectField.sendKeys(emailSubject);
                String expectedSubject = subjectField.getAttribute("value");
                if (emailSubject.contains(expectedSubject)) {
                    logger.info("Subject Validated and Entered Successfully:- " + expectedSubject);
                }
            }
        } catch (Exception mainException) {
            TakeScreenshot("setSubject");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());

        }

    }

    @Test(priority = 4)
    public void setMessageBody() throws IOException {
        try {
            WebElement messageBodyElement = composeMail.getMessageBody();
            wt.until(ExpectedConditions.elementToBeClickable(messageBodyElement));
            if (messageBodyElement.isDisplayed()) {
                Actions action = new Actions(driver);
                action.moveToElement(messageBodyElement)
                        .sendKeys(Keys.TAB)
                        .sendKeys("• Line one")
                        .sendKeys(Keys.ENTER)
                        .sendKeys("• Line two")
                        .sendKeys(Keys.ENTER)
                        .sendKeys("• Line three")
                        .perform();
                logger.info("Successfully Enter the Text In Body Area");
                enteredBodyText = messageBodyElement.getText();
                logger.info("Get The entered Body Message:- "+enteredBodyText);
            }
        } catch (Exception mainException) {
            TakeScreenshot("setMessageBody");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }


    @Test(priority = 5)
    public void attachFile() throws IOException {
        try {
            WebElement attachButton = composeMail.getComposeAttachButton();
            wt.until(ExpectedConditions.elementToBeClickable(attachButton));
            if (attachButton.isDisplayed()) {
                File file = new File("/Users/fleetx/Desktop/DamcoImageFile.png");
                System.out.println(file.getAbsolutePath());
//                String filePath = "/Users/fleetx/Desktop/DamcoImageFile.jpg";
                attachButton.sendKeys(file.getAbsolutePath());

                logger.info("File attached successfully: " + file.getAbsolutePath());
            }
        } catch (Exception mainException) {
            TakeScreenshot("attachFile");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }


    @Test(priority = 6)
    public void clickSendBtn() throws IOException {
        try {
            WebElement sendElement = composeMail.getSendBtn();
            wt.until(ExpectedConditions.elementToBeClickable(sendElement));
            if (sendElement.isDisplayed()) {
                sendElement.click();
                logger.info("Successfully Mail Send");
            }
        } catch (Exception mainException) {
            TakeScreenshot("clickSendBtn");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 7)
    public void clickInbox() throws IOException {
        try {
            WebElement inboxElement = composeMail.getInbox();
            wt.until(ExpectedConditions.visibilityOfElementLocated(composeMail.inbox));
            if (inboxElement.isDisplayed()) {
                inboxElement.click();
                logger.info("Successfully Click on Inbox Button");
            }
        } catch (Exception mainException) {
            TakeScreenshot("clickInbox");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 8)
    public void emailListing() throws IOException {
        try {
            String actualSubject = TestData.subject;
            List<WebElement> emailListElement = composeMail.getEmailListing();
            wt.until(ExpectedConditions.visibilityOfElementLocated(composeMail.emailListing));
            int totalEmailPresenceListing = emailListElement.size();
            boolean emailFound = false;

            for (int i = 0; i < totalEmailPresenceListing; i++) {
                String expectedText = emailListElement.get(i).getText();
                System.out.println("Expected Text: " + expectedText);
                if (actualSubject.contains(expectedText)) {
                    emailListElement.get(i).click();
                    emailFound = true;
                    break;
                }
                logger.info("Matched and went Email Section:- " + expectedText);
            }

            if (!emailFound) {
                Assert.fail("Email with subject '" + actualSubject + "' not found in the list.");
            }
            Thread.sleep(5000);

        } catch (Exception mainException) {
            TakeScreenshot("emailListing");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 9)
    public void verifySubjectAndBodyText() throws IOException {
        try{
            String actualSubject = TestData.subject;
            WebElement getSubjectElement = composeMail.getSubjectText();
            wt.until(ExpectedConditions.visibilityOf(getSubjectElement));
            String getSubjectText = getSubjectElement.getText();
            Assert.assertEquals(actualSubject, getSubjectText, "Both actual and expected subject doesnot match");
            logger.info("Successfully Match..Subject");

            try{
                WebElement getBodyMessageElement = composeMail.getMessageBodyText();
                wt.until(ExpectedConditions.visibilityOf(getBodyMessageElement));
                String getMessageBodyText = getBodyMessageElement.getText();
                System.out.println(getMessageBodyText);
                if(enteredBodyText.contains(getMessageBodyText)){
                    logger.info("Successfully Match..Body");
                }

            }catch (Exception childException){
                TakeScreenshot("getBodyMessageElement");
                logger.error("Failed due to Exception: " + childException.getMessage());
                Assert.fail("Failed due to Exception: " + childException.getMessage());
            }


        }catch (Exception mainException){
            TakeScreenshot("getSubjectElement");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }


}
