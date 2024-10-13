
package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class AlloSearchIphone {

    public static void main(String[] args) {

        // Открываем браузер
        WebDriver driver = new ChromeDriver();

        try {
            // Открываем сайт allo.ua
            driver.get("https://allo.ua");

            // Устанавливаем WebDriverWait для ожидания появления элементов
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Ищем строку поиска
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search-form__input']")));

            // Вводим запрос iPhone 16 и нажимаем Enter
            searchBox.sendKeys("Apple iPhone 16");
            searchBox.submit();

            // Ожидаем загрузки результатов поиска
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'product-card')]")));

            // Найдем первый элемент с Apple iPhone 16 в результатах поиска
            WebElement firstProduct = driver.findElement(By.xpath("//div[contains(@class, 'product-card')]//a[contains(text(), 'Apple iPhone 16')]"));

            // Ищем код товара, модель и цену
            WebElement productCode = firstProduct.findElement(By.xpath(".//div[contains(@class, 'product-sku')]"));
            WebElement productModel = firstProduct.findElement(By.xpath(".//a[contains(@class, 'product-card__name')]"));
            WebElement productPrice = firstProduct.findElement(By.xpath(".//div[contains(@class, 'v-pb__cur')]"));

            // Выводим результаты
            System.out.println("Код: " + productCode.getText());
            System.out.println("Модель: " + productModel.getText());
            System.out.println("Цена: " + productPrice.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрываем браузер
            driver.quit();
        }
    }
}
