import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tools {


    public static WebDriver driver = new ChromeDriver();

    public static Map<WebElement, String> sentKeys = new HashMap<>();


    public static boolean scrollSelect(String xpath, String value) {
        if (!containsElement(xpath)) return false;

        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            Select select = new Select(element);
            select.selectByValue(value);
            return true;
        } catch (Exception e) {
            System.out.println("Exception found: " + e.getMessage());
            return false;
        }

    }

    public static boolean scrollSelect(String xpath) {

        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();

            Random rand = new Random();

            options.get(rand.nextInt(options.size())).click();
            return true;


        } catch (NoSuchElementException e) {
            System.out.println("No elements found following \"" + xpath + "\"");
            return false;
        }

    }

    public static void keySender(WebElement[] expectedElements, String... keys) {


        int i = -1;
        sentKeys.clear();
        for (WebElement currentElement : expectedElements) {
            try {
                currentElement.clear();
                currentElement.sendKeys(keys[++i]);
                sentKeys.put(currentElement, keys[i]);
            } catch (NullPointerException e) {
                System.out.println("Can't send keys to Null element " + "[" + i + "]");
            }
        }
    }

    public static WebElement[] elementLocator(String... xPaths) {
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


    public static boolean pageContainsText(String text, String xpath) {
        try {
            String sourceText = driver.findElement(By.xpath(xpath)).getText();

            System.out.println("Text obtained: " + sourceText);
            if (sourceText.contains(text)) return true;
            return false;

        } catch (NoSuchElementException e) {

            System.out.println("No match for \"" + xpath + "\"");
            return false;
        }

    }


    public static boolean containsElement(String xpath) {

        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;


        }


    }

    public static void keyVerificator(Map<WebElement, String> expectedMap) {

        for (WebElement currentElement : expectedMap.keySet()) {

            String s = expectedMap.get(currentElement);
            boolean match = currentElement.getAttribute("value").contains(s);

            System.out.println(currentElement + " " + match);

        }
    }

    public void checkVisibility(String... xpaths) throws Exception, ArrayIndexOutOfBoundsException {

        if (xpaths == null) {
            System.out.println("No arguments taken");
            throw new Exception();
        }
        int failures = 0;

        for (int i = 0; i < xpaths.length; i++) {
            List<WebElement> elements = driver.findElements(By.xpath(xpaths[i]));
            if (elements == null) {
                System.out.println("No elements found following \"" + xpaths[i] + "\"");
                throw new Exception();
            }
            Thread.sleep(1000);

            for (WebElement each : elements) {
                if (!each.isDisplayed()) {
                    System.out.println(each + "NOT VISIBLE");
                    failures++;
                }

            }

        }

        if (failures > 0) throw new Exception();

    }

}
