package stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.api.User;
import stellarburgers.api.UserCredentials;
import stellarburgers.api.UserGenerator;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        User newUser = UserGenerator.getRandomUser();

        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        assertTrue(loginPage.isLoginHeaderDisplayed(),
                "После успешной регистрации должен открываться экран входа");

        accessToken = userApiClient.loginUser(UserCredentials.from(newUser))
                .extract()
                .path("accessToken");
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем короче шести символов")
    public void registrationWithShortPasswordShowsErrorTest() {
        User newUser = UserGenerator.getRandomUser();

        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(newUser.getName(), newUser.getEmail(), "12345");

        assertTrue(registerPage.isIncorrectPasswordErrorDisplayed(),
                "Должно отображаться сообщение об ошибке «Некорректный пароль»");
    }
}