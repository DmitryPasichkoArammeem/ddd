package browser;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.visible;

public class Element {
    SelenideDriver driver;
    String xpath = "";
    SelenideElement element = null;

    public Element(SelenideDriver driver) {
        this.driver = driver;
    }

    public Element() {
        this.driver = WebDriverRunner.getSelenideDriver();
    }
}
