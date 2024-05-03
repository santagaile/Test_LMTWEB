package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/h1")
    private WebElement pageTitle;

  //  @FindBy(how = How.XPATH, using = "//input[@value= 'Continue']")
  //  private WebElement buttonContinue;

    @FindBy(how = How.XPATH, using = "//*[@id=\"account-register\"]/div[contains(@Class,'alert')]")
    private WebElement warningMessagePrivacyPolicy;
    //-----------------METHODS--------------------------

    public void userFillsField(WebDriver driver, String field, String value) {
        if (field.equals("E-Mail") && value != null && value.contains("{TimeStamp}") ) {
            value = value.replace("{TimeStamp}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        }

        WebElement parentElement = driver.findElement(By.xpath(String.format("//div[.//*[text() = '%s'] and (@class='pull-right' or contains(@class,'form-group'))]", field)));
        WebElement formField = parentElement.findElement(By.cssSelector("input"));

        switch (formField.getAttribute("type")) {
            case "radio": {
                String index = (value.equalsIgnoreCase("yes") ? "1" : "0");
                parentElement.findElement(By.xpath(String.format(".//input[@value = '%s']", index))).click();
                return;
            }
            case "checkbox": {
                if (value.equalsIgnoreCase("yes") == !formField.isSelected()) {
                    formField.click();
                }
                return;
            }
            default: {
                value = (value == null) ? "" : value;
                formField.clear();
                formField.sendKeys(value);
            }
        }
    }

  //  public void clickButtonContinue() {
  //      buttonContinue.click();
  //  }

    public WebElement getFieldErrorMessage(WebDriver driver, String field) {
        return driver.findElement(By.xpath(String.format("//label[text() = '%s']/parent::div//div[@class='text-danger']", field)));
    }

    public WebElement getFieldLabel(WebDriver driver, String field) {
        return driver.findElement(By.xpath(String.format("//label[text() = '%s']/parent::div//label", field)));
    }

    public WebElement getFieldInput(WebDriver driver, String field) {
        return driver.findElement(By.xpath(String.format("//label[text() = '%s']/parent::div//input", field)));
    }

    //----------------------GETTERS AND SETTERS----------------------
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getWarningMessagePrivacyPolicy() {
        return warningMessagePrivacyPolicy;
    }
}
