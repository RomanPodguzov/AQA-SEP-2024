package org.prog.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverFactory {

    public static WebDriver getDriver() throws MalformedURLException {
        //LOCAL - Intellij
        //SELENOID - Intellij + Selenoid
        //JENKINS - Jenkins + Selenoid
        String env = System.getProperty("env", "LOCAL");
        switch (env) {
            case "LOCAL":
                return new ChromeDriver();
            case "SELENOID":
                return new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"), remoteChrome());
            case "JENKINS":
                return new RemoteWebDriver(
                        new URL("http://selenoid-selenoid-1:4444/wd/hub"), remoteChrome());
            default:
                throw new RuntimeException("No env defined!");
        }
    }

    private static ChromeOptions remoteChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return chromeOptions;
    }
}
