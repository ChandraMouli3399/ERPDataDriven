package driverFactory;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;
public class AppTest extends AppUtil{
 String inputPath = "./FileInput/ERPLoginData.xlsx";
 String outputpath ="./FileOutput/ERPLoginResults.xlsx";
 ExtentReports report;
 ExtentTest logger;
 @Test
 public void startTest() throws Throwable {
	 report = new ExtentReports("./target/Reports/DataDriven.html");
	 ExcelFileUtil xl = new ExcelFileUtil(inputPath);
	 int rc = xl.rowCount("Login");
	 Reporter.log("Number of Rows are::"+rc);
	 for(int i=1;i<=rc;i++) {
		 logger =report.startTest("Validate Login");
		 String user = xl.getCellData("Login", i, 0);
		 String pass = xl.getCellData("Login", i, 1);
		 boolean res = FunctionLibrary.adminLogin(user, pass);
		 if(res) {
			 xl.setCellData("Login", i, 2, "Login is Success", outputpath);
			 xl.setCellData("Login", i, 3, "Pass", outputpath);
			 logger.log(LogStatus.PASS, "Valid Username and Password");
		 }
		 else {
			 File screen = ((TakesScreenshot)app).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"LoginPage.png"));
			 xl.setCellData("Login", i, 2, "Login is Failure", outputpath);
			 xl.setCellData("Login", i, 3, "Fail", outputpath);
			 logger.log(LogStatus.FAIL, "Invalid Username and Password");
		 }
		 report.endTest(logger);
		 report.flush();
	 }
 }
}
