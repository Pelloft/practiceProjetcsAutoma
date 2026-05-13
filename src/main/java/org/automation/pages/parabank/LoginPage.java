package org.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";

    /* @FindBy le dice a PageFactory cómo localizar cada elemento.
       name="username" busca el atributo name="username" en el HTML.*/
    @FindBy(name = "username")
    private WebElement inputUsername;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(css = "input[value='Log In']")
    private WebElement btnLogin;

    @FindBy(css = ".error")
    private WebElement errorMessage;

    @FindBy(linkText = "Log Out")
    private WebElement btnLogOut;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    // Navega a la página de inicio al instanciar la clase.
    public LoginPage() {
        super();
        navigateTo(URL);
    }

    // Métodos de acción — cada metodo representa UNA acción del usuario.

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

    /* Metodo compuesto — agrupa acciones relacionadas.
       El Step Definition lo llamará con una sola línea.*/
    public void login(String username, String password) {
        ingresarUsername(username);
        ingresarPassword(password);
        clickLogin();
    }

    /*Métodos de verificación — devuelven datos para que
      el Step Definition valide con assertions.*/

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
}