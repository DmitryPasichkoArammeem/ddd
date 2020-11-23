package new_version;

import core.BaseTest;
import org.openqa.selenium.JavascriptException;
import org.testng.annotations.*;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class NewBrowserTest extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";

    String bookingLnkXpath = "//*[@id='ui-id-3']";
    String productNameH1Xpath = "//*[@data-ui-id='page-title-wrapper']";


    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{{"chrome"}, {"firefox"}, {"IE"}, {"safari"}};
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        result = new HashMap<>();
        result.put("version","New version");
        result.put("url",mainUrl);
        start = end = -1;
    }

    @Test(dataProvider = "browsers")
    public void mainPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open main page");
        start = open_page(mainUrl, browserName);
    }

    @Test(dataProvider = "browsers")
    public void detailsPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open product details page");
        start = open_page(mainUrl, browserName);
        link.setXpath(bookingLnkXpath).chkVisible().click();//click on Booklets link
        label.setXpath(productNameH1Xpath).chkVisible().chkText("BOOKLET PRINTING");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void aboutPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open about page");
        start = open_page(mainUrl, browserName);
        link.setCSS("#sm_megamenu_117").chkVisible().click();//click on Booklets link
        label.setXpath("//*[@data-ui-id='page-title-wrapper']").chkVisible().chkText("ABOUT US");//check that we on the required page
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
        results.add(result);
        getWebDriver().close();
    }
}
//    @Test(dataProvider = "browsers")
//    public void shoppingCartPageLoadingTest(String browserName) {
//        test_name = "Open shopping cart page";
//        open_page(mainUrl, browserName);
//        link.setXpath(bookingLnkXpath).chkVisible().click();//click on Booklets link
//        label.setXpath(productNameH1Xpath).chkVisible();//check that we on the required page
//
//        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");
//
//        radioButton.setCSS("#options_1026_2").chkVisible().chkEnabled().click();
//        button.setCSS("#new_submit").chkVisible().click();
//        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
//        link.setCSS("#shopping-cart-table .product-item-details>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
//    }
//
//    @Test(dataProvider = "browsers")
//    public void checkoutPageLoadingTest(String browserName) {
//        test_name = "Open shopping cart page";
//        open_page(mainUrl, browserName);
//        link.setXpath(bookingLnkXpath).chkVisible().click();//click on Booklets link
//        label.setXpath(productNameH1Xpath).chkVisible();//check that we on the required page
//        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");
//
//        radioButton.setCSS("#options_1026_2").chkVisible().chkEnabled().click();
//        button.setCSS("#new_submit").chkVisible().click();
//        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
//        link.setCSS("#shopping-cart-table .product-item-details>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
//
//        setVisibility("input.dz-hidden-input");
//        input.setCSS("input.dz-hidden-input").chkVisible().chkEnabled().uploadFile(new File("files/test_source.pdf"));
//        button.setXpath("//*[@data-role='proceed-to-checkout']").chkVisible().click();
//
//        input.setCSS("#email").chkVisible().chkEnabled().setValue("qauser3512@gmail.com");
//        input.setCSS("#pass").chkVisible().chkEnabled().setValue("qamandess2020Q");
//        button.setCSS("#send2").chkVisible().click();
//
//        refresh();
//        button.setXpath("//*[@data-role='proceed-to-checkout']").chkVisible().chkEnabled().click();
//
//        label.setCSS("#shipping").chkVisible();
//    }