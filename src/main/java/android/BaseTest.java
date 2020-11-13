package android;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.TestProperties;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BaseTest {

    public static final TestProperties tstProp = ConfigFactory.create(TestProperties.class);
    public static String browser = "chrome";
    public long start = 0;
    public long end = 0;
    public String test_name;


    public static void setBrowser(String newBrowser) {
        browser = newBrowser;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public double convertToSeconds(long time) {
        return time / 1000.0;
    }

    public void waitPageLoading() {
        new WebDriverWait(getWebDriver(), tstProp.pageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void open_page(String url) {
        SelenideLogger.addListener(tstProp.listenerName(),
                new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = tstProp.timeout();
        WebDriverRunner.setWebDriver(getDriver());
        clearBrowserCache();
        open(url);
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                ChromeDriverManager.chromedriver().version("85.0.4183.83").setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (tstProp.headless())
                    chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxDriverManager.firefoxdriver().version("0.27.0").setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (tstProp.headless())
                    firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "explorer":
                InternetExplorerDriverManager.iedriver().version("3.8.0").setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Select incorrect browser type");
        }
        driver.manage().window().setSize(new Dimension(tstProp.winWidth(), tstProp.winHeight()));
        return driver;
    }
}