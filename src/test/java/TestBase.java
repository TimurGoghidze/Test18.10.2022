import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    String url;

    @BeforeMethod
    public void setUp() {

        String path = System.getenv("cdr");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(enabled = true)

    public void cleanUp() {
        driver.quit();
    }
}
