package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateDetailsPage {
    public WebDriver driver;

    //constractor
    public UpdateDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    //dataMembers
    By checkBoxLocator = By.xpath("//*[@id=\"divView\"]/form/div/div[3]/div/div[2]/div/div[2]/div[2]/label");
    By btnUpdate = By.xpath("//*[@id=\"divView\"]/form/div/div[3]/div/div[2]/div/div[3]/div[2]/button");
    //methods

    public void checkBox() throws InterruptedException {
        WebElement checkBoxElement = driver.findElement(checkBoxLocator);
        boolean checkBoxStatus = checkBoxElement.isSelected();
        System.out.println(checkBoxStatus);
        Thread.sleep(2000);
        if (checkBoxStatus==true){
            checkBoxElement.click();
            Thread.sleep(2000);
        }
        checkBoxElement.click();
    }

    public void clickUpdate(){
        driver.findElement(btnUpdate).click();
    }
}
