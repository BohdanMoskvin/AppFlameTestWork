package pages;


import static com.codeborne.selenide.Selenide.$;
import static driver.EmulatorHelper.androidScrollToAnElementByText;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.logging.Logger;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class PreferenceFromCode extends BasePage<PreferenceFromCode> {

    private static final Logger logger = Logger.getLogger("PreferenceFromCode");
    private SelenideElement checkboxPreference = $(MobileBy.xpath("//android.widget.CheckBox[@resource-id='android:id/checkbox']"));
    private SelenideElement checkboxParent = $(MobileBy.xpath("(//android.widget.CheckBox[@resource-id='android:id/checkbox'])[1]"));
    private SelenideElement checkboxChild = $(MobileBy.xpath("(//android.widget.CheckBox[@resource-id='android:id/checkbox'])[2]"));
    private SelenideElement switcher = $(MobileBy.xpath("//android.widget.Switch[@resource-id='android:id/switch_widget']"));

    @Step("Select check box")
    private PreferenceFromCode selectCheckbox(SelenideElement checkbox) {
        logger.info("Select check box");
        if (!checkbox.getAttribute("checked").equals("true")) {
            checkbox.click();
            checkbox.shouldHave(Condition.attribute("checked", "true"));
        }
        return this;
    }

    @Step("Check is check box selected")
    private boolean isCheckboxSelected(SelenideElement checkbox) {
        logger.info("Check is check box selected");
        return checkbox.getAttribute("checked").equals("true");
    }

    @Step("Select Preference Check Box")
    public PreferenceFromCode selectPreferenceCheckbox() {
        logger.info("Select Preference Check Box");
        return selectCheckbox(checkboxPreference);
    }
    @Step("Select Parent Check Box")
    public PreferenceFromCode selectParentCheckBox() {
        logger.info("SSelect Parent Check Box");
        return selectCheckbox(checkboxParent);
    }
    @Step("Select Child Check Box")
    public PreferenceFromCode selectChildCheckBox() {
        logger.info("Select Child Check Box");
        return selectCheckbox(checkboxChild);
    }

    @Step("Check is switcher active")
    public boolean isSwitcherActive() {
        logger.info("Check is switcher active");
        return switcher.getAttribute("checked").equals("true");
    }

    @Step("Active switcher")
    public PreferenceFromCode activeSwitcher() {
        if (!isSwitcherActive()) {
            logger.info("Active switcher");
            switcher.click();
            switcher.shouldHave(Condition.attribute("checked", "true"));
        }
        return this;
    }

    @Step("Scroll to element")
    public PreferenceFromCode scrollToParentCheckbox() {
        logger.info("Scroll to element");
        androidScrollToAnElementByText("Parent checkbox preference");
        return this;
    }

    @Step("Check is preference check box selected")
    public boolean isPreferenceCheckboxSelected() {
        logger.info("Check is preference check box selected");
        return isCheckboxSelected(checkboxPreference);
    }
    @Step("Check is parent check box selected")
    public boolean isParentCheckBoxSelected() {
        logger.info("Check is parent check box selecte");
        return isCheckboxSelected(checkboxParent);
    }
    @Step("Check is child check box selected")
    public boolean isChildCheckBoxSelected() {
        logger.info("Check is child check box selected");
        return isCheckboxSelected(checkboxChild);
    }
}
