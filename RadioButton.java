package mobile;

import com.codeborne.selenide.SelenideDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RadioButton extends Element {
    public RadioButton(SelenideDriver driver) {
        super(driver);
    }

    public RadioButton() {
        super();
    }


    public RadioButton chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public RadioButton click() {
        element.click();
        return this;
    }

    public RadioButton setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public RadioButton setCSS(String cssSelector) {
        this.xpath = cssSelector;
        this.element = $(this.xpath);
        return this;
    }

    public RadioButton chkVisible() {
        element.shouldBe(visible);
        return this;
    }
}
