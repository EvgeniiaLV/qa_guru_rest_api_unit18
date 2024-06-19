package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {
    public static AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void config() {
        RestAssured.baseURI = config.getApiUrl();
        Configuration.baseUrl = config.getWebUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();

        Configuration.pageLoadStrategy = "eager";

        if (System.getProperty("env").equalsIgnoreCase("remote")) {
            Configuration.remote = "https://user1:1234@" + config.getRemoteDriverURL() + "/wd/hub";
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }
}
