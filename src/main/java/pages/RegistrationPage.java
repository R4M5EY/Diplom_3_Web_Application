package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    private final String url = "https://stellarburgers.nomoreparties.site/register";
    // локатор для кнопки 'Зарегистрироваться'
    private final By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // локатор для поля 'Имя'
    private final By nameInput = By.xpath(".//fieldset[1]//input");
    // локатор для поля 'Email'
    private final By emailInput = By.xpath(".//fieldset[2]//input");
    // локатор для поля 'Пароль'
    private final By passwordInput = By.xpath(".//fieldset[3]//input");
    // локатор для ошибки 'Некорректный пароль'
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");
    // локатор для ссылки 'Войти'
    private final By signInLink = By.linkText("Войти");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getUrl() {
        return url;
    }

    public void waitRegButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(regButton));
    }

    public void sendKeysName(String name) {
        WebElement element = driver.findElement(nameInput);
        element.sendKeys(name);
    }

    public void sendKeysEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.sendKeys(email);
    }

    public void sendKeysPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.sendKeys(password);
    }

    public void clickRegButton() {
        WebElement element = driver.findElement(regButton);
        element.click();
    }

    public void waitError() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
    }

    public String getError() {
        WebElement element = driver.findElement(passwordError);
        return element.getText();
    }

    public void clickSignInLink() {
        WebElement element = driver.findElement(signInLink);
        element.click();
    }
}
