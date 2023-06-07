package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    public WebDriver driver;

    //constractor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //dataMembers
    By btnRegisterlocator = By.xpath("//*[@id=\"navbar\"]/ul/li[2]/a");
    By cityLocstor = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div[1]/span/span[1]/span");
    By btnNameProgramLocator = By.id("select2-slctPlanName-container");
    By nameProgramLocator = By.cssSelector("ul[id='select2-slctPlanName-results'] li:nth-child(4)");
    By btnStatusProjectLocator = By.id("select2-slctStatus-container");
    By statusProgectLocator = By.cssSelector("ul[id='select2-slctStatus-results'] li:nth-child(1)");
    By btnEntitlementLocator = By.id("select2-slctEntitlement-container");
    By entitlementLocator = By.cssSelector("ul[id='select2-slctEntitlement-results'] li:nth-child(2)");
    By lotteryNumberLocator = By.id("lotteryNumber");
    By toPriceLocator = By.id("toPrice");
    By btnSearchApartmentLocator = By.xpath("//*[@id=\"search\"]/div[2]/div[2]/a[1]");
    By eladLocator = By.cssSelector("ul[id='select2-slctCity-results'] li:nth-child(9)");
    By btnToDetails = By.xpath("//*[@id=\"divView\"]/div/div[7]/div/div[1]/table/tbody/tr/td[14]/button");


//    method

    public void clickBtnRegister() {
        driver.findElement(btnRegisterlocator).click();
    }

    public void searchCity() throws InterruptedException {
        WebElement city = driver.findElement(cityLocstor);
        city.click();
        Thread.sleep(1000);
        WebElement cityElad = driver.findElement(eladLocator);
        cityElad.click();
    }

    public void theNameOfTheProgram() throws InterruptedException {
        WebElement btnNameP = driver.findElement(btnNameProgramLocator);
        btnNameP.click();
        Thread.sleep(1000);
        WebElement theProgram = driver.findElement(nameProgramLocator);
        theProgram.click();
    }

    public void entitlement() throws InterruptedException {
        WebElement btnEnti = driver.findElement(btnEntitlementLocator);
        btnEnti.click();
        Thread.sleep(1000);
        WebElement enti = driver.findElement(entitlementLocator);
        enti.click();
    }

    public void statusProgect() throws InterruptedException {
        WebElement btnStatusP = driver.findElement(btnStatusProjectLocator);
        btnStatusP.click();
        Thread.sleep(1000);
        WebElement statusPro = driver.findElement(statusProgectLocator);
        statusPro.click();
    }

    public void lotteryNumber(String slotteryNum){
        WebElement lotteryNum = driver.findElement(lotteryNumberLocator);
        lotteryNum.sendKeys(slotteryNum);
    }

    public void toPrice(String price){
        WebElement tp = driver.findElement(toPriceLocator);
        tp.sendKeys(price);
    }

    public void btnSearchApartment(){
        WebElement search = driver.findElement(btnSearchApartmentLocator);
        search.click();
    }

    public void toDetails(){
        driver.findElement(btnToDetails).click();
    }
}
