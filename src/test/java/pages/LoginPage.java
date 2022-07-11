package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (css = "div.login>input[name*=username]")
    public WebElement usernameField;

    @FindBy (css = "div.login>input[name*=password]")
    public WebElement passwordField;

    @FindBy (css = "div.login>[type=submit]")
    public WebElement loginButton;

    @FindBy (css = "span.error")
    public WebElement invalidLoginMessage;





}
