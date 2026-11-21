package stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import stellarburgers.api.User;
import stellarburgers.api.UserApiClient;
import stellarburgers.api.UserGenerator;
import stellarburgers.driver.BrowserFactory;

public class BaseTest {

    protected WebDriver driver;
    protected UserApiClient userApiClient;
    protected User testUser;
    protected String accessToken;

    @BeforeEach
    public void setUp() {
        driver = BrowserFactory.getWebDriver();
        driver.manage().window().maximize();
        userApiClient = new UserApiClient();
    }

    protected void createTestUserViaApi() {
        testUser = UserGenerator.getRandomUser();
        accessToken = userApiClient.createUser(testUser)
                .extract()
                .path("accessToken");
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}