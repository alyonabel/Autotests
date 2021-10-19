package web.pageObject.selenide;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import web.pageObject.selenide.mail.EmailMailPage;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class MailTestPageObject {

    String login = "testmail00";
    String password = "abHuPI%doY12";
    String domain = "internet.ru";

    @Test
    public void pageObjectTest() {
        Configuration.browser = "firefox";
        Configuration.timeout = 20000; //By default, the smart wait is 4 seconds, here we redefine it by 20 or -Dselenide.tiomeout = 20000
        WebDriverManager.firefoxdriver().setup();
        open("https://mail.ru");

        MailPageObject mailPageObject = new MailPageObject();
        mailPageObject.inputToLogin(login, domain);
        mailPageObject.inputToPassword(password);

        EmailMailPage emailMailPage = mailPageObject.clickOnButtonLogin();

        emailMailPage.isEmailContainerVisible();
        assertEquals(emailMailPage.getCountEmail(), 4);

    }
}

