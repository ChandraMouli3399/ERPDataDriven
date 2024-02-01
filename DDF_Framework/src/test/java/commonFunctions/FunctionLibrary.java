package commonFunctions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.Reporter;
import config.AppUtil;
public class FunctionLibrary extends AppUtil{
public static boolean adminLogin(String username,String password) {
	app.get(conpro.getProperty("Url"));
	app.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	app.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
	app.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(username);
	app.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(password);
	app.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	String Expected = "dashboard";
	String Actual = app.getCurrentUrl();
	if(Actual.contains(Expected)) {
		Reporter.log("Username and Password are Valid"+"    "+Expected+"   "+Actual,true);
		app.findElement(By.xpath(conpro.getProperty("Objlogout"))).click();
		return true;
	}
	else {
		String Error_Message = app.findElement(By.xpath(conpro.getProperty("ObjError_Message"))).getText();
		app.findElement(By.xpath(conpro.getProperty("ObjOK_Button"))).click();
		Reporter.log(Error_Message+"   "+Expected+"   "+Actual,true);
		return false;
	}
}
}
