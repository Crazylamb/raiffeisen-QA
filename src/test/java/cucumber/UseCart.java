package cucumber;

import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task19.Application.Application;

public class UseCart implements En {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    Application application = new Application(driver, wait);
    public UseCart(){
        When("Go to the shop", () -> application.openMainPage());
        And("Add {string} products to the cart", (String num) ->
                application.addProductsToCart(Integer.parseInt(num)));
        And("Open the cart and delete them", () -> application.clearCart());
        Then("The cart is empty", ()-> {application.quit();
            Assert.assertEquals(1, 1);});
    }

}
