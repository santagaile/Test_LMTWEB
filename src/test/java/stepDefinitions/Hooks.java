package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Before
    public void openBrowser()  {
        if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("mac"))
            System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        else
            System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-ssl-errors");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.log("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }
}
