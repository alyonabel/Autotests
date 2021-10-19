package web.pageObject.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    public static void main(String[] args) throws AWTException {
        System.setProperty("webdriver.gecko.driver","D:\\Projects\\Idea\\Autotests\\UI\\libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get ("https://google.com");

        MainPage mainPage = new MainPage(driver);
        mainPage.search ("yandex.ru");
        mainPage.searchEnter ("yandex.ru");
    }
}
