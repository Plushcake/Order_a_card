package ru.netology.web;

import com.google.common.util.concurrent.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTest {

    private WebDriver driver;

    @BeforeAll //Запускается перед всеми тестами.
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/windows/chromedriver.exe");

    }

    @BeforeEach
        //Запускается перед каждым тестовым методом.
    void setUp() {

        driver = new ChromeDriver();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иван Грозный");
        elements.get(1).sendKeys("+79045557799");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();//Продолжить
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
