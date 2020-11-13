package new_version;

import browser.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getSelenideDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.TestStepLogger.logStep;

public class BrowserTest extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";

    String bookletsLink="";

    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{{"chrome"}, {"firefox"}/**/};
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        start = end = -1;
        start = getTime();
    }

    @Test(dataProvider = "browsers")
    public void mainPageLoadingTest(String browserName) {
        test_name = "Open main page";
        logStep(1,"");
        open_page(mainUrl, browserName);
    }

    @Test(dataProvider = "browsers")
    public void detailsPageLoadingTest(String browserName) {
        test_name = "Open product details page";
        open_page(mainUrl, browserName);
        link.setXpath("//*[@id='ui-id-3']").chkVisible().click();//click on Booklets link
        label.setXpath("//*[@data-ui-id='page-title-wrapper']").chkVisible().chkText("BOOKLET PRINTING");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void shoppingCartPageLoadingTest(String browserName) {
        test_name = "Open shopping cart page";
        open_page(mainUrl, browserName);
        link.setXpath("//*[@id='ui-id-3']").chkVisible().click();//click on Booklets link
        label.setXpath("//*[@data-ui-id='page-title-wrapper']").chkVisible();//check that we on the required page
        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");

        radioButton.setCSS("#options_1026_2").chkVisible().chkEnabled().click();
        button.setCSS("#new_submit").chkVisible().click();
        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
        link.setCSS("#shopping-cart-table .product-item-details>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
    }

    @Test(dataProvider = "browsers")
    public void checkoutPageLoadingTest(String browserName) {
//        File f = new File("../../../files/test_source.pdf");
//        System.out.println(f.getName());

        test_name = "Open shopping cart page";
        open_page(mainUrl, browserName);
        link.setXpath("//*[@id='ui-id-3']").chkVisible().click();//click on Booklets link
        label.setXpath("//*[@data-ui-id='page-title-wrapper']").chkVisible();//check that we on the required page
        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");

        radioButton.setCSS("#options_1026_2").chkVisible().chkEnabled().click();
        button.setCSS("#new_submit").chkVisible().click();
        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
        link.setCSS("#shopping-cart-table .product-item-details>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");

        sleep(2000);
        getSelenideDriver().executeJavaScript("document.querySelector('input.dz-hidden-input').style.visibility='visible'");
        getSelenideDriver().executeJavaScript("document.querySelector('input.dz-hidden-input').style.width='1px'");
        getSelenideDriver().executeJavaScript("document.querySelector('input.dz-hidden-input').style.height='1px'");
        input.setCSS("input.dz-hidden-input").chkVisible().chkEnabled().uploadFile(new File("files/test_source.pdf"));
        button.setXpath("//*[@data-role='proceed-to-checkout']").chkVisible().click();

        input.setCSS("#email").chkVisible().chkEnabled().setValue("qauser3512@gmail.com");
        input.setCSS("#pass").chkVisible().chkEnabled().setValue("qamandess2020Q");
        button.setCSS("#send2").chkVisible().click();

        refresh();
        button.setXpath("//*[@data-role='proceed-to-checkout']").chkVisible().chkEnabled().click();

        label.setCSS("#shipping").chkVisible();

    }

    @AfterMethod(alwaysRun = true)
    public void down() {
        waitPageLoading();
        end = getTime();
        System.out.println(printReport(end, start, test_name));
        getWebDriver().close();
    }
}
