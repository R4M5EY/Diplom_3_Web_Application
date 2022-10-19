import extensions.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private final String email = "roha@ya.ru";
    private final String password = "qwertyuiop";

    @Before
    public void setup() {
        driver = DriverFactory.getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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