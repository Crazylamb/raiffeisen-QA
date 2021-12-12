package task19.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    private WebDriverWait wait;
    private int productsNum;

    @FindBy(css = "button[name=remove_cart_item]")
    public List<WebElement> removeButtons;

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.wait = wait;
        PageFactory.initElements(driver, this);
        productsNum = 0;
    }

    public void open(WebElement cart){
        cart.click();
    }

    public void deleteAllProducts(){
        productsNum = removeButtons.size();
        while (productsNum != 0){
            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0))).click();
            productsNum--;
            removeButtons.remove(0);
        }
    }

}
