package mobile;

import browser.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.TestProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.*;

public abstract class MobileBaseTest {

    public static final TestProperties tstProp = ConfigFactory.create(TestProperties.class);
    public static WebDriver driver;

    private static final String USERNAME = "dmitrypasichko2";
    private static final String AUTOMATE_KEY = "yk6Hzj2F4AC6Z8QeVMuL";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    protected browser.Link link = new Link();
    protected browser.Label label = new Label();
    protected browser.Input input = new Input();
    protected browser.RadioButton radioButton = new RadioButton();
    protected browser.Button button = new Button();
    protected browser.DropDown dropDown = new DropDown();

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

    protected void open_page(String url, String platform) {
        SelenideLogger.addListener(tstProp.listenerName(),
                new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = tstProp.timeout();
        WebDriverRunner.setWebDriver(getDriver(platform));
        clearBrowserCache();
        open(url);
    }

    public WebDriver getDriver(String platform) {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("browserName", platform);
        caps.setCapability("realMobile", "true");
//        caps.setCapability("webStorageEnabled", "true");
        caps.setCapability("nativeWebTap",true);
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

        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

}