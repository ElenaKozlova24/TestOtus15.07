package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KioskTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
            }
        }
    }

    @Test
    public void testModalVisibility() {
        try {
            String url = "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818";
            driver.get(url);

            WebElement modalBefore = null;
            try {
                modalBefore = driver.findElement(By.cssSelector("div.pp_hoverContainer"));
                assertFalse(modalBefore.isDisplayed(), "Modal is visible before the event");
            } catch (Exception e) {
                System.out.println("Modal not found before the event");
            }

            WebElement element = driver.findElement(By.cssSelector("ul.portfolio-area li:nth-child(4) img"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();

            WebElement modalAfter = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pp_hoverContainer")));

            assertNotNull(modalAfter, "Modal not found after the event");
            assertTrue(modalAfter.isDisplayed(), "Modal is not visible after the event");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
