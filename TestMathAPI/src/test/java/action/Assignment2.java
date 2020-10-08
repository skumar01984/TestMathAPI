package action;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment2 {

	WebDriver driver;

	String projPath = System.getProperty("user.dir");
	
	By table_Rows = By.cssSelector("div#divTrainsList > table tr");

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", projPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://erail.in/trains-between-stations/ghaziabad-GZB/new-delhi-NDLS");

	}

	@Test
	public void findRowIdCount() {

		try {

			// 1 Way - Selector
			List<WebElement> tableRows = driver.findElements(table_Rows);

			// 2 Way

			/*
			 * WebElement tableList = driver.findElement(By.xpath(
			 * "//*[@id='divTrainsList']/table[1]/tbody")); List<WebElement>
			 * tableRow = tableList.findElements(By.tagName("tr"));
			 */

			// 3 Way
			/*
			 * WebElement tableList = driver.findElement(By.xpath(
			 * "//*[@id='divTrainsList']/table[1]/tbody"));
			 * 
			 * 
			 * List<WebElement> tableRow = new WebDriverWait(driver, 10)
			 * .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
			 * tableList, By.tagName("tr")));
			 */

			int counter = 0;
			for (int i = 0; i < (tableRows.size() - 1); i++) {

				WebElement row = tableRows.get(i);

				// To check attribute "data-id" value "2" occurrence in the "tr"
				// tag
				String id = row.getAttribute("data-id");
				if (id != null) {
					if (row.getAttribute("data-id").startsWith("2")) {
						// Note: Attribute data-id is number string. To
						// differentiate M
						// value in data-id, I have used hype and then counter
						// To represent the data on the console
						System.out.print("TagName : " + row.getTagName() + "(id=");
						System.out.println(row.getAttribute("data-id") + "-" + (counter + 1) + ")");

						counter++;
					}
				} else {
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		driver.close();
	}

}
