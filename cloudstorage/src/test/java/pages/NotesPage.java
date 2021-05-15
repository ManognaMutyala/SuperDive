package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesPage {

    private JavascriptExecutor jE;
    private Wait wait;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(xpath = "//button[@id = 'btnAddNewNote']")
    private WebElement btnAddNewNote;

    @FindBy(id = "note-title")
    private WebElement noteTitleBtn;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "editNoteBtn")
    private WebElement editNoteBtn;

    @FindBy(id = "deleteNoteBtn")
    private WebElement deleteNoteBtn;

    @FindBy(id = "noteTitleId")
    private WebElement noteTitleElement;


    public NotesPage(WebDriver driver) {
        jE = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void navNotesTab() throws InterruptedException {
        jE.executeScript("arguments[0].click();", notesTab);
    }

    public void addNewNote(String title, String description) throws InterruptedException {
        jE.executeScript("arguments[0].click();", btnAddNewNote);
        jE.executeScript("arguments[0].value='" + title + "';", noteTitleBtn);
        jE.executeScript("arguments[0].value='" + description + "';", noteDescription);
        jE.executeScript("arguments[0].click();", noteSubmit);
    }

    public void editNote(String title) {
        jE.executeScript("arguments[0].click();", editNoteBtn);
        jE.executeScript("arguments[0].value='" + title + "';", noteTitleBtn);
        jE.executeScript("arguments[0].click();", noteSubmit);

    }

    public void deleteNote() {
        jE.executeScript("arguments[0].click();", deleteNoteBtn);
    }

    public String getNoteTitle() {
        return noteTitleElement.getText();
    }


}


/*
public class NotesPage {

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="btnAddNewNote")
    private WebElement addNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitleBtn;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="noteSubmit")
    private WebElement noteSubmit;

    public NotesPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void navNotesTab()
    {
        this.notesTab.click();
    }

    public void addNewNote(String title,String description)
    {
        //  this.notesTab.click();
        this.addNoteButton.click();
        this.noteTitleBtn.sendKeys(title);
        this.noteDescription.sendKeys(description);
        this.noteSubmit.click();

    }

}*/