package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.logging.Logger;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.qameta.allure.Step;

public class PreferenceDependencies extends BasePage<PreferenceDependencies> {

    private static final Logger logger = Logger.getLogger("PreferenceDependencies");

    private SelenideElement wiFiSettings = $(MobileBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));

    private SelenideElement wiFiCheckBox = $(MobileBy.xpath("//android.widget.CheckBox[@resource-id='android:id/checkbox']"));

    private SelenideElement wiFiBtn = $(MobileBy.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[1]/android.widget.RelativeLayout"));
    private SelenideElement wiFiSettingsMenu = $(MobileBy.xpath("//android.widget.LinearLayout[@resource-id='android:id/parentPanel']"));

    private SelenideElement wiFiInput = $(MobileBy.xpath("//android.widget.EditText[@resource-id='android:id/edit']"));

    private SelenideElement okBtn = $(MobileBy.xpath("//android.widget.Button[@resource-id='android:id/button1']"));

    @Step("Check is wi fi settings not clickable")
    public boolean isWiFiSettingsNotClickable() {
        logger.info("Check is wi fi settings not clickable");
        return wiFiSettings.getAttribute("clickable").equals("false");
    }

    @Step("Check is check box selected")
    private boolean isCheckboxSelected(SelenideElement checkbox) {
        logger.info("Check is check box selected");
        return wiFiCheckBox.getAttribute("checked").equals("true");
    }

    @Step("Check is wi fi check box selected")
    public boolean isWiFiCheckBoxSelected() {
        logger.info("Check is wi fi check box selected");
        return isCheckboxSelected(wiFiCheckBox);
    }

    @Step("Select wi fi check box")
    public PreferenceDependencies selectCheckbox() {
        logger.info("Select wi fi check box");
        if (!isCheckboxSelected(wiFiCheckBox)) {
            wiFiBtn.click();
            wiFiSettings.getAttribute("clickable").equals("true");
        }
        return this;
    }

    @Step("Go to wi fi settings")
    public PreferenceDependencies goToWiFiSettings() {
        logger.info("Go to wi fi settings");
        wiFiSettings.click();
        wiFiSettingsMenu.shouldBe(visible);
        return this;
    }

    @Step("Enter text in field and submit")
    public PreferenceDependencies enterTextInTheAppearedFieldAndSubmit() {
        logger.info("Enter text in field and submit");
        wiFiInput.setValue("Text");
        okBtn.click();
        return this;
    }

    @Step("Check is edit filed empty")
    public boolean isEditTextFieldEmpty() {
        logger.info("Check is edit filed empty");
        String text = wiFiInput.getAttribute("text");
        return text == null || text.isEmpty();
    }

}


