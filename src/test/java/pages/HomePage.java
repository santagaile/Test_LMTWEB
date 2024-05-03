package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage {
    @FindBy(how = How.XPATH, using = "//a[@title='My Account']")
    private WebElement myAccountDropDown;
    @FindBy(how = How.XPATH, using = "//a[@title='My Account']/following-sibling::ul//a")
    private List<WebElement> myAccountOptions;


    @FindBy(how = How.XPATH, using = "//*[@class=\"first-list\"]/li/span")
    private List<WebElement> menuOptions;
   // private WebElement menuOptions;

    //*[@class="first-list"]/li/span/text()
    //*[@id="__nuxt"]/div/header/div[2]/div[2]/div/nav/ul/li[2]/div/div/ul/li[3]/span/text()

    //*[@id="__nuxt"]/div/header/div[2]/div[2]/div/nav/ul/li[2]/div/div/ul/li[3]/span/text()
    //*[@id="__nuxt"]/div/header/div[2]/div[2]/div/nav/ul/li[2]/a/text()


    //-----------------METHODS--------------------------
  /*  public void clickMyAccount() {
        myAccountDropDown.click();
    }

    public void userSeesOptionsUnderMyAccount(List<String> expected) {
        List<String> actual = myAccountOptions.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        assertEquals(expected, actual);
    }

   */

    public void userSeesMenuOptions(List<String> expected) {
        List<String> actual = menuOptions.stream().map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .collect(Collectors.toList());

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        assertEquals(expected, actual);
    }

    public void clickOnOptionUnderMyAccount(String option) {
        myAccountOptions.stream().filter((o) -> o.getText().equals(option))
                .findFirst().orElseThrow().click();
    }
}
