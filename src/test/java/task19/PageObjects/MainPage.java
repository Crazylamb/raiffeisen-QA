package task19.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    @FindBy(css = "#box-most-popular > div > ul > li:nth-child(1)")
    public WebElement firstElement;

    @FindBy(css = "div#cart a.link")
    public WebElement cart;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    public void chooseFirstDuck(){
        firstElement.click();
    }
}
