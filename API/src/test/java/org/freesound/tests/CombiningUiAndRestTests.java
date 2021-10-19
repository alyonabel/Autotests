package org.freesound.tests;

import com.codeborne.selenide.Condition;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CombiningUiAndRestTests {
    private static final String URL = "http://freesound.org/";
    private static final String API_PATH = "apiv2/search/text/";
    private static final String API_KEY = "IkVNq0j9pPgfF1suTIoq97YPkPd2HtsG4sC0Fba0";

    private static String query = "cat";

    private static String filename;
    private static String username;
    private static String soundId;

    @BeforeAll
    public static  void beforeTest() throws UnirestException {
        String json = Unirest.get(URL + API_PATH)
                .queryString("token", API_KEY)
                .queryString("query", query)
                .asString()
                .getBody();


        filename = JsonPath.read(json, "$.results[0].name");
        username = JsonPath.read(json, "$.results[0].username");
        soundId = String.valueOf((Integer)JsonPath.read(json, "$.results[0].id"));
    }

    @Test
    public void playButtonTest(){
        open(URL + String.format("people/%s/sounds/%s", username,soundId));

        $("#single_sample_player .play")  //Check for REST
                .should(Condition.visible);
    }

    @Test
    public void filenameVerificationTest(){           //We compare the data that we checked on REST with what we see on the UI
        open(URL + "search/?q=" + query);
        $$(".sound_filename")
                .get(0)
                .should(text(filename));
    }

}
