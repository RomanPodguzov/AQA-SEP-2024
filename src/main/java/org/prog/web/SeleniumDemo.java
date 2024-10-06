package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumDemo {

    private final static String COOKIES_LINK = "//a[contains(@href,'technologies/cookies')]";

    public static void main(String[] args) {
        WebDriver driver = null;
        String celebrityName = "Ben Affleck";
        try {
            driver = new ChromeDriver();
            driver.get("https://google.com/");
            List<WebElement> cookiesLink = driver.findElements(By.xpath(COOKIES_LINK));
            if (!cookiesLink.isEmpty()) {
                driver.findElements(By.tagName("button"))
                        .get(4)
                        .click();
            }

            WebElement searchInput = driver.findElement(By.name("q"));
            searchInput.click();
            searchInput.sendKeys(celebrityName);
            searchInput.sendKeys(Keys.ENTER);

            List<WebElement> searchHeaders = new WebDriverWait(driver, Duration.ofSeconds(30L))
                    .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 2));

            int searchResultsCount = 0;
            for (WebElement searchHeader : searchHeaders) {
                if (searchHeader.getText().contains(celebrityName)) {
                    searchResultsCount++;
                }
            }

            if (searchResultsCount > 2) {
                System.out.println("Celebrity found: " + celebrityName);
            } else {
                System.err.println("FAILED!");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
