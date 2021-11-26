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
            List<WebElement> data = country.findElements(By.tagName("td"));
            String countryName = data.get(4).getText();
            names.add(countryName);
            sortedNames.add(countryName);
            if (Integer.parseInt(data.get(5).getText()) > 0){
                acr.add(data.get(3).getText());
            }
        }
        Collections.sort(sortedNames);
        Assert.assertEquals(names, sortedNames);
        for (String str : acr){
            driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=" + str);
            List<WebElement> els = driver.findElements(By.xpath("//*[@id=\"table-zones\"]/tbody/tr"));
            names = new ArrayList<String>();
            sortedNames = new ArrayList<String>();
            for (int i = 1; i < els.size() - 1; i++) {
                List<WebElement> data = els.get(i).findElements(By.tagName("td"));
                String name = data.get(2).getText();
                names.add(name);
                sortedNames.add(name);
            }
            Collections.sort(sortedNames);
            Assert.assertEquals(names, sortedNames);
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
            List<WebElement> data = country.findElements(By.tagName("td"));
            String name = data.get(2).getText();
            names.add(name);
            sortedNames.add(name);
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
