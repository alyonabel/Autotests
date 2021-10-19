package web.pageObject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {

    WebDriver driver;
    public ResultPage (WebDriver driver)
     {
         this.driver = driver;
     }

    private final By heading =  By.id("rcnt");

     public String getFirstResultText()
     {
      return driver.findElement(heading).getText();
     }

}
