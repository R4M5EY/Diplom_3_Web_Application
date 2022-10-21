package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    // локатор кнопки "Войти в аккаунт"
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    // локатор для кнопки 'Войти в аккаунт'
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // локатор для кнопки 'Оформить заказ'
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    // локатор для ссылки 'Личный кабинет'
    private final By accountButton = By.linkText("Личный Кабинет");
    // локатор для выбраного раздела
    private final By activeTab = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");
    // локатор для раздела 'Булки'
    private final By bunsTab = By.xpath(".//div/span[text()='Булки']");
    // локатор для раздела 'Соусы'
    private final By saucesTab = By.xpath(".//div/span[text()='Соусы']");
    // локатор для раздела 'Начинки'
    private final By fillingsTab = By.xpath(".//div/span[text()='Начинки']");
    private final By constructorLink = By.linkText("Конструктор");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return URL;
    }

    public void waitSignIn() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }
    public void waitOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    public void clickConstructorLink() {
        WebElement element = driver.findElement(constructorLink);
        element.click();
    }

    public void clickLogo() {
        WebElement element = driver.findElement(constructorLink);
        element.click();
    }

    public void clickSignInButton() {
        WebElement element = driver.findElement(signInButton);
        element.click();
    }

    public void clickAccountButton() {
        WebElement element = driver.findElement(accountButton);
        element.click();
    }

    public String getTextActiveTab() {
        WebElement element = driver.findElement(activeTab);
        return element.getText();
    }

    public void clickBuns() {
        WebElement element = driver.findElement(bunsTab);
    }

    public void clickSauces() {
        WebElement element = driver.findElement(saucesTab);
        element.click();
    }

    public void clickFillings() {
        WebElement element = driver.findElement(fillingsTab);
        element.click();
    }
}
