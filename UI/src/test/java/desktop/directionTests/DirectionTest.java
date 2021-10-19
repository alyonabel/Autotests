package desktop.directionTests;

import baseForTests.BeforeAfter;
import baseForTests.ParsingFile;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

// Checking Auto direction changes http://mantis.domprog.com/view.php?id=9270
public class DirectionTest extends BeforeAfter implements ParsingFile {

    Actions builder;
    public String [] items;
    String sourceCurrent;
    String targetCurrent;
    String searchWord;
    String langWord;
    String sourceExpected;
    String targetExpected;
    static String line;


    File file = new File("src/main/resources/auto_direction_cut.txt");
       public void parse(String line) throws IOException {
        items = line.split(";");
        sourceCurrent  = items[0];
        targetCurrent = items[1];
        searchWord = items[2];
        langWord=  items[3];
        sourceExpected = items[4];
        targetExpected = items[5];
    }

    @BeforeClass
   public void startBuilder() {
        builder = new Actions(reversoSession);
    }

    @AfterClass
    public void tearDown ()
    {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        builder.click(reversoSession.findElementByName("Ok")).perform();
    }

    @DataProvider
    public Object[] getNumber(){
        Object[] returnValues = new Object[35];
        for(int i = 0; i<returnValues.length;i++){
           returnValues[i]=i;}
            return returnValues;
    }

    @Test(dataProvider = "getNumber")
    @TmsLink("9270")
    @Description("Checking Auto direction changes" )
       public void checkDirection(int number) throws IOException {
        line = getArrayLine(file).get(number);
        parse(line);
        System.out.println("The description of the case: Primarly source: " + sourceCurrent + " / Primarly target: " + targetCurrent + ". The search word/phrase: " + searchWord + " in " + langWord);
        Allure.addAttachment("The description of the case","Primarly source: " + sourceCurrent + " / Primarly target: " + targetCurrent + ". The search word/phrase: " + searchWord + " in " + langWord);


        WebElement source = reversoSession.findElementByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_icon-on-the-right");
        builder.click(source).pause(10).perform();

        WebElement list = reversoSession.findElement(By.className("lang-list__list ng-star-inserted"));
        WebElement langSource = list.findElement(By.name(sourceCurrent));
        builder.click(langSource).pause(10).perform();


        WebElement target = (WebElement) reversoSession.findElementsByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_icon-on-the-right").get(1);
        builder.click(target).pause(10).perform();

        WebElement list2 = reversoSession.findElement(By.className("lang-list__list lang-list__list_target ng-star-inserted"));
        WebElement langTarget= list2.findElement(By.name(targetCurrent));
        builder.click(langTarget).pause(10).perform();


        WebElement editField = reversoSession.findElementByAccessibilityId("wordInput");
        String singleWord =searchWord;
        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).build().perform();

         try {
             Assert.assertEquals(source.getText(), sourceExpected);
             Assert.assertEquals(target.getText(), targetExpected);
             System.out.println("Test is successful! The result of case. " + "Final source: " + source.getText()+ " / Final target: " + target.getText());
             Allure.addAttachment("Test is successful!", "The result of case. Final source: " + source.getText()+ " / Final target: " + target.getText());

          }catch (AssertionError e){
             System.out.println("Test failed! The result of case. " + "Final source: " + source.getText()+ " / Final target: " + target.getText());
             Allure.addAttachment("Test failed!","The result of case. Final source: " + source.getText()+ " / Final target: " + target.getText());
         }

        editField.clear();
        builder.click(editField).sendKeys(Keys.ENTER).build().perform();
    }
    }


