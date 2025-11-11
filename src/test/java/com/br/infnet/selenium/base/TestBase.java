package com.br.infnet.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class TestBase {
    protected WebDriver driver;
    protected static final String BASE_URL = "http://localhost:7000";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless", "false").equals("true") ||
                System.getenv("CI") != null) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-web-security");
            options.addArguments("--allow-running-insecure-content");
        }

        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("window.setTimeout = function(fn, delay) { return window.originalSetTimeout(fn, delay * 2); };");
        driver.get(BASE_URL + "/livros");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            try {
                ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");

                ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

                driver.manage().deleteAllCookies();

                ((JavascriptExecutor) driver).executeScript(
                        "if ('caches' in window) {" +
                                "  caches.keys().then(function(names) {" +
                                "    names.forEach(function(name) {" +
                                "      caches.delete(name);" +
                                "    });" +
                                "  });" +
                                "}"
                );

            } catch (Exception e) {
                System.out.println("Erro ao limpar dados do navegador: " + e.getMessage());
            } finally {
                Thread.sleep(5000);
                driver.quit();
            }
        }
    }
}


