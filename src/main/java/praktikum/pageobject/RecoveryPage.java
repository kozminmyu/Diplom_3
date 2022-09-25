package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page object для страницы восстановления пароля
public class RecoveryPage {
    private WebDriver driver;

    // Локатор для кнопки "Восстановить"
    private By recoverButton = By.xpath(".//button[text()='Восстановить']");

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для ссылки "Войти"
    private By loginLink = By.xpath(".//a[@href='/login']");

    // Локатор значка загрузки страницы
    private By overlay = By.xpath(".//img[@alt='loading animation']");

    // Метод нажатия на ссылку "Войти"
    @Step("Click login link")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Метод ожидания загрузки страницы
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Восстановить", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for password recovery page to load")
    public void waitForLoadPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(recoverButton));
    }
}
