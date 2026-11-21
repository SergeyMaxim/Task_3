package stellarburgers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BrowserFactory {

    public static WebDriver getWebDriver() {
        String browserName = System.getProperty("browser", "chrome").toLowerCase();

        switch (browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);

            case "yandex":
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--remote-allow-origins=*");
                yandexOptions.setBinary(getYandexBrowserPath());
                yandexOptions.setBrowserVersion(System.getProperty("yandex.version", "154"));
                return new ChromeDriver(yandexOptions);

            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }

    private static String getYandexBrowserPath() {
        String customPath = System.getProperty("yandex.binary");
        if (customPath != null && !customPath.isEmpty()) {
            return customPath;
        }

        String[] possiblePaths = {
                System.getProperty("user.home") + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
        };

        for (String path : possiblePaths) {
            if (new File(path).exists()) {
                return path;
            }
        }

        throw new RuntimeException("Не найден исполняемый файл Яндекс.Браузера (browser.exe). " +
                "Укажите путь через параметр -Dyandex.binary=\"путь_к_browser.exe\"");
    }
}