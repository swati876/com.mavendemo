import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReusableMethods {
       static WebDriver driver;
       static ExtentReports reports;
       static String reportFolder = "C:\\Users\\rscha\\SeleniumExcelSheets\\Report\\";
       static ExtentTest Logger;

//       public static void InitializeDriver() {
//              WebDriverManager.chromedriver().setup();
//              LaunchUrl();
          //    Login();
       //}
      //System.setProperty("webdriver.chrome.driver", "C:\\Users\\rscha\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");

//       public static void launchUrl(){
//       driver = new ChromeDriver();
//       driver.manage().window().maximize();
//       driver.get("https://login.salesforce.com/");
//       }

       public static void InitializeReport(){
       reports = new ExtentReports(reportFolder + new SimpleDateFormat("'SalesForceReport_'YYYYMMddHHmm'.html'").format(new Date()));
       }

       public static void enterText(WebElement ele, String txtValue, String objectName) {
              if (ele.isDisplayed()) {
              ele.sendKeys(txtValue);
              System.out.println(txtValue + "has been successfully Entered into " + objectName);
              }
              else {
              System.out.println(objectName + " is not displayed on the web page.");
              }
              }

       public static void clickElement(WebElement ele, String objectName) throws IOException {
              if (ele.isDisplayed()){
              ele.click();
              System.out.println(objectName + " has been successfully clicked.");
              }
              else {
              System.out.println(objectName + " is not displayed on the web page.");
              Logger.log(LogStatus.ERROR, objectName + " is not displayed in the web page." +
              Logger.addScreenCapture(takeScreenshot()));
              }
              }

       public static String takeScreenshot() throws IOException {
              TakesScreenshot srcShot = ((TakesScreenshot) driver);
              File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
              String imagePath = reportFolder + "ScreenShots\\" + new SimpleDateFormat("'Image_'YYYYMMddHHmm'.png'").format(new Date());
              File destFile = new File(imagePath);
              FileUtils.copyFile(srcFile,destFile);
              return imagePath;
       }

       public static String[][] readXlData(String path, String sheetName) throws IOException {
              FileInputStream fs=new FileInputStream(new File(path));
              HSSFWorkbook wb=new HSSFWorkbook(fs);
              HSSFSheet sheet=wb.getSheet(sheetName);
              int rowCount=sheet.getLastRowNum()+1;
              int colCount=sheet.getRow(0).getLastCellNum();
              String[][] data=new String[rowCount][colCount];
              for(int i=0;i<rowCount;i++){
                     for(int j=0;j<colCount;j++){
                            int cellType=sheet.getRow(i).getCell(j).getCellType();
                            if(cellType== HSSFCell.CELL_TYPE_NUMERIC){
                                   int val=(int)sheet.getRow(i).getCell(j).getNumericCellValue();
                                   data[i][j]=String.valueOf(val);
                            }
                            else
                                   data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
                     }
              }
              return (data);
       }

       public static void writeXlData(String fileName, String sheetName, int row, int col, String value) throws IOException{
              File file=new File(fileName);
              FileInputStream fs=new FileInputStream(file);
              HSSFWorkbook wb=new HSSFWorkbook(fs);
              HSSFSheet sheet=wb.getSheet(sheetName);
              sheet.createRow(row).createCell(col).setCellValue(value);
              FileOutputStream fo=new FileOutputStream(file);
              wb.write(fo);
              fo.flush();
              fo.close();
       }

       public static void dropdownSelection(WebElement obj, String option, String optionName) {
              Select select = new Select(obj);
              select.selectByValue(option);
//            logger.log(Status.PASS,
//            MarkupHelper.createLabel(optionName + " is selected from the dropdown", ExtentColor.GREEN));
       }


}
