package tests;

import static helper.Constants.ACCESSIBILITY_ID_PERFERENCE_DEPENDENDENCIES_PAGE;
import static helper.Constants.ACCESSIBILITY_ID_PREFERENCE_PAGE;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.ConfigReader;
import driver.EmulatorHelper;
import jdk.jfr.Description;
import pages.MainPage;
import pages.Preference;
import pages.PreferenceDependencies;

public class WiFiTest extends BaseTest{

    String mainAcActivity = ConfigReader.emulatorConfig.appActivity();
    private static MainPage mainPage;

    @BeforeTest
    public static void init() {
        mainPage = new MainPage();
    }

    @Description("Verify the behavior of the WiFi settings checkbox and text field persistence after application restart.")
    @Test
    public void verifyWiFiSettingsCheckboxAndTextFieldPersistence(){
       PreferenceDependencies preferenceDependencies = mainPage.goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PREFERENCE_PAGE, Preference.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PERFERENCE_DEPENDENDENCIES_PAGE, PreferenceDependencies.class );

       Assert.assertTrue(preferenceDependencies.isWiFiSettingsNotClickable());
       preferenceDependencies.selectCheckbox().goToWiFiSettings().enterTextInTheAppearedFieldAndSubmit();

       EmulatorHelper.restartApp(mainAcActivity);

        PreferenceDependencies preferenceDependenciesAfterRestart = mainPage.goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PREFERENCE_PAGE, Preference.class)
                .goToAnotherPageByAccessibilityId(ACCESSIBILITY_ID_PERFERENCE_DEPENDENDENCIES_PAGE, PreferenceDependencies.class );

        Assert.assertTrue(preferenceDependenciesAfterRestart.isWiFiCheckBoxSelected());

        Assert.assertTrue( preferenceDependenciesAfterRestart.goToWiFiSettings().isEditTextFieldEmpty());
    }
}
