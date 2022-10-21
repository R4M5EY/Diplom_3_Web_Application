package extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getBrowser(){
        String browser;
        try {
            browser = System.getProperty("browser");
        } catch (RuntimeException e) {
            browser = "chrome";
        }

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "yandex":
                WebDriverManager.chromedriver().driverVersion("104.0.5112.20").setup();
                return new ChromeDriver(new ChromeOptions().setBinary("C:\\Users\\rohaw\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"));
            default:
                throw new RuntimeException("Открывает только Хром и Яндекс");
        }
    }
}
