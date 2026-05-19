package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    private Locator userNameInput() {
        return page.locator("#user-name");
    }

    private Locator passwordInput() {
        return page.locator("#password");
    }

    private Locator loginButton() {
        return page.locator("#login-button");
    }

    public void login(String username, String password) {
        userNameInput().fill(username);
        passwordInput().fill(password);
        loginButton().click();
    }
}
