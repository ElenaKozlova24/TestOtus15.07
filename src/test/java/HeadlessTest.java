import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeadlessTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/kirill/Documents/chromedriver-mac-x64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--silent");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver(options);

            String url = "https://duckduckgo.com/";
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            searchInput.sendKeys("Отус");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.searchbox_searchButton__F5Bwq")));
            searchButton.click();
            WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ikg2IXiCD14iVX7AdZo1")));
            String resultText = resultElement.getText();
            assertTrue(resultText.contains("Онлайн‑курсы для профессионалов, дистанционное обучение современным"));
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
