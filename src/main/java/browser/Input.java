package browser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Input extends Element{
    public Input(SelenideDriver driver) {
        super(driver);
    }
    public Input() {
        super();
    }


    public Input chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public Input click() {
        element.click();
        return this;
    }

    public Input setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public Input setCSS(String cssSelector) {
        this.xpath = cssSelector;
        this.element = $(this.xpath);
        return this;
    }

    public Input chkVisible() {
        element.shouldBe(visible);
        return this;
    }

    public Input setValue(String value) {
        element.setValue(value);
        return this;
    }

    public Input sendKeys(String value) {
        element.sendKeys(value);
        return this;
    }

    public Input uploadFile(File value) {
        element.uploadFile(value);
        return this;
    }

    public Input chkValue(String value) {
        element.shouldHave(Condition.value(value));
        return this;
    }

    public Input chkText(String value) {
        element.shouldHave(text(value));
        return this;
    }
}
