import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class RgsTest {





    @Test
    public void rgsTest() throws InterruptedException, NoSuchElementException {
        String url = "https://www.rgs.ru/";

        Tools.driver.get(url);
        Thread.sleep(2000);
        Tools.elementLocator("//a[@href = '#' and @data-toggle = 'dropdown']")[0].click();
        Thread.sleep(2000);
        Tools.elementLocator("//a[contains(text(),'ДМС')]")[0].click();
        Thread.sleep(2000);

        if (!Tools.containsElement("//h1[contains(text(), 'добровольное медицинское страхование')]")) {
            System.out.println("No header found");
            Tools.driver.close();
            return;

        }
        Thread.sleep(2000);
        Tools.elementLocator(
                "//a[@data-form='insuranceApplication' and contains(@class, 'hidden')]")[0].click();
        Thread.sleep(2000);

        if ((!Tools.pageContainsText
                ("Заявка на добровольное медицинское страхование",
                        "//b[@data-bind='text: options.title']"))) {
            System.out.println("Page - text match not found");
            Tools.driver.close();
            return;
        }
        Tools.keySender(Tools.elementLocator(
                "//input[contains(@name, 'LastName')]",
                "//input[contains(@name,'FirstName')]",
                "//input[contains(@name,'MiddleName')]",
                "//input[contains(@data-bind, 'Phone')]",
                "//input[contains(@name, 'Email')]",
                "//textarea[@name = 'Comment']"),
                "Петров", "Сергей", "Павлович", "9153842322", "qwertyqwerty", "мой коментарий");
        Tools.scrollSelect("//select[@name = 'Region']");
        Thread.sleep(1000);
        Tools.elementLocator("//input[contains(@*,'checkbox')]")[0].click();


        /**некорректно работает с номером телефона.
         т.к. для отображении  на веб странице используется маска.
         не смог понять, как обойти
         */
        Tools.keyVerificator(Tools.sentKeys);
        Tools.elementLocator("//button[contains(text(),'Отправить' )]")[0].click();
        if (Tools.elementLocator("//span[contains(text(),'Введите адрес электронной почты')]")[0] == null){

            return;
        }






    }


}



