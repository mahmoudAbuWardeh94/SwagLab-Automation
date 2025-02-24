package com.automation.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By productsPrices = By.className("inventory_item_price");
	private By addToCartButtons = By.className("btn_inventory");
	private By cartButton = By.className("shopping_cart_link");
	private By checkoutButton = By.id("checkout");
	private By firstNameField = By.id("first-name");
	private By lastNameField = By.id("last-name");
	private By postalCodeField = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By overviewTitle = By.className("title");
	private By totalAmount = By.className("summary_subtotal_label");
	private By finishButton = By.id("finish");
	private By thankYouMessage = By.className("complete-header");
	private By orderDispatchedMessage = By.className("complete-text");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds explicit wait

	}

	public void addMostExpensiveTwoProducts() {
		List<WebElement> getPrices = driver.findElements(productsPrices);
		List<Double> doublePrices = new ArrayList<Double>();
		System.out.println(getPrices);

		for (int i = 0; i < getPrices.size(); i++) {
			String priceText = getPrices.get(i).getText().replace("$", "");
			doublePrices.add(Double.parseDouble(priceText));

		}

		Collections.sort(doublePrices, Collections.reverseOrder());

		List<WebElement> addButtons = driver.findElements(addToCartButtons);

		// Add the top 2 most expensive products to the cart
		for (int k = 0; k < 2; k++) {
			addButtons.get(k).click();
		}

	}

	public void clickOnAddToCartButton() {
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
		cartBtn.click();
	}

	public void clickOnCheckoutButton() {
		WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkoutBtn.click();
	}

	public void fillTheForm(String firstName, String lastName, String postelCode) {
		WebElement firstField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
		firstField.sendKeys(firstName);

		WebElement lastField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
		lastField.sendKeys(lastName);

		WebElement postel = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField));
		postel.sendKeys(postelCode);
	}

	public void clickOnContinueButton() {
		WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		continueBtn.click();
	}

	public boolean getOverviewTitle() {

		WebElement getOverviewTitl = wait.until(ExpectedConditions.visibilityOfElementLocated(overviewTitle));
		return getOverviewTitl.getText().contains("Overview");
	}

	public boolean totalAmountIsDisplayed() {

		WebElement totalAmont = wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));
		return totalAmont.isDisplayed();
	}

	public void clickOnFinishButton() {
		WebElement FinishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
		FinishBtn.click();
	}

	public boolean isThankYouMessageDisplayed() {

		WebElement thankYouMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMessage));
		return thankYouMsg.isDisplayed();
	}

	public boolean isOrderDispatchedMessageDisplayed() {

		WebElement orderDipatched = wait.until(ExpectedConditions.visibilityOfElementLocated(orderDispatchedMessage));
		return orderDipatched.isDisplayed();
	}

}
