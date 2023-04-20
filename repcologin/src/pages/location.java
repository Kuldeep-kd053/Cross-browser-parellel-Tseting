package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class location {
    WebDriver driver;
    @BeforeTest
    public WebDriver initWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("profile.default_content_setting_values.notifications", 3);
        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver", "C:/automation/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mau.uat.repco.odxc.info/en");
        return new ChromeDriver(options);

    }
    @Test
    public void launch() throws InterruptedException{
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://mau.uat.repco.odxc.info/en");
        driver.findElement(By.xpath("//input[@placeholder='Search Product or Part #']")).sendKeys("oil");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //click for search
        driver.findElement(By.xpath("(//a[contains(text(),'engine oil')])[5]")).click();
        // click on product
        driver.findElement(By.xpath("(//a[@class='c-block'])[1]")).click();
        // click ON select store for stock anc price
        WebElement Element = driver.findElement(By.xpath(
                "(//div[@class='owl-dot active ng-star-inserted']//span)[1]"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//div[@class='col-12 mt-3 px-0']//button")).click();
        // send post code
        driver.findElement(By.xpath("//input[@placeholder='Enter Suburb or Postcode']")).sendKeys("3010");
        // click on search
        driver.findElement(By.xpath("(//i[@class='fa fa-search'])[2]")).click();

        // select store from list
        driver.findElement(By.xpath(
                "(//button[@class='btn btn-success btn-block rounded-0 ng-star-inserted'])[2]")).click();
        assertEquals(driver.findElement(By.xpath("//span[@class='vehicleName ng-star-inserted']")).getText(),
                "Repco St Peters");
        Thread.sleep(10000);
        System.out.println("store- Repco St Peters is selected");

        driver.quit();

    }
}