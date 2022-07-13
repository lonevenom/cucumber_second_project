package steps;

import com.github.javafaker.Faker;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.OrderPage;
import pages.WebOrdersHomePage;
import utils.Driver;
import utils.DropdownHandler;
import utils.GetRandomNumber;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SmartbearSteps {

    WebDriver driver;
    LoginPage loginPage;
    WebOrdersHomePage webOrdersHomePage;
    OrderPage orderPage;
    Faker faker;
    BaseSteps baseSteps;
    GetRandomNumber getRandomNumber;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        loginPage = new LoginPage();
        webOrdersHomePage = new WebOrdersHomePage();
        orderPage = new OrderPage();
        faker = new Faker();
        baseSteps = new BaseSteps();
        getRandomNumber = new GetRandomNumber();
    }

    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String text) {
        switch (text) {
            case "Invalid Login or Password.":
                Assert.assertTrue(loginPage.invalidLoginMessage.isDisplayed());
                Assert.assertEquals(text, loginPage.invalidLoginMessage.getText());
                break;
            case "":

                break;
        }

    }

    @And("validate below menu items are displayed")  //COMPLETE THIS ONE
    public void validateBelowMenuItemsAreDisplayed(DataTable table) {
        for (int i = 0; i < table.asList().size(); i++) {
            Assert.assertEquals(table.asList().get(i), webOrdersHomePage.leftSideDropDownMenu.get(i).getText());
            /* this is asserting if the elements(expectedText - converted into List) from the dataTable of the scenario in the feature file
               is matching the elements(actualText - in the List<WebElement>) located from the application */
        }
    }


    @Then("all rows should be {string}")
    public void allRowsShouldBe(String text) {
        switch (text) {
            case "checked":
                for (int i = 0; i < webOrdersHomePage.checkBoxes.size(); i++) {
  //                  Assert.assertTrue(webOrdersHomePage.checkBoxes.get(i).isDisplayed());
                    Assert.assertTrue(webOrdersHomePage.checkBoxes.get(i).isSelected());
                }
                break;
            case "unchecked":
                for (int i = 0; i < webOrdersHomePage.checkBoxes.size(); i++) {
               //     Assert.assertTrue(webOrdersHomePage.checkBoxes.get(i).isDisplayed());
                    Assert.assertFalse(webOrdersHomePage.checkBoxes.get(i).isSelected());
                }
                break;


        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuItem) {
        webOrdersHomePage.clickOnMenuLink(menuItem);
    }


    @And("user selects {string} as product")
    public void userSelectsAsProduct(String dropdownItem) {
        for (WebElement menuItem : orderPage.orderItemDropDownList) {
            if (menuItem.getText().equals(dropdownItem)) {
                DropdownHandler.selectOptionByVisibleText(orderPage.orderItemDropDown, dropdownItem);
            }
        }
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int itemQuantity) {
        orderPage.quantityInputBox.sendKeys(String.valueOf(itemQuantity));
    }


    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        orderPage.customerNameInputBox.isEnabled();
        orderPage.customerNameInputBox.sendKeys(faker.name().fullName());
        orderPage.streetInputBox.isEnabled();
        orderPage.streetInputBox.sendKeys(faker.address().streetAddress());
        orderPage.cityInputBox.isEnabled();
        orderPage.cityInputBox.sendKeys(faker.address().city());
        orderPage.stateInputBox.isEnabled();
        orderPage.stateInputBox.sendKeys(faker.address().state());
        orderPage.zipInputBox.isEnabled();
        orderPage.zipInputBox.sendKeys(faker.address().zipCode());
    }


    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        //method from getRandomNumber class generates a random number to click on a random radio box for card type
        //loops through card type options to make sure they are all enabled
        for (int i = 0; i < orderPage.cardTypeOptionsRadioFields.size(); i++) {
            orderPage.cardTypeOptionsRadioFields.get(i).isEnabled();
        }
        //clicks on a random card type radio field option using the random variable above.
        orderPage.cardTypeOptionsRadioFields.get(GetRandomNumber.getRandoNumba()).click();

        orderPage.cardNumberInputBox.isEnabled();
        orderPage.cardNumberInputBox.sendKeys(faker.business().creditCardNumber());
        orderPage.cardExpireDateInputBox.isEnabled();
        orderPage.cardExpireDateInputBox.sendKeys(faker.business().creditCardExpiry());
    }


    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String text) {
        if ("List of All Orders".equals(text)) {
            Assert.assertTrue(webOrdersHomePage.orderTable.isDisplayed());
            Assert.assertTrue(webOrdersHomePage.firstOrderInTable.isDisplayed());
        }
    }


    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        //stores info from first row into an arrayList (orderInfo)
        ArrayList<String> orderInfo = new ArrayList<>();
        for (WebElement webElement : webOrdersHomePage.firstRowInfo) {
            orderInfo.add(webElement.getText());
        }
        //We compare expected texts(userOrder) to what is being displayed on the order table (orderInfo)
        for (int i = 0; i < baseSteps.userOrder.size(); i++) {
            Assert.assertEquals(baseSteps.userOrder.get(i), orderInfo.get(i));
        }


    }


    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String text) {
        if ("List of All Orders".equals(text)) {
                Assert.assertNotNull(webOrdersHomePage.orderTable);
        }

    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String text) {
        webOrdersHomePage.messageAfterOrdersHaveBeenDeleted.isDisplayed();
        Assert.assertEquals(text, webOrdersHomePage.messageAfterOrdersHaveBeenDeleted.getText());
    }
}






