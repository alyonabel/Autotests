package web.pageObject.selenide.components;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class LoginBlock extends ElementsContainer {

    private static final By MAILBOX_LOGIN = By.cssSelector(".email-input");
    private static final By MAILBOX_DOMAIN = By.cssSelector(".domain-select");

    public  void inputToLogin (String login,String domain){
        $(MAILBOX_LOGIN).sendKeys(login);
        $(MAILBOX_DOMAIN).selectOptionContainingText(domain);
    }

    public  void  inputToPassword (String password) {
        $x("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[1]").click();
        $(".password-input").setValue(password);
    }

    public void clickOnButtonLogin(){
        $(".second-button").click();
           }

}
