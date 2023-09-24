package com.AoI.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage {
    public WebDriver driver;

    public By signInLink = By.xpath("//*[text()='Log in']");
    public By signInPage = By.xpath("//strong[@class='challenge-heading']");
    public By email = By.xpath("//input[@name='username']");
    public  By nextButton = By.id("login-signin");
    public By password = By.xpath("//input[@name='password']");
    public  By composeButton = By.xpath("//*[text()='Compose']");


    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getSignInLink(){
        return driver.findElement(signInLink);
    }

    public WebElement getSignInPage(){
        return driver.findElement(signInPage);
    }
    public WebElement getEmail(){
        return driver.findElement(email);
    }

    public WebElement getNextButton(){
        return driver.findElement(nextButton);
    }

    public WebElement getPassword(){
        return driver.findElement(password);
    }
    public WebElement getComposeButton(){
        return driver.findElement(composeButton);
    }


}
