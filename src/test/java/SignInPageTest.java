import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

// в файле pom.xml в <dependencies> на сайте https://mvnrepository.com/search?q=testng <dependencies></dependencies>
// установил TestNG последний и Selenium Java и вкладка Maven

public class SignInPageTest extends TestBase{                              // класс

    @BeforeMethod
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/sign-in";
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
    public void wrongCredentialTest() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();//click
        emailField.clear();//clear
        emailField.sendKeys("testQA36a@gmail.com");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("Qwer123"); //пишем ошибочный пароль

//        Actions a=new Actions(driver);
//        a.moveToElement(passField).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        for (int i=0; i<0;i++) { // если хотим более кликать
            signInButton.click();
            Thread.sleep(100);
        }

        WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[@class='StyledSignIn__Error-sc-t0jmvd-4 fTcqJJ']"));
        Assert.assertEquals(invalidEmailOrPass.getText(),"Too many login failures, this account will be locked for 10 minutes.");

        //WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[text()='Invalid Email or password.']"));
        //Assert.assertEquals(invalidEmailOrPass.getText(),"Invalid Email or password.");
        // Assert.assertFalse(signInButton.isEnabled()); надо разобраться
    }

}
