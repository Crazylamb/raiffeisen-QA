package task19.Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task19.PageObjects.CartPage;
import task19.PageObjects.MainPage;
import task19.PageObjects.ProductPage;

public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    private CartPage cartPage;
    private MainPage mainPage;
    private ProductPage productPage;

    public Application(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        cartPage = new CartPage(driver, wait);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver, wait);
    }

    public void openMainPage(){
        mainPage.open();
    }

    public void addProductsToCart(int num){
        for (int i = 0; i < num; i++) {
            mainPage.chooseFirstDuck();
            productPage.addToCart(i);
            openMainPage();
        }
    }

    public void clearCart(){
        cartPage.open(mainPage.cart);
        cartPage.deleteAllProducts();
    }

    public void quit(){
        this.wait = null;
        this.driver.quit();
        this.driver = null;
    }
}
