import jdk.internal.org.objectweb.asm.util.Printer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadlessChromeTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {

        System.setProperty("webdriver.chrome.driver", "/Users/kirill/Documents/chromedriver-mac-x64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testOtus() {
        driver.get("https://duckduckgo.com/");

        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("ОТУС");
        searchInput.submit();

        WebElement list = driver.findElement(By.className("react-results--main"));
        WebElement firstResult = list.findElement(By.cssSelector("a.result__a")); // Предполагая, что первый результат находится в теге 'a' с классом 'result__a'

        assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", firstResult.getText());
    }
}