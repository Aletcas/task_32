import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Данные логина и пароля не меняются.
 * Они даны для авторизации.
 */

public class TestDesign extends TestBase {

    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    @Tag("smoke")
    @Tag("regression")
    void successfulLoginWithValidCredentials() {
        Allure.step("Открыть страницу логина", () -> {
            loginPage.open();
        });

        Allure.step("Ввести корректные логин и пароль", () -> {
            loginPage.login("tomsmith", "SuperSecretPassword!");
        });

        Allure.step("Проверить успешную авторизацию", () -> {
            page.waitForTimeout(1000);


            assertTrue(loginPage.isLoggedIn(),
                    "Должна отображаться кнопка Logout");

            assertTrue(loginPage.isFlashMessageVisible(),
                    "Должно отображаться flash сообщение");

            String message = loginPage.getFlashMessageText();
            assertTrue(message.contains("You logged into a secure area"),
                    "Сообщение должно содержать текст: 'You logged into a secure area'");

            assertTrue(page.url().contains("/secure"),
                    "После входа должен быть редирект на /secure");
        });
    }
}
