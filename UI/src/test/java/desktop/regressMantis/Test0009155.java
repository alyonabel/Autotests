package desktop.regressMantis;

import baseForTests.BeforeAfter;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


// Log in_Facebook  http://mantis.domprog.com/view.php?id=9155

public class Test0009155 extends BeforeAfter {
    static  Actions builder;

    @BeforeClass
    public void startBuilder(){
        builder = new Actions(reversoSession);
    }

    @BeforeMethod
    public void goRegister() {
        WebElement sign_up =  reversoSession.findElementByClassName("icon-user");
        sign_up.click();
       // builder.click(sign_up).click().perform();
    }
    @AfterClass
    public void tearDown ()
    {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        reversoSession.findElementByName("Ok").click();
    }
    @TmsLink("9155")
    @Test
    @Description("Log in through Facebook in Profile")
    public void registerFacebookProfile()  {
        WebElement sign_up_with_facebook=  reversoSession.findElementByName("Facebook");
        builder.click(sign_up_with_facebook).click().pause(1000).perform();
    }
    @Test
    @TmsLink("9156")
    @Description("Log in through Google in Profile")
    public  void registerGoogleProfile()
    {
        WebElement sign_up_with_google=  reversoSession.findElementByName("Google");
        builder.click(sign_up_with_google).click().pause(1000).perform();
    }

    @Test
    @TmsLink("9157")
    @Description("Log in through Apple in Profile")
    public  void registerAppleProfile()
    {
        WebElement sign_up_with_apple=  reversoSession.findElementByName("Apple");
        builder.click(sign_up_with_apple).click().pause(1000).perform();
    }

    @Test
    @TmsLink("9158")
    @Description("Sign up in Profile")
    public  void loginHereProfile()
    {
        WebElement login_here=  reversoSession.findElementByName("Sign up");
        builder.click(login_here).click().pause(1000).perform();
    }
    @Test
    @TmsLink("9159")
    @Description("Log in with Email in Profile")
    public  void signUpProfile()
    {
        WebElement sign_up_with_an_email=  reversoSession.findElementByName("Log in with email");
        builder.click(sign_up_with_an_email).click().pause(1000).perform();
    }
}
