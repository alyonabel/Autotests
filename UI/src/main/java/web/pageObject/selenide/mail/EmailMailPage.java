package web.pageObject.selenide.mail;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EmailMailPage {

    public void isEmailContainerVisible(){
      $(By.cssSelector(".letter-list")).should(Condition.visible);
    }

    public  int getCountEmail(){
        return  $$(By.cssSelector(".llc__container > div")).size();
    }
}
