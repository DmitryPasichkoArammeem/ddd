package browser;

import com.codeborne.selenide.SelenideDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DropDown extends Element {
    public DropDown(SelenideDriver driver) {
        super(driver);
    }

    public DropDown() {
        super();
    }


    public DropDown chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public DropDown click() {
        element.click();
        return this;
    }

    public DropDown setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public DropDown setCSS(String cssSelector) {
        this.xpath = cssSelector;
        this.element = $(this.xpath);
        return this;
    }

    public DropDown chkVisible() {
        element.shouldBe(visible);
        return this;
    }

    public DropDown selectValue(String value) {
        element.selectOptionContainingText(value);
        return this;
    }
}
