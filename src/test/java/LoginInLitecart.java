import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginInLitecart {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Login(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //wait.until(titleIs("Title"));
        System.out.println("Success");
    }

    @Test
    public void SideMenu(){
        Login();
        List<WebElement> args = driver.findElements(By.id("app-"));
        for (int i = 0; i < args.size(); i++) {
            args.get(i).click();
            List<WebElement> subArgs = driver.findElements(By.cssSelector("li[id*=\"doc-\"]"));
            //System.out.println(subArgs.size());
            for (int j = 0; j < subArgs.size(); j++) {
                subArgs.get(j).click();
                // Had to do this otherwise it won't find needed elements in DOM
                subArgs = driver.findElements(By.cssSelector("li[id*=\"doc-\"]"));
                //System.out.println("clicked");
            }
            args = driver.findElements(By.id("app-"));
        }
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
