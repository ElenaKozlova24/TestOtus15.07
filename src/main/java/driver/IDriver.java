package driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriver {
    WebDriver newDriver() throws DriverNotSupportedException, MalformedURLException;
}