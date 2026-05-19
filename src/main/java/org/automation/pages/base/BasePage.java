package org.automation.pages.base;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected void click(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        scrollToElement(element);

        try {

            element.click();

        } catch (ElementClickInterceptedException e) {

            javascriptClick(element);
        }
    }

    protected void type(By locator, String text) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    protected void selectByVisibleText(By locator, String text) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        Select select = new Select(element);

        select.selectByVisibleText(text);
    }

    protected void waitForDropdownOptions(By locator) {

        wait.until(driver -> {

            Select select =
                    new Select(driver.findElement(locator));

            return select.getOptions().size() > 1;
        });
    }

    protected void scrollToElement(WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    protected void javascriptClick(WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                element
        );
    }

    protected boolean isElementDisplayed(By locator) {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            ).isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }
}