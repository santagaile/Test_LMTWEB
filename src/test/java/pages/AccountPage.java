package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='buttons']/div/*[text() = 'Continue']")
    private WebElement buttonContinue;

   // @FindBy(how = How.XPATH, using = "//*[@id='content']/h2")
   // private List<WebElement> accountPageLeftSideSection;

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public WebElement getLeftSideSection(WebDriver driver, String section) {
        return driver.findElement(By.xpath(String.format("//h2[contains(text(), '%s')]", section)));
    }


    public WebElement getLeftSideLink(WebDriver driver, String section, String link) {
        return driver.findElement(By.xpath(String.format("//h2[contains(text(), '%s')]/following-sibling::ul[1]//a[text()='%s']", section, link)));
    }


}
