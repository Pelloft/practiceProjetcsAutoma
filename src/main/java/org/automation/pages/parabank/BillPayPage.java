package org.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillPayPage extends BasePage {

    private static final String URL =
            "https://parabank.parasoft.com/parabank/billpay.htm";

    // ── Datos del destinatario del pago
    @FindBy(name = "payee.name")
    private WebElement inputPayeeName;

    @FindBy(name = "payee.address.street")
    private WebElement inputAddress;

    @FindBy(name = "payee.address.city")
    private WebElement inputCity;

    @FindBy(name = "payee.address.state")
    private WebElement inputState;

    @FindBy(name = "payee.address.zipCode")
    private WebElement inputZipCode;

    @FindBy(name = "payee.phoneNumber")
    private WebElement inputPhone;

    // ── Datos bancarios del destinatario
    @FindBy(name = "payee.accountNumber")
    private WebElement inputAccountNumber;

    @FindBy(name = "verifyAccount")
    private WebElement inputVerifyAccount;

    // ── Monto a pagar
    @FindBy(name = "amount")
    private WebElement inputAmount;

    // ── Cuenta origen desde la que se debita el pago
    @FindBy(name = "fromAccountId")
    private WebElement selectFromAccount;

    @FindBy(css = "input[value='Send Payment']")
    private WebElement btnSendPayment;

    // ── Mensajes de resultado
    @FindBy(css = "#billpayResult h1")
    private WebElement resultTitle;

    @FindBy(css = "#billpayResult .title")
    private WebElement resultMessage;

    @FindBy(css = "#billpayResult p")
    private WebElement resultDetail;

    public BillPayPage() {
        super();
        navigateTo(URL);
    }

    // ── Completa todos los campos del formulario de pago.
    //    Cada parámetro corresponde a una columna de la tabla Gherkin.
    public void completarFormularioPago(String payeeName, String address,
                                        String city,      String state,
                                        String zipCode,   String phone,
                                        String account,   String verifyAccount,
                                        String amount) {
        type(inputPayeeName,     payeeName);
        type(inputAddress,       address);
        type(inputCity,          city);
        type(inputState,         state);
        type(inputZipCode,       zipCode);
        type(inputPhone,         phone);
        type(inputAccountNumber, account);
        type(inputVerifyAccount, verifyAccount);
        type(inputAmount,        amount);
    }

    // ── Solo ingresa el monto — útil para escenarios de validación.
    public void ingresarMonto(String amount) {
        type(inputAmount, amount);
    }

    public void clickEnviarPago() {
        click(btnSendPayment);
    }

    // ── Devuelve el título del resultado: "Bill Payment Complete"
    public String obtenerTituloResultado() {
        return getText(resultTitle);
    }

    // ── Devuelve el detalle: "$50.00 was sent to John Smith from account..."
    public String obtenerDetalleResultado() {
        return getText(resultDetail);
    }

    public boolean resultadoVisible() {
        return isElementDisplayed(resultTitle);
    }
}
