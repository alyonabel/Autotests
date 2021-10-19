package web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//With JavascriptExecutor we change the attributes of elements, make them invisible
public class JsTests {
    static WebDriver driver;

    @Test
    public void jsTest() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://yahoo.com");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement mailLink = (WebElement) jsExecutor.executeScript("return document.getElementById('mega-bottombar-mail')"); //Finding an element on the page

        WebElement logo = (WebElement) jsExecutor.executeScript("document.getElementById('ybar-search-box-container').setAttribute('class','display:none');return document.getElementById('ybar-logo')");
        //or 'display:block' - then the element will be visible

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        logo.click();
        driver.quit();

    }
}
