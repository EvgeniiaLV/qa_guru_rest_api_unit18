package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.WebDriverProvider;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setUpConfig() {
        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.config();
    }

    @AfterEach
    void clearEnv() {
        Selenide.closeWebDriver();
    }

    @AfterEach
    void addAttachment() {
        Attach.pageSource();
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        if (!WebDriverRunner.isFirefox()) {
            Attach.browserConsoleLogs();
        }

    }
}
