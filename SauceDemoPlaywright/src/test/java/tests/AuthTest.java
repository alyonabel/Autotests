package tests;


import base.BaseTest;
import com.microsoft.playwright.Page;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AuthTest extends BaseTest {

    @Test
    void successfulLoginTest(){
        page.navigate("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(page);

        loginPage.login("standard_user","secret_sauce");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }

}
