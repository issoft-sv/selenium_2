package task_100;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class Listener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.addAttachment("Browser", "Chrome, v.86.0.4240.198");
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
