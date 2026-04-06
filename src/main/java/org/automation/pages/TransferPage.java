package org.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TransferPage extends BasePage {

    private static final String URL =
            "https://parabank.parasoft.com/parabank/transfer.htm";

    // ── Monto a transferir
    @FindBy(id = "amount")
    private WebElement inputAmount;

    // ── Select de cuenta origen — contiene todas las cuentas del usuario
    @FindBy(id = "fromAccountId")
    private WebElement selectFromAccount;

    // ── Select de cuenta destino
    @FindBy(id = "toAccountId")
    private WebElement selectToAccount;

    @FindBy(css = "input[value='Transfer']")
    private WebElement btnTransfer;

    // ── Título del resultado: "Transfer Complete!"
    @FindBy(css = "#showResult h1")
    private WebElement resultTitle;

    // ── Detalle: "$100.00 has been transferred from account X to account Y"
    @FindBy(css = "#showResult p")
    private WebElement resultDetail;

    // ── Mensaje de error cuando el monto es inválido
    @FindBy(css = ".error")
    private WebElement errorMessage;

    public TransferPage() {
        super();
        navigateTo(URL);
    }

    public void ingresarMonto(String amount) {
        type(inputAmount, amount);
    }

    // ── Selecciona el primer elemento disponible como cuenta origen.
    //    En ParaBank el select se llena dinámicamente con las cuentas del usuario.
    public void seleccionarCuentaOrigen() {
        Select select = new Select(selectFromAccount);
        waitUntilSelectHasOptions(selectFromAccount);
        select.selectByIndex(0);
    }

    // ── Selecciona la segunda cuenta disponible como destino
    //    para garantizar que origen y destino sean diferentes.
    public void seleccionarCuentaDestino() {
        Select select = new Select(selectToAccount);
        waitUntilSelectHasOptions(selectToAccount);
        List<WebElement> options = select.getOptions();
        if (options.size() > 1) {
            select.selectByIndex(1);
        } else {
            select.selectByIndex(0);
        }
    }

    public void clickTransferir() {
        click(btnTransfer);
    }

    public String obtenerTituloResultado() {
        return getText(resultTitle);
    }

    public String obtenerDetalleResultado() {
        return getText(resultDetail);
    }

    public String obtenerMensajeError() {
        return getText(errorMessage);
    }

    public boolean resultadoVisible() {
        return isElementDisplayed(resultTitle);
    }

    // ── Espera a que el select tenga al menos una opción cargada.
    //    ParaBank carga las cuentas con una petición Ajax al abrir la página.
    private void waitUntilSelectHasOptions(WebElement selectElement) {
        wait.until(driver -> {
            Select select = new Select(selectElement);
            return select.getOptions().size() > 0;
        });
    }
}
