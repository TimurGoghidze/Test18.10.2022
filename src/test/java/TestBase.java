import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    String url,browser;
    Logger logger = LoggerFactory.getLogger(TestBase.class);//инициализирум переменную Logger и указываем параметром класс

    protected static WebElement findByXpath(SignInPageTest signInPageTest, String xpathExpression) {
        return signInPageTest.driver.findElement(By.xpath(xpathExpression));
    }

    protected static WebElement findByXPath(SignInPageTest signInPageTest, String xpathExpression) {
        return signInPageTest.driver.findElement(By.xpath(xpathExpression));
    }

    @BeforeTest
    public void setUp() {
        String path;
        if (browser.equals(Browser.CHROME.browserName())){
            path = System.getenv("chromeDriver");
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        }else if (browser.equals(Browser.FIREFOX.browserName())){
            path = System.getenv("firefoxDriver");
            System.setProperty("webdriver.gecko.driver", path);
            driver = new ChromeDriver();
        }else {
            logger.error("No supported browser specified. Supported browsers: chrome, firefox");
        }

        driver.get(url); // проходит на ссылку какая укзана
        driver.manage().window().maximize(); // увеличение окна
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // 5 сек пауза
    }

    @AfterSuite(enabled = true)
    public void cleanUp() {
        driver.quit();
    }

//helper method
    public void signIn(String email, String pass) { //refactoring correct behavior
        WebElement emailField1 = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField1.click();
        emailField1.clear();
        emailField1.sendKeys(email);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys(pass);

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
