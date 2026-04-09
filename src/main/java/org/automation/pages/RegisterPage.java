package org.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    private static final String URL =
            "https://parabank.parasoft.com/parabank/register.htm";

    // ── Campos del formulario de registro
    @FindBy(id = "customer.firstName")
    private WebElement inputFirstName;

    @FindBy(id = "customer.lastName")
    private WebElement inputLastName;

    @FindBy(id = "customer.address.street")
    private WebElement inputAddress;

    @FindBy(id = "customer.address.city")
    private WebElement inputCity;

    @FindBy(id = "customer.address.state")
    private WebElement inputState;

    @FindBy(id = "customer.address.zipCode")
    private WebElement inputZipCode;

    @FindBy(id = "customer.phoneNumber")
    private WebElement inputPhone;

    @FindBy(id = "customer.ssn")
    private WebElement inputSsn;

    @FindBy(id = "customer.username")
    private WebElement inputUsername;

    @FindBy(id = "customer.password")
    private WebElement inputPassword;

    @FindBy(id = "repeatedPassword")
    private WebElement inputConfirmPassword;

    @FindBy(css = "input[value='Register']")
    private WebElement btnRegister;

    // ── Mensaje de éxito o error tras el registro
    @FindBy(css = "#rightPanel .title")
    private WebElement resultTitle;

    @FindBy(xpath = "//div[@id='rightPanel']/p[not(@class)]")
    private WebElement resultMessage;

    public RegisterPage() {
        super();
        navigateTo(URL);
    }

    // ── Llena todos los campos del formulario de una sola vez.
    //    Recibe los datos desde el Step Definition (tabla Gherkin).
    public void completarFormulario(String firstName, String lastName,
                                    String address,   String city,
                                    String state,     String zipCode,
                                    String phone,     String ssn,
                                    String username,  String password) {
        type(inputFirstName,       firstName);
        type(inputLastName,        lastName);
        type(inputAddress,         address);
        type(inputCity,            city);
        type(inputState,           state);
        type(inputZipCode,         zipCode);
        type(inputPhone,           phone);
        type(inputSsn,             ssn);
        type(inputUsername,        username);
        type(inputPassword,        password);
        type(inputConfirmPassword, password);
    }

    public void clickRegistrar() {

        click(btnRegister);
    }

    public String obtenerTituloResultado() {
        return getText(resultTitle);
    }

    public String obtenerMensajeResultado() {
        return getText(resultMessage);
    }

    public String obtenerTituloPagina() {
        return getText(resultMessage);
    }
}