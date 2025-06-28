package extentreport;

import com.common.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport.html";
        extent = new ExtentReports(reportPath, true);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.startTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(LogStatus.PASS, "Test passed");
        extent.endTest(test.get());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // ✅ Use ThreadLocal WebDriver
        WebDriver driver = ((BaseClass) result.getInstance()).getdriver();

        String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

        ExtentTest extentTest = test.get();
        extentTest.log(LogStatus.FAIL, result.getThrowable());
        extentTest.log(LogStatus.FAIL, "Screenshot below: " + extentTest.addScreenCapture(screenshotPath));

        extent.endTest(extentTest);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(LogStatus.SKIP, "Test skipped: " + result.getThrowable());
        extent.endTest(test.get());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        extent.close();
    }

    private String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) return "";

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots";
        String screenshotPath = screenshotDir + File.separator + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    // Optional methods
    @Override public void onStart(ITestContext context) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) {}

    public static ExtentTest getCurrentTest() {
        return test.get();
    }
}