import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BrowserOpener {
    public void OpenDrowser(){
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
        driver.close();
        driver.quit();
        driver = null;
    }
}
