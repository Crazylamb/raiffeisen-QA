package task19.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {
    private WebDriverWait wait;

    @FindBy(css = "button[name=add_cart_product]")
    WebElement submit;

    @FindBy(css = "form[name=buy_now_form] select[required=required]")
    public List<WebElement> requiredFields;

    @FindBy(css = "#cart > a.content > span.quantity")
    WebElement cart;

    public ProductPage(WebDriver driver, WebDriverWait wait){
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    private void fillRequired(){
        if (requiredFields.size() > 0){
            Select select = new Select(requiredFields.get(0));
            select.selectByIndex(1);
        }
    }

    public void addToCart(int num){
        fillRequired();
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        wait.until(ExpectedConditions.attributeToBe(cart, "textContent", String.valueOf(num+1)));
    }

}
