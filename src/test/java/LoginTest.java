import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private final String email = "roha@ya.ru";
    private final String password = "qwertyuiop";
    private final String browser;

    public LoginTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
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
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\104.0.5112.79\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\rohaw\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                driver = new ChromeDriver(options);
                break;
            default:
                System.out.println("Для такого браузера тестирование не предусмотрено");
        }
        homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        driver.manage().window().maximize();
        homePage.waitSignIn();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void homePageSignInButtonCheck() {
        loginPage = new LoginPage(driver);
        homePage.clickSignInButton();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void accountButtonSighInCheck() {
        loginPage = new LoginPage(driver);
        homePage.clickAccountButton();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void regPageSignInLinkCheck() {
        loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(registrationPage.getUrl());
        registrationPage.waitRegButton();
        registrationPage.clickSignInLink();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void forgotPageSignInLinkCheck() {
        loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        driver.get(forgotPasswordPage.getUrl());
        forgotPasswordPage.waitRestorePasswordButton();
        forgotPasswordPage.clickSignInLink();
        loginPage.waitSignInButton();
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickSignInButton();
        homePage.waitOrder();
        assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }
}