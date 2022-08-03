package ru.netology.web;

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

public class OrderCardTest {

    private WebDriver driver;

    @BeforeAll
    //Запускается перед всеми тестами.
    public static void setupAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    //Запускается перед каждым тестовым методом.
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }


    @AfterEach
    //Закрываем все окна браузера.
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void test() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Александр Пушкин");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995554433");

        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();//Продолжить

        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
