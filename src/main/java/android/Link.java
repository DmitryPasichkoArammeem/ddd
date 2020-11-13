//package android;
//
//import com.codeborne.selenide.SelenideDriver;
//
//import static com.codeborne.selenide.Condition.visible;
//
//public class Link extends Element {
//    public Link(SelenideDriver driver) {
//        super(driver);
//    }
//
//
//    public Link chkEnabled() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Link click() {
//        driver.$(xpath).click();
//        return this;
//    }
//
//    public Link setXpath(String xpath) {
//        this.xpath = xpath;
//        return this;
//    }
//
//    public Link chkVisible() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//}
