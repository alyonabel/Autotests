package desktop.regressMantis;

import baseForTests.BeforeAfter;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


@TmsLink("9161")
public class Test0009161 extends BeforeAfter {
    @Test
    @Description("Close Reverso through the tray")
    public void closeTray()  {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        Actions builder = new Actions(reversoSession);
        builder.click(reversoSession.findElementByName("Ok")).perform();
    }
}
