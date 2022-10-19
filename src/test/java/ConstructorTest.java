import extensions.DriverFactory;
import pages.HomePage;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;

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
    @DisplayName("Проверяем что работает переход к разделу «Булки»")
    public void shouldSwitchToBunsTab() {
        homePage.clickBuns();
        assertEquals("Булки", homePage.getTextActiveTab());
    }

    @Test
    @DisplayName("Проверяем что работает переход к разделу «Соусы»")
    public void shouldSwitchToSaucesTab() {
        homePage.clickSauces();
        assertEquals("Соусы", homePage.getTextActiveTab());
    }

    @Test
    @DisplayName("Проверяем что работает переход к разделу «Начинки»")
    public void shouldSwitchToFillingsTab() {
        homePage.clickFillings();
        assertEquals("Начинки", homePage.getTextActiveTab());
    }
}
