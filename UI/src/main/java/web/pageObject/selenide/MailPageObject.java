package web.pageObject.selenide;

import web.pageObject.selenide.mail.EmailMailPage;
import web.pageObject.selenide.components.LoginBlock;

import static com.codeborne.selenide.Selenide.*;


public class MailPageObject {

    private LoginBlock loginBlock = new LoginBlock();  //Инициализировать не нужно, так как доавбили extends ElementsContainer


  public  void  inputToLogin (String login,String domain) {
      loginBlock.inputToLogin(login,domain);
  }

    public  void  inputToPassword (String password) {

      loginBlock.inputToPassword(password);
    }

    public EmailMailPage clickOnButtonLogin(){
       loginBlock.clickOnButtonLogin();
        return page(EmailMailPage.class);
    }

}
