package org.automation.pages.parabank;

import org.automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";

    // Locators usando By en lugar de @FindBy (más flexible)
    private final By inputUsername = By.name("username");
    private final By inputPassword = By.name("password");
    private final By btnLogin = By.cssSelector("input[value='Log In']");
    private final By errorMessage = By.cssSelector(".error");
    private final By btnLogOut = By.linkText("Log Out");
    private final By pageTitle = By.cssSelector(".title");

    // Constructor que recibe WebDriver
    public LoginPage(WebDriver driver) {
        super(driver);
        navigateTo(URL);
    }

    // Métodos de acción
    public void ingresarUsername(String username) {
        type(inputUsername, username);
    }

    public void ingresarPassword(String password) {
        type(inputPassword, password);
    }

    public void clickLogin() {
        click(btnLogin);
    }

    public void clickLogOut() {
        click(btnLogOut);
    }

    public void login(String username, String password) {
        ingresarUsername(username);
        ingresarPassword(password);
        clickLogin();
    }

    // Métodos de verificación
    public String obtenerMensajeError() {
        return getText(errorMessage);
    }

    public String obtenerTituloPagina() {
        return getText(pageTitle);
    }

    public boolean logOutVisible() {
        return isElementDisplayed(btnLogOut);
    }

    public boolean loginButtonVisible() {
        return isElementDisplayed(btnLogin);
    }

    private void navigateTo(String url) {
        driver.get(url);
    }
}