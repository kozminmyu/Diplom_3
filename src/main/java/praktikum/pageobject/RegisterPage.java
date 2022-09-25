package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page object для страницы регистрации
public class RegisterPage {
    private WebDriver driver;

    // Локаторы полей регистрации пользователя
    private By name = By.xpath(".//fieldset[1]//input[@name = 'name']");
    private By email = By.xpath(".//fieldset[2]//input[@name = 'name']");
    private By password = By.xpath(".//input[@name = 'Пароль']");

    // Локатор для кнопки "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Локатор для сообщения "Некорректный пароль"
    private By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    // Локатор для ссылки "Войти"
    private By loginLink = By.xpath(".//a[@href='/login']");

    // Локатор значка загрузки страницы
    private By overlay = By.xpath(".//img[@alt='loading animation']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод вводит регистрационные данные
    @Step("Input registration data")
    public void inputRegistrationData(String inputName, String inputEmail, String inputPassword) {
        driver.findElement(name).sendKeys(inputName);
        driver.findElement(email).sendKeys(inputEmail);
        driver.findElement(password).sendKeys(inputPassword);
    }

    // Метод нажатия на кнопкку "Зарегистрироваться"
    @Step("Click register button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // Метод ожидания появления ошибки регистрации
    @Step("Wait for an error")
    public void waitForInputError (){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    // Метод нажатия на ссылку "Войти"
    @Step("Click login link")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Метод ожидания загрузки страницы
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Зарегистрировать", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for registration page to load")
    public void waitForLoadPage (){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(registerButton));
    }
}
