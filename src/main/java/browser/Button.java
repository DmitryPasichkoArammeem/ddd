package browser;

import com.codeborne.selenide.SelenideDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Button extends Element {
    public Button(SelenideDriver driver) {
        super(driver);
    }

    public Button() {
        super();
    }


    public Button chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public Button click() {
        element.click();
        return this;
    }

    public Button setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public Button setCSS(String xpath) {
        this.xpath = xpath;
        this.element = $(this.xpath);
        return this;
    }

    public Button chkVisible() {
        element.shouldBe(visible);
        return this;
    }
}
