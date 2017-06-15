package avantica.tests;

import org.testng.annotations.*;

import avantica.generic.BasePage;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegisterTests {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private BasePage seleniumUtility;

	@BeforeClass
	public void BeforeClass() {
		System.out.println("Executing class: RegisterTests...");
	}
	
	//El método setUp setea variables iniciales como el driver a utilizar, el url y el tiempo que se va a usar como límite para declarar un timeout
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.out.println("Creating Driver...");
		//System.setProperty("webdriver.gecko.driver", "/Users/it/Documents/java_projects/geckodriver");
		driver = new ChromeDriver();
		baseUrl = "http://192.168.0.103:86";
		
		seleniumUtility = new BasePage(driver);
		seleniumUtility.goToSite(baseUrl + "/");
	}
	
	@BeforeTest
	public void BeforeTest() {
		System.out.println("Starting Execution...");
	}
	
	

	/*
	 *Verify the "Register" page is displayed correctly when clicking on "Register" link
	 */
	@Test
	public void testRegisterTc11() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_RegisterLink"));
		seleniumUtility.waitForPageTitle("Register");
		assertEquals(seleniumUtility.textCompare(By.cssSelector("h2.section"), "Register"), true);
		assertEquals(seleniumUtility.textCompare(By.cssSelector("h3"), "Registration"), true);
		assertEquals(seleniumUtility.textCompare(By.cssSelector("p"), "Enter the information of the user"), true);
		assertEquals(seleniumUtility.textCompare(By.cssSelector("legend"), "First Name:"), true);
		
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[2]"), "Last Name:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[3]"), "Email:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[4]"), "User Name:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[5]"), "Password:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[6]"), "Confirm Password:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[7]"), "Security Question:"), true);
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_CreateUserWizardControl']/tbody/tr/td/table/tbody/tr/td/legend[8]"), "Security Answer:"), true);
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer")));
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton")));
		assertEquals(seleniumUtility.verifyPageTitle("Register"), true);
	}
	
	/*
	 *Verify all the mandatory messages are displayed when leaving empty all the mandatory fields and clicking on "Submit" button
	 */
	@Test
	public void testRegisterTc12() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_RegisterLink"));
		seleniumUtility.waitForPageTitle("Register");
		seleniumUtility.clickElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton"));
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstNameRequired"), "First name is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastNameRequired"), "Last name is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_EmailRequired"), "Email is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserNameRequired"), "User name is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordRequired"), "Password is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPasswordRequired"), "Confirm password is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_QuestionRequired"), "Security question is required."), true);
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_AnswerRequired"), "Security answer is required."), true);
	}
	
	/*
	 *Verify an error message is displayed when creating an user with an user name that is already taken 
	 */
	@Test
	public void testRegisterTc13() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_RegisterLink"));
		seleniumUtility.waitForPageTitle("Register");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName"),"Priscilla");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName"),"Salas");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email"),"priscilla.salas@avantica.net");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName"),"priscilla2491");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password"),"PpSs135#");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword"),"PpSs135#");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question"),"Test");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer"),"Test");
		seleniumUtility.clickElement(By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton"));
		
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_InfoLabel"), "User name already exists. Please enter a different user name."), true);
		assertEquals(seleniumUtility.verifyPageTitle("Register"), true);
	}
	
	/*
	 *Verify an error message is displayed when sending a different password on 'Password' and 'Confirm Password' fields 
	 */
	@Test
	public void testRegisterTc14() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_RegisterLink"));
		seleniumUtility.waitForPageTitle("Register");
		assertEquals(seleniumUtility.textCompare(By.cssSelector("h2.section"), "Register"), true);
		
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName"), "Priscilla");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName"), "Salas");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email"), "priscilla.salas@avantica.net");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName"), "priscilla2491");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password"), "PpSs135#");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword"), "Invalid");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question"), "foobar");
		seleniumUtility.waitForElementVisible(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare"));
		
		assertEquals(seleniumUtility.textCompare(By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare"), "The password and confirmation password must match."), true);
		assertEquals(seleniumUtility.verifyPageTitle("Register"), true);
	}
	
	@AfterTest
	public void testCompletion() {
		System.out.println("Completing Execution...");
	}
	
	@AfterMethod
	public void tearDownEach() {
		System.out.println("Deleting Driver...");
		
		seleniumUtility.closeBrowser();
		driver.quit();
	}
	
	@AfterClass(alwaysRun = true)
	//Este método restaura el escenario al estado previo a la ejecución del testcase, y libera el driver
	public void tearDown() throws Exception {
		System.out.println("Completing execution of class: RegisterTests...");
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
