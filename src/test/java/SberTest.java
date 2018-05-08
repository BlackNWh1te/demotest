import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SberTest {


    Map<WebElement, String> inputMap = new HashMap<>();
    WebDriver driver;
    String url;

    @Before
    public void setUp() {

        driver = new ChromeDriver();

        url = "http://www.sberbank.ru/ru/person";


    }

    @Test
    public void sberTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(url+ "/");

        elementLocator("//span[@class = 'region-list__name']")[0].click();
        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Введите название региона']")));

        element.sendKeys("Нижегородская область");
        /*keySender(elementLocator("//input[@placeholder='Введите название региона']"),
                "Нижегородская область");*/
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
                ("//span[@class='region-search-box__option']")));

        element = driver.findElement(By.xpath("//span[@class='region-search-box__option']"));
        element.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
                ("//span[@class ='region-list__name' and text() = 'Нижегородская область']")));

        Assert.assertEquals("Нижегородская область",
                elementLocator("//span[@class ='region-list__name' and text() = 'Нижегородская область']")[0].getText());

        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//div[@class = 'footer-info']")));

        checkVisibility("//span[contains(@class, 'social__icon')]");

        System.out.println("Successfully tested");
        driver.close();











    }
    public  WebElement[] elementLocator(String... xPaths) {
        WebElement[] elements = new WebElement[xPaths.length];
        int i = 0;


        for (; i < xPaths.length; i++) {
            try {
                elements[i] = driver.findElement(By.xpath(xPaths[i]));
            } catch (NoSuchElementException e) {
                System.out.println("No elements found following \"" + xPaths[i] + "\"");
            }
        }

        return elements;

    }

    public  void keySender(WebElement[] expectedElements, String... keys) {


        int i = -1;
        inputMap.clear();
        for (WebElement currentElement : expectedElements) {
            try {
                currentElement.clear();
                currentElement.sendKeys(keys[++i]);
                inputMap.put(currentElement, keys[i]);
            } catch (NullPointerException e) {
                System.out.println("Can't send keys to Null element " + "[" + i + "]");
            }
        }
    }
    public void checkVisibility(String... xpaths)  {

        if (xpaths.length == 0) {
            System.out.println("No arguments taken");
            throw new RuntimeException();
        }
        int failures = 0;

        for (int i = 0; i < xpaths.length; i++) {
            List<WebElement> elements = driver.findElements(By.xpath(xpaths[i]));
            if (elements == null) {
                System.out.println("No elements found following \"" + xpaths[i] + "\"");
                throw new RuntimeException();
            }


            for (WebElement each : elements) {
                if (!each.isDisplayed()) {
                    System.out.println(each + "NOT VISIBLE");
                    failures++;
                }

            }

        }

        if (failures > 0) throw new RuntimeException();

    }



}
