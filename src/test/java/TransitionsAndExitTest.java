import extensions.DriverFactory;
import pages.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TransitionsAndExitTest {
    private WebDriver driver;
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
