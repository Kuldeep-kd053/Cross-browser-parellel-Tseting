package pwa_uat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class Order_placing {
WebDriver driver;
    @Test
    public static void launch_browser() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/automation/chromedriver_win32/chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://mau.uat.repco.odxc.info/en");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //send values in search box
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

        // create variable to get expected scroll
        WebElement Element1 = driver.findElement(By.xpath("(//div[@class='owl-dot ng-star-inserted']//span)[1]"));
        // scroll window
        JavascriptExecutor jss = (JavascriptExecutor) driver;
        jss.executeScript("arguments[0].scrollIntoView();", Element1);
        // click on add to cart
        driver.findElement(By.xpath("//div[@class='atc-btn']//button")).click();
        // click on check out
        driver.findElement(By.xpath("(//div[@class='px-3 row rounded-0 mb-1']//button)[1]")).click();
        WebElement Element2 = driver.findElement(By.xpath(
                "//div[@class='col-10 store_address pl-0 ml--15px text-gray']"));
        JavascriptExecutor jsss = (JavascriptExecutor) driver;
        jsss.executeScript("arguments[0].scrollIntoView();", Element2);
        Thread.sleep(10000);
        // click on order now
        driver.findElement(By.xpath("//div[@class='col-12 padding-top padding-bottom text-right']//button")).click();

        // guest user mail
        driver.findElement(By.xpath("//input[@formcontrolname='emailId']")).sendKeys("ss@malinator.com");
        // confirm mail
        driver.findElement(By.xpath("//input[@formcontrolname='confirmEmailId']")).sendKeys(
                "ss@malinator.com");
        // checkout as guest
        driver.findElement(By.xpath(" //button[contains(text(),'Checkout as Guest ')]")).click();
        // enter fist name
        driver.findElement(By.xpath("//input[@id='address.firstName']")).sendKeys("kuldeep");
        //enter last name
        driver.findElement(By.xpath("//input[@id='address.surname']")).sendKeys("kumar");
        //click use our manual entry form
        driver.findElement(By.xpath("//span[@class='field-link js-switch-to-manual']")).click();
        //enter address
        driver.findElement(By.xpath("//input[@id='address.line1']")).sendKeys("Camperdown Pre School");
        // enter address 2
        driver.findElement(By.xpath("//input[@id='address.line2']")).sendKeys("50 Campbell St");
        //enter city
        driver.findElement(By.xpath("//input[@id='address.townCity']")).sendKeys("CAMPERDOWN");
        //enter post code
        driver.findElement(By.xpath("//input[@id='address.postcode']")).sendKeys("3260");
        // select city
        driver.findElement(By.xpath("//select[@id='address.region']//option[@value='VIC']")).click();
        // enter mobile no
        driver.findElement(By.xpath("//input[@id='address.phone']")).sendKeys("0000000000");

        // scroll page to get expected element
        WebElement Element3 = driver.findElement(By.xpath("//div[@class='row checkout-giftcard-options__title']"));
        JavascriptExecutor jssss= (JavascriptExecutor) driver;
        jssss.executeScript("arguments[0].scrollIntoView();", Element3);
        //enter card number
        driver.findElement(By.xpath("//div[@id='cardNumber-container']//iframe")).sendKeys(
                "4111 1111 1111 1111");
        // enter card expiry
        driver.findElement(By.xpath("//input[@id='card_expiration']")).sendKeys("03/22");
        // card cvv
        driver.findElement(By.xpath("//input[@id='card_cvNumber']")).sendKeys("123");

        // click on pay now
        driver.findElement(By.xpath("//button[@id='pay-button']")).click();

        //assert order confirmation page
        assertEquals(driver.findElement(By.xpath("//div[@class='checkout-success__body__headline']")).getText(),
                "Thank you for your order");
        System.out.println("PASS- Thank you for your order");
        driver.quit();
        }
    }

