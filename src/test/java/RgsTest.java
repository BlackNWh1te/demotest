import Pages.RGS_Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class RgsTest implements iHandies {

    WebDriver driver;


    @Before
    public void setUp() {

        driver = setDriver("chrome", 10);

    }


    @Test
    public void rgsTest()  {

        driver.get(RGS_Page.URL);

        RGS_Page rgsPage = PageFactory.initElements(driver, RGS_Page.class);


        rgsPage.insuranceLink.click();
        rgsPage.dmsLink.click();

        rgsPage.dmsHeader1.isDisplayed();
        rgsPage.insuranceApplication.click();

        rgsPage.applicationHeader.isDisplayed();

        rgsPage.appFirstName.sendKeys(Person.getName());
        rgsPage.appLastName.sendKeys(Person.getLastName());
        rgsPage.appMiddleName.sendKeys(Person.getMiddleName());
        rgsPage.appPhone.sendKeys(Person.getPhone());
        rgsPage.appEMail.sendKeys("qwertyqwerty");
        rgsPage.appComment.sendKeys("Комментарий");
        rgsPage.appRegion.get(randomizer(rgsPage.appRegion.size())).click();
        rgsPage.appCheckbox.click();


        Assert.assertEquals(Person.getName(), rgsPage.appFirstName.getAttribute("value"));
        Assert.assertEquals(Person.getLastName(), rgsPage.appLastName.getAttribute("value"));
        Assert.assertEquals(Person.getMiddleName(), rgsPage.appMiddleName.getAttribute("value"));
        Assert.assertEquals("qwertyqwerty", rgsPage.appEMail.getAttribute("value"));
        rgsPage.appCheckbox.isEnabled();


        rgsPage.sendApp.click();

        rgsPage.emailInputError.isDisplayed();


    }

    @After
    public void tearDown() {

        driver.quit();

    }


}













