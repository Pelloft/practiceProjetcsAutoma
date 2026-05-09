package org.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    // ThreadLocal garantiza un driver independiente por hilo.
    // Esto es clave si en el futuro ejecutas pruebas en paralelo.
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Constructor privado — nadie puede instanciar esta clase directamente.
    private DriverManager() {}

    // Devuelve el driver del hilo actual.
    // Si no existe, lanza un error claro en lugar de un NullPointer.
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException(
                    "El driver no ha sido inicializado. Llama a initDriver() primero."
            );
        }
        return driver.get();
    }

    // Inicializa Chrome y lo asigna al hilo actual.
    //    WebDriverManager descarga y configura ChromeDriver automáticamente.
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");        // Pantalla completa
        options.addArguments("--disable-notifications");  // Sin popups de notificaciones
        options.addArguments("--remote-allow-origins=*"); // Evita errores de CORS en Chrome
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");

        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);


        WebDriver chromeDriver = new ChromeDriver(options);
        driver.set(chromeDriver);
    }

    // Cierra el navegador y libera el driver del ThreadLocal.
    //    Sin el remove(), el hilo retiene el driver aunque ya no lo use.
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
