import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import netscape.javascript.JSException;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MyNewClass {

	String TheWebsite = "https://codenboxautomationlab.com/practice/";
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(TheWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));// wait maximum 3 seconds to find element
	}

	@Test(priority = 1, enabled = false)
	public void RadioButton() throws InterruptedException {
		WebElement Radio1 = driver.findElement(By.cssSelector(".radioButton"));
		WebElement Radio2 = driver.findElement(By.xpath("//input[@value='radio2']"));
		WebElement Radio3 = driver.findElement(By.xpath("//input[@value='radio3']"));
		Radio1.click();
		Radio2.click();
		Radio3.click();

		Assert.assertFalse(Radio1.isSelected(), "The radio1 Button should not be selected");
		Assert.assertFalse(Radio2.isSelected(), "The radio2 Button should not be selected");
		Assert.assertTrue(Radio3.isSelected(), "The radio3 Button should be selected");

		System.out.println("Radio1 selected:" + Radio1.isSelected());
		System.out.println("Radio2 selected:" + Radio2.isSelected());
		System.out.println("Radio3 selected:" + Radio3.isSelected());

	}

	@Test(priority = 2, enabled = false)
	// This Test search between array of countries and seen if exist
	public void DynamicDropDown() throws InterruptedException {

		String[] countries = { "Jordan", "Japan" };
		WebElement DropDown = driver.findElement(By.id("autocomplete"));
		for (String country : countries) {

			DropDown.clear();
			Thread.sleep(1000);
			DropDown.sendKeys(country.toLowerCase());

			Thread.sleep(2000);
			List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item-wrapper"));
			boolean found = false;
			for (int i = 0; i < options.size(); i++) {
				if (options.get(i).getText().contentEquals(country)) {
					options.get(i).click();
					found = true;
					break;
				}
			}
			Assert.assertTrue(found, "The country does not exist");
		}
	}

	@Test(priority = 3, enabled = false)
	// Print The Dynamic Drop down list based on array of suggested letters
	public void PrintDynamicDropDown() throws InterruptedException {

		String[] countries = { "Jo", "Ja", "sy" };
		WebElement DropDown = driver.findElement(By.id("autocomplete"));
		for (String country : countries) {

			DropDown.clear();
			DropDown.sendKeys(country.toLowerCase());

			Thread.sleep(2000);
			List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item-wrapper"));
			for (int i = 0; i < options.size(); i++) {
				System.out.println(options.get(i).getText());
			}
		}
	}

	@Test(priority = 4, enabled = false)
	public void AutoCompleteTest() throws InterruptedException {

		String[] countries = { "jo", "ja", "sy" };
		WebElement AutoComplete = driver.findElement(By.id("autocomplete"));
		int index = rand.nextInt(countries.length);
		System.out.println(index);
		AutoComplete.sendKeys(countries[index]);
		Thread.sleep(1000);
		AutoComplete.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

	}

	@Test(priority = 5, enabled = false)
	public void StaticDropDown() throws InterruptedException {

		WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
		List<WebElement> options = dropdown.findElements(By.tagName("option"));

		for (WebElement option : options) {
			option.click();
			System.out.println(option.getText());

		}
	}

	@Test(priority = 6, enabled = false)
	public void StaticList() {
		WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
		Select Myselect = new Select(dropdown);
//		Myselect.selectByVisibleText("Appium");
//		Myselect.selectByIndex(1);
		Myselect.selectByValue("option3");
		System.out.println(dropdown.findElements(By.tagName("option")).get(1).getText());

	}

	@Test(priority = 7, enabled = false)
	public void CheckboxTest1() {
		WebElement Checkbox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> options = Checkbox.findElements(By.tagName("input"));
		int index = rand.nextInt(options.size());
		options.get(index).click();
	}

	@Test(priority = 8, enabled = false)
	// This test to make sure every checkbox Buttons are worked
	public void CheckboxTest() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,200)");
		WebElement Checkbox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> options = Checkbox.findElements(By.tagName("input"));
		for (int i = 0; i < options.size(); i++) {
			options.get(i).click();
		}
		WebElement Checkboxbutton1 = driver.findElement(By.xpath("//input[@name='checkBoxOption1']"));
		WebElement Checkboxbutton2 = driver.findElement(By.xpath("//input[@name='checkBoxOption2']"));
		WebElement Checkboxbutton3 = driver.findElement(By.xpath("//input[@name='checkBoxOption3']"));

		Assert.assertTrue(Checkboxbutton1.isSelected(), "The Checkboxbutton1 does not select");
		Assert.assertTrue(Checkboxbutton2.isSelected(), "The Checkboxbutton2 does not select");
		Assert.assertTrue(Checkboxbutton3.isSelected(), "The Checkboxbutton3 does not select");
	}

	@Test(priority = 9, enabled = false)
	// This Test for Select CheckBox Button based on his name
	public void SelectSecondButtonOfCheckbox() {
		List<WebElement> options = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (WebElement option : options) {
			String value = option.getAttribute("value");
			if (value.equalsIgnoreCase("option2")) {
				option.click();
				Assert.assertFalse(option.isSelected(), "Option2 checkbox should not be selected");
				break;
			}
		}
	}

	@Test(priority = 10, enabled = false)
	public void OpenNewWindow() throws InterruptedException {

		WebElement WindowButton = driver.findElement(By.id("openwindow"));

		WindowButton.click();

		Set<String> SwitchWindow = driver.getWindowHandles();
		List<String> WidowsList = new ArrayList<>(SwitchWindow);
		driver.switchTo().window(WidowsList.get(1));
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(WidowsList.get(0));
		Thread.sleep(1000);
		System.out.println(driver.getTitle());

	}

	@Test(priority = 11, enabled = false)
	public void OpenNewTab() throws InterruptedException {

		WebElement TabButton = driver.findElement(By.id("opentab"));

		TabButton.click();

		Set<String> SwitchTab = driver.getWindowHandles();
		List<String> TapList = new ArrayList<>(SwitchTab);
		driver.switchTo().window(TapList.get(1));
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(TapList.get(0));
		Thread.sleep(1000);
		System.out.println(driver.getTitle());

	}

	@Test(priority = 12, enabled = false)
	public void AlertTest() throws InterruptedException {

		WebElement AlertButton = driver.findElement(By.id("name"));
		AlertButton.sendKeys("Anas Jarrar");
		WebElement Alert = driver.findElement(By.id("alertbtn"));
		Alert.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

	}

	@Test(priority = 13, enabled = false)
	public void ConfirmTest() throws InterruptedException {

		WebElement InputFieldForName = driver.findElement(By.id("name"));
		InputFieldForName.sendKeys("Anas Jarrar");
		WebElement confirm = driver.findElement(By.id("confirmbtn"));
		confirm.click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();

	}

	@Test(priority = 14, enabled = false)
	public void PrintTableData() {

		WebElement Table = driver.findElement(By.id("product"));

		List<WebElement> TableData = Table.findElements(By.tagName("tr"));
		int RandomIndex = rand.nextInt(TableData.size());
		System.out.println(TableData.get(RandomIndex).getText());

	}

	@Test(priority = 15, enabled = false)
	public void printTableData() {

		WebElement Table = driver.findElement(By.id("product"));

		List<WebElement> TableData = Table.findElements(By.tagName("th"));
		int RandomIndex = rand.nextInt(TableData.size());
//		System.out.println(TableData.get(RandomIndex).getText());
		for (int i = 0; i < TableData.size(); i++) {

			System.out.println(TableData.get(i).getText());
		}

	}

	@Test(priority = 16, enabled = false)
	public void PrintPriceFromTable() {

		WebElement Table = driver.findElement(By.id("product"));
		List<WebElement> TableData = Table.findElements(By.tagName("td"));
		for (int i = 2; i < TableData.size(); i = i + 3) {

			System.out.println(TableData.get(i).getText());
		}

	}

	@Test(priority = 17, enabled = false)
	public void ElementDisplayedTest() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1500)");
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		WebElement TextField = driver.findElement(By.id("displayed-text"));
		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		Thread.sleep(2000);
		ShowButton.click();
		Thread.sleep(2000);
		TextField.sendKeys("Anas Jarrar");
		Thread.sleep(2000);
		HideButton.click();

	}

	@Test(priority = 18, enabled = false)
	public void EnableDisableTest() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1700)");
		WebElement EnableButton = driver.findElement(By.id("enabled-button"));
		WebElement TextField = driver.findElement(By.id("enabled-example-input"));
		WebElement DisableButton = driver.findElement(By.id("disabled-button"));
		Thread.sleep(2000);
		EnableButton.click();
		Thread.sleep(2000);
		TextField.sendKeys("Anas Jarrar");
		Thread.sleep(2000);
		DisableButton.click();
		Thread.sleep(2000);

	}

	@Test(priority = 19, enabled = false)
	public void MouseHovering() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1900)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement MouseHoverButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mousehover")));
		Actions action = new Actions(driver);
		action.moveToElement(MouseHoverButton).perform();
		WebElement TopButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Top")));
		TopButton.click();
		js.executeScript("window.scrollTo(0,1900)");
		action.moveToElement(MouseHoverButton).click().perform();
		WebElement RelodButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reload")));
		RelodButton.click();

	}

	@Test(priority = 20)
	public void BokingCalenderNewTapTest() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,2000)");
		Thread.sleep(2000);
		WebElement BookingButton =driver.findElement(By.linkText("Booking Calendar"));
		BookingButton.click();
		Set<String> handel = driver.getWindowHandles();
		List<String> SwitchTap = new ArrayList(handel);
		driver.switchTo().window(SwitchTap.get(1));
		Thread.sleep(2000);
		WebElement calender =driver.findElement(By.cssSelector(".datepick.wpbc_calendar"));

		List<WebElement> TableOfDays = driver.findElements(By.tagName("td"));
		for (int i = 0; i < TableOfDays.size(); i++) {

			System.out.println(TableOfDays.get(i).getText());

		}

	}

}
