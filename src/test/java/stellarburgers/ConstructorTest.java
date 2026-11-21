package stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Соусы» в конструкторе")
    public void switchToSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesTabSelected(),
                "Вкладка «Соусы» должна стать активной");
    }

    @Test
    @DisplayName("Переход к разделу «Начинки» в конструкторе")
    public void switchToFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsTabSelected(),
                "Вкладка «Начинки» должна стать активной");
    }

    @Test
    @DisplayName("Переход к разделу «Булки» в конструкторе")
    public void switchToBunsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();
        mainPage.isSaucesTabSelected();

        mainPage.clickBunsTab();

        assertTrue(mainPage.isBunsTabSelected(),
                "Вкладка «Булки» должна стать активной");
    }
}