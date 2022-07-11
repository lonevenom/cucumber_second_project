package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class OrderPage {

    public OrderPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (css = "a[id*=InsertButton]")
    public WebElement processButton;

    @FindBy (css = "table select>option")
    public List<WebElement> orderItemDropDownList;
    //3 items (selects 3 items from product type drop down menu. MyMoney,FamilyAlbum,ScreenSaver)

    @FindBy (css = "table select")
    public WebElement orderItemDropDown;
    //selects 1, this is the menu drop down of the above elements.)


    //   =================   ADDRESS INFORMATION LOCATORS   =================
    @FindBy (css = "input[id*=txtQuantity]")
    public WebElement quantityInputBox;

    @FindBy (css = "td>:nth-child(5) input[name*=txtName]")
    public WebElement customerNameInputBox;

    @FindBy (css = "td>:nth-child(5) input[name*=TextBox2]")
    public WebElement streetInputBox;

    @FindBy (css = "td>:nth-child(5) input[name*=TextBox3]")
    public WebElement cityInputBox;

    @FindBy (css = "td>:nth-child(5) input[name*=TextBox4]")
    public WebElement stateInputBox;

    @FindBy (css = "td>:nth-child(5) input[name*=TextBox5]")
    public WebElement zipInputBox;

    //   =================   PAYMENT INFORMATION LOCATORS   =================

    @FindBy (css = "td>:nth-child(7) [id*=cardList_]")
    public List<WebElement> cardTypeOptionsRadioFields;
    //selects 3 Radio type fields for the 3 card types this application accepts (Visa,MasterCard,Amex)

    @FindBy (css = "td>:nth-child(7) [name*=TextBox6]")
    public WebElement cardNumberInputBox;

    @FindBy (css = "td>:nth-child(7) [name*=TextBox1]")
    public WebElement cardExpireDateInputBox;

    // =================   ORDER THAT WAS ENTERED   =================





}
