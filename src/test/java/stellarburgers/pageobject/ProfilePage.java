package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By profileSectionLink = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logoLink = By.xpath("//div[contains(@class,'AppHeader_header__logo')]/a");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Проверить, что открыта страница личного кабинета")
    public boolean isProfilePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileSectionLink)).isDisplayed();
    }

    @Step("Нажать кнопку «Выйти» в личном кабинете")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажать кнопку «Конструктор» в шапке сайта")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать на логотип Stellar Burgers в шапке сайта")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoLink)).click();
    }
}