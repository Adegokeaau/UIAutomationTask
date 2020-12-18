package Login;

import io.github.bonigarcia.wdm.managers.SeleniumServerStandaloneManager;
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
                System.setProperty("webdriver.chrome.driver","C:\\Users\\Adeshola\\workspace\\Deydam_login\\Deydam_Login\\Resources\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                System.out.println(driver.getTitle());
                driver.findElement(By.id("username")).sendKeys("adegokeaau");
                driver.findElement(By.id("password")).sendKeys("Blackman1*");
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();


        }
        @Test(priority = 0)
        public void testsuccessfullogin(){
                if (driver.getCurrentUrl().contains("https://dev.d2rxvhrryr2bkn.amplifyapp.com/app/feed\""))
                //pass
                 System.out.println("The page URL contains /app/feed");
                else
                        //fail
                System.out.println("The page URL does not contain /app/feed");

        }
        @Test(priority = 1)
        public void testlogout() throws InterruptedException {
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div[1]/div/div[2]/div[3]/button/img")).click();
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div[1]/div/div[2]/div[3]/div/div/a[3]")).click();
                if (driver.getCurrentUrl().contains("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login\""))
                        //pass
                        System.out.println("The login URL contains /login");
                else

                //fail
                System.out.println("The login URL does not contain /login");

            }
        @Test(priority = 2)
        public void testNegativelogin(){
                driver.findElement(By.id("username")).sendKeys("qertyukeaau");
                driver.findElement(By.id("password")).sendKeys("Blackman1*");
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();
                String expectedErrorMessage = "Login Failed: Invalid username or password";
                String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/div[4]/div/div/p")).getText();
                Assert.assertEquals(actualErrorMessage,expectedErrorMessage);

        }

        public static void main(String args[])throws InterruptedException{
                LoginTests tests =new LoginTests();
                tests.setup();

        }
        @AfterClass
        public void closeBrowser()throws InterruptedException {
                Thread.sleep(10000);
                driver.quit();
        }


}
