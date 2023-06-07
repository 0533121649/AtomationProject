package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {
    public WebDriver driver;

    //constractor
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    //dataMembers

    By hrefToContantLocator = By.xpath("//*[@id=\"navbar\"]/ul/li[6]/a");

    By contantBylinkTextLocator = By.linkText("יצירת קשר");

    //methods

    public void hrefToContant(){
        driver.findElement(hrefToContantLocator).click();
    }

}
