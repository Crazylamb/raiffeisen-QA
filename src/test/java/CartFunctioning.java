import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartFunctioning {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    private void shopping(String times){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1)")).click();
        wait.until(urlContains("rubber-ducks"));
        //Если выбрана желтая уточка
        if (driver.findElements(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[5]/form/table/tbody/tr[1]/td/select"))
                .size() > 0){
            WebElement size = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[5]/form/table/tbody/tr[1]/td/select"));
            Select select = new Select(size);
            select.selectByIndex(1);
        }
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        WebElement cart = driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]/span[1]"));
        wait.until(textToBePresentInElement(cart, times));
    }

    @Test
    public void addToCart(){
        for (int i = 1; i < 4; i++) {
            shopping(Integer.toString(i));
        }
        driver.findElement(By.xpath("//*[@id=\"cart\"]/a[3]")).click();
        List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"order_confirmation-wrapper\"]/table/tbody/tr"));
        //Переменные для цикла и рассчета элементов в таблице
        int totalSize = table.size();
        int finalTotalSize = totalSize;
        for (int i = 1; i < totalSize - 4; i++) {
            if (i != totalSize - 5)
                driver.findElement(By.xpath("//*[@id=\"box-checkout-cart\"]/ul/li[1]/a/img")).click();
            wait.until((WebDriver driver) -> !driver.findElements(By.xpath("//*[@id=\"box-checkout-cart\"]/div/ul/li[1]/form/div/p[4]")).isEmpty());
            WebElement temp = driver.findElement(By.xpath("//*[@id=\"box-checkout-cart\"]/div/ul/li[1]/form/div"));
            List<WebElement> els = temp.findElements(By.tagName("button"));
            els.get(els.size() - 1).click();
            finalTotalSize -= 1;
            if (finalTotalSize - 5 != 0) {
                int Size1 = finalTotalSize;
                wait.until((WebDriver driver) -> driver.findElements(By.xpath("//*[@id=\"order_confirmation-wrapper\"]/table/tbody/tr"))
                        .size() == Size1);
            }
        }
        driver.findElement(By.xpath("//*[@id=\"site-menu\"]/ul/li[1]/a/i")).click();
        Assert.assertEquals("0", driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]/span[1]"))
                .getText());
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
