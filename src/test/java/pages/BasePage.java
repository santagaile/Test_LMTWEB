package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage() {
       // this.driver =  Hooks.driver;
       // PageFactory.initElements(driver, this);
       // configFileReader = new ConfigFileReader();
    }

    public WebElement waitUntilElementVisible(WebDriver driver, String pathPrefix, String elementName, String timeout) {
        return new WebDriverWait(driver, Integer.parseInt(timeout)).until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath(String.format("%s[normalize-space(text())='%s' or @value='%s']", pathPrefix, elementName, elementName))));

    }

    public void clickElement(WebDriver driver, String pathPrefix, String elementName, String timeout) {
        waitUntilElementVisible(driver, pathPrefix, elementName, timeout).click();
    }

}
