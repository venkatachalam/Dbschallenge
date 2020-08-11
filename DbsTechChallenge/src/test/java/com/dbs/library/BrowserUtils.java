package com.dbs.library;

import java.io.FileReader;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserUtils {
	
	
	public static RemoteWebDriver driver = null;
	DesiredCapabilities capabilities = null;
	//private static EnvironmentPropertiesReader envProperty = EnvironmentPropertiesReader.getEnvironmentDataInstance();

	public RemoteWebDriver initialize() throws Exception {
		if (driver == null) {
			createNewInstanceDriver();
			BrowserUtils.driver = getDriver();
		} else {
			BrowserUtils.driver = getDriver();
		}
		return driver;
	}

	@SuppressWarnings("deprecation")
	private void createNewInstanceDriver() throws Exception {
		
		
	    FileReader reader=new FileReader("/Users/venkatachalamn/eclipse-workspace/BGPTechChallenge/src/test/java/com/bgp/config/config.properties");  
		Properties p=new Properties();  
	    p.load(reader);  
	    
	    
		String browser = p.getProperty("browser");


		try {
			String OS = System.getProperty("os.name").toLowerCase();
			if (OS.equalsIgnoreCase("windows")) {
				switch (browser.toLowerCase()) {
				case "chrome":
					String downloadFilepath = System.getProperty("user.dir") + "\\testreports\\latest\\downloadedPDFs";
					System.out.println("Path=>" + downloadFilepath);
					Map<String, Object> preferences = new Hashtable<String, Object>();
					preferences.put("profile.default_content_settings.popups", 0);
					preferences.put("download.prompt_for_download", "false");
					preferences.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
					preferences.put("download.default_directory", downloadFilepath);
					System.setProperty("webdriver.chrome.driver",
							"src/test/java/com/bgp/support/drivers/chromedriver.exe");
					ChromeOptions chromeOptions = new ChromeOptions();
					//chromeOptions.addArguments("disable-extensions");
					chromeOptions.addArguments("start-maximized");
					chromeOptions.setExperimentalOption("useAutomationExtension", false);
					chromeOptions.setExperimentalOption("prefs", preferences);
					
					capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
					driver = new ChromeDriver(capabilities);
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver",
							"src/test/java/com/bgp/support/drivers/geckodriver.exe");
					driver = new FirefoxDriver();
					break;
				default:
					System.out.println("Default");
				}
			} else {
				switch (browser.toLowerCase()) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver",
							//"src/test/java/com/bgp/support/drivers/chromedriver");
					"/usr/local/bin/chromedriver");
							driver = new ChromeDriver();
							driver.manage()
							.window().maximize();
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", "src/test/java/com/bgp/support/drivers/geckodriver");
					driver = new FirefoxDriver();
					break;
				default:
					System.out.println("Default");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error during invoking driver - " + e);
		}
	}

	private RemoteWebDriver getDriver() {
		return driver;
	}

	public void destroyDriver() throws InterruptedException {
		driver.quit();
		driver = null;
	}
	
	

}
