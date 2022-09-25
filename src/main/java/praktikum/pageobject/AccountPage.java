package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page object для страницы личного кабинета
public class AccountPage {
    private WebDriver driver;

    // Локатор для кнопки "Сохранить"
    private By saveButton = By.xpath(".//button[text()='Сохранить']");

    // Локатор значка загрузки страницы
    private By overlay = By.xpath(".//img[@alt='loading animation']");

    // Локатор для логотипа Stellar Burgers
    private By logo = By.xpath(".//div/a[@href='/']");

    // Локатор для ссылки "Конструктор"
    private By constructorLink = By.xpath(".//div//ul/li/a[@href='/']");

    // Локатор для кнопки "Сохранить"
    private By logoutButton = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод нажимает на логотип
    @Step("Click logo")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    // Метод нажимает на ссылку "Конструктор"
    @Step("Click constructor link")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    // Метод нажимает на кнопку "Выйти"
    @Step("Click logout button")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    // Метод ожидания загрузки страницы
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Сохранить", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for account page to load")
    public void waitForLoadPage (){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(saveButton));
    }
}
