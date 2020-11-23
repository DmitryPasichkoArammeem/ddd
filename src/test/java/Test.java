import core.BaseTest;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.AssertJUnit.fail;

@Ignore
public class Test extends BaseTest {
    String mainUrl = "https://bvcm2.nextmp.net/";

    String bookingLnkXpath = ".navigation-mobile li.category-item.first.level-top";
    String productNameH1Xpath = "span.base";


    @DataProvider(name = "browsers")
    public static Object[][] primeNumbers() {
        return new Object[][]{{"iPhone"}, {"Android"}};
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        result = new HashMap<>();
        result.put("version","Old");
        result.put("url",mainUrl);

        start = end = -1;
        start = getTime();
    }


    @org.testng.annotations.Test(dataProvider = "browsers")
    public void detailsPageL1oadingTest(String browserName) {
        result.put("device",browserName);result.put("desk","Open 1 details page");


    }

    @org.testng.annotations.Test(dataProvider = "browsers")
    public void detailsPageLoa2dingTest(String browserName) {
        fail();
        result.put("device",browserName);result.put("desk","Open 2 details page");

    }

    @org.testng.annotations.Test(dataProvider = "browsers")
    public void detailsPageL3oadingTest(String browserName) {
        fail();

        result.put("device",browserName);result.put("desk","Open 3 details page");

    }

    @org.testng.annotations.Test(dataProvider = "browsers")
    public void detailsPageLoa4dingTest(String browserName) {
        fail();
        result.put("device",browserName);result.put("desk","Open 4 details page");

    }

    @AfterMethod
    public void down() {
        end = getTime();
        result.put("time",""+convertToSeconds(end - start));
        results.add(result);
//        getWebDriver().quit();
    }
    @AfterClass(alwaysRun = true)
    public void dow1n() {
//        writeDataLineByLine(results);
    }
}
