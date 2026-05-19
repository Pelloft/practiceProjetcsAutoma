package org.automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();

    private TestDataGenerator() {}

    // Genera un username único por timestamp.
    // Ejemplo: user_20250519_143022
    public static String generateUsername() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return "user_" + timestamp;
    }

    // Genera un SSN aleatorio con formato válido para ParaBank.
    public static String generateSsn() {
        return String.format("%03d-%02d-%04d",
                random.nextInt(900) + 100,
                random.nextInt(90)  + 10,
                random.nextInt(9000) + 1000
        );
    }

    // Genera un número de teléfono aleatorio.
    public static String generatePhone() {
        return String.format("09%08d", random.nextInt(100000000));
    }
}
