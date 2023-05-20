package fanselect.fancurve;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
public static WebDriver driver;
static String drv_loc="E:\\software\\java software\\selenium\\chrome_driver\\chromedriver112.exe";
public static void driverLoad() {
	System.setProperty("webdriver.chrome.driver", drv_loc);
	
	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	//chromePrefs.put("profile.default_content_settings.popups", 0);
	chromePrefs.put("download.default_directory", "D:\\temp");
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", chromePrefs);
	
	driver=new ChromeDriver(options);
	driver.get("https://www.fanselect.net");
	driver.manage().window().maximize();
}

}
