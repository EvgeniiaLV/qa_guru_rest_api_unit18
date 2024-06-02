package api.authorization;

import io.qameta.allure.Step;
import models.lombok.LoginRequestModel;
import models.lombok.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.DemoQASpec.loginUserRequestSpec;
import static specs.DemoQASpec.responseSpec200;

public class AuthorizationApi {
    @Step("User authorization")
    public LoginResponseModel userLogin(LoginRequestModel credentials) {
        return given()
                .spec(loginUserRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec200)
                .extract().response().as(LoginResponseModel.class);
    }
}
