import core.BaseTest;
import org.testng.annotations.*;

import java.io.File;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.TestStepLogger.logStep;

@Ignore
public class IOSMobileTest extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";

    String bookingLnkXpath = ".navigation-mobile li.category-item.first.level-top";
    String productNameH1Xpath = "span.base";


    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{{"iPhone"}};
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
        link.setCSS("#sidebar-button").chkVisible().click();//click on Booklets link
        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link

        label.setCSS(productNameH1Xpath).chkVisible().chkText("BOOKLET PRINTING");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void shoppingCartPageLoadingTest(String browserName) {
        test_name = "Open shopping cart page";
        open_page(mainUrl, browserName);
        link.setCSS("#sidebar-button").chkVisible().jsClick();//click on Booklets link
        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link

        label.setCSS(productNameH1Xpath).chkVisible();//check that we on the required page

        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");

        label.setXpath("//*[@id='panel_1']/parent::div/h2").chkVisible().jsClick();

        label.setXpath("//label[@for='options_1026_4']").chkVisible().jsClick();

        button.setCSS(".buttomframe #new_submit").chkVisible().jsClick();
        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
        link.setCSS("#shopping-cart-table .product-item-details--mobile>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
    }

    @Test(dataProvider = "browsers")
    public void checkoutPageLoadingTest(String browserName) {
        open_page(mainUrl, browserName);
        link.setCSS("#sidebar-button").chkVisible().jsClick();//click on Booklets link
        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link

        label.setCSS(productNameH1Xpath).chkVisible();//check that we on the required page

        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");

        label.setXpath("//*[@id='panel_1']/parent::div/h2").chkVisible().jsClick();
        label.setXpath("//label[@for='options_1026_4']").chkVisible().jsClick();

        button.setCSS(".buttomframe #new_submit").chkVisible().jsClick();
        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
        link.setCSS("#shopping-cart-table .product-item-details--mobile>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");

        setVisibility("input.dz-hidden-input");
        input.setCSS("input.dz-hidden-input").chkVisible().chkEnabled().uploadFileJs(new File("files/test_source.pdf"));
        link.setCSS(".upload-area").moveToElement().chkVisible();


        button.setXpath("//div[@class='cart-container']//*[@data-role='proceed-to-checkout']").chkVisible().jsClick();

        input.setCSS("#email").chkVisible().chkEnabled().setValue("qauser3512@gmail.com");
        input.setCSS("#pass").chkVisible().chkEnabled().setValue("qamandess2020Q");
        button.setCSS("#send2").chkVisible().jsClick();

//        refresh();
        button.setXpath("//div[@class='cart-container']//*[@data-role='proceed-to-checkout']").chkVisible().chkEnabled().jsClick();

        label.setCSS("#shipping").chkVisible();
    }

    @AfterMethod(alwaysRun = true)
    public void down() {
        end = getTime();
        System.out.println(printReport(end, start, test_name));
        getWebDriver().quit();
    }
}
