import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        WebDriver driver = new ChromeDriver(options);


        String url = "https://otus.ru/";
        driver.get(url);

        WebElement signInButton = driver.findElement(By.cssSelector(".header-nav__item--sign-in"));
        signInButton.click();


        WebElement emailInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));

        emailInput.sendKeys("testuser@example.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("testpassword");
        driver.findElement(By.cssSelector(".auth-form__button")).click();

        WebElement profileLink = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header-nav__item--profile")));

        System.out.println("All cookies:");
        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println(cookie);
        }

        driver.quit();
    }
}
