package listener;

import com.codeborne.selenide.Selenide;

import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (!testName.contains("Screenshot")) {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            saveScreenshot(screenshot);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
    }

}
