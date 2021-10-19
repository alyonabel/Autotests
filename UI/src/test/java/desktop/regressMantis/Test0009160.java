package desktop.regressMantis;

import baseForTests.BeforeAfter;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


// Log in_"Register to see more examples" http://mantis.domprog.com/view.php?id=9160
@TmsLink("9160")
public class Test0009160 extends BeforeAfter {
static  Actions builder;

    @BeforeClass
    public void typeSingleWord() {
        builder = new Actions(reversoSession);
        WebElement editField = reversoSession.findElementByAccessibilityId("wordInput");
        String singleWord = "go";
        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).perform();
    }
    @AfterClass
    public void tearDown ()
    {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        reversoSession.findElementByName("Ok").click();
    }

    @Test
    @Description("Log in through Facebook after the search")
    public void loginFacebookAfterSearch()  {

        WebElement sign_up_with_facebook=  reversoSession.findElementByClassName("icon-facebook ng-star-inserted");
        builder.click(sign_up_with_facebook).click().perform();

    }

    @Test
    @Description("Log in through Google after the search")
    public void loginGoogleAfterSearch(){
        WebElement sign_up_with_google=  reversoSession.findElementByClassName("icon-google ng-star-inserted");
        builder.click(sign_up_with_google).click().perform();
    }

    @Test
    @Description("Log in through Apple after the search")
    public void loginAppleAfterSearch(){
        WebElement sign_up_with_apple=  reversoSession.findElementByClassName("icon-apple ng-star-inserted");
        builder.click(sign_up_with_apple).click().perform();
    }

    @Test
    @Description("Login here after the search")
    public void loginHereAfterSearch(){
        WebElement login_here=  reversoSession.findElementByName("Log in");
        builder.click(login_here).click().perform();
    }

    @Test
    @Description("Sign up with Email after the search")
    public void signUpAfterSerch(){
    WebElement sign_up_with_an_email=  reversoSession.findElementByName("Sign up with Email");
        builder.click(sign_up_with_an_email).click().perform();}
}

