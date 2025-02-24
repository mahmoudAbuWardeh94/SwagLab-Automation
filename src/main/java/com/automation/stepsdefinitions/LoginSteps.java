package com.automation.stepsdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.automation.base.LoginBase;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	private WebDriver driver;
	private LoginPage loginPage;
	public SoftAssert softassert = new SoftAssert();

	public LoginSteps() {
		System.out.println("Initializing LoginSteps...");

		this.driver = LoginBase.getDriver();
		this.loginPage = new LoginPage(driver);
		System.out.println("WebDriver in LoginSteps: " + driver);
	}

	@Given("the user navigates to the login page")
	public void navigateToLoginPage() {
		String baseUrl = ConfigReader.getProperty("url"); // ✅ Fetch URL dynamically
		System.out.println("Navigating to: " + baseUrl);
		driver.get(baseUrl);
	}

	@When("the user enters invalid username {string} and password {string}")
	public void enterInvalidCredentials1(String username, String password) {
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
	}

	@Then("the error message should be shown {string}")
	public void verifyLoginErrorMessage(String expectedErrorMessage) {
		String actualErrorMessage = loginPage.getErrorMessage();

		softassert.assertEquals(actualErrorMessage, expectedErrorMessage, "error message mismatch in login page");
		softassert.assertAll();
	}

	@When("the user enters valid username and password")
	public void enterValidCredentials() {
		loginPage.enterUserName("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickOnLoginButton();
	}

	@When("the user enters Invalid username and password")
	public void enterinValidCredentials() {
		loginPage.enterUserName("standard_userTest");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickOnLoginButton();
	}

	@Then("the user should be logged in successfully")
	public void verifyLogin() {

		softassert.assertTrue(loginPage.verifyLogoutButtonIsDisabled(), "Login successfully");
		softassert.assertAll();
	}

}
