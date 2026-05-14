package org.automation.pages.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartButton = By.cssSelector("#cart-total");
    private final By viewCartButton = By.xpath("//strong[text()=' View Cart']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
    }
}
