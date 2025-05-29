package extentreport;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportUtil {

    public static void logStep(String message, LogStatus status) {
        ExtentTest test = ExtentReporterNG.getCurrentTest();
        if (test != null) {
            test.log(status, message);
        }
    }
}
