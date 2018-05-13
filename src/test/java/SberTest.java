import Pages.SberPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SberTest implements iHandies {


    WebDriver driver;


    @Before
    public void setUp() {

        driver = setDriver("chrome", 10);

    }


    @Test
    public void sberTest() {

        driver.get(SberPage.URL);

        SberPage sberPage = PageFactory.initElements(driver, SberPage.class);

        sberPage.regionList.click();

        sberPage.regionInputBar.sendKeys("Нижегородская область");

        sberPage.suggestedRegion.click();

        Assert.assertEquals("Нижегородская область", sberPage.regionList.getText());

        scrollToElement(driver, sberPage.footerBar);

        checkVisibility(sberPage.networkIcons);


    }


    @After
    public void tearDown() {

        driver.quit();

    }


}
