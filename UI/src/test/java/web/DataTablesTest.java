package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class DataTablesTest {
    static WebDriver driver;

    @Test
    public void implicitlyWaitTest() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);//Implicit expectation helps when there is a full page reload

        driver.get("https://datatables.net/examples/server_side/row_details.html");

        WebElement tableExamples = driver.findElement(By.id("example"));

        List<WebElement> tableRows = tableExamples.findElements(By.cssSelector("[class='display dataTable'] tbody tr")); //Looking for a row with the value row

        assertEquals(tableRows.size(), 10);//Checking that the table size is 10 by default

        Select select = new Select(driver.findElement(By.name("example_length")));  //Sets the display of 25 rows in the table on the site
        select.selectByValue("25");  //Check that there are now 25 values in the table

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        tableRows = tableExamples.findElements(By.cssSelector("[class='display dataTable'] tbody tr")); //To overwrite all the values in our table

        assertEquals(tableRows.size(), 25);
    }
}
