package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pages.HomePage;
import pages.LoginPage;
import pages.NotesPage;
import pages.SignUpPage;

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

	@Test
	public  void AddNotesIntegrationTest() throws InterruptedException {
		String username="test";
		String password="test";
		String firstname="test";
		String lastname="test";
		String noteTitle="noteTitleTest";
		String noteDescription="note description";
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
		//notesPage.navNotesTab();
		driver.findElement(By.id("nav-notes-tab")).click();
		//Thread.sleep(50000);
	//	Assertions.assertEquals("add-note-btn",driver.findElement(By.id("add-note-btn")).getText());
		notesPage.addNewNote(noteTitle,noteDescription);



	}


}
