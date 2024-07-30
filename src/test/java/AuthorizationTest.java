import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
class AuthorizationTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/kirill/Documents/chromedriver-mac-x64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        WebDriver driver = new ChromeDriver(options);
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


        System.out.println("All cookies:");
        for (
                Cookie cookie : driver.manage().

                getCookies()) {
            System.out.println(cookie.toString());
        }
        driver.quit();
    }
}
