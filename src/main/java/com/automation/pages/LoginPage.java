package com.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By userNameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By logoutButton = By.xpath("//button[@id='react-burger-menu-btn']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds explicit wait

	}

	public void enterUserName(String userName) {
		WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
		userField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
		passField.sendKeys(password);
	}

	public void clickOnLoginButton() {
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginBtn.click();
	}

	public String getErrorMessage() {
		WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return errorMsgElement.getText();
	}

	public boolean verifyLogoutButtonIsDisabled() {
		WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
		return errorMsgElement.isDisplayed();
	}

}
