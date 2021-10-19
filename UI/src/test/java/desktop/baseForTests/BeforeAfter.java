package desktop.baseForTests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BeforeAfter {

    protected static WindowsDriver reversoSession = null;
    static WebDriver driver;
    static int num = 0;

    @BeforeClass
    public static void setUp() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Users\\ABeletskaya\\Desktop\\Reverso.lnk");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            LoggingPreferences loggingPreferences = new LoggingPreferences();
            loggingPreferences.enable(LogType.DRIVER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
            reversoSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            reversoSession.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            reversoSession.manage().window().maximize();


        } catch (NullPointerException e) {
            //  e.printStackTrace();
            System.out.println("Launch WinAppDriver.exe from destop");
            System.out.println("Check the icon 'Reverso' on the desktop");
        } catch (Exception e) {
            //  e.printStackTrace();
        }

    }

    @BeforeClass
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "91.0.4472.106 (Official Build) (64-bit)")
                        .put("Stand", "Test")
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/");
    }

    @AfterClass
    public void tearDown() {
        reversoSession.quit();
    }


    public void makeScreenshots(String strPath) throws IOException {
        num++;
        File scrFile = ((TakesScreenshot) reversoSession).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(strPath + num + ".jpg"));

    }

    public void launchChrome() throws MalformedURLException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("debuggerAddress", "http://127.0.0.1:4723");

        driver = new ChromeDriver(chromeOptions);
        //  driver=new RemoteWebDriver(new URL("http://127.0.0.1:4723"), chromeOptions);

    }


}
