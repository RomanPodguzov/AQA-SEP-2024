package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.util.DataHolder;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebSteps {
    public static WebDriver driver;

    private final static String COOKIES_LINK = "//a[contains(@href,'technologies/cookies')]";


    @Given("Load google page")
    public void loadGooglePage() {
        driver.get("https://google.com/");
    }

    @Given("Accept Google cookies if present")
    public void acceptCookiesIfPresent() {
        List<WebElement> cookiesLink = driver.findElements(By.xpath(COOKIES_LINK));
        if (!cookiesLink.isEmpty()) {
            driver.findElements(By.tagName("button"))
                    .get(4)
                    .click();
        }
    }

    @When("I google for {string}")
    public void searchForCelebrity(String alias) {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.click();
        searchInput.sendKeys((String) DataHolder.HOLDER.get(alias));
        searchInput.sendKeys(Keys.ENTER);
    }

    @Then("I can see {int} search results for name {string}")
    public void validateResults(int amount, String alias) {
        List<WebElement> searchHeaders = new WebDriverWait(driver, Duration.ofSeconds(30L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 2));

        int searchResultsCount = 0;
        for (WebElement searchHeader : searchHeaders) {
            if (searchHeader.getText().contains((String) DataHolder.HOLDER.get(alias))) {
                searchResultsCount++;
            }
        }

        Assert.assertTrue(searchResultsCount > amount);
    }

    private String whichNameToUse(String name) {
        if ("random_name".equals(name)) {
            List<String> randomNames = (List<String>) DataHolder.HOLDER.get("random_users");
            return randomNames.get(0);
        } else {
            return name;
        }
    }
}
