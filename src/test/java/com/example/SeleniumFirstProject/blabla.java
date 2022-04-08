package com.example.SeleniumFirstProject;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class blabla {
    @Test
    public void simpleTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://youtube.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]"));
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        searchBar.sendKeys("Веб. Раздел 12");
        element.click();

    }
}
