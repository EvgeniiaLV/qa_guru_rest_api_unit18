package tests;

import config.WebDriverProvider;
import io.restassured.response.Response;
import models.lombok.LoginRequestModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoQASpec.loginUserRequestSpec;
import static specs.DemoQASpec.responseSpec200;

public class LoginTests extends TestBase {
    @Test
    void successfulLoginWithApiTest() {
        LoginRequestModel userLoginData = new LoginRequestModel(WebDriverProvider.authConfig.getUserLogin(), WebDriverProvider.authConfig.getUserPassword());
        Response authResponse = given(loginUserRequestSpec)
                .body(userLoginData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec200)
                .extract().response();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $("#userName-value").shouldHave(text("123a"));
    }
}
