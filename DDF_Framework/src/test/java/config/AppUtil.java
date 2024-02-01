package config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static Properties conpro;
public static WebDriver app;
@BeforeTest
public static void setUp() throws Throwable, IOException {
	conpro = new Properties();
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome")) {
		app = new ChromeDriver();
		app.manage().window().maximize();
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox")) {
		app = new FirefoxDriver();
	}
	else {
		Reporter.log("Browser Value is Not Matching",true);
	}
}
@AfterTest
public static void tearDown() {
	app.quit();
}
}
