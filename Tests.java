
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LoginStep1Test {
	private WebDriver driver;
	JavascriptExecutor js;
	private String inputFilePath = "src\\input.json";
	private String logFilePath = "src\\log.txt";
	private Map<String, String> paramsMap;
	private PrintWriter pr;
	File logFile;
	
	public LoginStep1Test() throws IOException {

		paramsMap = new ObjectMapper().readValue(
				new Scanner(new File(inputFilePath), "UTF-8").useDelimiter(UUID.randomUUID().toString()).next(),
				Map.class);
		
		logFile = new File(logFilePath);
		if(!logFile.exists())
			logFile.createNewFile();
		
		pr = new PrintWriter(new FileWriter(logFile, true));
	}

	@Before
	public void setUp() {
		System.setProperty(paramsMap.get("webDriverName"), paramsMap.get("webDriverPath"));
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
	}

	@After
	public void tearDown() {
		pr.close();
		driver.quit();
	}

	@Test
	public void loginStep1() throws Exception {
		String param = "";
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1040, 824));
			{
				WebElement element = driver.findElement(By.linkText("Contact"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			driver.findElement(By.id("login2")).click();
			{
				WebElement element = driver.findElement(By.id("login2"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			{
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}
			driver.findElement(By.cssSelector("#logInModal form")).click();
			param = "loginStep1.userName";
			driver.findElement(By.id("loginusername")).sendKeys(paramsMap.get("loginStep1.userName"));
			driver.findElement(By.id("loginpassword")).click();
			param = "loginStep1.pass";
			driver.findElement(By.id("loginpassword")).sendKeys(paramsMap.get("loginStep1.pass"));
			driver.findElement(By.cssSelector("#logInModal .btn-primary")).click();
		} catch (java.lang.IllegalArgumentException e) {
			printLog("\"loginStep1\" test Failed: parameter \""+param+"\" not in input file.");
			throw e;
		} catch (Exception e) {
			printLog("\"loginStep1\" test Failed: " + e.toString());
			throw e;
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert.getText().equals("User does not exist.") || alert.getText().equals("Wrong password.")) {
				printLog("\"loginStep1\" test Failed");
			}
			alert.accept();

		} catch (org.openqa.selenium.TimeoutException e) {
			printLog("\"loginStep1\" test Passed.");
		} catch (Exception e) {
			printLog("\"loginStep1\" test Failed: " + e.toString());
			throw e;
		}

	}

	@Test
	public void loginStep4() {
		String param = "";
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1552, 840));
			driver.findElement(By.id("login2")).click();
			{
				WebElement element = driver.findElement(By.id("login2"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			{
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}
			driver.findElement(By.cssSelector("#logInModal form")).click();
			driver.findElement(By.id("loginusername")).click();
			param = "loginStep4.userName";
			driver.findElement(By.id("loginusername")).sendKeys(paramsMap.get("loginStep4.userName"));
			driver.findElement(By.id("loginpassword")).click();
			param = "loginStep4.pass";
			driver.findElement(By.id("loginpassword")).sendKeys(paramsMap.get("loginStep4.pass"));
			driver.findElement(By.id("loginpassword")).sendKeys(Keys.ENTER);
			driver.findElement(By.cssSelector("#logInModal .btn-primary")).click();
		}
		catch(java.lang.IllegalArgumentException e) {
			printLog("\"loginStep4\" test Failed: parameter\" "+param+"\" not in input file.");
			throw e;
		}
		catch(Exception e) {
			printLog("\"loginStep4\" test Failed: "+ e.toString());
			throw e;
		}
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			assertEquals(alert.getText(), "User does not exist.");
			alert.accept();
		}
		catch(org.openqa.selenium.TimeoutException e) {
			printLog("\"loginStep4\" test Failed: account is logged in.");
			throw e;
		}
		catch(Exception e) {
			printLog("\"loginStep4\" test Failed: "+ e.toString());
			throw e;
		}
		
		printLog("\"loginStep4\" test Passed");
	}

	@Test
	public void contactStep3() throws InterruptedException {
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1552, 840));
			driver.findElement(By.linkText("Contact")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("#exampleModal .btn-secondary")).click();
			Thread.sleep(500);	
		}catch(Exception e) {
			printLog("\"contactStep3\" test Failed: "+ e.toString());
			throw e;
		}
		printLog("\"contactStep3\" test Passed");
	}

	@Test
	public void contactStep1() throws InterruptedException {
		String param = "";
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1496, 835));
			driver.findElement(By.linkText("Contact")).click();
			{
				WebElement element = driver.findElement(By.linkText("Contact"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			{
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}
			driver.findElement(By.id("recipient-email")).click();
			param = "contactStep1.recipient-email";
			driver.findElement(By.id("recipient-email")).sendKeys(paramsMap.get("contactStep1.recipient-email"));
			driver.findElement(By.id("recipient-name")).click();
			param = "contactStep1.recipient-name";
			driver.findElement(By.id("recipient-name")).sendKeys(paramsMap.get("contactStep1.recipient-name"));
			driver.findElement(By.id("message-text")).click();
			param = "contactStep1.message-text";
			driver.findElement(By.id("message-text")).sendKeys(paramsMap.get("contactStep1.message-text"));
			driver.findElement(By.cssSelector("#exampleModal .btn-primary")).click();
		} catch (java.lang.IllegalArgumentException e) {
			printLog("\"contactStep1\" test Failed: parameter \""+param+"\" not in input file.");
			throw e;
		} catch (Exception e) {
			printLog("\"contactStep1\" test Failed: " + e.toString());
			throw e;
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			assertEquals(alert.getText(), "Thanks for the message!!");
			alert.accept();
		} catch (org.openqa.selenium.TimeoutException e) {
			printLog("\"contactStep1\" test Failed: \"Thanks for the message!!\" didn't popped up.");
		} catch (Exception e) {
			printLog("\"contactStep1\" test Failed: " + e.toString());
			throw e;
		}
		printLog("\"contactStep1\" test Passed.");
	}

	@Test
	public void orderStep1() throws InterruptedException {
		String param = "";
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1552, 840));
			driver.findElement(By.id("cartur")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector(".btn-success")).click();
			Thread.sleep(500);
			driver.findElement(By.id("name")).click();
			param = "orderStep1.name";
			driver.findElement(By.id("name")).sendKeys(paramsMap.get("orderStep1.name"));
			driver.findElement(By.id("country")).click();
			param = "orderStep1.country";
			driver.findElement(By.id("country")).sendKeys(paramsMap.get("orderStep1.country"));
			driver.findElement(By.id("city")).click();
			param = "orderStep1.city";
			driver.findElement(By.id("city")).sendKeys(paramsMap.get("orderStep1.city"));
			driver.findElement(By.id("card")).click();
			param = "orderStep1.card";
			driver.findElement(By.id("card")).sendKeys(paramsMap.get("orderStep1.card"));
			driver.findElement(By.id("month")).click();
			param = "orderStep1.month";
			driver.findElement(By.id("month")).sendKeys(paramsMap.get("orderStep1.month"));
			driver.findElement(By.id("year")).click();
			param = "orderStep1.year";
			driver.findElement(By.id("year")).sendKeys(paramsMap.get("orderStep1.year"));
			driver.findElement(By.cssSelector("#orderModal .btn-primary")).click();
			driver.findElement(By.cssSelector(".confirm")).click();
		} catch (java.lang.IllegalArgumentException e) {
			printLog("\"orderStep1\" test Failed: parameter \""+param+"\" not in input file.");
			throw e;
		} catch (Exception e) {
			printLog("\"orderStep1\" test Failed: " + e.toString());
			throw e;
		}
		printLog("\"orderStep1\" test Passed.");
	}

	@Test
	public void orderStep3() throws InterruptedException {
		try {
			driver.get(paramsMap.get("url"));
			driver.manage().window().setSize(new Dimension(1552, 840));
			driver.findElement(By.id("cartur")).click();
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(".btn-success")).click();
			Thread.sleep(1500);
	
			driver.findElement(By.id("name")).click();
			driver.findElement(By.cssSelector("#orderModal .btn-primary")).click();
			Thread.sleep(500);
			} catch (Exception e) {
			printLog("\"orderStep3\" test Failed: " + e.toString());
			throw e;
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			assertEquals(alert.getText(), "Please fill out Name and Creditcard.");
			alert.accept();
		} catch (org.openqa.selenium.TimeoutException e) {
			printLog("\"orderStep3\" test Failed: \"Please fill out Name and Creditcard\" didn't popped up.");
		} catch (Exception e) {
			printLog("\"orderStep3\" test Failed: " + e.toString());
			throw e;
		}
		printLog("\"orderStep3\" test Passed.");
	}

	@Test
	public void orderSubStepAddToCart() throws InterruptedException {
		try {
			driver.get(paramsMap.get("url"));
		    driver.manage().window().setSize(new Dimension(1496, 835));
		    Thread.sleep(1500);
		    driver.findElement(By.linkText(paramsMap.get("orderSubStepAddToCart.productName"))).click();
		    Thread.sleep(500);
		    driver.findElement(By.linkText("Add to cart")).click();
		}catch (java.lang.IllegalArgumentException e) {
			printLog("\"orderSubStepAddToCart\" test Failed: parameter \"orderSubStepAddToCart.productName\" not in input file.");
			throw e;
		} catch (Exception e) {
			printLog("\"orderSubStepAddToCart\" test Failed: " + e.toString());
			throw e;
		}
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			assertEquals(alert.getText(), "Product added");
			alert.accept();
		} catch (org.openqa.selenium.TimeoutException e) {
			printLog("\"orderSubStepAddToCart\" test Failed: \"Product added\" didn't popped up.");
		} catch (Exception e) {
			printLog("\"orderSubStepAddToCart\" test Failed: " + e.toString());
			throw e;
		}
		printLog("\"orderSubStepAddToCart\" test Passed.");
	}

	private void printLog(String s) {
		System.out.println(s);	
		pr.append(s+"\ntest time: "+new Date()+"\n");
	}
}
