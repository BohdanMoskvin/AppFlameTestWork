package tests;

import static helper.Constants.ACCESSIBILITY_ID_LAUNCHING_PERFERENCES_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_PREFERENCE_PAGE;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.jfr.Description;
import pages.AdvancedPreferences;
import pages.LaunchingPreferences;
import pages.MainPage;
import pages.Preference;

public class LaunchingPreferencesTest extends BaseTest {

    private static MainPage mainPage;

    @BeforeTest
    public static void init() {
        mainPage = new MainPage();
    }

    @Description("Verify that clicking the 'My preference' button X times correctly increments the counter value near the button.")
    @Test
    private void verifyCounterIncrementOnPreferenceButtonClick() {

        int count = 5;

        LaunchingPreferences launchingPreferences = mainPage.goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PREFERENCE_PAGE, Preference.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_LAUNCHING_PERFERENCES_PAGE, LaunchingPreferences.class);
        launchingPreferences.setInitCount();
        AdvancedPreferences advancedPreferences = launchingPreferences.startLaunch().clickMultipleTimesAndSetFinalCount(count);

        Assert.assertTrue(advancedPreferences.isDifferenceCorrect(count));

        advancedPreferences
                .back().setFinalCount();
        Assert.assertTrue(launchingPreferences.isDifferenceCorrect(count));
    }
}
