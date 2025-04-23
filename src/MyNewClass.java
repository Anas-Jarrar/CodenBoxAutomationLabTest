import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class MyNewClass {

	String TheWebsite = "https://codenboxautomationlab.com/practice/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(TheWebsite);

	}

	@Test(priority = 1)
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

	@Test(priority = 2)
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

	@Test(priority = 3)
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

	@Test(priority = 4)
	// This test to make sure every checkbox Buttons are worked
	public void CheckboxTest() {

		WebElement Checkboxbutton1 = driver.findElement(By.xpath("//input[@name='checkBoxOption1']"));
		WebElement Checkboxbutton2 = driver.findElement(By.xpath("//input[@name='checkBoxOption2']"));
		WebElement Checkboxbutton3 = driver.findElement(By.xpath("//input[@name='checkBoxOption3']"));
		Checkboxbutton1.click();
		Checkboxbutton2.click();
		Checkboxbutton3.click();

		Assert.assertTrue(Checkboxbutton1.isSelected(), "The Checkboxbutton1 does not select");
		Assert.assertTrue(Checkboxbutton2.isSelected(), "The Checkboxbutton2 does not select");
		Assert.assertTrue(Checkboxbutton3.isSelected(), "The Checkboxbutton3 does not select");
	}
	
	
	
	@Test(priority = 5)
	//This Test for Select CheckBox Button based on his name
	public void SelectSecondButtonOfCheckbox() {
	    List<WebElement> options = driver.findElements(By.xpath("//input[@type='checkbox']"));

	    for (WebElement option : options) {
	        String value = option.getAttribute("value");  // values are like: option1, option2, etc.
	        if (value.equalsIgnoreCase("option2")) {
	            option.click();
	            Assert.assertFalse(option.isSelected(), "Option2 checkbox should not be selected");
	            break;
	        }
	    }
	}

	
	
	
	

}
