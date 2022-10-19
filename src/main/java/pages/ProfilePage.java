package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    // локатор для ссылки 'Профиль'
    private final By profileLink = By.linkText("Профиль");
    // локатор для кнопки 'Выход'
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return URL;
    }

    public void waitProfileLink() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
    }

    public void clickExitLink() {
        WebElement element = driver.findElement(exitButton);
        element.click();
    }
}
