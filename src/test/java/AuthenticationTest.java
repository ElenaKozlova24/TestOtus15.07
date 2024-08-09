import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationTest {
    private static final Logger logger = LogManager.getLogger(AuthenticationTest.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAuthentication() {
        try {
            String url = "https://otus.ru/";
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 10);

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.sc-mrx253-0")));
            signInButton.click();

            WebElement divElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.hGvqzc")));
            divElement.click();

            WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
            emailInput.sendKeys("4688104@mail.ru");

            WebElement divElement2 = driver.findElement(By.cssSelector(".sc-11ptd2v-1-Component"));
            divElement2.click();

            WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
            passwordInput.sendKeys("Kozlova23.");

            WebElement loginButton = driver.findElement(By.cssSelector("div.sc-10p60tv-2.bQGCmu"));
            loginButton.click();

            logger.info("All cookies:");
            for (Cookie cookie : driver.manage().getCookies()) {
                logger.info(cookie.toString());
            }
        } catch (Exception e) {
            logger.error("Ошибка при выполнении теста", e);
        }
    }
} а мо