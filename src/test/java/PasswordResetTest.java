import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PasswordResetTest extends TestBase {
    @BeforeSuite
    public void setUpInternal() {
        super.url = "https://cloudrein.com/newapp#/reset-password";
    }

    @Test
    public void loginEmailForNewPass() throws InterruptedException {

        WebElement emailField1 = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]")); //enter mail
        emailField1.click();//click
        emailField1.clear();//clear
        emailField1.sendKeys(MyCredentials.email1);
        Thread.sleep(100); // a little break

        WebElement resetButton = driver.findElement(By.xpath("//button[@type=\"submit\"]")); // find button
        resetButton.click(); // click button
        Thread.sleep(100);

        //WebElement invalidEmailOrPass1 = driver.findElement(By.xpath("//strong[contains(text(),'An email has been sent to you.')]")); // find xpath
        WebElement invalidEmailOrPass1 = driver.findElement(By.tagName("strong")); // search text by tag
        Assert.assertEquals(invalidEmailOrPass1 .getText(), "An email has been sent to you."); // accept
        Thread.sleep(3000);
    }

    @Test
    public void loginEmailFail() throws InterruptedException {
        WebElement emailField1 = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField1.click();//click
        emailField1.clear();//clear
        emailField1.sendKeys("testQA36a@dvss");
        Thread.sleep(1000);

        WebElement invalidButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        invalidButton.click();
        Thread.sleep(1000);

        Assert.assertFalse(invalidButton.isEnabled());
        Thread.sleep(1000);

    }
}
