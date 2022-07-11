package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.OrderPage;
import pages.WebOrdersHomePage;
import utils.Driver;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BaseSteps {

    WebDriver driver;
    LoginPage loginPage;
    WebOrdersHomePage webOrdersHomePage;
    OrderPage orderPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        loginPage = new LoginPage();
        webOrdersHomePage = new WebOrdersHomePage();
        orderPage = new OrderPage();
    }

    ArrayList<String> userOrder = new ArrayList<>();

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String key) {
        loginPage.usernameField.sendKeys(key);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String key) {
        loginPage.passwordField.sendKeys(key);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.loginButton.click();
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }


    @When("user clicks on {string} button")
    public void userClicksOnButton(String text) {
        switch(text){
            case"Check All":
                webOrdersHomePage.checkAllButton.click();
                break;
            case"Uncheck All":
                webOrdersHomePage.uncheckAllButton.click();
                break;
            case"Delete Selected":
                webOrdersHomePage.deleteSelectedButton.click();
                break;
            case"Process":
                //I store the order info here before I click on process.
                //name,productType,quantity,date,street,city,state,zip,card,card#,expDate
                userOrder.add(orderPage.customerNameInputBox.getText()); //storing name
                userOrder.add(orderPage.orderItemDropDown.getText()); //storing product type
                userOrder.add(orderPage.quantityInputBox.getText()); //storing product quantity
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); //storing date when order was processed (Today's date)
                Date date = new Date();
                userOrder.add(formatter.format(date));
                userOrder.add(orderPage.streetInputBox.getText()); //storing street info
                userOrder.add(orderPage.cityInputBox.getText()); //storing city info
                userOrder.add(orderPage.stateInputBox.getText()); //storing state info
                userOrder.add(orderPage.zipInputBox.getText()); //storing zipcode info
                for (WebElement cardType : orderPage.cardTypeOptionsRadioFields) { //storing the cc type that was used for order
                    if (cardType.isSelected()) {
                        userOrder.add(cardType.getText());
                        break;
                    }
                }
                userOrder.add(orderPage.cardNumberInputBox.getText()); //storing cc number
                userOrder.add(orderPage.cardExpireDateInputBox.getText()); //storing cc expire date info
                orderPage.processButton.click();
                break;
            default:
                throw new NotFoundException("The button text is not defined properly in the feature file!!!");
        }
    }






}
