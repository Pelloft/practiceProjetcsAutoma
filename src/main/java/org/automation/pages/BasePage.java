package org.automation.pages;

import com.automation.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    // Todas las páginas heredan acceso al driver y al wait.
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Tiempo máximo de espera para cada elemento: 10 segundos.
    //    Si el elemento no aparece en ese tiempo, la prueba falla con mensaje claro.
    private static final int TIMEOUT = 10;

    // El constructor recibe el driver y configura PageFactory.
    //    PageFactory inicializa los @FindBy de la página hija automáticamente.
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    // Espera a que el elemento sea visible y luego hace clic.
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Espera a que el campo sea visible, lo limpia y escribe el texto.
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Espera hasta que el elemento sea visible y devuelve su texto.
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // Espera hasta que un elemento localizado por By sea visible.
    //    Útil cuando no tienes el @FindBy declarado pero necesitas esperar.
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Navega a una URL específica.
    protected void navigateTo(String url) {
        driver.get(url);
    }

    // Verifica si un elemento está presente en la página (sin esperar).
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
