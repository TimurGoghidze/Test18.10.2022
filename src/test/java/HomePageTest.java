import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    @BeforeSuite
    public void setUpInternal() {
        super.url = "https://cloudrein.com/newapp#/backups";
    }

    @BeforeMethod
    public void signInCheck() {

        sleep(5000);
        try {
            WebElement logOut = driver.findElement(By.xpath("//span[@xpath='1']"));
        } catch (NoSuchElementException exception) {
            //we are not logged in, and we need to sign in
            signIn();
        }
    }

    private void signIn() {
    }


    @Test
    public void homePageExistenceTest() {
        sleep(3000);
        WebElement homePage = driver.findElement(By.xpath("//h3[@class='ant-typography StyledTypography__StyledTitle-sc-d1hytu-0 kAQgff StyledTypography__SubTitle-sc-d1hytu-3 StyledPageTitle__PageTitle-sc-18q9nsb-0 kkxYVF bHsrDp']"));
        Assert.assertEquals(homePage.getText(), "Homepage");


    }


}
