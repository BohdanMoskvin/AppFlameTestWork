package driver;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nonnull;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class EmulatorDriver implements WebDriverProvider {
    protected static AndroidDriver driver;
    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String APP = ConfigReader.emulatorConfig.app();
    private static final String URL = ConfigReader.emulatorConfig.remoteURL();

    public static java.net.URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        Assert.assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);

        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);

        desiredCapabilities.setCapability("app", getAbsolutePath(APP));
        driver = new AndroidDriver<>(getUrl(), desiredCapabilities);
        return driver;
    }
}
