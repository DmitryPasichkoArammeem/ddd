package old_version;

import core.BaseTest;
import org.openqa.selenium.JavascriptException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OldMobileTest extends BaseTest {
    String mainUrl = "https://www.bestvaluecopy.com/";

    String bookingLnkXpath = "//ul[@class='sidenav']//a[contains(@href,'booklet-printing')]";
    String productNameH1Css = ".section_header";


    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {"android"}, {"iPhone"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        result = new HashMap<>();
        result.put("version","Old version");
        result.put("url",mainUrl);
        start = end = -1;
    }

    @Test(dataProvider = "browsers")
    public void mainPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open main page");
        start =  open_page(mainUrl, browserName);
    }

    @Test(dataProvider = "browsers")
    public void detailsPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open product details page");
        start =  open_page(mainUrl, browserName);
        link.setXpath(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link
        label.setCSS(productNameH1Css).chkVisible().chkText("Booklet Printing");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void aboutPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open about page");
        start =  open_page(mainUrl, browserName);
        link.setXpath("//ul//a[contains(@href,'/about-us')]").chkVisible().jsClick();//click on Booklets link
        label.setCSS(".title.title2").chkVisible().chkText("About Us");//check that we on the required page
    }

    @AfterMethod(alwaysRun = true)
    public void down() {
        try {
            waitPageLoading();
        } catch (JavascriptException e) {
            System.out.println("sad(");
        }
        end = getTime();
        result.put("time",""+convertToSeconds(end - start));
        old_results.add(result);
        getWebDriver().close();
    }
}
