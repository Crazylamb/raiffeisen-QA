import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LogsChecker {
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
    public void login(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //wait.until(titleIs("Title"));
    }

    @Test
    public void checkLogs(){
        login();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("form[name=catalog_form] tr.row"), 3));
        List<WebElement> els = driver.findElements(By.cssSelector("form[name=catalog_form] tr.row"));
        els.get(2).findElement(By.cssSelector("td:nth-child(3) a")).click();
        els = driver.findElements(By.cssSelector("form[name=catalog_form] tr.row"));
        for (int i = 3; i < els.size(); i++) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("form[name=catalog_form] tr.row"), 3))
                    .get(i)
                    .findElement(By.cssSelector("td:nth-child(3) a"))
                    .click();
            Assert.assertEquals(driver.manage().logs().get("browser").getAll().size(), 0);
            driver.navigate().back();
        }
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
