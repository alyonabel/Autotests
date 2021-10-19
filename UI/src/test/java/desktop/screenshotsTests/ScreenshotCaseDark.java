package desktop.screenshotsTests;

import baseForTests.BeforeAfter;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

//Preconditions: state log in, in the browser log out

public class ScreenshotCaseDark extends BeforeAfter {

    static Actions builder;
    String strPath = "\\\\devserver\\Exchange\\ABeletskaya\\Reverso\\Screenshots\\";
    String version = "v1.4.10.401";
    String mode = "\\Dark";

    @BeforeClass
    public void startBuilder() {
        builder = new Actions(reversoSession);
    }

     @AfterClass
    public void tearDown() {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        builder.click(reversoSession.findElementByName("Ok")).perform();
    }

    @TmsLink("9155")
    @Test(priority=1)
    @Description("Set dark mode")
    public void setMode() {
        WebElement hamburger = reversoSession.findElementByClassName("icon-burger");
        hamburger.click();
        WebElement settings = reversoSession.findElementByName("Settings");
        settings.click();

        WebElement notActiveList = reversoSession.findElementByClassName("select__val");
        notActiveList.click();
        WebElement selectedMode = reversoSession.findElementByName("Dark");
        builder.click(selectedMode).build().perform();

        WebElement back = reversoSession.findElementByName("Back");
        back.click();
    }

    @TmsLink("9155")
    @Test(priority =2)
    @Description("The current state -  log in. The results: \\\\devserver\\Exchange\\ABeletskaya\\Reverso\\Screenshots")
    public void launchScreeshotsLogInDark() throws IOException, InterruptedException {

        String finalPath = strPath + version + mode+"\\ScreenshotLogIn";

        WebElement editField = reversoSession.findElementByAccessibilityId("wordInput");
        editField.click();
        makeScreenshots(finalPath);

        WebElement source = reversoSession.findElementByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_icon-on-the-right");
        builder.click(source).pause(10).perform();
        makeScreenshots(finalPath);

        WebElement langSource = reversoSession.findElement(By.name("English"));
        builder.click(langSource).pause(10).perform();

        String MTtext = "go to the park and to the cinema";
        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);
        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();

        String singleWord = "walk";
        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);

        WebElement chevron = reversoSession.findElementByClassName("translation-list__show-more-button");
        chevron.click();
        Thread.sleep(2000);
        makeScreenshots(finalPath);

        WebElement otherMode = reversoSession.findElementByClassName("switch-mode__list");
        otherMode.click();
        makeScreenshots(finalPath);

        WebElement seeMore = reversoSession.findElementByClassName("synonyms-list__more-button ng-star-inserted");
        seeMore.click();
        Thread.sleep(2000);
        makeScreenshots(finalPath);

        editField.clear();
        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);
        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).pause(10000).perform();


        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).perform();
        WebElement conjugation = reversoSession.findElementByName("Conjugation");
        builder.click(conjugation).pause(5000).perform();
        makeScreenshots(finalPath);

        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).pause(10000).perform();
        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);

        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();

        WebElement hamburger = reversoSession.findElementByClassName("icon-burger");
        hamburger.click();
        makeScreenshots(finalPath);

        WebElement settings = reversoSession.findElementByName("Settings");
        settings.click();
        makeScreenshots(finalPath);

        WebElement voice = reversoSession.findElementByName("Voice settings");
        voice.click();
        makeScreenshots(finalPath);

        WebElement back = reversoSession.findElementByName("Back");
        back.click();
        back.click();

        hamburger.click();
        WebElement tips = reversoSession.findElementByName("Info & Tips");
        tips.click();
        makeScreenshots(finalPath);

        WebElement arrow = reversoSession.findElementByClassName("info-modal__content__slider-button ng-star-inserted");
        arrow.click();
        makeScreenshots(finalPath);
        arrow.click();
        makeScreenshots(finalPath);
        WebElement arrowGotIt = reversoSession.findElementByName("Got it");
        arrowGotIt.click();

        WebElement sign_up = reversoSession.findElementByClassName("icon-user");
        sign_up.click();
        makeScreenshots(finalPath);

        WebElement upgrade_to_premium = reversoSession.findElementByName("Upgrade to Premium");
        upgrade_to_premium.click();
        makeScreenshots(finalPath);

        WebElement closePremium = reversoSession.findElementByClassName("ng-star-inserted");
        closePremium.click();

        sign_up.click();
        WebElement account = reversoSession.findElementByName("Account");
        account.click();
        makeScreenshots(finalPath);
        WebElement doLogOut = reversoSession.findElementByName("Log out");
        builder.click(doLogOut).build().perform();

    }

    @TmsLink("9155")
    @Test(priority=3)
    @Description("The current state -  log out. The results: \\\\devserver\\Exchange\\ABeletskaya\\Reverso\\Screenshots")
    public void launchScreeshotsLogOutDark() throws IOException, InterruptedException {


        String finalPath = strPath + version + mode + "\\ScreenshotLogOut";

        WebElement sign_up = reversoSession.findElementByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_only-icon");
        sign_up.click();
        makeScreenshots(finalPath);

        WebElement modeTranslation = reversoSession.findElementByName("Translation");
        modeTranslation .click();

        WebElement editField = reversoSession.findElementByAccessibilityId("wordInput");

        String MTtext = "go to the park and to the cinema";
        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);
        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();

        String singleWord = "walk";
        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);

        WebElement chevron = reversoSession.findElementByClassName("translation-list__show-more-button");
        chevron.click();
        Thread.sleep(2000);
        makeScreenshots(finalPath);

        WebElement otherMode = reversoSession.findElementByClassName("switch-mode__item");
        otherMode.click();
        makeScreenshots(finalPath);

        editField.clear();
        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);
        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();

        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).perform();
        WebElement conjugation = reversoSession.findElementByName("Conjugation");
        builder.click(conjugation).pause(10000).perform();
        makeScreenshots(finalPath);

        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();
        makeScreenshots(finalPath);

        builder.click(editField).sendKeys(MTtext).sendKeys(Keys.ENTER).pause(10000).perform();
        makeScreenshots(finalPath);
        editField.clear();
    }

}