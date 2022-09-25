package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page object для страницы авторизации
public class LoginPage {
    private WebDriver driver;

    // Локатор для кнопки "Восстановить"
    private By loginButton = By.xpath(".//button[text()='Войти']");

    // Локаторы полей авторизации пользователя
    private By email = By.xpath(".//fieldset//input[@name = 'name']");
    private By password = By.xpath(".//input[@name = 'Пароль']");

    // Локатор значка загрузки страницы
    private By overlay = By.xpath(".//img[@alt='loading animation']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод нажатия на кнопкку "Войти"
    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Метод вводит данных для авторизации
    @Step("Input login data")
    public void inputLoginData(String inputEmail, String inputPassword) {
        driver.findElement(email).sendKeys(inputEmail);
        driver.findElement(password).sendKeys(inputPassword);
    }

    // Метод ожидания загрузки страницы
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Войти", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for login page to load")
    public void waitForLoadPage (){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}
