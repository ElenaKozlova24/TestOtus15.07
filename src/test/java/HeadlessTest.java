import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

            // Ожидание загрузки страницы
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));

            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            searchInput.sendKeys("Отус");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.searchbox_searchButton__F5Bwq")));
            searchButton.click();

            // Ожидание загрузки результатов поиска
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".result__a")));

            // Прокрутка страницы до элемента
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Ожидание видимости элемента
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".EKtkFWMYpwzMKOYr0GYm.LQVY1Jpkk8nyJ6HBWKAk")));

            String resultText = resultElement.getText();
            System.out.println(resultText);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
