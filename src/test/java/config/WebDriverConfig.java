package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface WebDriverConfig extends Config {
    @Key("webUrl")
    @DefaultValue("https://demoqa.com")
    String getWebUrl();

    @Key("apiUrl")
    @DefaultValue("https://demoqa.com")
    String getApiUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("122")
    String getBrowserVersion();

    @Key("remoteDriverURL")
    @DefaultValue("selenoid.autotests.cloud")
    String getRemoteDriverURL();

}
