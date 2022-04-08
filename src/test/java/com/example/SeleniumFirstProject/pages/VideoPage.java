package com.example.SeleniumFirstProject.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class VideoPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"container\"]/h1/yt-formatted-string")
    private WebElement videoName;

    public VideoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getName() {
        return videoName.getText();
    }

}