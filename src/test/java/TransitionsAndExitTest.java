import pages.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TransitionsAndExitTest {
    private WebDriver driver;
    private HomePage homePage;
    private final String browser;
    private final String email = "roha@ya.ru";
    private final String password = "qwertyuiop";

    public TransitionsAndExitTest(String browser) {
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
        homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        driver.manage().window().maximize();
        homePage.waitSignIn();
        homePage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitOrder();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверяем переход по клику на «Личный кабинет»")
    public void shouldGoToProfile() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        assertEquals(profilePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем переход по клику на «Конструктор»")
    public void shouldGoToConstructor() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        homePage.clickConstructorLink();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем переход по клику на логотип Stellar Burgers")
    public void shouldGoToHomePage() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        homePage.clickLogo();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем выход по кнопке «Выйти» в личном кабинете")
    public void shouldExit() {
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickAccountButton();
        profilePage.waitProfileLink();
        profilePage.clickExitLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitSignInButton();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }
}
