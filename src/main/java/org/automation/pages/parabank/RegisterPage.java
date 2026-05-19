package org.automation.pages.parabank;

import org.automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class RegisterPage extends BasePage {

    private static final String URL = "https://parabank.parasoft.com/parabank/register.htm";

    // Locators
    private final By inputFirstName = By.id("customer.firstName");
    private final By inputLastName = By.id("customer.lastName");
    private final By inputAddress = By.id("customer.address.street");
    private final By inputCity = By.id("customer.address.city");
    private final By inputState = By.id("customer.address.state");
    private final By inputZipCode = By.id("customer.address.zipCode");
    private final By inputPhone = By.id("customer.phoneNumber");
    private final By inputSsn = By.id("customer.ssn");
    private final By inputUsername = By.id("customer.username");
    private final By inputPassword = By.id("customer.password");
    private final By inputConfirmPassword = By.id("repeatedPassword");
    private final By btnRegister = By.cssSelector("input[value='Register']");
    private final By resultTitle = By.cssSelector("#rightPanel h1.title");
    private final By resultMessage = By.cssSelector("#rightPanel p");
    private final By pageTitle = By.cssSelector("h1.title");

    public RegisterPage(WebDriver driver) {
        super(driver);
        navigateTo(URL);
    }

    public void completarFormulario(String firstName, String lastName,
                                    String address, String city,
                                    String state, String zipCode,
                                    String phone, String ssn,
                                    String username, String password) {
        type(inputFirstName, firstName);
        type(inputLastName, lastName);
        type(inputAddress, address);
        type(inputCity, city);
        type(inputState, state);
        type(inputZipCode, zipCode);
        type(inputPhone, phone);
        type(inputSsn, ssn);
        type(inputUsername, username);
        type(inputPassword, password);
        type(inputConfirmPassword, password);
    }

    public void clickRegistrar() {
        click(btnRegister);
    }

    public String obtenerMensajeResultado() {
        // Espera que el título contenga "Welcome"
        wait.until(ExpectedConditions.textMatches(
                resultTitle,
                Pattern.compile(".*Welcome.*")
        ));

        return getText(resultMessage);
    }

    public String obtenerTituloResultado() {
        return getText(resultTitle);
    }

    public String obtenerTituloPagina() {
        return getText(pageTitle);
    }

    private void navigateTo(String url) {
        driver.get(url);
    }
}
