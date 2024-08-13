package driver.impl;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import driver.DriverNotSupportedException;
import driver.IDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class ChromeWebDriver implements IDriver {
    private String remoteUrl = System.getProperty("remote.url", "http://193.104.57.173");

    @Override
    public WebDriver newDriver() throws DriverNotSupportedException, MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("no-first-run");
        chromeOptions.addArguments("homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-error");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
        chromeOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "")));

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        downloadLocalWebDriver(DriverManagerType.CHROME);

//        if (remoteUrl.startsWith("http")) {
//            return new RemoteWebDriver(new URL(remoteUrl + "/wd/hub"), chromeOptions);
//        }
        return new ChromeDriver(chromeOptions);
    }

    private void downloadLocalWebDriver(DriverManagerType driverManagerType) {
        // Implement the logic to download the local WebDriver if needed
    }
}
