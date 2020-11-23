package old_version;

import core.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        start = end = -1;
        start = getTime();
    }

    @Test(dataProvider = "browsers")
    public void mainPageLoadingTest(String browserName) {
        test_name = "Open main page";
        open_page(mainUrl, browserName);
    }

    @Test(dataProvider = "browsers")
    public void detailsPageLoadingTest(String browserName) {
        test_name = "Open product details page";
        open_page(mainUrl, browserName);
        link.setXpath(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link
        label.setCSS(productNameH1Css).chkVisible().chkText("Booklet Printing");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void aboutPageLoadingTest(String browserName) {
        test_name = "Open about page";
        open_page(mainUrl, browserName);
        link.setXpath("//ul//a[contains(@href,'/about-us')]").chkVisible().jsClick();//click on Booklets link
        label.setCSS(".title.title2").chkVisible().chkText("About Us");//check that we on the required page
    }

    @AfterMethod(alwaysRun = true)
    public void down() {
        end = getTime();
        System.out.println(printReport(end, start, test_name));
        getWebDriver().close();
    }
}
