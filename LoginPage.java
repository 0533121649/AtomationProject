package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public WebDriver driver;

    //constractor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //dataMembers
    By loginNumberLocator = By.id("loginNumber");
    By loginPasswordLocator = By.id("loginPassword");
    By loginBtnLocator = By.xpath("//*[@id=\"index-page\"]/body/div/div[1]/div[1]/div[3]/form/div[2]/div/div[3]/div[2]");

    //methods
    public void insertLoginNumber(String loginNumber){
        driver.findElement(loginNumberLocator).sendKeys(loginNumber);
    }

    public void insertLoginPassword(String loginPassword){
        driver.findElement(loginPasswordLocator).sendKeys(loginPassword);
    }
    public void clickLoginBtn(){
        driver.findElement(loginBtnLocator).click();
    }
}
