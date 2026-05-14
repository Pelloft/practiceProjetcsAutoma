package org.automation.steps.opencart;

import io.cucumber.java.en.*;
import org.automation.pages.opencart.*;
import org.automation.utils.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class PurchaseSteps {

    private final WebDriver driver = DriverManager.getDriver();

    private final HomePage homePage = new HomePage(driver);
    private final CartPage cartPage = new CartPage(driver);
    private final CheckoutPage checkoutPage = new CheckoutPage(driver);
    private final SuccessPage successPage = new SuccessPage(driver);

    @Given("el usuario ingresa a la tienda OpenCart")
    public void openStore() {
        homePage.openStore();
    }

    @When("el usuario agrega un producto al carrito")
    public void addProduct() {
        homePage.addFirstProductToCart();
    }

    @And("el usuario visualiza el carrito")
    public void viewCart() {
        cartPage.openCart();
    }

    @And("el usuario realiza checkout como invitado")
    public void guestCheckout() {

        checkoutPage.startCheckout();
        checkoutPage.selectGuestCheckout();
        checkoutPage.fillBillingDetails();
    }

    @Then("la compra se realiza exitosamente")
    public void validatePurchase() {

        Assertions.assertTrue(
                successPage.isPurchaseSuccessful()
        );
    }
}
