package desktop.regressMantis;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

//Start the Reverso Desktop from Start Menu and system disk  http://mantis.domprog.com/view.php?id=9162

@TmsLink("9162")
public class Test0009162_2 {
    private static WindowsDriver reversoSession = null;
    @BeforeClass
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "91.0.4472.106 (Official Build) (64-bit)")
                        .put("Stand","Test")
                        .build());
    }
    @BeforeClass
    public static void setUp() {
        try {
            Configurator.initialize(new DefaultConfiguration());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Users\\ABeletskaya\\Desktop\\Reverso.lnk");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            reversoSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            reversoSession.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            reversoSession.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Check the icon 'Reverso' on the desktop");
        }
    }

    @AfterMethod
    public void closeTray()  {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        Actions builder = new Actions(reversoSession);
        builder.click(reversoSession.findElementByName("Ok")).perform();
    }

    @Test
    @Description("Start the Reverso Desktop from start menu")
    public void checkAboutWindowFromIcon() {
        WebElement reverso  = reversoSession.findElementByName("Reverso");
        boolean isOpened = reverso.isDisplayed();
        if(!isOpened) System.out.println("Error: The application doesn't open");

    }
}

