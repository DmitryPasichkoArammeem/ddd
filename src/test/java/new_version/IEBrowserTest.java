package new_version;

import browser.BaseTest;
import org.openqa.selenium.JavascriptException;
import org.testng.annotations.*;

import java.io.File;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getSelenideDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.TestStepLogger.logStep;

public class IEBrowserTest extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";
    String browserName = "explorer";

    String bookletsLinkCSS = "li.level0.nav-1.category-item.first.level-top";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        start = end = -1;
        start = getTime();
    }

    @Test
    public void mainPageLoadingTest() {
        test_name = "Open main page";
        logStep(1,"");
        open_page(mainUrl, browserName);
    }

    @Test
    public void detailsPageLoadingTest() {
        test_name = "Open product details page";
        open_page(mainUrl, browserName);
        link.setCSS(bookletsLinkCSS).chkVisible().click();//click on Booklets link
        label.setXpath("//*[@data-ui-id='page-title-wrapper']").chkVisible().chkText("BOOKLET PRINTING");//check that we on the required page
    }

    @Test
    public void shoppingCartPageLoadingTest() {
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

    @Ignore
    @Test
    public void checkoutPageLoadingTest() {
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
        try {
            waitPageLoading();
        }catch (JavascriptException e){
            System.out.println("sad(");
        }

        end = getTime();
        System.out.println(printReport(end, start, test_name));
//        close();
        getWebDriver().close();
    }
}
