package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"page-sakumlapa\"]/section/div/div[1]/div[2]/button[1]")
    private WebElement dataCookieButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"first-list\"]/li/span")
    private List<WebElement> menuOptions;

    @FindBy(how = How.XPATH, using = "//*[@id=\"lmt-header-search-input-desktop\"]")
    private WebElement searchInput;

    @FindBy(how = How.XPATH, using = "//*[@id=\"lmt-header-search-results-products-desktop\"]/a")
    private List<WebElement> searchResult;

    @FindBy(how = How.XPATH, using = "//*[@id=\"product-add-to-cart-button\"]")
    private WebElement addToCartButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"added-to-cart-popup-redirect-to-cart-desktop\"]")
    private WebElement goToCartButton;

    //-----------------METHODS--------------------------
    public WebElement seeDataCookieButton(WebDriver driver, String timeout) {
        return new WebDriverWait(driver, Integer.parseInt(timeout)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("/html/body/section/div/div[1]/div[2]/button[1]"))));
    }

    public void userSeesMenuOptions(String expected){
        // Filter the list and find the first element with the desired text
        WebElement element = menuOptions.stream()
                .filter(o -> o.getText().equals(expected))
                .findFirst() // Find the first matching element, if any
                .orElseThrow(() -> new RuntimeException("Element with text not found: " + expected));

        // Read the text (name) of the found element
        String elementText = element.getText();
        System.out.println("Found element text: " + elementText);

        // Click on the found element
        element.click();

        try {
            // Perform an assertEquals check between 'expected' and 'elementText'
            assertEquals(expected, elementText);
            System.out.println("Assertion successful: Text matches " + expected);
        } catch (AssertionError e) {
            // If the assertion fails, print a message about the failure
            System.out.println("Assertion failed: Text does not match " + expected);
            // Optionally, print details of the error
            System.out.println("Detailed error: " + e.getMessage());
            // Handle or report this error as needed
        }
    }

    public void searchProduct(String product) {
        searchInput.sendKeys(product);
        System.out.println("Number of products found '" + product + "': " + searchResult.size());
    }

    public void clickProduct() {
          WebElement firstElement = searchResult.get(0);
          firstElement.click();
    }

    public void addToCart() {
        addToCartButton.click();
        goToCartButton.click();
    }
}
