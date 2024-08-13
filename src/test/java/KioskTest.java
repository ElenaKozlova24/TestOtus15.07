import driver.DriverFactory;
import driver.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class KioskTest {

    private static final Logger logger = LogManager.getLogger(KioskTest.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException, DriverNotSupportedException {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testKioskMode() {
        try {
            String url = "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818";
            driver.get(url);

            WebElement modalBefore = null;
            try {
                modalBefore = driver.findElement(By.cssSelector("div.pp_hoverContainer"));
                Assertions.assertFalse(modalBefore.isDisplayed(), "Modal is visible before the event");
            } catch (Exception e) {
                logger.info("Modal not found before the event");
            }

            WebElement element = driver.findElement(By.cssSelector("ul.portfolio-area li:nth-child(4) img"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();

            WebElement modalAfter = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pp_hoverContainer")));

            Assertions.assertNotNull(modalAfter, "Modal not found after the event");
            Assertions.assertTrue(modalAfter.isDisplayed(), "Modal is not visible after the event");

            logger.info("Тест пройден!");
        } catch (Exception e) {
            logger.error("Ошибка при выполнении теста", e);
            Assertions.fail("Test failed", e);
        }
    }
}
