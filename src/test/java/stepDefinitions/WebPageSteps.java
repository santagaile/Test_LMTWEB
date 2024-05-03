package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import utils.PropertiesLoader;


public class WebPageSteps {
    private WebDriver driver;
    //-------------PAGES-------------------
    static BasePage basePage;
    static HomePage homePage;
    public String timeout;


    //---------------CONSTRUCTOR-----------------
    public WebPageSteps() {
        this.driver = Hooks.driver;
        //  WebDriverWait wait = new WebDriverWait(driver,40);
        //  this.wait = new WebDriverWait(driver, (10));
        timeout = PropertiesLoader.get().getProperty("Wait");

        //Initialise pages
        basePage = PageFactory.initElements(Hooks.driver, BasePage.class);
        homePage = PageFactory.initElements(Hooks.driver, HomePage.class);
    }

    //---------------STEPS----------------
    @Given("user navigate to webshop {string} page")
    public void userNavigateToWebshop(String configParam) throws Throwable {
        String pageURL = PropertiesLoader.get().getProperty(configParam);
        driver.navigate().to(pageURL);

        //click accept DataCookieButton
        WebElement cookieButton = homePage.seeDataCookieButton(driver, timeout);
        Thread.sleep(1000);
        cookieButton.click();
    }

    @Then("user sees main product options:")
    public void userSeesMainProductOptions(DataTable table) throws Throwable {
            table.asMap().keySet().forEach(menuItem -> {
              homePage.userSeesMenuOptions(menuItem);
                try {
                    Thread.sleep(2000); //delays execution for demonstration purposes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
    }

    @Then("user search product {string}")
    public void userSearchProduct(String product) throws Throwable {
        homePage.searchProduct(product);
        Thread.sleep(10000);
    }

    @Then("user clicks on first search result")
    public void userClicksOnFirstSearchResult() throws Throwable {
        homePage.clickProduct();
        Thread.sleep(10000);
    }

    @Then("user add to cart product and go to cart")
    public void userAddToCartProduct() throws Throwable {
        homePage.addToCart();
        Thread.sleep(10000);
    }
}
