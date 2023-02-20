import org.testng.annotations.BeforeSuite;

public class HomePageTest extends TestBase {
    @BeforeSuite
    public void setUpInternal() {
        super.url = "https://cloudrein.com/newapp#/backups";
    }


}
