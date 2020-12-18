package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Adeshola\\workspace\\Facebook_login\\Facebook_Login\\Resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://web.facebook.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.findElement(By.id("email")).sendKeys("09057643026");
        driver.findElement(By.id("pass")).sendKeys("Blackman1*");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).click();
    }

    @Test(priority = 0)
    public void testsuccessfullogin() {
        if (driver.getCurrentUrl().contains("https://web.facebook.com/?sk=welcome"))
            //pass
            System.out.println("The page URL contains welcome");
        else
            //fail
            System.out.println("The page URL does not contain welcome");

    }

    @Test(priority = 1)
    public void testlogout() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"mount_0_0\"]/div/div[1]/div[1]/div[2]/div[4]/div[1]/span/div/div[1]/img")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[1]/div/div[3]/div/div[4]/div/div[1]/div[2]/div")).click();
        if (driver.getCurrentUrl().contains("https://web.facebook.com/?stype=lo&jlou=AfcIYHOz5CbBQVUXMnIRLKB0mq3HZoov5kQVxwehIxQIhEjTOapiz0zJr3ZlCoz0pWHtIg0mOj8pE9g3b1CrpBoTtfRjG7HRTXp4jsWrAPPWLg&smuh=21093&lh=Ac9EmAg7q2oOmxpPJ2Q"))
            //pass
            System.out.println("The login URL contains /login");
        else

            //fail
            System.out.println("The login URL does not contain /login");

    }

    @Test(priority = 2)
    public void testNegativelogin() {
        driver.navigate().refresh();
        driver.findElement(By.id("email")).sendKeys("08076573217");
        driver.findElement(By.id("pass")).sendKeys("Blackman1*");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).click();
        String expectedErrorMessage = "The phone number that you've entered doesn't match any account. Sign up for an account.";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[2]")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }

    public static void main(String args[]) throws InterruptedException {
        LoginTests tests = new LoginTests();
        tests.setup();
    }
    @AfterClass
    public void closeBrowser()throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}
