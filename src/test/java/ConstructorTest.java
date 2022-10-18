import pages.HomePage;
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
public class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;
    private final String browser;

    public ConstructorTest(String browser) {
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
    public void shouldSwitchToSausecTab() {
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
