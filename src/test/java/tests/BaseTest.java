package tests;

import static helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static helper.DeviceHelper.executeBash;
import static helper.RunHelper.runHelper;
import static io.qameta.allure.Allure.step;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import listener.AllureListener;

@Listeners(AllureListener.class)
public class BaseTest {
    @BeforeClass
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.startMaximized = true;
        Configuration.browserSize = null;
        Configuration.timeout = 20000;
        disableAnimationOnEmulator();
    }

    private static void disableAnimationOnEmulator() {
        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
        executeBash("adb -s shell settings put global window_animation_scale 0.0");
        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
    }

    public void assertScreenshot(File beforeAction, String fileName, File afterAction, ImageComparisonState expectedState) {

        fileName = fileName + ".png";
        String beforeActionScreensDir = "build/screenshots/beforeAction/";

        File beforeActionScreensDirFile = new File(beforeActionScreensDir);
        if (!beforeActionScreensDirFile.exists()) {
            beforeActionScreensDirFile.mkdirs();
        }
        try {
            Files.move(beforeAction.toPath(), new File(beforeActionScreensDir + fileName).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("The expected screenshot could not be saved: " + fileName, e);
        }

        BufferedImage firstImage = ImageComparisonUtil
                .readImageFromResources(beforeActionScreensDir + fileName);

        BufferedImage secondImage = ImageComparisonUtil
                .readImageFromResources(SCREENSHOT_TO_SAVE_FOLDER + afterAction.getName());

        File resultDestination = new File("build/screenshots/diff_" + fileName);

        ImageComparisonResult imageComparisonResult = new ImageComparison(firstImage, secondImage, resultDestination)
                .compareImages();

        ImageComparisonState actualState = imageComparisonResult.getImageComparisonState();

        if (actualState.equals(ImageComparisonState.MISMATCH)) {
            try {
                byte[] diffImageBytes = Files.readAllBytes(resultDestination.toPath());
                AllureListener.saveScreenshot(diffImageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Assert.assertEquals(actualState, expectedState, "Image comparison failed: " + fileName);
    }

    @BeforeMethod
    public void startDriver() {
        step("Open APK", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    @AfterMethod
    public void afterEach() {
        step("Close APK", Selenide::closeWebDriver);
    }
}
