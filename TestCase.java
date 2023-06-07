package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCase {

    private static WebDriver driver;
    String currentTime = String.valueOf(System.currentTimeMillis());
    public static ExtentSparkReporter spark = new ExtentSparkReporter("target/spark/report.html");
    public static ExtentReports report = new ExtentReports();

    LoginPage login = new LoginPage(driver);
    UpdateDetailsPage updateDetails = new UpdateDetailsPage(driver);
    HomePage homePage =new HomePage(driver);
    ContactUsPage contactPage = new ContactUsPage(driver);

    @BeforeClass
    public static void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("beforeClass");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Automation\\drivers\\driver113\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        report.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("my report");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.HOME_PAGE);
    }

    //כניסה למערכת הגרלת דירה בהנחה ע"י שמ משתמש וכניסה
    @Test
    public void a_loginTest() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest login_test = report.createTest("login test");
        login_test.log(Status.INFO,"the login test start");
        Thread.sleep(1000);
        login.insertLoginNumber(getData("loginNumber"));
        Thread.sleep(1000);
        login.insertLoginPassword(getData("loginPassword"));
        Thread.sleep(1000);
        login.clickLoginBtn();
        login_test.log(Status.PASS,"test passed");
        Thread.sleep(3000);
        try {
            Assert.assertEquals("test failed",Constants.EXPECTED_URL_AFTER_LOGIN,driver.getCurrentUrl());
            login_test.pass("login passed - you reached the sing in page");
        } catch (Exception e){
            login_test.fail("login fail - then was an error reaching the sing in page", MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot("target\\screenshots\\"+currentTime+"homeCategoryTestStep4")).build());
        }
        Thread.sleep(2000);
    }

    //שינוי ועדכון פרטים של המשתמש
    @Test
    public void b_updateT() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest update_test = report.createTest("update test");
        update_test.log(Status.INFO,"update test");
        updateDetails.checkBox();
        Thread.sleep(2000);
        updateDetails.clickUpdate();
        Thread.sleep(5000);
        try {
            Assert.assertEquals("test failed",Constants.EXPECTED_URL_AFTER_UPDATE,driver.getCurrentUrl());
            update_test.pass("update test passed - the details update and you reached the ProjectsList page");
        } catch (Exception e){
            update_test.fail("update test fail - then was an error reaching the ProjectsList page", MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot("target\\screenshots\\"+currentTime+"updateDetailsTest")).build());
        }
        Thread.sleep(2000);
    }

    //מעבר לרשימת הפרויקטים ובחירת פרוייקט לפי פרמטרים שונים, לחיצה למעבר לפרטים ותוצאות של ההגרלה
    @Test
    public void c_homePageTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest ProjectsList_test = report.createTest("ProjectsList test");
        ProjectsList_test.log(Status.INFO,"ProjectsList test");
        homePage.clickBtnRegister();
        Thread.sleep(1000);
        homePage.searchCity();
        Thread.sleep(800);
        homePage.theNameOfTheProgram();
        Thread.sleep(800);
        homePage.statusProgect();
        Thread.sleep(800);
        homePage.entitlement();
        Thread.sleep(800);
        homePage.lotteryNumber(getData("lotteryNumber"));
        Thread.sleep(800);
        homePage.toPrice(getData("price"));
        Thread.sleep(2000);
//        MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot("target\\screenshots\\"+currentTime+"after_search")).build();
        Thread.sleep(2000);
        homePage.btnSearchApartment();
        Thread.sleep(3000);
        homePage.toDetails();
        Thread.sleep(2000);
        try {
            Assert.assertEquals("test failed",Constants.EXPECTED_URL_AFTER_TODETAILS,driver.getCurrentUrl());
            ProjectsList_test.pass("ProjectsList test passed - the details update and you reached the details project page");
        } catch (Exception e){
            ProjectsList_test.fail("ProjectsList test fail - was an error reaching the details project page", MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot("target\\screenshots\\"+currentTime+"updateDetailsTest")).build());
        }
        Thread.sleep(2000);
    }

    //חזרה לדף הבית
    @Test
    public void d_navigateToHomePageTest() throws InterruptedException, IOException {
        ExtentTest navigate = report.createTest("navigate test");
        navigate.log(Status.INFO,"navigate test");
        driver.navigate().to(Constants.HOME_PAGE);
        Thread.sleep(5000);
        try {
            Assert.assertEquals("test failed",Constants.HOME_PAGE,driver.getCurrentUrl());
            navigate.pass("you reached to home page");
        } catch (Exception e){
            navigate.fail("an error reaching to home page");
        }
        Thread.sleep(2000);
    }

    //מעבר ליצירת קשר
    @Test
    public void e_contactTest() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest contact = report.createTest("contant test");
        contact.log(Status.INFO,"contant test");
        Thread.sleep(2000);
        contactPage.hrefToContant();
        Thread.sleep(7000);
    }

    @AfterClass
    public static void afterClass() throws InterruptedException {
        System.out.println("AfterClass");
        Thread.sleep(5000);
        driver.quit();
        report.flush();
    }

    //get data from XML File - get Key name  - returns the item of the key found
    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File(Constants.CONFIG_FILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }


    //get data from XML File - gets Key name and index of item - returns the item for the specific index of the key found
    private static String getDataItem (String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException {
        File configXmlFile = new File(Constants.CONFIG_FILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(index).getTextContent();
    }

    //a function to create screenshot
    private static String takescreenshot(String ImagesPath){
        TakesScreenshot takescreenshot = (TakesScreenshot)driver;
        File screenshotFile = takescreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+" .png");
        try {
            FileUtils.copyFile(screenshotFile,destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+" .png";
    }
}
