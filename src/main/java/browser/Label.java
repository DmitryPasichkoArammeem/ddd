package browser;

import com.codeborne.selenide.SelenideDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Label extends Element{
    public Label(SelenideDriver driver) {
        super(driver);
    }

    public Label() {
        super();
    }


    public Label chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public Label click() {
        element.click();
        return this;
    }

    public Label setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public Label setCSS(String xpath) {
        this.xpath = xpath;
        this.element = $(this.xpath);
        return this;
    }

    public Label chkVisible() {
        element.shouldBe(visible);
        return this;
    }

    public Label chkText(String expectedText) {
        element.shouldHave(text(expectedText));
        return this;
    }
}
