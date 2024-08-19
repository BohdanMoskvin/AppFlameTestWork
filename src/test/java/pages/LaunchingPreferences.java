package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class LaunchingPreferences extends BasePage<LaunchingPreferences> {

    private static final Logger logger = Logger.getLogger("LaunchingPreferences");

    private int finalCountOnTheLaunchPage;

    private int initCountOnTheLaunchPage;

    private SelenideElement counterValue = $(MobileBy.xpath("//android.widget.TextView[starts-with(@text, 'The counter value is')]"));

    private SelenideElement launchBtn = $(MobileBy.xpath("//android.widget.Button[@text='Launch PreferenceActivity']"));

    @Step("Get counter value")
    private int getCounterValue() {
        logger.info("Get counter value");

        String text = counterValue.getText();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            throw new RuntimeException("Numeric value not found in the text: " + text);
        }
    }

    @Step("Start launch")
    public AdvancedPreferences startLaunch() {
        logger.info("Start launch");
        launchBtn.shouldBe(visible).click();
        return new AdvancedPreferences(this);
    }

    @Step("Set final count")
    public void setFinalCount() {
        logger.info("Set final count");
        finalCountOnTheLaunchPage = getCounterValue();
    }

    @Step("Set init count")
    public void setInitCount() {
        logger.info("Set init count");
        initCountOnTheLaunchPage = getCounterValue();
    }

    @Step("Check if the difference between final and initial counts is {expectedDifference}")
    public boolean isDifferenceCorrect(int expectedDifference) {
        logger.info("Check if the difference between final and initial counts is {expectedDifference}");
        return (finalCountOnTheLaunchPage - initCountOnTheLaunchPage) == expectedDifference;
    }
}
