import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        WebElement name = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[2]/td[1]/input"));
        name.sendKeys("Name");
        WebElement surname = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[2]/td[2]/input"));
        surname.sendKeys("Surname");
        WebElement address = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[3]/td[1]/input"));
        address.sendKeys("New Street");
        WebElement city = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[4]/td[2]/input"));
        city.sendKeys("New City");
        WebElement postcode = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[4]/td[1]/input"));
        Random random = new Random();
        int post = random.nextInt(10000) + 10000;
        postcode.sendKeys(Integer.toString(post));
        WebElement country =  driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[5]/td[1]/span[2]/span[1]/span"));
        actions.moveToElement(country).click().perform();
        WebElement countryInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        countryInput.sendKeys("United States" + Keys.ENTER);
        // Generating emails with hash function and local time to avoid repetitions
        email = Integer.toString(LocalDateTime.now().hashCode()) + "@gmail.com";
        WebElement mail = driver.findElement( By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[6]/td[1]/input"));
        mail.sendKeys(email);
        WebElement phoneNumber = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[6]/td[2]/input"));
        phoneNumber.sendKeys("+12345678990");
        WebElement passwordFirst = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[8]/td[1]/input"));
        passwordFirst.sendKeys(password);
        WebElement passwordConfirm = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[8]/td[2]/input"));
        passwordConfirm.sendKeys(password);
        WebElement register = driver.findElement(By.xpath("//*[@id=\"create-account\"]/div/form/table/tbody/tr[9]/td/button"));
        register.click();

        //Logout and login
        String logout = driver.findElement(By.xpath("//*[@id=\"box-account\"]/div/ul/li[4]/a")).getAttribute("href");
        driver.get(logout);
        WebElement loginEmail = driver.findElement(By.xpath("//*[@id=\"box-account-login\"]/div/form/table/tbody/tr[1]/td/input"));
        loginEmail.sendKeys(email);
        WebElement loginPassword = driver.findElement(By.xpath("//*[@id=\"box-account-login\"]/div/form/table/tbody/tr[2]/td/input"));
        loginPassword.sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"box-account-login\"]/div/form/table/tbody/tr[4]/td/span/button[1]")).click();

        //Final logout
        logout = driver.findElement(By.xpath("//*[@id=\"box-account\"]/div/ul/li[4]/a")).getAttribute("href");
        driver.get(logout);
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
