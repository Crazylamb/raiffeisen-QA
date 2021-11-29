import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class OtherWindows {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        ExpectedCondition<String> expectedCondition = new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
        return expectedCondition;
    }


        @Test
    public void openWindows(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.linkText("Angola")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("i[class=\"fa fa-external-link\"]"));
        String ourWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            // Можно просто усыплять поток на сколько-то секунд, но так выглядит немного разумнее
            wait.until((WebDriver driver) -> (driver.getTitle().contains("Wikipedia") ||
                    driver.getTitle().contains("Informatica")));
            driver.close();
            driver.switchTo().window(ourWindow);
        }
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
