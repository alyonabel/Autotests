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


//Synonyms_Checking Auto direction http://mantis.domprog.com/view.php?id=9271
public class SynonymsDirection extends BeforeAfter implements ParsingFile {

    Actions builder;
    File file = new File("src/main/resources/synonyms_direction.txt");
    static String line;
    String sourceCurrent;
   // String targetCurrent;
    String searchWord;
    String sourceExpected;
    String langWord;
    public String [] items;


    public void parse(String line) throws IOException {
        items = line.split(";");
        sourceCurrent  = items[0];
     //   targetCurrent = items[1];
        searchWord = items[2];
        langWord = items[3];
        sourceExpected = items[4];
    }

    @BeforeClass
    public void startBuilder() {
        builder = new Actions(reversoSession);
        WebElement source = reversoSession.findElementByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_icon-on-the-right");
        builder.click(source).pause(10).perform();
        WebElement list = reversoSession.findElement(By.className("lang-list__list ng-star-inserted"));
        WebElement langSource = list.findElement(By.name("Arabic"));
        builder.click(langSource).pause(1000).perform();
        WebElement reph_syn = reversoSession.findElementByName("Synonyms");
        builder.click(reph_syn).click().perform();
    }


    @AfterClass
    public void tearDown() {
        reversoSession.close();
        reversoSession.switchTo().activeElement();
        reversoSession.findElementByName("Ok").click();
    }

    @DataProvider
    public Object[] getNumber(){
        Object[] returnValues = new Object[144];

        for(int i = 0; i<returnValues.length;i++)
        {
            returnValues[i]=i;
        }
        return returnValues;
    }

   @TmsLink("9271")
    @Test(dataProvider = "getNumber")
   @Description("Synonyms_Checking Auto direction" )
    public void checkDetectionSynonyms(int number) throws IOException {
        line = getArrayLine(file).get(number);
        parse(line);

        System.out.println("The description of the case. Primarly source: "+ sourceCurrent +  ". The search word/phrase: " + searchWord + " in " + langWord);
        Allure.addAttachment("The description of the case.","Primarly source: " + sourceCurrent + " . The search word/phrase: " + searchWord + " in " + langWord);

        WebElement source = reversoSession.findElementByClassName("button button_size_small button_cursor_ background-color-- color-text-- button_icon-on-the-right");
        builder.click(source).pause(10).perform();

        WebElement list = reversoSession.findElement(By.className("lang-list__list ng-star-inserted"));
        WebElement langSource = list.findElement(By.name(sourceCurrent));
        builder.click(langSource).pause(10).perform();

        WebElement editField = reversoSession.findElementByAccessibilityId("wordInput");
        String singleWord =searchWord;
        builder.click(editField).sendKeys(singleWord).sendKeys(Keys.ENTER).pause(10000).build().perform();

       try {
           Assert.assertEquals(source.getText(),sourceExpected);
           System.out.println("The result of case. " + "Final source: " + source.getText());
           Allure.addAttachment("The result of case.", "Final source: " + source.getText());
       }catch (AssertionError e){
           System.out.println("The result of case. " + "Final source: " + source.getText());
           Allure.addAttachment("The result of case.", "Final source: " + source.getText());
       }
              editField.clear();
      }
}
