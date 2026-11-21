package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.api.UserApiClient;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By signInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By constructorHeader = By.xpath("//h1[text()='Соберите бургер']");

    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу Stellar Burgers")
    public MainPage open() {
        driver.get(UserApiClient.BASE_URL);
        return this;
    }

    @Step("Нажать кнопку «Войти в аккаунт» на главной странице")
    public void clickSignInAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInAccountButton)).click();
    }

    @Step("Нажать кнопку «Личный кабинет» в шапке сайта")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Проверить, что отображается кнопка «Оформить заказ»")
    public boolean isCreateOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton)).isDisplayed();
    }

    @Step("Проверить, что открыт раздел «Конструктор» с заголовком «Соберите бургер»")
    public boolean isConstructorHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader)).isDisplayed();
    }

    @Step("Переключиться на вкладку «Булки» в конструкторе")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Переключиться на вкладку «Соусы» в конструкторе")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Переключиться на вкладку «Начинки» в конструкторе")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить, что активна вкладка «Булки»")
    public boolean isBunsTabSelected() {
        return wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что активна вкладка «Соусы»")
    public boolean isSaucesTabSelected() {
        return wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что активна вкладка «Начинки»")
    public boolean isFillingsTabSelected() {
        return wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }
}