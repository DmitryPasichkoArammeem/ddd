package core;

import browser.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.*;

public abstract class BaseTest {

    public static final TestProperties tstProp = ConfigFactory.create(TestProperties.class);
    public static WebDriver driver;
    private final String URL = "https://" + tstProp.browserstackUser() + ":" + tstProp.browserstackKey() + "@hub-cloud.browserstack.com/wd/hub";

    protected Link link = new Link();
    protected Label label = new Label();
    protected Input input = new Input();
    protected RadioButton radioButton = new RadioButton();
    protected Button button = new Button();

    public long start = 0;
    public long end = 0;
    public String test_name;


    public static String printReport(Long start, Long end, String description) {
        return description + " - " + convertToSeconds(end - start) + " sec";
    }

    public static void setVisibility(String cssSelector) {
        sleep(2000);
        getSelenideDriver().executeJavaScript("document.querySelector('" + cssSelector + "').style.visibility='visible'");
        getSelenideDriver().executeJavaScript("document.querySelector('" + cssSelector + "').style.width='1px'");
        getSelenideDriver().executeJavaScript("document.querySelector('" + cssSelector + "').style.height='1px'");
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static double convertToSeconds(long time) {
        return time / 1000.0;
    }

    public void waitPageLoading() {
        new WebDriverWait(getWebDriver(), tstProp.pageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void open_page(String url, String browser) {
        SelenideLogger.addListener(tstProp.listenerName(),
                new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = tstProp.timeout();
        WebDriverRunner.setWebDriver(getDriver(browser));
        clearBrowserCache();
        open(url);
    }

    public WebDriver getDriver(String browser) {
        driver = getRemoteDriver(browser);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        return driver;
    }


    public WebDriver getRemoteDriver(String platform) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("name", "Best value print " + platform + " mobile testing");
        switch (platform) {
            case "chrome":
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("browserstack.selenium_version", "3.14.0");
                break;
            case "firefox":
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Firefox");
                caps.setCapability("browser_version", "latest-beta");
                caps.setCapability("browserstack.selenium_version", "3.5.2");
                break;
            case "safari":
                caps.setCapability("os", "OS X");
                caps.setCapability("os_version", "Catalina");
                caps.setCapability("browser", "Safari");
                caps.setCapability("browser_version", "13.0");
                caps.setCapability("browserstack.selenium_version", "3.14.0");
                break;
            case "IE":
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "IE");
                caps.setCapability("browser_version", "11.0");
                caps.setCapability("browserstack.selenium_version", "3.5.2");
                break;
            case "android":
            case "iPhone":
                caps = getMobileCapability(platform);
                break;
            default:
                throw new RuntimeException("Select incorrect platform type");
        }
        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public DesiredCapabilities getMobileCapability(String platform) {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("browserName", platform);
        caps.setCapability("realMobile", "true");
        caps.setCapability("webStorageEnabled", "true");
        caps.setCapability("nativeWebTap", true);
        caps.setCapability("name", "Best value print " + platform + " mobile testing");

        switch (platform) {
            case "android":
                caps.setCapability("device", "Samsung Galaxy S9");
                caps.setCapability("os_version", "8.0");

                break;
            case "iPhone":
                caps.setCapability("device", "iPhone X");
                caps.setCapability("os_version", "11");
                break;
            default:
                throw new RuntimeException("Select incorrect platform type");
        }
        return caps;
    }

}