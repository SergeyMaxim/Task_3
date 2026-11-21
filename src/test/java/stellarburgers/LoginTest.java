package stellarburgers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.pageobject.ForgotPasswordPage;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @BeforeEach
    public void prepareUser() {
        createTestUserViaApi();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginViaSignInAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickSignInAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue(mainPage.isCreateOrderButtonDisplayed(),
                "После входа на главной странице должна отображаться кнопка «Оформить заказ»");
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue(mainPage.isCreateOrderButtonDisplayed(),
                "После входа через «Личный кабинет» должна отображаться кнопка «Оформить заказ»");
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме регистрации")
    public void loginViaRegisterFormLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue(mainPage.isCreateOrderButtonDisplayed(),
                "После входа через форму регистрации должна отображаться кнопка «Оформить заказ»");
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
    public void loginViaForgotPasswordFormLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue(mainPage.isCreateOrderButtonDisplayed(),
                "После входа через форму восстановления пароля должна отображаться кнопка «Оформить заказ»");
    }
}