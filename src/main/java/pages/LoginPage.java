package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";
    // локатор для кнопки 'Войти'
    public final By signInButton = By.xpath(".//button[text()='Войти']");
    // локатор для поля 'Email'
    private final By emailInput = By.xpath(".//fieldset[1]//input");
    // локатор для поля 'Пароль'
    private final By passwordInput = By.xpath(".//fieldset[2]//input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return URL;
    }
    public void waitSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }

    public void sendKeysEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.sendKeys(email);
    }

    public void sendKeysPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.sendKeys(password);
    }
    public void clickSignInButton() {
        WebElement element = driver.findElement(signInButton);
        element.click();
    }
}
