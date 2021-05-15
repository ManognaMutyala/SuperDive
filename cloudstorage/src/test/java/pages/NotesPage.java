package pages;

import org.mockito.internal.matchers.Not;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="add-note-btn")
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

}
