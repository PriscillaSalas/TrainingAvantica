package avantica.tests;

import org.testng.annotations.*;

import avantica.generic.BasePage;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTests {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private BasePage seleniumUtility;

	@BeforeClass
	public void BeforeClass() {
		System.out.println("Executing class: RegisterTests...");
	}
	
	//El m�todo setUp setea variables iniciales como el driver a utilizar, el url y el tiempo que se va a usar como l�mite para declarar un timeout
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.out.println("Creating Driver...");
		driver = new ChromeDriver();
		baseUrl = "http://192.168.0.103:86";
		
		seleniumUtility = new BasePage(driver);
		seleniumUtility.goToSite(baseUrl + "/");
	}
	
	@BeforeTest
	public void BeforeTest() {
		System.out.println("Starting Execution...");
	}

	//Este es el m�todo que contiene los pasos del testcase
	@Test
	public void testLoginTc15() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_LoginLink"));
		seleniumUtility.waitForPageTitle("Login");
		assertEquals(seleniumUtility.verifyPageTitle("Login"), true); //Verifica que se encuentre en la p�gina de Login
		assertEquals(seleniumUtility.textCompare(By.cssSelector("label"), "User Name:"), true);
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_LoginConrol_UserName"))); //Verifica que el campo de texto Username exista
		assertTrue(seleniumUtility.isElementPresent(By.xpath("//table[@id='ctl00_Main_LoginConrol']/tbody/tr/td/table/tbody/tr[2]/td/label"))); //Verifica que el label Password exista
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_LoginConrol_Password"))); //Verifica que el campo de password exista
		assertTrue(seleniumUtility.isElementPresent(By.id("ctl00_Main_LoginConrol_LoginButton"))); //Verifica que el bot�n de login exista
		assertTrue(seleniumUtility.isElementPresent(By.cssSelector("h2.section"))); //Verifica la existencia del css h2.section
	}
	
	//Este es el m�todo que contiene los pasos del testcase
	@Test
	public void testLoginTc16() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_LoginLink")); //Hacer click al bot�n de Login
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_UserName"), "priscilla2491");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_Password"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_Password"), "PpSs135#");//ingresar contrase�a
		seleniumUtility.clickElement(By.id("ctl00_Main_LoginConrol_LoginButton"));//hacer click a bot�n de submit
		seleniumUtility.waitForPageTitle("Login");
		assertEquals(seleniumUtility.textCompare(By.id("nav_login"), "Welcome, priscilla2491 | Logout"), true);//Verificar que efectivamente se haya ingresado buscando el texto que corresponde a un login satisfactorio
		assertEquals(seleniumUtility.verifyPageTitle("Welcome"), true);//Verificar que efectivamente se haya ingresado buscando el texto que corresponde a un login satisfactorio
		seleniumUtility.clickElement(By.id("ctl00_LoginView_MemberLoginStatus"));//Hacer click en el link de logout
	}

	//Este es el m�todo que contiene los pasos del testcase
	@Test
	public void testLoginTc17() throws Exception {
		seleniumUtility.clickElement(By.id("ctl00_LoginView_LoginLink"));//Hacer click al bot�n de Login

		//Prueba para verificar que un usuario v�lido sin password ingresado, devuelve error
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_UserName"), "priscilla2491");
		seleniumUtility.clickElement(By.id("ctl00_Main_LoginConrol_LoginButton"));
		seleniumUtility.waitForPageTitle("Login");
		//assertTrue(isElementPresent(By.id("ctl00_Main_LoginConrol_PasswordRequired")));

		//Prueba para verificar que un usuario inv�lido con password inv�lido devuelva error y no se loguee el usuario
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_UserName"), "priscillaSalas");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_Password"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_Password"), "invalid");
		seleniumUtility.clickElement(By.id("ctl00_Main_LoginConrol_LoginButton"));
		seleniumUtility.waitForPageTitle("Login");
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_LoginConrol']/tbody/tr/td/table/tbody/tr[4]/td"), "Your login attempt was not successful. Please try again."), true);
		assertEquals(seleniumUtility.verifyPageTitle("Login"), true);

		//Prueba para verificar que un usuario v�lido con password inv�lido devuelva error y no se loguee el usuario
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_UserName"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_UserName"), "priscilla2491");
		seleniumUtility.clearFieldContent(By.id("ctl00_Main_LoginConrol_Password"));
		seleniumUtility.typeOnElement(By.id("ctl00_Main_LoginConrol_Password"), "invalid");
		seleniumUtility.clickElement(By.id("ctl00_Main_LoginConrol_LoginButton"));
		seleniumUtility.waitForPageTitle("Login");
		assertEquals(seleniumUtility.textCompare(By.xpath("//table[@id='ctl00_Main_LoginConrol']/tbody/tr/td/table/tbody/tr[4]/td"), "Your login attempt was not successful. Please try again."), true);
		assertEquals(seleniumUtility.verifyPageTitle("Login"), true);
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
	//Este m�todo restaura el escenario al estado previo a la ejecuci�n del testcase, y libera el driver
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

