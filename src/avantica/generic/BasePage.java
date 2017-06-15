package avantica.generic;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static WebDriver driver;
	public WebElement element;
	public WebDriverWait wait;
	
	public BasePage(WebDriver baseDriver)
	{
		driver = baseDriver;
		wait = new WebDriverWait(driver, 40);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public void goToSite(String url)
	{
		try
		{
			driver.get(url);
		}
		catch (Exception e)
		{
			System.out.println("Page not found or invalid route.\n");
			System.out.println(e);
		}
	}
	
	private WebElement elementFinder(By locator)
	{
		WebElement element;
		
		element = driver.findElement(locator);
		
		return element;
		
	}
	
	public void typeOnElement(By locator, String textToType)
	{
		try
		{
			this.elementFinder(locator).sendKeys(textToType);
		}
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public void clickElement(By locator)
	{
		try
		{
			this.elementFinder(locator).click();
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public void clearFieldContent(By locator)
	{

		try
		{
			this.elementFinder(locator).clear();
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public boolean textCompare(By locator, String textToFind)
	{	
		try
		{
			if (textToFind.equals(this.elementFinder(locator).getText()))
				return true;
			else
				return false;
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
			
			return false;
		}
		
		
	}
	
	//Declaración de método que valida si un elemento se encuentra presente
	public boolean isElementPresent(By locator) {
		try {
			this.elementFinder(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean verifyPageTitle(String title)
	{
		try
		{
			if (driver.getTitle().equals(title))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void waitForElementVisible(By locator)
	{
		try
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public void waitForElementEnable(By locator)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public void waitForElementNotVisible(By locator)
	{
		try
		{
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
		
		catch (Exception e)
		{
			System.out.println("Element not found or error returned.\n");
			System.out.println(e);
		}
	}
	
	public void waitForPageTitle(String title)
	{
		if (!title.isEmpty())
		{
			try
			{
				wait.until(ExpectedConditions.titleContains(title));
			}
			catch (Exception e)
			{
				System.out.println("Element not found or error returned.\n");
				System.out.println(e);
			}
		}
	}

}
