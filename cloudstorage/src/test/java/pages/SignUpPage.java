package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id="inputFirstName")
    private WebElement firstNamefield;

    @FindBy(id="inputLastName")
    private WebElement lastNamefield;

    @FindBy(id="inputUsername")
    private WebElement userNamefield;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="submit-btn")
    private WebElement submitField;


    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstname, String lastname, String username, String password)
    {
        this.firstNamefield.sendKeys(firstname);
        this.lastNamefield.sendKeys(lastname);
        this.userNamefield.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitField.click();

    }
}
