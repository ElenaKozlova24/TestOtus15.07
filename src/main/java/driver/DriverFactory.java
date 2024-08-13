package driver;

import org.openqa.selenium.WebDriver;
import driver.impl.ChromeWebDriver;

import java.net.MalformedURLException;
import java.util.Locale;

public class DriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

    @Override
    public WebDriver getDriver() throws DriverNotSupportedException, MalformedURLException {
        switch (this.browserType) {
            case "chrome": {
                return new ChromeWebDriver().newDriver();
            }
            default:
                throw new DriverNotSupportedException(this.browserType);
        }
    }
}
