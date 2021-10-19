package desktop.regressMantis;

import baseForTests.BeforeAfter;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


//  The page "Log in" http://mantis.domprog.com/view.php?id=9173

public class Test0009173 extends BeforeAfter {
    static Actions builder;

    @BeforeClass
    public void startBuilder() {
        builder = new Actions(reversoSession);
    }

    @BeforeMethod
    public void goRegister() {
        WebElement sign_up = reversoSession.findElementByClassName("icon-user");
        sign_up.click();
    }

    @AfterClass
    public void tearDown() {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        reversoSession.findElementByName("Ok").click();
    }

    @TmsLink("9173")
    @Test
    @Description("Appearance_The popup Log in")
    public void checkPageLogIn() throws org.openqa.selenium.NoSuchElementException {

        List<WebElement> listOfItems = new ArrayList<>();

        //WebElement close = reversoSession.findElementByClassName("icon-close");
        WebElement title = reversoSession.findElementByName("Log in");
        WebElement description = reversoSession.findElementByName("Translate longer texts, bookmark your favorites, enjoy more translation examples, and more.");
        WebElement buttonEmail = reversoSession.findElementByName("Log in with email");
        WebElement text1 = reversoSession.findElementByName("or sign up with");


        WebElement button1 = reversoSession.findElementByClassName("icon-facebook ng-star-inserted");
        WebElement button2 = reversoSession.findElementByClassName("icon-google ng-star-inserted");
        WebElement button3 = reversoSession.findElementByClassName("icon-apple ng-star-inserted");
        WebElement text5 = reversoSession.findElementByName("Don't have an account?");
        WebElement link2 = reversoSession.findElementByName("Sign up");


       // listOfItems.add(close);
        listOfItems.add(title);
        listOfItems.add(description);
        listOfItems.add(buttonEmail);
        listOfItems.add(text1);


        listOfItems.add(button1);
        listOfItems.add(button2);
        listOfItems.add(button3);
        listOfItems.add(text5);
        listOfItems.add(link2);


        for (WebElement listOfItem : listOfItems) {
            Assert.assertTrue(listOfItem.isDisplayed());
        }
    }
}



