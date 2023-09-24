package com.AoI.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class composeMailPage {
    private WebDriver driver;

    By senderEmailAddress = By.id("message-to-field");
    By subject = By.xpath("//input[@placeholder='Subject']");
    By messageBody = By.xpath("//div[@aria-label='Message body']");
    By composeAttachButton = By.xpath("//input[@type='file']/..");
    public By goToInbox = By.xpath("//*[text()='Go to Inbox']");
    By sendBtn = By.xpath("//*[text()='Send']");
    public By inbox = By.xpath("//*[text()='Inbox']");
    public By emailListing = By.xpath("(//*[text()='Damco'])[1]");
    public By getSubjectText = By.xpath("//*[contains(@data-test-id, 'subject-text')]");
    public By getMessageBodyText = By.xpath("//*[contains(@data-test-id, '-body-content')]");


    public composeMailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSenderEmailAddress(){
        return driver.findElement(senderEmailAddress);
    }
    public WebElement getSubject(){
        return driver.findElement(subject);
    }
    public WebElement getMessageBody(){
        return driver.findElement(messageBody);
    }
    public WebElement getComposeAttachButton(){
        return driver.findElement(composeAttachButton);
    }
    public WebElement getGoToInbox(){
        return driver.findElement(goToInbox);
    }
    public WebElement getSendBtn(){
        return driver.findElement(sendBtn);
    }
    public WebElement getInbox(){
        return driver.findElement(inbox);
    }
    public List <WebElement> getEmailListing(){
        return driver.findElements(emailListing);
    }

    public WebElement getSubjectText(){
        return driver.findElement(getSubjectText);
    }

    public WebElement getMessageBodyText(){
        return driver.findElement(getMessageBodyText);
    }










}
