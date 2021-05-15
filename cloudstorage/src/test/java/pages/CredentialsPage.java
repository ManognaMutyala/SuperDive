package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id="addCredentialBtn")
    private WebElement addCredentials;

    @FindBy(id="credential-url")
    private WebElement credentialUrlField;

    @FindBy(id="credential-username")
    private WebElement credUsernameField;

    @FindBy(id="credential-password")
    private WebElement credPasswordField;

    @FindBy(id="savecred-btn")
    private WebElement saveCredBtn;

    @FindBy(id="edit-cred-btn")
    private WebElement editCredBtn;

    @FindBy(id="cred-url")
    private WebElement credUrl;

    @FindBy(id="delete-cred-btn")
    private WebElement deleteCredBtn;

    @FindBy(id="cred-pwd-field")
    private WebElement credPwdField;


    private JavascriptExecutor jE;

    public CredentialsPage(WebDriver driver){
        jE=(JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    public void addCredentials(String credentialUrl,String username,String password)
    {
        jE.executeScript("arguments[0].click();",navCredentialsTab);
        jE.executeScript("arguments[0].click()",addCredentials);
        jE.executeScript("arguments[0].value='"+credentialUrl+"';",credentialUrlField);
        jE.executeScript("arguments[0].value='"+username+"';",credUsernameField);
        jE.executeScript("arguments[0].value='"+password+"';",credPasswordField);
        jE.executeScript("arguments[0].click();",saveCredBtn);
        jE.executeScript("arguments[0].click();",navCredentialsTab);
    }

    public void editCredUrl(String url) throws InterruptedException {
        jE.executeScript("arguments[0].click();",navCredentialsTab);
        jE.executeScript("arguments[0].click();",editCredBtn);
        jE.executeScript("arguments[0].value='"+url+"';",credentialUrlField);
        Thread.sleep(5000);

    }

    public void saveCredChanges()
    {
        jE.executeScript("arguments[0].click();",saveCredBtn);
        jE.executeScript("arguments[0].click();",navCredentialsTab);
    }

    public String getPasswordFieldInModel(){
          return this.credPasswordField.getAttribute("value");
    }
    public void deleteCredentials()
    {
        jE.executeScript("arguments[0].click();",deleteCredBtn);
    }

    public String getCredentials()
    {
        return credUrl.getText();
    }

    public String getCredPassword()
    {
        return credPwdField.getText();
    }
}
