package com.swiggy.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RootPageSteps {

	WebDriver driver = null;
	String actualTitle = null;
	WebElement signUp = null;
	WebElement login = null;
	WebElement location = null;
	WebElement findFood = null;
	String actualPlaceHolderText = null;
	String findButtonText = null;
	List<WebElement> cityList;
	List<String> actualCityNames = new ArrayList<>();
	WebElement locationDropDown;

	@Before
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@Given("user is on root page of swiggy application")
	public void user_is_on_root_page_of_swiggy_application() {
		driver.get("https://swiggy.com");
	}

	@When("user tries to see the title of the page")
	public void user_tries_to_see_the_title_of_the_page() {
		actualTitle = driver.getTitle();
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		Assert.assertTrue(actualTitle.contentEquals(expectedTitle), "Title of Root Page is not matched");
	}

	@When("user tries to see the login and sign in link")
	public void user_tries_to_see_the_login_and_sign_in_link() {
		try {
			signUp = driver.findElement(By.xpath("//a[text()='Sign up']"));
			login = driver.findElement(By.xpath("//a[text()='Login']"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("login and sign in link should be visible with the text {string} and {string}")
	public void login_and_sign_in_link_should_be_visible_with_the_text_and(String loginString, String signUpString) {
		Assert.assertTrue(login.getText().contentEquals(loginString), "Login button text is not matched");
		Assert.assertTrue(signUp.getText().contentEquals(signUpString), "Sign up button text is not matched");
	}

	@When("user tries to see the delivery location text box & find food button")
	public void user_tries_to_see_the_delivery_location_text_box_find_food_button() {
		try {
			location = driver.findElement(By.xpath("//input[@id='location']"));
			findFood = driver.findElement(By.xpath("//a/span[text()='FIND FOOD']"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("delivery location text box should be visible with placed holder value {string}")
	public void delivery_location_text_box_should_be_visible_with_placed_holder_value(String expectedPlaceHolderText) {
		actualPlaceHolderText = location.getAttribute("placeholder");
		Assert.assertTrue(actualPlaceHolderText.contentEquals(expectedPlaceHolderText),
				"Location Place Holder Attribute value is not matching");
	}

	@Then("find food button should be visible with the text {string}")
	public void find_food_button_should_be_visible_with_the_text(String expectedFindButtonText) {
		findButtonText = findFood.getText();
		Assert.assertTrue(findButtonText.contentEquals(expectedFindButtonText), "Find Button Text is not matching");
	}

	@When("user tries to see the information of popular <cities> in india")
	public void user_tries_to_see_the_information_of_popular_cities_in_india() {
		cityList = driver.findElements(By.xpath("//a[@class='_3zoZ8']"));
		for (WebElement webElement : cityList) {
			if (!webElement.getText().contains("more"))				
				actualCityNames.add(webElement.getText());			
		}
	}

	@Then("system should be showing the links with below city name")
	public void system_should_be_showing_the_links_with_below_city_name(DataTable expectedCityNames) {
		Assert.assertTrue(actualCityNames.containsAll(expectedCityNames.asList()), "List of City Names are not matching");			
	}

	@When("user enters the location {string} in the search text box")
	public void user_enters_the_location_in_the_search_text_box(String locationName) {
		location.sendKeys(locationName);
	}

	@Then("system should list the possible locations as a drop down list")
	public void system_should_list_the_possible_locations_as_a_drop_down_list() throws InterruptedException {
		Thread.sleep(2000);
		try {
			locationDropDown = driver.findElement(By.xpath("//div[@class='_1oLDb']"));
			Assert.assertTrue(locationDropDown.isDisplayed(), "Location Drop Down List is not showed to the user");
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@When("user chooses the desired location {string} from the drop down list")
	public void user_chooses_the_desired_location_from_the_drop_down_list(String expectedLocation) {
		try{
			driver.findElement(By.xpath("//div[contains(@class,'_3lmRa')]/span[@class='_2W-T9' and text()='" + expectedLocation + "']")).click();			
		}
		catch(Exception e) {
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}

	@Then("system should navigate the user to restaurants page with the url {string}")
	public void system_should_navigate_the_user_to_restaurants_page_with_the_url(String url) throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains(url), "System is not navigated to expected URL");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
