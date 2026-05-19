package org.automation.pages.parabank;

import org.automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferPage extends BasePage {

    private static final String URL = "https://parabank.parasoft.com/parabank/transfer.htm";

    // Locators
    private final By inputAmount = By.id("amount");
    private final By selectFromAccount = By.id("fromAccountId");
    private final By selectToAccount = By.id("toAccountId");
    private final By btnTransfer = By.cssSelector("input[value='Transfer']");
    private final By resultTitle = By.cssSelector("#showResult h1");
    private final By resultDetail = By.cssSelector("#showResult p");
    private final By errorMessage = By.cssSelector(".error");

    public TransferPage(WebDriver driver) {
        super(driver);
        navigateTo(URL);
    }

    public void ingresarMonto(String amount) {
        type(inputAmount, amount);
    }

    public void seleccionarCuentaOrigen() {
        waitForDropdownOptions(selectFromAccount);
        Select select = new Select(driver.findElement(selectFromAccount));
        select.selectByIndex(0);
    }

    public void seleccionarCuentaDestino() {
        waitForDropdownOptions(selectToAccount);
        Select select = new Select(driver.findElement(selectToAccount));
        int size = select.getOptions().size();
        if (size > 1) {
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

    private void navigateTo(String url) {
        driver.get(url);
    }
}
