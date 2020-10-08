package action;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.JsonParser;

import io.restassured.path.json.JsonPath;
import util.Config;

public class Assignment1_1_UI_Validation {

	WebDriver driver;

	String projPath = System.getProperty("user.dir");
	String result;
	JsonPath jsonPath = null;
	List<String> resultList = null;
	static String exprValue;
	float expr1, expr2;

	// String postUrl = "http://api.mathjs.org/v4/";

	// By table_Rows = By.cssSelector("div#divTrainsList > table tr");

	By post_label = By.cssSelector("#post");
	By post_Path = By.xpath("//h2[@id='post']/following-sibling::code");
	By post_expression_textarea = By.id("expr2");
	By post_evaluate_btn = By.id("postButton");

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", projPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://api.mathjs.org/");

	}

	@Test
	public void postExpressionData() throws InterruptedException {
		String postLabel = "", postPath = "", postRequestPath = "";

		postLabel = driver.findElement(post_label).getText();
		postPath = driver.findElement(post_Path).getText();
		postRequestPath = postLabel + " " + Config.postURL;

		if (postRequestPath.equals(postPath)) {
			driver.findElement(post_expression_textarea).click();
			driver.findElement(post_expression_textarea).clear();
			driver.findElement(post_expression_textarea).sendKeys(Config.postJsonExpressionData2);
			driver.findElement(post_evaluate_btn).click();
			Thread.sleep(2000);
			result = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
		}

		validateExpressionData();
	}

	public void validateExpressionData() throws InterruptedException {

		expr1 = (float) (1.2 * (2 + 4.5));
		expr2 = (float) expr1 / 2;
		System.out.println(expr1 + " ==> " + expr2);

		jsonPath = new JsonPath(result);

		resultList = jsonPath.getList("result");

		System.out.println("Total no. of expression : " + resultList.size());
		System.out.println(
				"List Value are " + jsonPath.getString("result[0]") + " ==> " + jsonPath.getString("result[1]"));
		Assert.assertTrue("The json expression result 1 is passed.",
				jsonPath.getString("result[0]").equalsIgnoreCase(expr1 + ""));
		Assert.assertTrue("The json expression result 2 is passed.",
				jsonPath.getString("result[1]").equalsIgnoreCase(expr2 + ""));

	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}