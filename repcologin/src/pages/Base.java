package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;


public class Base {
    WebDriver driver;
    @Test
    public void validateResponsive() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/automation/chromedriver_win32/chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        //ChromeDriver driver = new ChromeDriver(options);
        Map<String, String> deviceMobEmu = new HashMap<>();
        deviceMobEmu.put("deviceName", "iPhone X");
        options.setExperimentalOption("mobileEmulation", deviceMobEmu);
        driver = new ChromeDriver(options);
        driver.get("https://mau.uat.repco.odxc.info/en");
        driver.quit();

    }

}
