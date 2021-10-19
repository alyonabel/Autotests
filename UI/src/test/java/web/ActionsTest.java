package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ActionsTest {

    static WebDriver driver;

    @Test
    public void dragAndDropTest() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://demo.guru99.com/test/drag_drop.html");
        WebElement bankBlock = driver.findElement(By.cssSelector("#credit2 a")); //The element that we will drag

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(bankBlock));

        WebElement accountSideBlock = driver.findElement(By.cssSelector("#bank li")); //The place where we will drag

        Actions actions = new Actions(driver);
        actions.dragAndDrop(bankBlock, accountSideBlock).pause(10000).perform();

        WebElement debitTable = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("bal3")));

        assertTrue(debitTable.isDisplayed());
        driver.quit();

    }


}
