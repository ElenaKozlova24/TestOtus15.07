package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeadlessTest {

    private static final Logger logger = LogManager.getLogger(HeadlessTest.class);
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--silent");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
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
    public void testDuckDuckGoSearch() {
        try {
            String url = "https://duckduckgo.com/";
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            searchInput.sendKeys("Отус");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.searchbox_searchButton__F5Bwq")));
            searchButton.click();
            WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ikg2IXiCD14iVX7AdZo1")));
            String resultText = resultElement.getText();
            assertTrue(resultText.contains("Онлайн‑курсы для профессионалов, дистанционное обучение современным"), "Текст не найден");

            logger.info("Тест пройден!");
        } catch (Exception e) {
            takeScreenshot(driver, "error_screenshot.png");
            logger.error("Ошибка при выполнении теста", e);
        }
    }

    private static void takeScreenshot(WebDriver driver, String filePath) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(Paths.get(screenshot.getPath()), Paths.get(filePath));
                logger.info("Скриншот сохранен в: " + filePath);
            } catch (IOException e) {
                logger.error("Ошибка при сохранении скриншота", e);
            }
        }
    }
}
