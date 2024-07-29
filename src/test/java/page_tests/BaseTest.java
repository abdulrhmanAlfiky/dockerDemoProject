package page_tests;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static utils.ExtentReportHandler.getReportObject;

public class BaseTest {

    protected WebDriver driver ;
    public String browser;

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);


    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(@Optional String browserName, ITestResult iTestResult){

        ChromeOptions co = new ChromeOptions();
        EdgeOptions ed = new EdgeOptions();

        if(browserName!=null){
            browser = browserName;
        }
        else {
            browser = AppConstants.browserName;
        }

        logger.info("Browser name is:"+browser);

//        System.out.println("browser name is " + browser);

        if(browser.equalsIgnoreCase("chrome"))
        {
           if (AppConstants.platform.equalsIgnoreCase("local")){
               WebDriverManager.chromedriver().setup();
               driver = new ChromeDriver();
           } else if (AppConstants.platform.equalsIgnoreCase("remote"))
           {
               co.setPlatformName("linux");
               co.setPageLoadStrategy(PageLoadStrategy.EAGER);

               try {
                   driver = new RemoteWebDriver(new URL("http://localhost:4441"),co);
               } catch (MalformedURLException e) {
                   throw new RuntimeException(e);
               }

           }
           else {
               logger.error("Platform not supported");
           }

        }
        else if (browser.equalsIgnoreCase("edge")){
            if (AppConstants.platform.equalsIgnoreCase("local")){
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            else if (AppConstants.platform.equalsIgnoreCase("remote"))
            {
                ed.setPlatformName("linux");
                ed.setPageLoadStrategy(PageLoadStrategy.EAGER);

                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4442"), ed);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            }
            else {
                logger.error("platform note supported");
            }
        }
        else {
            logger.info("Invalid Browser Name");
//          System.out.println("browser name entered is not supported !");
            }

        ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Driver Start Time: "+ LocalDateTime.now());
    }

    @AfterMethod
    public void tearDownTest(ITestResult iTestResult){
        if(iTestResult.isSuccess())
        {
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName()+" is successful!!", ExtentColor.GREEN));
        }

        else {
            testLogger.get().log(Status.FAIL, "Test failed due to: "+ iTestResult.getThrowable());
            String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName()+".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertImg_Base64(screenshot)).build());
        }

        testLogger.get().log(Status.INFO, "Driver End Time: "+ LocalDateTime.now());

        driver.quit();
    }

    @AfterClass
    public void flushTestReport()
    {
        reports.flush();
    }
}
