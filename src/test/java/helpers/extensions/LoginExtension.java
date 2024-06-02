package helpers.extensions;

import api.authorization.AuthorizationApi;
import config.App;
import io.qameta.allure.Step;
import models.lombok.LoginRequestModel;
import models.lombok.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    @Step("User login --> setting cookies")
    public void beforeEach(ExtensionContext context) {
        AuthorizationApi authorizationApi = new AuthorizationApi();
        LoginResponseModel authResponse = authorizationApi.userLogin(new LoginRequestModel(App.config.userLogin(), App.config.userPassword()));

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
    }
}
