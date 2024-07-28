import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KioskTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/kirill/Documents/chromedriver-mac-x64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        WebDriver driver = new ChromeDriver(options);

        String url = ("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.get(url);

        WebElement element = driver.findElement(By.cssSelector("ul.portfolio-area li:nth-child(4) img"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        WebElement modal = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pp_hoverContainer")));

        if (modal != null) {
            System.out.println("Modal found");
        } else {
            System.out.println("Modal not found");
        }

        driver.quit();
    }
}