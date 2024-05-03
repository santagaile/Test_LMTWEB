package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import utils.PropertiesLoader;

import static org.junit.Assert.*;

public class WebPageSteps {
    private WebDriver driver;
    //-------------PAGES-------------------
    static BasePage basePage;
    static HomePage homePage;
    static RegisterPage registerPage;
    static AccountPage accountPage;
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
        registerPage = PageFactory.initElements(Hooks.driver, RegisterPage.class);
        accountPage = PageFactory.initElements(Hooks.driver, AccountPage.class);
    }

    //---------------STEPS----------------
    @Given("user navigate to webshop {string} page")
    public void userNavigateToWebshop(String configParam) {
        String pageURL = PropertiesLoader.get().getProperty(configParam);
        driver.navigate().to(pageURL);
    }

    @Then("user sees main product options:")
    public void userSeesMainProductOptions(DataTable table) {
        homePage.userSeesMenuOptions(table.asList());
    }

    @When("user clicks {string} at header dropdown")
    public void userClicksAtHeaderDropdown(String option) {
        homePage.clickOnOptionUnderMyAccount(option);
    }

    @Then("user is redirected to {string} page")
    public void userIsRedirectedToPage(String configParam) {
        String pageURL = PropertiesLoader.get().getProperty(configParam);
        assertEquals("Incorrect URL: ", pageURL, driver.getCurrentUrl());
    }

    @Then("user sees page title {string}")
    public void userSeesPageTitle(String title) {
        WebElement pageTitleText = registerPage.getPageTitle();
        assertEquals("Message text is NOT EQUAL!", title, pageTitleText.getText());
    }

    @When("user fills fields:")
    public void userFillsFields(DataTable dataTable) {
        dataTable.asMap().forEach((field, value) -> registerPage.userFillsField(driver, field, value));
    }

    @Then("user sees red warning message {string}")
    public void userSeesRedWarningMessage(String message) {
        WebElement textWarningMessagePrivacyPolicy = registerPage.getWarningMessagePrivacyPolicy();
        assertTrue(textWarningMessagePrivacyPolicy.isDisplayed());
        assertEquals("Message text is NOT EQUAL!", message, textWarningMessagePrivacyPolicy.getText());
        assertEquals("Font RGB is NOT EQUAL!", "rgba(169, 68, 66, 1)", textWarningMessagePrivacyPolicy.getCssValue("color"));
        assertEquals("Background RGB is NOT EQUAL!", "rgba(242, 222, 222, 1)", textWarningMessagePrivacyPolicy.getCssValue("background-color"));
        assertEquals("Border RGB is NOT EQUAL!", "rgb(235, 204, 209)", textWarningMessagePrivacyPolicy.getCssValue("border-color"));
    }

    @And("user sees red error messages under fields:")
    public void userSeesRedErrorMessagesUnderFields(DataTable dataTable) {
        dataTable.asMap().forEach((field, errorText) -> {
            WebElement errorMessage = registerPage.getFieldErrorMessage(driver, field);
            assertTrue(errorMessage.isDisplayed());
            assertEquals("Message text is NOT EQUAL!", errorText, errorMessage.getText());
            assertEquals("Font RGB is NOT EQUAL!", "rgba(169, 68, 66, 1)", errorMessage.getCssValue("color"));
            //field label
            WebElement fieldLabel = registerPage.getFieldLabel(driver, field);
            assertEquals("Font RGB is NOT EQUAL!", "rgba(169, 68, 66, 1)", fieldLabel.getCssValue("color"));
            //field border
            WebElement formField = registerPage.getFieldInput(driver, field);
            assertEquals("Font RGB is NOT EQUAL!", "rgb(169, 68, 66)", formField.getCssValue("border-color"));
        });
    }

    @Then("user sees popup message:")
    public void userSeesPopupMessage(DataTable dataTable) {
            dataTable.asMap().forEach((field, errorText) -> {
                WebElement formField = registerPage.getFieldInput(driver, field);
                assertEquals("Message text is NOT EQUAL!", errorText, formField.getAttribute("validationMessage"));
        });
    }

    @When("user clicks {string} {string}")
    public void userClicks(String elementName, String elementType) {
            basePage.clickElement(driver, "//*", elementName, timeout);
    }

    @And("user sees in left side sections and links:")
    public void userSeesInLeftSideMenu(DataTable dataTable) {
        dataTable.asMap().forEach((section, link) -> {
               try {
                  //  WebElement leftMenuSection = accountPage.getLeftSideSection(driver, section);
                   WebElement leftMenuSection = basePage.waitUntilElementVisible(driver, "//h2", section, timeout);
                    assertTrue("Section '" + section + "' not found", leftMenuSection.isDisplayed());
                    if (link != null) {
                       // WebElement leftMenuLink = accountPage.getLeftSideLink(driver, section, link);
                        String pathPrefix = "//h2[normalize-space(text())=\'" + section + "\']/following-sibling::ul[1]//a";
                        WebElement leftMenuLink = basePage.waitUntilElementVisible(driver, pathPrefix, link, timeout);
                        assertTrue("Link '" + link + "' not found", leftMenuLink.isDisplayed());
                    }
                } catch (NoSuchElementException e) {
                    assertFalse("Section/Link: '" + section + "/" + link + "' not found", true);
                }
        });
    }



 /*   @Then("user in {string} clicks link {string}")
    public void userInClicksLinkEditYourAccountInformation(String section, String link) {
        WebElement leftMenuLink = accountPage.getLeftSideLink(driver, section, link);
        leftMenuLink.click();
    }

  */

}
