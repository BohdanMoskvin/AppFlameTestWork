package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import java.util.logging.Logger;

import driver.EmulatorHelper;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class AdvancedPreferences extends BasePage<AdvancedPreferences> {

    private static final Logger logger = Logger.getLogger("AdvancedPreferences");
    private LaunchingPreferences launchingPreferences;

    public AdvancedPreferences(LaunchingPreferences launchingPreferences) {
        this.launchingPreferences = launchingPreferences;
    }

    private int finalCount;
    private int initialCount;
    private SelenideElement countPrefernces = $(MobileBy.xpath("//android.widget.TextView[@resource-id='io.appium.android.apis:id/mypreference_widget']"));

    private SelenideElement myPrefetenceBtn =
            $(MobileBy.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[1]/android.widget.RelativeLayout"));

    private SelenideElement toastWithCount =
            $(MobileBy.xpath("//android.widget.Toast[starts-with@text='Thanks! You increased my count to']"));

    @Step("Get count")
    private int getCount() {
        logger.info("Get count");
        return Integer.parseInt(countPrefernces.shouldBe(visible).getText());
    }

    @Step("Click multiple times and set final count")
    public AdvancedPreferences clickMultipleTimesAndSetFinalCount(int clicks) {
        logger.info("Click multiple times and set final count");
        initialCount = getCount();

        for (int i = 0; i < clicks; i++) {
            myPrefetenceBtn.shouldBe(visible).click();
        }

        finalCount = initialCount + clicks;
        return this;
    }

    @Step("Get final count")
    private int getFinalCount() {
        logger.info("Get final count");
        return finalCount;
    }

    @Step("Get init count")
    private int getInitCount() {
        logger.info("Get init count");
        return initialCount;
    }

    @Step("Check if the difference between the final count and initial count is equal to {expectedDifference}")
    public boolean isDifferenceCorrect(int expectedDifference) {
        logger.info("Check if the difference between the final count and initial count is equal to {expectedDifference}");
        return (getFinalCount() - getInitCount()) == expectedDifference;
    }

    public LaunchingPreferences back() {
        EmulatorHelper.goBack();
        return launchingPreferences;
    }
}
