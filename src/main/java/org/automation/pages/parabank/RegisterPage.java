package org.automation.pages.parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private static final String URL =
            "https://parabank.parasoft.com/parabank/register.htm";

    //  Selectores dinámicos para elementos post-navegación.
    //  Se buscan en el momento exacto que se necesitan, evitando StaleElementReferenceException.
    private static final By RESULT_MESSAGE =
            By.cssSelector("#rightPanel p");

    private static final By RESULT_TITLE =
            By.cssSelector("#rightPanel h1.title");

    private static final By PAGE_TITLE =
            By.cssSelector("h1.title");

    private static final By BTN_REGISTER =
            By.cssSelector("input[value='Register']");

    //  Campos del formulario con @FindBy — seguros antes de navegación
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

    public RegisterPage() {
        super();
        navigateTo(URL);
    }

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
        WebElement btn = waitForElement(BTN_REGISTER);
        btn.click();
    }

    public String obtenerMensajeResultado() {
        // Espera que el título contenga "Welcome" — indica navegación exitosa.
        //    textMatches acepta regex, útil porque el título incluye
        //    el username dinámico: "Welcome user_20250408_143022"
        wait.until(ExpectedConditions.textMatches(
                By.cssSelector("#rightPanel h1.title"),
                java.util.regex.Pattern.compile(".*Welcome.*")
        ));

        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#rightPanel p")
                )
        );
        return mensaje.getText();
    }

    // Devuelve "Welcome [String]" tras registro exitoso
    public String obtenerTituloResultado() {
        WebElement titulo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(RESULT_TITLE)
        );
        return titulo.getText();
    }

    public String obtenerTituloPagina() {
        WebElement titulo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE)
        );
        return titulo.getText();
    }

}