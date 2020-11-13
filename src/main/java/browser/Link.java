package browser;

import com.codeborne.selenide.SelenideDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Link extends Element{
    public Link(SelenideDriver driver) {

        super(driver);
    }
    public Link() {

        super();
    }


    public Link chkEnabled() {
        element.shouldBe(visible);
        return this;
    }

    public Link click() {
        element.click();
        return this;
    }

    public Link setXpath(String xpath) {
        this.xpath = xpath;
        this.element = $x(this.xpath);
        return this;
    }

    public Link setCSS(String xpath) {
        this.xpath = xpath;
        this.element = $(this.xpath);
        return this;
    }

    public Link chkVisible() {
        element.shouldBe(visible);
        return this;
    }

    public Link chkText(String text) {
        element.shouldHave(text(text));
        return this;
    }
}
