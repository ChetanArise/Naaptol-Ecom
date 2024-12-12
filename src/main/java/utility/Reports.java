package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	public static ExtentReports createReport()
	{
		ExtentSparkReporter htmlreports = new ExtentSparkReporter("NaaptolTestReports");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(htmlreports);
		reports.setSystemInfo("ProjectName","Naaptol-Ecom");
		reports.setSystemInfo("Owner","Chetan");
		return reports;
		
	}
}
