
package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderPositiveTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void testPositiveAllInput() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Дмитрий");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79217552824");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        var actualTextElement = driver.findElement(By.cssSelector("[data-test-id=callback-success]"));
        var actualText = actualTextElement.getText().trim();
        assertEquals("Ваша заявка успешно отправлена!", actualText);
        assertTrue(actualTextElement.isDisplayed());
    }
}
