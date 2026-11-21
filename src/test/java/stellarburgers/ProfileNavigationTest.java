package stellarburgers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileNavigationTest extends BaseTest {

    @BeforeEach
    public void prepareAndLoginUser() {
        createTestUserViaApi();

        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickSignInAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.isCreateOrderButtonDisplayed();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void navigateToPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isProfilePageDisplayed(),
                "Должна открыться страница личного кабинета");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void navigateFromProfileToConstructorViaButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isProfilePageDisplayed();
        profilePage.clickConstructorButton();

        assertTrue(mainPage.isConstructorHeaderDisplayed(),
                "Должен открыться раздел «Конструктор» с заголовком «Соберите бургер»");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void navigateFromProfileToConstructorViaLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isProfilePageDisplayed();
        profilePage.clickLogo();

        assertTrue(mainPage.isConstructorHeaderDisplayed(),
                "По клику на логотип должен открыться раздел «Конструктор»");
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logoutFromPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isProfilePageDisplayed();
        profilePage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginHeaderDisplayed(),
                "После выхода из аккаунта должна отображаться форма входа");
    }
}