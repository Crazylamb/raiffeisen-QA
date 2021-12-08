import org.junit.After;
import org.junit.Assert;
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
    private String productName;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        productName = Integer.toString(LocalDateTime.now().hashCode());
    }

    @Test
    public void addProduct(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");
        driver.findElement(By.linkText("General")).click();
        WebElement name = driver.findElement(By.cssSelector("input[name=\"name[en]\""));
        name.sendKeys(productName);
        WebElement code = driver.findElement(By.cssSelector("input[name=\"code\""));
        code.sendKeys("42");
        driver.findElement(By.cssSelector("input[value=\"1-3\"")).click();
        WebElement picture = driver.findElement(By.cssSelector("input[name=\"new_images[]\""));
        picture.sendKeys(System.getProperty("user.dir") + "/src/test/java/duck.png");
        WebElement dateFrom = driver.findElement(By.cssSelector("input[name=\"date_valid_from\""));
        dateFrom.sendKeys("26.11.2021");
        WebElement dateTo = driver.findElement(By.cssSelector("input[name=\"date_valid_to\""));
        dateTo.sendKeys("26.11.2022");
        driver.findElement(By.linkText("Information")).click();

        //information
        WebElement shortDesc = driver.findElement(By.cssSelector("input[name=\"short_description[en]\""));
        shortDesc.sendKeys("A special duck");
        WebElement longDesc = driver.findElement(By.cssSelector("div[class=\"trumbowyg-editor\""));
        longDesc.sendKeys("It's a special duck for you!");
        WebElement head = driver.findElement(By.cssSelector("input[name=\"head_title[en]\""));
        head.sendKeys("SuperDuck");
        WebElement metaDesc = driver.findElement(By.cssSelector("input[name=\"meta_description[en]\""));
        metaDesc.sendKeys("ULTRACOOL DUCK");
        driver.findElement(By.linkText("Prices")).click();

        //Price
        WebElement price = driver.findElement(By.cssSelector("input[name=\"prices[USD]\""));
        price.sendKeys("10");
        WebElement currency = driver.findElement(By.cssSelector("select[name=\"purchase_price_currency_code\""));
        Select select = new Select(currency);
        select.selectByIndex(1);

        WebElement saveButton = driver.findElement(By.cssSelector("button[name=\"save\""));
        saveButton.click();

        // JUNIT 4 не имеет assertNotThrows, в идеале здесь нужно использовать его
        // Но он доступен только в JUNIT 5
        driver.findElement(By.linkText(productName));
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
