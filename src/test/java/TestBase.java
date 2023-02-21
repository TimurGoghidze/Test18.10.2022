import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    String url;
    Logger logger = LoggerFactory.getLogger(TestBase.class);//иниц переменную Logger и указываем параметром класс

    protected static WebElement findByXpath(SignInPageTest signInPageTest, String xpathExpression) {
        return signInPageTest.driver.findElement(By.xpath(xpathExpression));
    }

    protected static WebElement findByXPath(SignInPageTest signInPageTest, String xpathExpression) {
        return signInPageTest.driver.findElement(By.xpath(xpathExpression));
    }

    @BeforeTest
    public void setUp() {
        String path = System.getenv("cdr");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterSuite(enabled = true)
    public void cleanUp() {
        driver.quit();
    }

//helper method
    public void signIn() { //refactoring correct behavior
        WebElement emailField1 = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField1.click();
        emailField1.clear();
        emailField1.sendKeys("testqa36a@gmail.com");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("Qwer1234");

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();
    }
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
