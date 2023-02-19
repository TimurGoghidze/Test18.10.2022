import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

// в файле pom.xml в <dependencies> на сайте https://mvnrepository.com/search?q=testng <dependencies></dependencies>
// установил TestNG последний и Selenium Java и вкладка Maven

public class FistSeleniumTest {                              // класс

    WebDriver driver;

    //Before test - здесь подготовительная работа
    @BeforeMethod                                            //аннотация
    public void setUp() {
        String path= System.getenv("cdr");
        System.setProperty("webdriver.chrome.driver", path);  // идет по этому пути чтобы связать дравером path in Enviroment variable прописан
        driver = new ChromeDriver();                         // скачанный хром драйвер, использование
        driver.get("https://cloudrein.com/newapp#/sign-in"); // сайт который будем использовать
        driver.manage().window().maximize();                 // maximize window
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // режим ожидания
    }

    //Test
    @Test
    public void myFirstTest() throws Exception {                               // следующий метод
        //WebElement tag = driver.findElement(By.tagName("h1")); //ищем по тегу
        //WebElement emailInputField = driver.findElement(By.id("email")); //ищем по cssSelector
        //WebElement passInputField = driver.findElement(By.cssSelector("[id=password]"));
        WebElement buttonPushEnter = driver.findElement(By.cssSelector("[type=submit]"));

        Assert.assertEquals(buttonPushEnter.getText(), "Sign In");
    }

    @Test
    public void mySecondTest() {
        WebElement tag = driver.findElement(By.tagName("h1")); //ищем по тегу
        Assert.assertTrue(tag.getText().contains("Sign In to your Account")); //contains это содержимое
    }

    @Test
    public void findClass() {
//        WebElement cl = driver.findElement(By.className("asdasd"));
//        WebElement id = driver.findElement(By.id("rec434702325"));
//        WebElement id2 = driver.findElement(By.cssSelector("#rec434702325"));
        WebElement linkText = driver.findElement(By.linkText("Terms of Service"));
        WebElement partial = driver.findElement(By.partialLinkText("rms of Ser"));

        WebElement or = driver.findElement(By.xpath("//p[@class='StyledLineSeparator__TextSeparator-sc-tvmx61-2 jdsPEF']"));
        Assert.assertEquals(or.getText(), "or");
    }
    @Test // picture Cloud`
    public void CloudTest() {
        WebElement cloud = driver.findElement(By.xpath("//img[@class='StyledHeader__LogoIcon-sc-1y31m8t-3 jfcCnf']"));
        Assert.assertTrue(cloud.getAttribute("src").equals("https://cloudrein.com/images/app/LOGO_280X51.svg"));
    }


    @Test // button
    public void buttonSignIn() {
        WebElement buttonSignIn = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertEquals(buttonSignIn.getText(),"Sign In");
    }

    @Test
    public void wrongCredentialTest() {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("failmail@mail.ru");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("failpassword!"); //пишем ошибочный пароль

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        //Thread.sleep(5000); for sleeps 5sec

        WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[text()='Invalid Email or password.']"));
        Assert.assertEquals(invalidEmailOrPass.getText(),"Invalid Email or password.");

    }




    // After test - чистим за собой ресурсы
    @AfterMethod
    public void cleanUp() {
        driver.quit();                                      //Syntax's for close chrome
        // driver.close(); // close tab

    }
}
