import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Styles {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Before(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkProduct(){
        driver.get("http://localhost/litecart/en/");
        WebElement duck = driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li"));
        String link = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).getAttribute("href");
        String name = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[2]")).getText();
        String regularPrice = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s")).getText();
        String discountPrice = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong")).getText();
        String regularColor = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s"))
                .getCssValue("color");
        String regularDecoration = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s"))
                .getCssValue("text-decoration");
        String regularSize = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s"))
                .getCssValue("font-size");
        String discountWeight = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong"))
                .getCssValue("font-weight");
        String discountSize = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong"))
                .getCssValue("font-size");
        String discountColor = duck.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong"))
                .getCssValue("color");
        driver.get(link);
        String itemName = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[1]/h1")).getText();
        //Names
        Assert.assertEquals(name, itemName);
        String itemRegularPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s")).getText();
        //Prices
        Assert.assertEquals(regularPrice, itemRegularPrice);
        String itemDiscountPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong")).getText();
        //Discounts
        Assert.assertEquals(discountPrice, itemDiscountPrice);
        String itemRegularColor = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s"))
                .getCssValue("color");
        String itemRegularDecoration = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s"))
                .getCssValue("text-decoration");
        //Colors and decorations
        regularColor = regularColor.replace(" ", "");
        Assert.assertTrue(regularColor.replace('(', ',').split(",")[1].equals(
                regularColor.replace('(', ',').split(",")[2]) &&
                regularColor.replace('(', ',').split(",")[2].equals(
                        regularColor.replace('(', ',').split(",")[3]));

        itemRegularColor = itemRegularColor.replace(" ", "");
        Assert.assertTrue(itemRegularColor.replace('(', ',').split(",")[1].equals(
                itemRegularColor.replace('(', ',').split(",")[2]) &&
                itemRegularColor.replace('(', ',').split(",")[2].equals(
                        itemRegularColor.replace('(', ',').split(",")[3]));

        Assert.assertTrue(regularDecoration.contains("line-through"));
        Assert.assertTrue(itemRegularDecoration.contains("line-through"));
        String itemRegularSize = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s"))
                .getCssValue("font-size");
        //Sizes
        Double regSize = Double.parseDouble(regularSize.substring(0,regularSize.length() - 2));
        Double disSize = Double.parseDouble(discountSize.substring(0,discountSize.length() - 2));
        Assert.assertTrue(disSize > regSize);
        String itemDiscountWeight = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong"))
                .getCssValue("font-weight");
        String itemDiscountColor = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong"))
                .getCssValue("color");
        //Bold
        Assert.assertTrue(Integer.parseInt(discountWeight) > 500);
        Assert.assertTrue(Integer.parseInt(itemDiscountWeight) > 500);
        String itemDiscountSize = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong"))
                .getCssValue("font-size");
        Double itemRegSize = Double.parseDouble(itemRegularSize.substring(0,itemRegularSize.length() - 2));
        Double itemDisSize = Double.parseDouble(itemDiscountSize.substring(0,itemDiscountSize.length() - 2));
        Assert.assertTrue(itemDisSize > itemRegSize);
        //Check that some prices are red
        Assert.assertTrue(itemDiscountColor.endsWith("0, 0, 1)"));
        Assert.assertTrue(discountColor.endsWith("0, 0, 1)"));
    }

    @After
    public void After(){
        driver.quit();
        driver = null;
    }
}
