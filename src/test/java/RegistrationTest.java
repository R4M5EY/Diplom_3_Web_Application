import extensions.DriverFactory;
import pages.*;
import org.junit.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private final String name = "name";
    private String email, password;

    @Before
    public void setup() {
        driver = DriverFactory.getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
