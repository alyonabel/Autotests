package net.dictionary.api.client;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class RestDictionaryTests {
    private static final String URL_KEY="https://apertium.org/apy";
    @Test
    public void test(){
        RestAssured.baseURI = URL_KEY;
        RestAssured.useRelaxedHTTPSValidation();  //In case the SSL certificate is not installed to continue working

        RestAssured.given()
                  .header("User-Agent","Mozilla")
                  .header("host", "apertium.org")
                  .header("Origin", "https://apertium.org")
                  .header("Accept","application/json")
                  .header("Accept-Encoding","gzip")
                  .header("Accept-Language", "en-US")
                  .header("Connection", "keep-alive");

        RestAssured.when()
                .get(EndpointUrl.TRANSLATE.addPath(String.format("?langpair=%s&markUnknown=%s&prefs=&q=%s","eng|spa","no","Hello bear!")))
                .then()
                .statusCode(200) //HTTP request status = OK
                .body("responseData.translatedText",equalTo("Hola Oso!"))
                .body("responseDetails", equalTo(null))
                .body("responseStatus", equalTo(200));


    }


}
