package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.logging.Logger;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class SeekingPage extends BasePage<SeekingPage> {

    private static final Logger logger = Logger.getLogger("SeekingPage");
    private SelenideElement seekBar = $(MobileBy.id("io.appium.android.apis:id/seekBar"));
    private SelenideElement ball = $(MobileBy.className("android.view.View"));
    @Step("Take a screenshot")
    public File takePageScreenshot(){
        logger.info("Take a screenshot");
        return ball.screenshot();
    }

    @Step("Drag the slider")
    public SeekingPage dragSlider(int x, int y) {
        logger.info("Drag the slider");
        actions().clickAndHold(seekBar)
                .moveByOffset(x, y)
                .release()
                .perform();
        return this;
    }
}
