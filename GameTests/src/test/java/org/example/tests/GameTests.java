package org.example.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.example.constants.Actions;
import org.example.steps.ApiSteps;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTests {

    private WireMockServer wireMockServer;
    private ApiSteps apiSteps;
    private String token;

    @BeforeAll
    void setup() {
        wireMockServer = new WireMockServer(8888);
        wireMockServer.start();
        apiSteps = new ApiSteps(wireMockServer);
    }

    @AfterAll
    void tearDown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void preEach() {
        token = apiSteps.newToken();
        wireMockServer.resetAll();    }

    // -------- LOGIN --------

    @Test
    @DisplayName("Авторизация: пользователь с валидным токеном успешно входит в систему (OK)")
    void loginSuccess() {
        apiSteps.mockAuthSuccess();
        apiSteps.sendRequest(token,Actions.LOGIN)
                .then()
                .statusCode(200)
                .body("result", equalTo("OK"));
    }

    @Test
    @DisplayName("Авторизация: ошибка внешнего сервиса при входе (возвращается ERROR)")
    void loginFail_externalError() {
        apiSteps.mockAuthFail();
        apiSteps.sendRequest(token,Actions.LOGIN)
                .then()
                .statusCode(500)
                .body("result", equalTo("ERROR"));
    }

    // -------- ACTION --------

    @Test
    @DisplayName("Действие ACTION: попытка выполнения без авторизации отклоняется системой (403)")
    void actionWithoutLogin() {
        apiSteps.sendRequest(token, Actions.ACTION)
                .then()
                .statusCode(403)
                .body("result", equalTo("ERROR"));
    }

    @Test
    @DisplayName("Действие ACTION: пользователь успешно выполняет действие после логина")
    void actionAfterLogin() {
        apiSteps.mockAuthSuccess();
        apiSteps.mockActionSuccess();
        apiSteps.sendRequest(token,Actions.LOGIN);
        apiSteps.sendRequest(token, Actions.ACTION)
                .then()
                .statusCode(200)
                .body("result", equalTo("OK"));
    }

    // -------- LOGOUT --------

    @Test
    @DisplayName("Выход из системы: токен становится недействительным после logout")
    void logoutInvalidatesToken() {
        apiSteps.mockAuthSuccess();
        apiSteps.mockActionSuccess();
        apiSteps.sendRequest(token,Actions.LOGIN);
        apiSteps.sendRequest(token, Actions.LOGOUT);
        apiSteps.sendRequest(token, Actions.ACTION)
                .then()
                .statusCode(403)
                .body("result", equalTo("ERROR"));
    }

    // -------- SECURITY --------

    @Test
    @DisplayName("Безопасность: запрос без API-ключа отклоняется системой (401 Unauthorized)")
    void missingApiKey() {
        apiSteps.mockMissingApiKey();
        apiSteps.sendRequestWithoutApiKey(token, Actions.LOGIN)
                .then()
                .statusCode(401)
                .body("result", equalTo("ERROR"));
    }

    // -------- PARAMETERIZED --------

    @ParameterizedTest
    @ValueSource(strings = {
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ",
            "99999999999999999999999999999999"
    })
    @DisplayName("Авторизация: система принимает корректные токены формата A-Z0-9 длиной 32 символа")
    void validTokens(String token) {
        apiSteps.mockAuthSuccess();
        apiSteps.sendRequest(token,Actions.LOGIN)
                .then()
                .body("result", equalTo("OK"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "!!!!!!!!!!!!AAAAAAAAAAAAAAAAAAAA"
    })
    @DisplayName("Авторизация: некорректные токены отклоняются системой (ошибка сервера 500)")
    void invalidTokens(String token) {
        apiSteps.mockAuthFail();
        apiSteps.sendRequest(token,Actions.LOGIN)
                .then()
                .statusCode(500)
                .body("result", equalTo("ERROR"));
    }

}
