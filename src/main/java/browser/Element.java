package browser;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Element {
    SelenideDriver driver;
    String xpath = "";
    SelenideElement element = null;

    public int getX(){
        return element.getCoordinates().inViewPort().x+2;
    }

    public int getY(){
        return element.getCoordinates().inViewPort().y+2;
    }
    public void jsClick(){
        JavascriptExecutor js = (JavascriptExecutor)(getWebDriver());
        js.executeScript("document.elementFromPoint("+getX()+", "+getY()+").click();");
    }

    public SelenideElement getElement(){
        return element;
    }

    public Element(SelenideDriver driver) {
        this.driver = driver;
    }

    public Element() {
        this.driver = WebDriverRunner.getSelenideDriver();
    }
}
