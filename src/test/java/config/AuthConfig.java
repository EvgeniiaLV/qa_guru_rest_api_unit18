package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/auth.properties"})
public interface AuthConfig extends Config {
    @Key("userLogin")
    String getUserLogin();

    @Key("userPassword")
    String getUserPassword();
}