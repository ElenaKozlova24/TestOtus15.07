package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AuthenticationTest {
    private static final Logger logger = LogManager.getLogger(AuthenticationTest.class);
    private WebDriver driver;
    private Properties properties;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);

        // Загрузка свойств из файла
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/auth.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Ошибка при загрузке файла свойств", e);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                logger.error("Ошибка при закрытии драйвера", e);
            }
        }
    }

    @Test
    public void testAuthentication() {
        try {
            String url = "https://otus.ru/";
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.sc-mrx253-0")));
            signInButton.click();

            WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.hGvqzc")));
            login.click();

            WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
            emailInput.sendKeys(properties.getProperty("email"));

            WebElement password = driver.findElement(By.cssSelector(".sc-11ptd2v-1-Component"));
            password.click();

            WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
            passwordInput.sendKeys(properties.getProperty("password"));

            WebElement loginButton = driver.findElement(By.cssSelector("div.sc-10p60tv-2.bQGCmu"));
            loginButton.click();

            logger.info("All cookies:");
            for (Cookie cookie : driver.manage().getCookies()) {
                logger.info(cookie.toString());
            }
        } catch (Exception e) {
            logger.error("Ошибка при выполнении теста", e);
        }
    }
}
