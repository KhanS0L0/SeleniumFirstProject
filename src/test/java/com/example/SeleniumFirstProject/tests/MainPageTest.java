package com.example.SeleniumFirstProject.tests;

import com.example.SeleniumFirstProject.pages.MainPage;
import com.example.SeleniumFirstProject.pages.VideoPage;
import com.example.SeleniumFirstProject.utils.PropertyReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTest {

    private PropertyReader propertyReader;
    private MainPage mainPage;
    private VideoPage videoPage;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    void init() {
        propertyReader = new PropertyReader();
        propertyReader.setProp("driver.properties");

        if (propertyReader.getProperty("current_driver").equals("chrome_driver")) {
            System.setProperty("webdriver.chrome.driver", propertyReader.getProperty("chrome_driver"));
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption(propertyReader.getProperty("debugger_address_property"),
                    propertyReader.getProperty("debugger_address_property_value"));
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", propertyReader.getProperty("mozilla_driver"));
            driver = new FirefoxDriver();
        }

        propertyReader.setProp("config.properties");
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        videoPage = new VideoPage(driver);
    }

    @Test
    @DisplayName("checking correctness of search functionality")
    public void mainPageSearchTest() throws InterruptedException{
        assumeTrue(driver.getClass() == ChromeDriver.class);
        driver.get(propertyReader.getProperty("youtube_main_page"));

        mainPage.click(mainPage.getSearchBtn());
        mainPage.inputSearchCondition("never gonna");
        mainPage.click(mainPage.getSearchBtn());

        WebElement foundElement = wait.until(presenceOfElementLocated(By.xpath(propertyReader.getProperty("found_video_xpath"))));
        mainPage.click(foundElement);

        Thread.sleep(3000);
        assertEquals("Rick Astley - Never Gonna Give You Up (Official Music Video)",videoPage.getName());
    }

    @AfterAll
    void destroy() {
        driver.quit();
    }
}
