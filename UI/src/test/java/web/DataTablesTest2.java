package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

//Here instead of Thread.sleep, we use explicit expectation in order to wait for a separate element from the Dom model

public class DataTablesTest2 {
    static WebDriver driver;

    @Test
    public void implicitlyWaitTest() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        driver.get("https://datatables.net/examples/server_side/row_details.html");

        WebElement tableExamples = driver.findElement(By.id("example"));

        List<WebElement> tableRows = tableExamples.findElements(By.cssSelector("[class='display dataTable'] tbody tr")); //Looking for a row with the value row

        assertEquals(tableRows.size(), 10);

        Select select = new Select(driver.findElement(By.name("example_length")));  //Sets the display of 25 rows in the table on the site
        select.selectByValue("25");


        tableRows = new WebDriverWait(driver, 10, 1000).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("[class='display dataTable'] tbody tr"), 25));//We expect 25 elements, every second we check our condition
        //After passing the condition, the method will return us a collection of 25 elements=>List<WebElement> tableRows
        assertEquals(tableRows.size(), 25);
    }
}
