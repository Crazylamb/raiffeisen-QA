package task19.Test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task19.Application.Application;

public class CartFunctioningApplication {
    private Application application;
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        application = new Application(driver, wait);
    }

    @Test
    public void testApp(){
        application.openMainPage();
        application.addProductsToCart(5);
        application.clearCart();
        application.quit();
    }
}
