import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Main extends ReusableMethods {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        launchUrl();
       //login();
        InitializeReport();

       //TC1_LoginSalesForce_Maven();
       //TC4A_LoginSalesForce_Maven();
       TC5_UserMenuDropdown_Maven();
        reports.flush();
    }

    public static void launchUrl(){
        driver =new ChromeDriver();
        driver.get("https://login.salesforce.com/");
        driver.manage().window().maximize();
    }


    public static void login() throws InterruptedException {
       // InitializeDriver();
        WebElement uName = driver.findElement(By.xpath("//input[@id='username']"));
        uName.sendKeys("swatic@abc.com");
        WebElement pWord = driver.findElement(By.xpath("//input[@id='password']"));
        pWord.sendKeys("swati1chavan");
        Thread.sleep(2000);
        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='Login']"));
        loginBtn.click();
        Thread.sleep(3000);
        driver.close();
    }

    public static void TC1_LoginSalesForce_Maven() throws InterruptedException {
       // InitializeDriver();
        WebElement uName = driver.findElement(By.xpath("//input[@id='username']"));
        uName.sendKeys("swatic@abc.com");
        WebElement pWord = driver.findElement(By.xpath("//input[@id='password']"));
        pWord.sendKeys("swati1chavan");
        Thread.sleep(2000);
        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='Login']"));
        loginBtn.click();
        Thread.sleep(3000);
        driver.close();
    }

    public static void TC4A_LoginSalesForce_Maven() throws InterruptedException, IOException {
        Logger = reports.startTest("TC4A_LoginSalesForce_Mavan");
        Logger.log(LogStatus.PASS, " Test case Started");
        //InitializeDriver();
        //launchUrl();
        String[][] data = readXlData("C:\\Users\\rscha\\SeleniumExcelSheets\\TC4A_LoginSalesForce.xls", "Sheet1");
        driver.get(data[0][1]);

        WebElement fgtPwd = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
        fgtPwd.click();
        Logger.log(LogStatus.PASS, "ForgotPassword is entered successfully");
        Thread.sleep(2000);

        WebElement ContinueBtn = driver.findElement(By.xpath("//input[@id='continue']"));
        ContinueBtn.click();
        Logger.log(LogStatus.PASS, "ContinueBtn is entered successfully");
        Thread.sleep(4000);

        Logger.log(LogStatus.PASS, "TC4A Passed and see the attached screenshot. \r\n" + Logger.addScreenCapture(takeScreenshot()));
        reports.endTest(Logger);
        driver.close();

    }

    public static void TC5_UserMenuDropdown_Maven() throws InterruptedException, IOException {
        Logger = reports.startTest("TC5_UserMenuDropdown");
        Logger.log(LogStatus.PASS, " Test case Started");
        //InitializeDriver();
        String[][] data = readXlData("C:\\Users\\rscha\\SeleniumExcelSheets\\TC5_LoginSalesForce.xls", "Sheet1");
        driver.get(data[0][1]);

        WebElement uName = driver.findElement(By.xpath("//input[@id='username']"));
        enterText(uName, data[1][1], "UserName");
        Logger.log(LogStatus.PASS, "Username is entered successfully");
        WebElement pWord = driver.findElement(By.xpath("//input[@id='password']"));
        enterText(pWord, data[2][1], "Password");
        Logger.log(LogStatus.PASS, "password is entered successfully");
        Thread.sleep(2000);
        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='Login']"));
        clickElement(loginBtn, "LoginButton");
        Logger.log(LogStatus.PASS, "Login Button Clicked Successfully");

        WebElement UserMenu = driver.findElement(By.xpath("//div[@id='userNavButton']"));
        clickElement(UserMenu, "userNameField");

        Logger.log(LogStatus.PASS, "UserNav Button Clicked Successfully");
        Thread.sleep(2000);
        WebElement logoutBtn = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        logoutBtn.click();
        Thread.sleep(10000);
        Logger.log(LogStatus.PASS, "TC5 Passed and see the attached screenshot. \r\n" + Logger.addScreenCapture(takeScreenshot()));
        driver.close();
        System.out.println("TC5_UserMenuDropdown is completed");
        reports.endTest(Logger);
        driver.close();

    }

}

