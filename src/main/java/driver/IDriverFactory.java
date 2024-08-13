package driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriverFactory {
    WebDriver getDriver() throws DriverNotSupportedException, MalformedURLException;
}
