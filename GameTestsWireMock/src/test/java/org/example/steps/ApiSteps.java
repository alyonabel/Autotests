package org.example.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.example.constants.TestData.API_KEY;
import static org.example.constants.TestData.BASE_URL;

public class ApiSteps {

    private final WireMockServer wireMockServer;

    public ApiSteps(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    @Step("Mock: успешный /auth")
    public void mockAuthSuccess() {
        wireMockServer.stubFor(post(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\":\"OK\"}")));
    }

    @Step("Mock: ошибка /auth")
    public void mockAuthFail() {
        wireMockServer.stubFor(post(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\":\"ERROR\"}")));
    }

    @Step("Mock: успешный /doAction")
    public void mockActionSuccess() {
        wireMockServer.stubFor(post(urlEqualTo("/doAction"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\":\"OK\"}")));

    }

    public void mockMissingApiKey() {
        wireMockServer.stubFor(post(urlEqualTo("/"))
                .withHeader("X-Api-Key", absent())
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\":\"ERROR\"}")));
    }


    @Step("Отправка запроса с token и с action")
    public Response sendRequest(String token, String action) {
        return given()
                .header("X-Api-Key", API_KEY)
                .contentType("application/x-www-form-urlencoded")
                .formParam("token", token)
                .formParam("action", action)
                .when()
                .post(BASE_URL);
    }

    @Step("Отправка запроса без X-Api-Key")
    public Response sendRequestWithoutApiKey(String token, String action) {
        return given()
                .baseUri(BASE_URL)
                .contentType("application/x-www-form-urlencoded")
                .formParam("token", token)
                .formParam("action", action)
                .when()
                .post();
    }

    public String newToken() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }

}

