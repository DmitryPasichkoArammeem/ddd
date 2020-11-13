// ~ Copyright © 2019 Aram Meem Company Limited. All Rights Reserved.

package core;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:testing.properties")
public interface TestProperties extends Config {

    @Key("browser")
    String browser();

    @Key("pageLoadTimeout")
    int pageLoadTimeout();

    @Key("timeout")
    int timeout();

    @Key("windowSize")
    String windowSize();

    @Key("winWidth")
    int winWidth();

    @Key("winHeight")
    int winHeight();

    @Key("headless")
    boolean headless();

    @Key("chromeVersion")
    boolean chromeVersion();

    @Key("firefoxVersion")
    boolean firefoxVersion();

    @Key("listenerName")
    String listenerName();

}

