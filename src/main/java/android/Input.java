//package android;
//
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.SelenideDriver;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Condition.visible;
//
//public class Input extends Element {
//    public Input(SelenideDriver driver) {
//        super(driver);
//    }
//
//
//    public Input chkEnabled() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Input click() {
//        driver.$(xpath).click();
//        return this;
//    }
//
//    public Input setXpath(String xpath) {
//        this.xpath = xpath;
//        return this;
//    }
//
//    public Input chkVisible() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Input setValue(String value) {
//        driver.$(xpath).setValue(value);
//        return this;
//    }
//
//    public Input chkValue(String value) {
//        driver.$(xpath).shouldHave(Condition.value(value));
//        return this;
//    }
//
//    public Input chkText(String value) {
//        driver.$(xpath).shouldHave(text(value));
//        return this;
//    }
//}
