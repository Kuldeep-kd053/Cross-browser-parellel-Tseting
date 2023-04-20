package pwa_uat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
public class Loop {
    WebDriver driver;
    @Test
    public void executeSessionOne(){
        //First session of WebDriver
        System.setProperty("webdriver.edge.driver", "C:\\automation\\chromedriver.exe");
        //create Edge instance
        WebDriver driver = new EdgeDriver();
        //Goto guru99 site
        driver.get("https://mau.uat.repco.odxc.info/login");
        driver.quit();
    }

    @Test
    public void executeSessionTwo(){
        //Second session of WebDriver
        System.setProperty("webdriver.chrome.driver","C:\\automation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Goto guru99 site
        driver.get("https://mau.uat.repco.odxc.info/login");
        //find username text box and fill it
        driver.quit();
    }
    @Test
    public void executeSessionThree(){
        //Third session of WebDriver
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Genius\\IdeaProjects\\SAMPLE\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //Goto guru99 site
        driver.get("https://mau.uat.repco.odxc.info/login");
        //find username text box and fill it
    driver.quit();

    }
}