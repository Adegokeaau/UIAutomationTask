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
    public void setup(){
       System.setProperty("webdriver.chrome.driver","C:\\Users\\Adeshola\\workspace\\Monosnap_Login\\Monosnap_Login\\Resources\\chromedriver.exe");
       driver = new ChromeDriver();
       driver.get("https://monosnap.com/");
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       System.out.println(driver.getTitle());
       driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[3]/div[2]")).click();
       driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[1]/div/div[2]/div[3]/span")).click();
       driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[1]")).sendKeys("adegokeaau@gmail.com");
       driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[2]")).sendKeys("benediadeade1*");
       driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/button")).click();
   }
    @Test(priority = 0)
    public void testsuccessfullogin(){
        if (driver.getCurrentUrl().contains("https://monosnap.com/list/5fcba99a8a2e603f4f5f7e25"))
            //pass
            System.out.println("The page URL contains /list");
        else
            //fail
            System.out.println("The page URL does not contain /list");
   }
    @Test(priority = 1)
    public void testlogout() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"store\"]/div[1]/div/div[2]/div[2]/div[1]/div/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[4]/div/div[1]/div/div[3]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[4]/div/div[1]/div/div[1]/div[2]")).click();
        if (driver.getCurrentUrl().contains("https://monosnap.com/"))
            //pass
            System.out.println("The login URL contains /login");
        else

            //fail
            System.out.println("The login URL does not contain /login");

    }
    @Test(priority = 2)

    public void testNegativelogin(){
        driver.navigate().refresh();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/div[3]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[1]")).sendKeys("adegokau@gmail.com");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/div[2]/input[2]")).sendKeys("benediadeade1*");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/form/button")).click();
        String expectedErrorMessage = "Wrong email or password\n" +
                "Please check if you've typed your email and password correctly.";
        String actualErrorMessage = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div")).getText();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);

    }


    public static void main(String args[])throws InterruptedException{
        LoginTests tests =new LoginTests();
        tests.setup();
    }
    @AfterClass
    public void closeBrowser()throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}
