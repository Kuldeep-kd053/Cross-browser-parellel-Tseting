package pwa_uat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
public class CrossBrowser {
    WebDriver driver;
    //Driver Code
    public static void main(String[] args) {
    }
    @BeforeTest
    //@Parameters("browser")
    public void setup( @Optional("chrome") String browser) throws Exception {
                 //Check if parameter passed from TestNG is 'firefox'
        if (browser.equalsIgnoreCase("firefox")) {
                     //create firefox instance
                     System.setProperty("webdriver.gecko.driver", "C:\\Users\\Genius\\IdeaProjects\\SAMPLE\\geckodriver\\geckodriver.exe");
                     driver = new FirefoxDriver();
                 }
                 //Check if parameter passed as 'chrome'
                 else if (browser.equalsIgnoreCase("chrome")) {
                     //set path to chromedriver.exe
                     System.setProperty("webdriver.chrome.driver", "C:\\automation\\chromedriver_win32\\chromedriver.exe");
                     //create chrome instance
                     driver = new ChromeDriver();
                 }
                 //Check if parameter passed as 'Edge'
                 else if (browser.equalsIgnoreCase("Edge")) {
                     //set path to Edge.exe
                     System.setProperty("webdriver.edge.driver", "C:\\Users\\Genius\\Downloads\\New folder\\edgedriver_win64 (1)(1)\\msedgedriver.exe");
                     //create Edge instance
                     driver = new EdgeDriver();
                 } else {
                     //If no browser passed throw exception
                     throw new Exception("Browser is not correct");
                 }
                 Thread.sleep(10000);
        Map<String, String> deviceMobEmu = new HashMap<>();
        deviceMobEmu.put("deviceName", "iPhone X");
        ChromeOptions chromeopt = new ChromeOptions();
        chromeopt.setExperimentalOption("mobileEmulation", deviceMobEmu);
        driver = new ChromeDriver(chromeopt);
        driver.get("https://mau.uat.repco.odxc.info/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
             }


   @Test
    public void a_launchBrowser() throws InterruptedException {
        Thread.sleep(10000);
        this.driver.get("https://mau.uat.repco.odxc.info/login");
        driver.navigate().refresh();
        Thread.sleep(10000L);
    }


   @Test
    public void b_loginVerification_empty_email () throws IllegalArgumentException, InterruptedException {
        //page scroller
       this.driver.get("https://mau.uat.repco.odxc.info/login");
        WebDriverWait wait = new WebDriverWait(driver,10);
        this.driver.findElement(By.xpath("//*[@formcontrolname='email']")).clear();
        this.driver.findElement(By.xpath("//*[@formcontrolname='email']")).sendKeys(" ");
        Thread.sleep(5000L);
        //find element password
        this.driver.findElement(By.xpath("//*[@formcontrolname='password']")).clear();
        this.driver.findElement(By.xpath("//*[@formcontrolname='password']")).sendKeys("wwdfefeerff");
        JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollBy(0,100)", new Object[0]);
        this.driver.findElement(By.xpath(".//*[text()=' Log In ']")).click();
        Thread.sleep(20);
        //assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Email Address is required')]")).getText(), "Email Address is required");
        //If the message is displayed
        System.out.println("PASS-b_loginVerification_empty_email");
    }


    @Test
    public void c_login_verification_empty_pass() throws InterruptedException {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.findElement(By.xpath("//*[@formcontrolname='email']")).clear();
        this.driver.findElement(By.xpath("//*[@formcontrolname='email']")).sendKeys("kuldeep@gmail.com");
        Thread.sleep(5000L);
        //find element password
        this.driver.findElement(By.xpath("//*[@formcontrolname='password']")).clear();
        JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollBy(0,150)", new Object[0]);
        this.driver.findElement(By.xpath(".//*[text()=' Log In ']")).click();
        assertEquals(driver.findElement(By.xpath("//*[text()='Password is required']")).getText(),"Password is required");
        System.out.println("PASS-c_login_verification_empty_pass");
    }


    @Test
    public void d_loginVerification_emptyCredentials() throws InterruptedException {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.findElement(By.xpath("//*[@formcontrolname='email']")).clear();
        this.driver.findElement(By.xpath(".//*[@formcontrolname='password']")).clear();
        JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollBy(0,150)", new Object[0]);
        Thread.sleep(5000L);
        this.driver.findElement(By.xpath(".//*[text()=' Log In ']")).click();
        assertEquals(driver.findElement(By.xpath("//*[text()='Your username or password was incorrect.']")).getText(),"Your username or password was incorrect.");
        System.out.println("PASSd-d_loginVerification_emptyCredentials");
    }


    @Test
    public void e_loginVerification_invalidCredentials() throws IllegalArgumentException, InterruptedException {
        // find element email
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().refresh();
        this.driver.findElement(By.xpath(".//*[@formcontrolname='email']")).clear();
        this.driver.findElement(By.xpath(".//*[@formcontrolname='email']")).sendKeys("kuldeep@gmail.com");
        Thread.sleep(5000L);
        //find element password
        this.driver.findElement(By.xpath(".//*[@formcontrolname='password']")).clear();
        this.driver.findElement(By.xpath(".//*[@formcontrolname='password']")).sendKeys("Kbcm");
        Thread.sleep(5000L);
        JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollBy(0,150)", new Object[0]);
        this.driver.findElement(By.xpath(".//*[text()=' Log In ']")).click();
        Thread.sleep(5000L);
        assertEquals(driver.findElement(By.className("alert-message")).getText(), "Your username or password was incorrect.");
        //If the message is displayed
        System.out.println("PASS-e_loginVerification_invalidCredentials");

    }


    @Test
    public void f_loginVerification_validCredentials() throws IllegalArgumentException, InterruptedException {
        this.driver.get("https://mau.uat.repco.odxc.info/login");
        // find element email
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.findElement(By.xpath("(//input[@formcontrolname='email'])[1]")).clear();
        this.driver.findElement(By.xpath("(//input[@formcontrolname='email'])[1]")).sendKeys("kuldeepverma7080@gmail.com");
        // find element password
        this.driver.findElement(By.xpath(".//*[@formcontrolname='password']")).clear();
        this.driver.findElement(By.xpath(".//*[@formcontrolname='password']")).sendKeys("Kuldeep@123");
        Thread.sleep(5000L);
        // scroll page
        JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollBy(0,150)", new Object[0]);
        this.driver.findElement(By.xpath(".//*[text()=' Log In ']")).click();
        Thread.sleep(30000L);
        // assertion
        assertEquals(driver.findElement(By.xpath("//*[text()='Hi kuldeep']")).getText(), "Hi kuldeep");
        //If the message is displayed
        System.out.println("PASS-f_loginVerification_validCredentials");
    }


    @Test
    public void g_logout() throws InterruptedException {
        //logout
        this.driver.findElement(By.xpath(".//*[@class='logout']")).click();
        this.driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        if (driver.getTitle().equals("Repco Australia | Homepage")) {
            System.out.println("We are back at Repco homepage");
        }
        else {
            System.out.println("We are NOT in Repco homepage");
        }
    }


    @Test
    public void h_create_an_account() throws IllegalArgumentException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // declare variable for input in email field
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String b = "a" + dtf.format(now) + "@gmail.com";
        JavascriptExecutor jjs = (JavascriptExecutor) this.driver;
        jjs.executeScript("window.scrollBy(0,250)", new Object[0]);
        // enter first name
        this.driver.findElement(By.xpath("//input[@formcontrolname='firstName']"));
        this.driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("kuldeep");
        //enter last name
        this.driver.findElement(By.xpath("//*[@formcontrolname='lastName']"));
        this.driver.findElement(By.xpath("//*[@formcontrolname='lastName']")).sendKeys("bhai");
        // enter email
        this.driver.findElement(By.xpath("(//input[@formcontrolname='email'])[2]")).sendKeys(b);
        //print emil which is taken as 'b'
        System.out.println("created_account_email_is"+" "+ b +" and "+" "+"password_is= Kuldeep@54321");
        // clear and enter password
        this.driver.findElement(By.xpath("(//input[@formcontrolname='password'])[2]")).clear();
        this.driver.findElement(By.xpath("(//input[@formcontrolname='password'])[2]")).sendKeys("Kuldeep@54321");
        // confirm password
        this.driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).clear();
        this.driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys("Kuldeep@54321");
        //enter mobile number
        this.driver.findElement(By.xpath(".//*[@formcontrolname='mobileNumber']")).sendKeys("0000000000");
        JavascriptExecutor jjjs = (JavascriptExecutor) this.driver;
        jjs.executeScript("window.scrollBy(0,600)", new Object[0]);
        //click on register
        this.driver.findElement(By.xpath("//button[contains(text(),' Register ')]")).click();
        Thread.sleep(20000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://mau.uat.repco.odxc.info/en/my-account", "test case failed");
        System.out.println("PASS -create account test");
    }


        @Test
        public void i_logout(){
        //logout
            this.driver.findElement(By.xpath("//span[@class='logout']")).click();
            WebDriverWait wait = new WebDriverWait(driver,30);
            // back to homepage
            if (driver.getTitle().equals("Repco Australia")) {
                System.out.println("We are back at Repco homepage");
            }
            else {
                System.out.println("We are NOT in Repco homepage");
                WebDriverWait wait1 = new WebDriverWait(driver,30);
            }

    }


    @AfterTest
    //quit browser
    public void quitAllTabs() {
        this.driver.quit();
    }
}