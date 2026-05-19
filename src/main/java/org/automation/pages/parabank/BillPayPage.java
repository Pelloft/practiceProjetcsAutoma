package org.automation.pages.parabank;

import org.automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillPayPage extends BasePage {

    private static final String URL = "https://parabank.parasoft.com/parabank/billpay.htm";

    // Locators
    private final By inputPayeeName = By.name("payee.name");
    private final By inputAddress = By.name("payee.address.street");
    private final By inputCity = By.name("payee.address.city");
    private final By inputState = By.name("payee.address.state");
    private final By inputZipCode = By.name("payee.address.zipCode");
    private final By inputPhone = By.name("payee.phoneNumber");
    private final By inputAccountNumber = By.name("payee.accountNumber");
    private final By inputVerifyAccount = By.name("verifyAccount");
    private final By inputAmount = By.name("amount");
    private final By selectFromAccount = By.name("fromAccountId");
    private final By btnSendPayment = By.cssSelector("input[value='Send Payment']");
    private final By resultTitle = By.cssSelector("#billpayResult h1");
    private final By resultMessage = By.cssSelector("#billpayResult .title");
    private final By resultDetail = By.cssSelector("#billpayResult p");

    public BillPayPage(WebDriver driver) {
        super(driver);
        navigateTo(URL);
    }

    public void completarFormularioPago(String payeeName, String address,
                                        String city, String state,
                                        String zipCode, String phone,
                                        String account, String verifyAccount,
                                        String amount) {
        type(inputPayeeName, payeeName);
        type(inputAddress, address);
        type(inputCity, city);
        type(inputState, state);
        type(inputZipCode, zipCode);
        type(inputPhone, phone);
        type(inputAccountNumber, account);
        type(inputVerifyAccount, verifyAccount);
        type(inputAmount, amount);
    }

    public void ingresarMonto(String amount) {
        type(inputAmount, amount);
    }

    public void clickEnviarPago() {
        click(btnSendPayment);
    }

    public String obtenerTituloResultado() {
        return getText(resultTitle);
    }

    public String obtenerDetalleResultado() {
        return getText(resultDetail);
    }

    public boolean resultadoVisible() {
        return isElementDisplayed(resultTitle);
    }

    private void navigateTo(String url) {
        driver.get(url);
    }
}
