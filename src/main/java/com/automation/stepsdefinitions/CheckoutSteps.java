package com.automation.stepsdefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.automation.base.LoginBase;
import com.automation.pages.CheckoutPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

	private WebDriver driver;
	private CheckoutPage checkoutPage;
	SoftAssert softassert = new SoftAssert();

	public CheckoutSteps() {
		this.driver = LoginBase.getDriver();
		this.checkoutPage = new CheckoutPage(driver);
	}

	@When("the user can add the most expensive two products")
	public void addTwoExpensiveProducts() {

		checkoutPage.addMostExpensiveTwoProducts();

		checkoutPage.clickOnAddToCartButton();
	}

	@And("the user proceeds to checkout")
	public void proceedsCheckout() {
		checkoutPage.clickOnCheckoutButton();

	}

	@When("the user fills in the checkout form and continues")
	public void fillTheFormPage() {
		checkoutPage.fillTheForm("Mahmoud", "Abu Wardeh", "335540");
		checkoutPage.clickOnContinueButton();
	}

	@Then("the user should be on the Overview page")
	public void overviewPageIsOpened() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		softassert.assertTrue(checkoutPage.getOverviewTitle());
		softassert.assertAll();
	}

	@And("the total amount before taxes should be verified")
	public void verifyTotalAmount() {

		softassert.assertTrue(checkoutPage.totalAmountIsDisplayed());
		softassert.assertAll();
	}

	@And("the URL should match the expected checkout URL")
	public void verifyCheckoutURL() throws InterruptedException {

		Thread.sleep(2000);
		String actualResult = driver.getCurrentUrl();
		String expectedResult = "https://www.saucedemo.com/checkout-step-two.html";

		softassert.assertEquals(actualResult, expectedResult);
		softassert.assertAll();
	}

	@When("the user clicks on the Finish button")
	public void clickOnFinishButton() {
		checkoutPage.clickOnFinishButton();

	}

	@Then("the Thank You and Order Dispatched messages should be displayed")
	public void verifyOrderConfirmation() {

		softassert.assertTrue(checkoutPage.isThankYouMessageDisplayed());
		softassert.assertTrue(checkoutPage.isOrderDispatchedMessageDisplayed());
		softassert.assertAll();

	}

}
