import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.util.Random;

public class AddProduct {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    @Test
    public void addProduct(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[1]/td/label[1]/input")).click();
        WebElement name = driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[2]/td/span/input"));
        name.sendKeys("Big Duck");
        WebElement code = driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[3]/td/input"));
        code.sendKeys("42");
        driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[7]/td/div/table/tbody/tr[4]/td[1]/input")).click();
        WebElement picture = driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[9]/td/table/tbody/tr[1]/td/input"));
        picture.sendKeys(System.getProperty("user.dir") + "/src/test/java/duck.png");
        WebElement dateFrom = driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[10]/td/input"));
        dateFrom.sendKeys("26.11.2021");
        WebElement dateTo = driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[11]/td/input"));
        dateTo.sendKeys("26.11.2022");
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]")).click();

        //information
        WebElement shortDesc = driver.findElement(By.xpath("//*[@id=\"tab-information\"]/table/tbody/tr[4]/td/span/input"));
        shortDesc.sendKeys("A special duck");
        WebElement longDesc = driver.findElement(By.xpath("//*[@id=\"tab-information\"]/table/tbody/tr[5]/td/span/div/div[2]"));
        longDesc.sendKeys("It's a special duck for you!");
        WebElement head = driver.findElement(By.xpath("//*[@id=\"tab-information\"]/table/tbody/tr[6]/td/span/input"));
        head.sendKeys("SuperDuck");
        WebElement metaDesc = driver.findElement(By.xpath("//*[@id=\"tab-information\"]/table/tbody/tr[7]/td/span/input"));
        metaDesc.sendKeys("ULTRACOOL DUCK");
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[4]")).click();

        //Price
        WebElement price = driver.findElement(By.xpath("//*[@id=\"tab-prices\"]/table[3]/tbody/tr[2]/td[1]/span/input"));
        price.sendKeys("10");
        WebElement currency = driver.findElement(By.xpath("//*[@id=\"tab-prices\"]/table[1]/tbody/tr/td/select"));
        Select select = new Select(currency);
        select.selectByIndex(1);

        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"content\"]/form/p/span/button[1]"));
        saveButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
