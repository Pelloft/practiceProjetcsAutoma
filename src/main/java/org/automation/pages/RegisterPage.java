package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private static final String URL =
            "https://parabank.parasoft.com/parabank/register.htm";

    private static final By RESULT_MESSAGE =
            By.xpath("//*[@id='rightPanel']/p[not(@class)]");

    private static final By PAGE_TITLE =
            By.cssSelector(".title");

    private static final By BTN_REGISTER =
            By.cssSelector("input[value='Register']");


    //Campos del formulario de registro
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

    // Llena todos los campos del formulario de una sola vez.
    // Recibe los datos desde el Step Definition (tabla Gherkin).
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

        WebElement btnRegister = waitForElement(BTN_REGISTER);
        btnRegister.click();
    }

    public String obtenerMensajeResultado() {

        WebElement mensaje = wait.until(
                ExpectedConditions.presenceOfElementLocated(RESULT_MESSAGE)
        );
        return mensaje.getText();
    }



    public String obtenerTituloPagina() {

        WebElement titulo = wait.until(
                ExpectedConditions.presenceOfElementLocated(PAGE_TITLE)
        );
        return titulo.getText();
    }

    // Imprime el HTML de #rightPanel para diagnóstico.
    //    Útil cuando el localizador no encuentra el elemento esperado.
    public void imprimirHtmlResultado() {
        try {
            WebElement panel = driver.findElement(By.id("rightPanel"));
            System.out.println("=== HTML rightPanel ===");
            System.out.println(panel.getAttribute("innerHTML"));
            System.out.println("======================");
        } catch (Exception e) {
            System.out.println("No se encontró #rightPanel: " + e.getMessage());
        }
    }

}