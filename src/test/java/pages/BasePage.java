package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public abstract class BasePage<T extends BasePage<T>> {


    private ElementsCollection listItems = $$(MobileBy.id("list"));

    @Step("Go to the next page")
    public   <P extends BasePage<?>> P goToAnotherPageByAccessibilityId(String accessibilityId, Class<P> nextPage) {

        SelenideElement item = $(MobileBy.AccessibilityId(accessibilityId));
        item.shouldBe(visible);
        item.click();
        return page(nextPage);
    }

}
