package tests;

import static helper.Constants.ACCESSIBILITY_ID_ANIMATION_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_SEEKING_PAGE;

import com.github.romankh3.image.comparison.model.ImageComparisonState;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.io.File;

import jdk.jfr.Description;
import pages.AnimationPage;
import pages.MainPage;
import pages.SeekingPage;

public class SeekingTest extends BaseTest{
    private static MainPage mainPage;
    private String methodName;

    @BeforeMethod
    public void init(ITestContext context, ITestResult result) {
        mainPage = new MainPage();
        methodName = result.getMethod().getMethodName();
    }
    @Description("Verify that the position of the 'circle' changes after adjusting the SeekBar value.")
    @Test
    public void verifyCirclePositionChangeAfterSeekBarAdjustment() {
        SeekingPage seekingPage = mainPage.goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_ANIMATION_PAGE, AnimationPage.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_SEEKING_PAGE, SeekingPage.class );
        File fileBeforeDrag = seekingPage.takePageScreenshot();
        seekingPage.dragSlider(200,0);
        File fileAfterDrag = seekingPage.takePageScreenshot();
        assertScreenshot(fileBeforeDrag, methodName, fileAfterDrag, ImageComparisonState.MISMATCH);
    }
}
