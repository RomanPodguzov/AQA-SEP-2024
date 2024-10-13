package org.prog.testng.web;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.dto.ResultsDto;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MyWebTest {

    private final static String COOKIES_LINK = "//a[contains(@href,'technologies/cookies')]";

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "celebrityNames")
    public void myNgTests1(String celebrityName, int resultCount) {
//        String celebrityName = "Ben Affleck";
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

        Assert.assertTrue(searchResultsCount > resultCount);
    }

    @DataProvider(name = "celebrityNames")
    public Object[][] dataSource() {
        List<String> users = getUsers(5);
        users.add("Ben Affleck");
        users.add("Margot Robbie");
        Object[][] dataSource = new Object[users.size()][2];

        users.forEach(u -> {
            dataSource[users.indexOf(u)][0] = u;
            dataSource[users.indexOf(u)][1] = 2;
        });

        return dataSource;
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private List<String> getUsers(int amount) {
        return RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("/api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResultsDto.class)
                .getResults()
                .stream()
                .map(u -> u.getName().getFirst() + " " + u.getName().getLast())
                .collect(Collectors.toList());
    }
}
