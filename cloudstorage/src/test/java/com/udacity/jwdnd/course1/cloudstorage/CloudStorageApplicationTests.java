package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pages.*;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private String baseURL;
	private SignUpPage signUpPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private NotesPage notesPage;
	private CredentialsPage credentialsPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL="http://localhost:" + this.port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {

			driver.quit();
		}
	}



	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignUpPageUnauth(){

		driver.get(baseURL+"/signup");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assertions.assertEquals("Sign Up",driver.getTitle());


	}

	//Test to validate unauthorized access to urls apart from signup and login
	@Test
	public void visitHomeUnAuth(){
		driver.get(baseURL+"/home");
		Assertions.assertEquals("Login",driver.getTitle());
		driver.get(baseURL+"/file/upload");
		Assertions.assertEquals("Login",driver.getTitle());

	}

	//Test to validate integration flow for login ,signup and home page
	@Test
	public void homePageIntegrationTest()
	{
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		driver.get(baseURL+"/signup");
		signUpPage=new SignUpPage(driver);
		signUpPage.signup(firstname,lastname,username,password);
		driver.get(baseURL + "/login");
		loginPage=new LoginPage(driver);
		loginPage.login(username,password);
		driver.get(baseURL+"/home");
		homePage=new HomePage(driver);
		Assertions.assertEquals("Home",driver.getTitle());
		homePage.logout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	//Test to check if notes is added and if it can be viewed,and  delete notes
	@Test
	public  void AddNotesIntegrationTest() throws InterruptedException {
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		String noteTitle="noteTitleTest";
		String noteDescription="note description";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseURL+"/signup");
		signUpPage=new SignUpPage(driver);
		signUpPage.signup(firstname,lastname,username,password);
		driver.get(baseURL + "/login");
		loginPage=new LoginPage(driver);
		loginPage.login(username,password);
		driver.get(baseURL+"/home");
		homePage=new HomePage(driver);
		Assertions.assertEquals("Home",driver.getTitle());
		notesPage=new NotesPage(driver);
		driver.findElement(By.id("nav-notes-tab")).click();
		notesPage.addNewNote(noteTitle,noteDescription);
		notesPage.navNotesTab();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editNoteBtn"))).click();
		Assertions.assertEquals(noteTitle,notesPage.getNoteTitle());
	//	wait.until(ExpectedConditions.elementToBeClickable(By.id("editNoteBtn"))).click();
		notesPage.deleteNote();
		homePage.logout();

	}

	//Test to edit the notes and delete notes
	@Test
	public void editNotesTest() throws InterruptedException {
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		String noteTitle="noteTitleTest";
		String noteDescription="note description";
		String editTitle="NewTitle";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseURL+"/signup");
		signUpPage=new SignUpPage(driver);
		signUpPage.signup(firstname,lastname,username,password);
		driver.get(baseURL + "/login");
		loginPage=new LoginPage(driver);
		loginPage.login(username,password);
		driver.get(baseURL+"/home");
		homePage=new HomePage(driver);
		notesPage=new NotesPage(driver);
		driver.findElement(By.id("nav-notes-tab")).click();
		notesPage.addNewNote(noteTitle,noteDescription);
		notesPage.navNotesTab();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editNoteBtn"))).click();
		notesPage.editNote(editTitle);
		notesPage.navNotesTab();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editNoteBtn"))).click();
		Assertions.assertEquals(editTitle,notesPage.getNoteTitle());
		notesPage.deleteNote();
	}

	//Test to add credentials and check if it is present, also check if password is encrypted
	@Test
	public void addCredTest() throws InterruptedException {
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		String credentialUrl="dummy.com";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseURL+"/signup");
		signUpPage=new SignUpPage(driver);
		signUpPage.signup(firstname,lastname,username,password);
		driver.get(baseURL + "/login");
		loginPage=new LoginPage(driver);
		loginPage.login(username,password);
		driver.get(baseURL+"/home");
		homePage=new HomePage(driver);
		credentialsPage=new CredentialsPage(driver);
		credentialsPage.addCredentials(credentialUrl,username,password);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-cred-btn"))).click();
		Assertions.assertEquals(credentialUrl,credentialsPage.getCredentials());
		Assertions.assertNotEquals(password,credentialsPage.getCredPassword());
		credentialsPage.deleteCredentials();
	}

	//Test to edit existing credentials
	@Test
	public void editCredTest() throws InterruptedException {
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		String credentialUrl="dummy.com";
		String newUrl="dummydummy.com";
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseURL+"/signup");
		signUpPage=new SignUpPage(driver);
		signUpPage.signup(firstname,lastname,username,password);
		driver.get(baseURL + "/login");
		loginPage=new LoginPage(driver);
		loginPage.login(username,password);
		driver.get(baseURL+"/home");
		homePage=new HomePage(driver);
		credentialsPage=new CredentialsPage(driver);
		credentialsPage.addCredentials(credentialUrl,username,password);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-cred-btn"))).click();
		credentialsPage.editCredUrl(newUrl);
		Assertions.assertEquals(password,credentialsPage.getPasswordFieldInModel());
		credentialsPage.saveCredChanges();
		credentialsPage.deleteCredentials();
	}

}
