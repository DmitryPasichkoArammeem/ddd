package browser;

import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    public Link moveToElement() {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public boolean isVisible() {
        return element.isDisplayed();
    }

    public Link chkText(String text) {
        element.shouldHave(text(text));
        return this;
    }
}
