//package android;
//
//import com.codeborne.selenide.SelenideDriver;
//
//import static com.codeborne.selenide.Condition.visible;
//
//public class Button extends Element {
//    public Button(SelenideDriver driver) {
//        super(driver);
//    }
//
//
//    public Button chkEnabled() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Button click() {
//        driver.$(xpath).click();
//        return this;
//    }
//
//    public Button setXpath(String xpath) {
//        this.xpath = xpath;
//        return this;
//    }
//
//    public Button chkVisible() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//}
