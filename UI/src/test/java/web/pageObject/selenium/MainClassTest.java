package web.pageObject.selenium;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MainClassTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() throws AWTException {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://google.com");
        mainPage = new MainPage(driver);
    }

    @Test
    public void check() {
        ResultPage resultPage = mainPage.search("yahoo.com");
        String heading = resultPage.getFirstResultText();
        Assert.assertTrue(heading.contains("Latest news coverage, email, free stock quotes, live scores and video are just the beginning."));
    }

    @Test
    public void checkEnter() {
        ResultPage resultPage = mainPage.searchEnter("facebook.com");
        String heading = resultPage.getFirstResultText();
        Assert.assertTrue(heading.contains("Create an account or log into Facebook."));
    }


    @After
    public void tearDown() {
        driver.quit();
    }


}
