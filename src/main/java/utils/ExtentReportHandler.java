package utils;

import base.AppConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHandler {

    public static ExtentReports reports ;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm");

    public static ExtentReports getReportObject(){
        String dateText = dtf.format(LocalDateTime.now());
        String browserText = AppConstants.browserName;
        String reportPath = "./reports/"+dateText+"_"+browserText;
//        String reportPath = "./reports/"+B;

        ExtentSparkReporter sparkReporter =new ExtentSparkReporter(reportPath);

        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");
        sparkReporter.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size','-webkit-fill-available');");


        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);

        reports.setSystemInfo("Tester is: ", "Abdulrhman Alfiky");
        return  reports;

    }





}
