package org.example.page;

import org.openqa.selenium.WebDriver;
import org.example.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}



