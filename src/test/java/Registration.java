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

public class Registration {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private String email;
    private String password;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        password = "12345qwert";
    }

    @Test
    public void register(){
        driver.get("http://localhost/litecart/en/create_account");

        //registration
        WebElement name = driver.findElement(By.cssSelector("input[name=\"firstname\"]"));
        name.sendKeys("Name");
        WebElement surname = driver.findElement(By.cssSelector("input[name=\"lastname\"]"));
        surname.sendKeys("Surname");
        WebElement address = driver.findElement(By.cssSelector("input[name=\"address1\"]"));
        address.sendKeys("New Street");
        WebElement city = driver.findElement(By.cssSelector("input[name=\"city\"]"));
        city.sendKeys("New City");
        WebElement postcode = driver.findElement(By.cssSelector("input[name=\"postcode\"]"));
        Random random = new Random();
        int post = random.nextInt(10000) + 10000;
        postcode.sendKeys(Integer.toString(post));
        WebElement country =  driver.findElement(By.cssSelector("span[class=\"select2-selection__rendered\"]"));
        actions.moveToElement(country).click().perform();
        WebElement countryInput = driver.findElement(By.cssSelector("input[class=\"select2-search__field\"]"));
        countryInput.sendKeys("United States" + Keys.ENTER);
        WebElement state = driver.findElement(By.cssSelector("select[name=\"zone_code\"]"));
        Select select = new Select(state);
        select.selectByVisibleText("California");
        // Generating emails with hash function and local time to avoid repetitions
        email = Integer.toString(LocalDateTime.now().hashCode()) + "@gmail.com";
        WebElement mail = driver.findElement(By.cssSelector("input[name=\"email\"]"));
        mail.sendKeys(email);
        WebElement phoneNumber = driver.findElement(By.cssSelector("input[name=\"phone\"]"));
        phoneNumber.sendKeys("+12345678990");
        WebElement passwordFirst = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        passwordFirst.sendKeys(password);
        WebElement passwordConfirm = driver.findElement(By.cssSelector("input[name=\"confirmed_password\"]"));
        passwordConfirm.sendKeys(password);
        WebElement register = driver.findElement(By.cssSelector("button[name=\"create_account\"]"));
        register.click();

        //Logout and login
        driver.findElement(By.linkText("Logout")).click();
        WebElement loginEmail = driver.findElement(By.cssSelector("input[name=\"email\"]"));
        loginEmail.sendKeys(email);
        WebElement loginPassword = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        loginPassword.sendKeys(password);
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();

        //Final logout
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
