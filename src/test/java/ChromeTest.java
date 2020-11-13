import browser.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChromeTest extends BaseTest {
    String mainUrl = "https://www.bestvaluecopy.com/";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        setBrowser("firefox");
    }

    @Test
    public void openMainPage() {
        start = getTime();
        test_name = "Open main page";
//        open_page(mainUrl);
        waitPageLoading();
        end = getTime();
    }

    @AfterMethod(alwaysRun = true)
    public void down() {
        System.out.println(test_name + " - " + convertToSeconds(end - start) + " sec");
        WebDriverRunner.getWebDriver().close();
    }
}
