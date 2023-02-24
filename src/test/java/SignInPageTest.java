
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import java.sql.Array;

import static java.lang.Thread.sleep;

// в файле pom.xml в <dependencies> на сайте https://mvnrepository.com/search?q=testng <dependencies></dependencies>
// установил TestNG последний и Selenium Java и вкладка Maven

public class SignInPageTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> newUser() {
        List<Object[]> list = new ArrayList<>(); // инициализируем список пока пустой но ниже добавляет list.add
        list.add(new Object[]{"testQA36@gmail.com", "Qwer1234"}); // используем массив т.к. данные состоят из нескольких атрибутов
        list.add(new Object[]{"testQA36@gmail.com", "Qwer1234"});
        list.add(new Object[]{"testQA36@gmail.com", "Qwer12345"});
        return list.iterator();//обязательно возвращаем итератор
    }

  /*  @DataProvider
    public Iterator<Object[]> newUserWithCsv() {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/123.csv"))) ;
        String line = reader.readLine();
        while (line != null) {
            String[] data = line.split(",");
            MyCredentials myCredential = new MyCredentials(data[0], data[1]);
            list.add(new Object[]{new MyCredentials(data[0], data[1])});
            line = reader.readLine();
        }
    }catch(
    IOException e)
    {
        logger.error(e.getMessage(), e);
    }
return list.iterator();
}*/

    @BeforeSuite // если будет проблема сменить на @BeforeMethod
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/sign-in";
    }

    //Test
    @Test
    public void myFirstTest() throws Exception {                               // следующий метод

        //WebElement emailInputField = driver.findElement(By.id("email")); //ищем по cssSelector
        //WebElement passInputField = driver.findElement(By.cssSelector("[id=password]"));
        WebElement buttonPushEnter = driver.findElement(By.cssSelector("[type=submit]"));
        Assert.assertEquals(buttonPushEnter.getText(), "Sign In");
    }
    


    @Test
    public void headerTest() throws Exception{
        logger.info("headerTest");// и через инициализацию вызываем различные методы логирования



        WebElement tag = driver.findElement(By.tagName("h1")); //ищем по тегу
        Assert.assertTrue(tag.getText().contains("Sign In to your Account")); //contains это содержимое
    }

    @Test
    public void findClass() {
        logger.info("starting to test sign in is enabled.");

        WebElement linkText = driver.findElement(By.linkText("Terms of Service"));
        WebElement partial = driver.findElement(By.partialLinkText("rms of Ser"));

        WebElement or = driver.findElement(By.xpath("//p[@class='StyledLineSeparator__TextSeparator-sc-tvmx61-2 jdsPEF']"));
        Assert.assertEquals(or.getText(), "or");
    }
    @Test // picture Cloud`
    public void CloudTest() {
        WebElement cloud = driver.findElement(By.xpath("//img[@class='StyledHeader__LogoIcon-sc-1y31m8t-3 jfcCnf']"));
        Assert.assertTrue(cloud.getAttribute("src").equals("https://cloudrein.com/images/app/LOGO_280X51.svg"));
        logger.info("finished signInIsEnabledTest.");
    }

    @Test // button
    public void buttonSignIn() {
        logger.info("starting to test find the Button");
        WebElement buttonSignIn = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertEquals(buttonSignIn.getText(),"Sign In");
        logger.info("finish to test find the Button");
    }

    @Test
    public void tenErrorTest()  {
        logger.info("starting to test tenErrorTest.");

        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();//click
        emailField.clear();//clear
        emailField.sendKeys(MyCredentials.email1);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys(MyCredentials.pass1); //пишем ошибочный пароль

//        Actions a=new Actions(driver);
//        a.moveToElement(passField).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        for (int i=0; i<0;i++) { // если хотим более кликать
            signInButton.click();
            sleep(100);
                    }

        WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[@class='StyledSignIn__Error-sc-t0jmvd-4 fTcqJJ']"));
        Assert.assertEquals(invalidEmailOrPass.getText(),"Too many login failures, this account will be locked for 10 minutes.");

        //WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[text()='Invalid Email or password.']"));
        //Assert.assertEquals(invalidEmailOrPass.getText(),"Invalid Email or password.");
        // Assert.assertFalse(signInButton.isEnabled()); надо разобраться
        logger.info("Finish to test tenErrorTest.");
    }


    @Test(dataProvider ="newUser")
    public void correctCredentialTest(String email, String pass)  {
        signIn(email,pass);
        sleep(5000);
        WebElement headEmail = driver.findElement(By.xpath("//span[@class='StyledHeader__StyledUserEmail-sc-17b3aa3-7 esuuvU']"));
        Assert.assertEquals(headEmail.getText(), email.toLowerCase());
        
    }

}
