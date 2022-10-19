package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    // локатор для кнопки 'Восстановить'
    public final By restorePasswordButton = By.xpath(".//button[text()='Восстановить']");
    // локатор для ссылки 'Войти'
    public final By signInLink = By.linkText("Войти");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return URL;
    }

    public void waitRestorePasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(restorePasswordButton));
    }

    public void clickSignInLink() {
        WebElement element = driver.findElement(signInLink);
        element.click();
    }
}
