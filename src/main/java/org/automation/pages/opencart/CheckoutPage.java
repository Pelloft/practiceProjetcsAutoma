package org.automation.pages.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By checkoutButton = By.linkText("Checkout");

    private final By guestCheckoutRadio = By.cssSelector("input[value='guest']");
    private final By continueButton = By.id("button-account");

    private final By firstNameInput = By.id("input-payment-firstname");
    private final By lastNameInput = By.id("input-payment-lastname");
    private final By emailInput = By.id("input-payment-email");
    private final By telephoneInput = By.id("input-payment-telephone");
    private final By addressInput = By.id("input-payment-address-1");
    private final By cityInput = By.id("input-payment-city");
    private final By postCodeInput = By.id("input-payment-postcode");
    private final By countrySelect = By.id("input-payment-country");
    private final By regionSelect = By.id("input-payment-zone");
    private final By continueCheckoutButton = By.id("button-guest");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void startCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void selectGuestCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutRadio)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void fillBillingDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys("Juan");
        driver.findElement(lastNameInput).sendKeys("Perez");
        driver.findElement(emailInput).sendKeys("juan@test.com");
        driver.findElement(telephoneInput).sendKeys("0999999999");
        driver.findElement(addressInput).sendKeys("Av Principal");
        driver.findElement(cityInput).sendKeys("Guayaquil");
        driver.findElement(postCodeInput).sendKeys("090101");

        // Selecciona país
        Select countryDropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(countrySelect)));
        countryDropdown.selectByVisibleText("Ecuador");

        wait.until(driver -> {
            Select select = new Select(driver.findElement(regionSelect));
            return select.getOptions().size() > 1;
        });

        // Selecciona región
        Select regionDropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(regionSelect)));
        regionDropdown.selectByIndex(10); // O la región correctata

        // Continúa con el checkout
        wait.until(ExpectedConditions.elementToBeClickable(continueCheckoutButton)).click();
    }
}
