import pages.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.lang3.RandomStringUtils;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private final String name = "name";
    private String email, password;
    private final String browser;

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {
                {"Chrome"},
                {"Yandex"},
        };
    }

    @Before
    public void setup() {
        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Yandex":
                WebDriverManager.chromedriver().driverVersion("104.0.5112.20").setup();
                driver = new ChromeDriver(new ChromeOptions().setBinary("C:\\Users\\rohaw\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"));
                break;
            default:
                System.out.println("Открывает только Хром и Яндекс");
        }
        registrationPage = new RegistrationPage(driver);
        driver.get(registrationPage.getUrl());
        driver.manage().window().maximize();
        registrationPage.waitRegButton();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @Description("Создаем аккаунт(пароль6+), проверяем регистрацию")
    public void shouldSuccessReg() {
        LoginPage loginPage = new LoginPage(driver);
        email = RandomStringUtils.randomAlphanumeric(15)+"@ya.ru";
        password = RandomStringUtils.randomAlphanumeric(10);
        registrationPage.sendKeysName(name);
        registrationPage.sendKeysEmail(email);
        registrationPage.sendKeysPassword(password);
        registrationPage.clickRegButton();
        loginPage.waitSignInButton();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Создаем аккаунт(пароль6-), получаем ошибку")
    public void regShouldBeError() {
        email = RandomStringUtils.randomAlphanumeric(15)+"@ya.ru";
        password = RandomStringUtils.randomAlphanumeric(5);
        registrationPage.sendKeysName(name);
        registrationPage.sendKeysEmail(email);
        registrationPage.sendKeysPassword(password);
        registrationPage.clickRegButton();
        registrationPage.waitError();
        assertEquals("Некорректный пароль", registrationPage.getError());
    }
}
