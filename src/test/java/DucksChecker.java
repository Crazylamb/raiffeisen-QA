import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DucksChecker {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void EveryDuckHasASticker(){
        driver.get("http://localhost/litecart/");
        int total = 0;
        List<WebElement> ducks = driver.findElements(By.cssSelector(".product"));
        for (WebElement el:ducks){
            List<WebElement> stickers = el.findElements(By.cssSelector("div.sticker"));
            Assert.assertTrue(stickers.size() == 1);
            total += stickers.size();
        }
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
