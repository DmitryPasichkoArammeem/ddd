//package android;
//
//import com.codeborne.selenide.SelenideDriver;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Condition.visible;
//
//public class Label extends Element {
//    public Label(SelenideDriver driver) {
//        super(driver);
//    }
//
//
//    public Label chkEnabled() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Label click() {
//        driver.$(xpath).click();
//        return this;
//    }
//
//    public Label setXpath(String xpath) {
//        this.xpath = xpath;
//        return this;
//    }
//
//    public Label chkVisible() {
//        driver.$(xpath).shouldBe(visible);
//        return this;
//    }
//
//    public Label cgkText(String expectedText) {
//        driver.$(xpath).shouldHave(text(expectedText));
//        return this;
//    }
//}
