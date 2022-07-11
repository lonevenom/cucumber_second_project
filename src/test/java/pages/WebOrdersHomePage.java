package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class WebOrdersHomePage {

    public WebOrdersHomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (css = ".menu li")
    public List<WebElement> leftSideDropDownMenu; //3 items (drop down items: all orders, all prod., order)

    @FindBy (css = "a[id*=CheckAll]")
    public WebElement checkAllButton;

    @FindBy (css = "a[id*=UncheckAll]")
    public WebElement uncheckAllButton;

    @FindBy (css = ".btnDeleteSelected")
    public WebElement deleteSelectedButton;

    @FindBy (css = ".SampleTable tr>td:first-child")
    public List<WebElement> checkBoxes;
    //8 items (contains all 8 check boxes - I want to loop through ea. one to check if checked/unchecked)

    @FindBy (css = "table[class='SampleTable'] tr:nth-child(2)")
    public WebElement firstOrderInTable;

    @FindBy (css = "table[class='SampleTable']")
    public WebElement orderTable;

    @FindBy (css = "table[class='SampleTable'] tr:nth-child(2)>td")
    public List<WebElement> firstRowInfo;

    @FindBy (css = ".content>div[id*=orderMessage]")
    public WebElement messageAfterOrdersHaveBeenDeleted;

    public void clickOnMenuLink(String MenuLink){
        for(WebElement link : leftSideDropDownMenu){
            if(link.getText().equals(MenuLink)){
                link.click();
                break;
            }
        }
    }






}
