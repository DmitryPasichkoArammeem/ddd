package new_version;

import core.BaseTest;
import org.openqa.selenium.JavascriptException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class NewMobileTest extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";

    String bookingLnkXpath = ".navigation-mobile li.category-item.first.level-top";
    String productNameH1Xpath = "//*[@data-ui-id='page-title-wrapper']";


    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {"android"},
                {"iPhone"}};
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
        link.setCSS("#sidebar-button").chkVisible().click();//click on Booklets link
        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link

        label.setXpath(productNameH1Xpath).chkVisible().chkText("BOOKLET PRINTING");//check that we on the required page
    }

    @Test(dataProvider = "browsers")
    public void aboutPageLoadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open about page");
        start = open_page(mainUrl, browserName);
        link.setXpath("//div[@class='footer-top']//a[contains(@href,'/about-us')]").chkVisible().jsClick();//click on Booklets link
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
//        link.setCSS("#sidebar-button").chkVisible().click();//click on Booklets link
//        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link
//
//        label.setXpath(productNameH1Xpath).chkVisible();//check that we on the required page
//
//        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");
//
//        label.setXpath("//*[@id='panel_1']/parent::div/h2").chkVisible().jsClick();
//
//        label.setXpath("//label[@for='options_1026_4']").chkVisible().click();
//
//        if (browserName.equals("android")) {
//            dropDown.setCSS("#select_1027").chkVisible().selectValue("6 x 9");
//            dropDown.setCSS("#select_1028").chkVisible().selectValue("20# White Bond");
//        }
//
//        button.setCSS("#new_submit").chkVisible().jsClick();
//        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
//        link.setCSS("#shopping-cart-table .product-item-details--mobile>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
//    }
//
//    @Test(dataProvider = "browsers")
//    public void checkoutPageLoadingTest(String browserName) {
//        open_page(mainUrl, browserName);
//        link.setCSS("#sidebar-button").chkVisible().click();//click on Booklets link
//        link.setCSS(bookingLnkXpath).chkVisible().jsClick();//click on Booklets link
//
//        label.setXpath(productNameH1Xpath).chkVisible();//check that we on the required page
//
//        input.setCSS("#options_1022_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1023_text").chkVisible().chkEnabled().setValue("1");
//        input.setCSS("#options_1024_text").chkVisible().chkEnabled().setValue("UserNAme");
//
//        label.setXpath("//*[@id='panel_1']/parent::div").chkVisible().click();
////        radioButton.setCSS("#options_1025_2").chkVisible().chkEnabled().click();
//
//        label.setXpath("//label[@for='options_1026_4']").chkVisible().click();
//
//        dropDown.setCSS("#select_1027").chkVisible().selectValue("6 x 9");
//        dropDown.setCSS("#select_1028").chkVisible().selectValue("20# White Bond");
//
//        button.setCSS("#new_submit").chkVisible().click();
//        label.setXpath("//*[@id='shopping-cart-table']").chkVisible();
//        link.setCSS("#shopping-cart-table .product-item-details--mobile>.product-item-name ").chkVisible().chkText("BOOKLET PRINTING");
//
//        setVisibility("input.dz-hidden-input");
//        input.setCSS("input.dz-hidden-input").chkVisible().chkEnabled().uploadFile(new File("files/test_source.pdf"));
////        button.setCSS(".action.remove-file").chkVisible();
//        link.setCSS(".upload-area").moveToElement().chkVisible();
//
//
//        button.setXpath("//div[@class='cart-container']//*[@data-role='proceed-to-checkout']").chkVisible().click();
//
//        input.setCSS("#email").chkVisible().chkEnabled().setValue("qauser3512@gmail.com");
//        input.setCSS("#pass").chkVisible().chkEnabled().setValue("qamandess2020Q");
//        button.setCSS("#send2").chkVisible().click();
//
////        refresh();
//        button.setXpath("//div[@class='cart-container']//*[@data-role='proceed-to-checkout']").chkVisible().chkEnabled().click();
//
//        label.setCSS("#shipping").chkVisible();
//    }