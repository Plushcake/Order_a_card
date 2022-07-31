package ru.netology.web;

import com.google.common.util.concurrent.Service;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import static io.github.bonigarcia.wdm.WebDriverManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTest {

    private WebDriver driver;

    @BeforeAll
    //Запускается перед всеми тестами.
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "E:\\Git\\Order_a_card\\driver\\windows\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
        //Запускается перед каждым тестовым методом.
    void setup() {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }


    @AfterEach
        //Закрываем все окна браузера.
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Александр Пушкин");
        elements.get(1).sendKeys("+79995557744");

        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();//Продолжить

        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
