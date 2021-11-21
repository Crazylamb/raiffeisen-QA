import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedCountries {
    private WebDriver driver;
    private WebDriverWait wait;
    private boolean loggedin;

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
        loggedin = true;
    }

    public void checkCountries(String link){
        if (!loggedin)
            Login();
        driver.get(link);
        List<WebElement> countries = driver.findElements(By.cssSelector("tr[class=\"row\""));
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> sortedNames = new ArrayList<String>();
        ArrayList<String> acr = new ArrayList<String>();
        for (WebElement country: countries){
            names.add(country.findElements(By.tagName("td")).get(4).getText());
            sortedNames.add(country.findElements(By.tagName("td")).get(4).getText());
            if (Integer.parseInt(country.findElements(By.tagName("td")).get(5).getText()) > 0){
                acr.add(country.findElements(By.tagName("td")).get(3).getText());
            }
        }
        Collections.sort(sortedNames);
        Assert.assertEquals(names, sortedNames);
        for (String str : acr){
            System.out.println(str);
            checkCountries("http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=" + str);
        }
    }

    public void checkZones(String link){
        if (!loggedin)
            Login();
        driver.get(link);
        List<WebElement> countries = driver.findElements(By.cssSelector("tr[class=\"row\""));
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> sortedNames = new ArrayList<String>();
        for (WebElement country: countries){
            names.add(country.findElements(By.tagName("td")).get(2).getText());
            sortedNames.add(country.findElements(By.tagName("td")).get(2).getText());
        }
        Collections.sort(sortedNames);
        Assert.assertEquals(names, sortedNames);
    }

    //Probably must be split in 2 different tests, but it was given as a single task
    @Test
    public void runAll(){
        checkCountries("http://localhost/litecart/admin/?app=countries&doc=countries");
        checkZones("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
