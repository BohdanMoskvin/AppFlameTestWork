package tests;

import static helper.Constants.ACCESSIBILITY_ID_PREFERENCES_FROM_CODE_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_PREFERENCE_PAGE;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.jfr.Description;
import pages.MainPage;
import pages.Preference;
import pages.PreferenceFromCode;

public class PreferenceFromCodeTest extends BaseTest {
    private static MainPage mainPage;

    @BeforeTest
    public static void init() {
        mainPage = new MainPage();
    }

    @Description("Verify that all elements on the screen are enabled after enabling the switch and all checkboxes.")
    @Test
    public void verifyElementsEnabledAfterSwitchAndCheckboxes() {
        PreferenceFromCode preferenceFromCode = mainPage
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PREFERENCE_PAGE, Preference.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PREFERENCES_FROM_CODE_PAGE, PreferenceFromCode.class);

        preferenceFromCode
                .activeSwitcher()
                .selectPreferenceCheckbox()
                .scrollToParentCheckbox()
                .selectParentCheckBox()
                .selectChildCheckBox();

        Assert.assertTrue(preferenceFromCode.isPreferenceCheckboxSelected(), "Preference checkbox should be selected");
        Assert.assertTrue(preferenceFromCode.isParentCheckBoxSelected(), "Parent checkbox should be selected");
        Assert.assertTrue(preferenceFromCode.isChildCheckBoxSelected(), "Child checkbox should be selected");
        Assert.assertTrue(preferenceFromCode.isSwitcherActive(), "Switcher should be active");
    }

}
