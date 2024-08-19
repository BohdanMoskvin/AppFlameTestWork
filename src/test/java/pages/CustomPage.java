package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.logging.Logger;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class CustomPage extends BasePage<CustomPage> {
    private static final Logger logger = Logger.getLogger("CustomPage");

    private SelenideElement navigationBar = $(MobileBy.xpath("//android.widget.TextView[@text='App/Loader/Custom']"));
    private SelenideElement searchIcon = $(MobileBy.xpath("//android.widget.TextView[@content-desc='Search']"));

    private SelenideElement searchCloseBtn = $(MobileBy.xpath("//android.widget.ImageView[@content-desc='Clear query']"));
    private SelenideElement searchInput = $(MobileBy.xpath("//android.widget.AutoCompleteTextView[@resource-id='android:id/search_src_text']"));

    private ElementsCollection searchResult = $$(MobileBy.xpath("//*[@resource-id='io.appium.android.apis:id/text']"));


    @Step("Checking the navigation display in the header")
    public boolean isCorrectNavigation(String path) {
        logger.info("Checking the navigation display in the header");
        String actualPath = navigationBar.getText();
        return actualPath.equals(path);
    }

    @Step("Click on the search input")
    public CustomPage clickOnTheSearchInput() {
        logger.info("Click on the search input");
        searchIcon.should(Condition.visible).click();
        return this;
    }

    @Step("Enter text in the search input")
    public CustomPage enterTextInTheSearchInput(String text) {
        logger.info("Enter text in the search input");
        searchInput.setValue(text);
        return this;
    }

    @Step("Check all elements start with 'text'")
    public boolean allElementsStartWith(String prefix) {
        logger.info("Check all elements start with 'text'");
        return searchResult.stream()
                .allMatch(element -> element.getText().contains(prefix));
    }
    @Step("Clear search input")
    public CustomPage clearSearchInput() {
        logger.info("Clear search input");
        searchCloseBtn.should(Condition.visible).click();
        return this;
    }

    @Step("Get count elements")
    public int getCountElements() {
        logger.info("Get count elements");
        return searchResult.size();
    }

    @Step("Get count elements by Text")
    public int getCountElementsByText(String text) {
        logger.info("Get count elements by Text");
        return (int) searchResult.stream()
                .filter(element -> element.getText().contains(text))
                .count();
    }
}
