package praktikum.pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page object для главной страницы/конструктора
public class MainPage {
    private WebDriver driver;

    // Локатор для кнопки "Войти в аккаунт"
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    // Локатор для ссылки "Личный кабинет"
    private By accountLink = By.xpath(".//a[@href='/account']");

    // Локатор для кнопки "Оформиро заказ"
    private By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    // Локатор значка загрузки страницы
    private By overlay = By.xpath(".//img[@alt='loading animation']");

    // Локатор разделов конструктора
    private By buns = By.xpath(".//div[span[text()='Булки']]");
    private By sauces = By.xpath(".//div[span[text()='Соусы']]");
    private By fillings = By.xpath(".//div[span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод нажатия на кнопкку "Войти в аккаунт"
    @Step("Click 'Login into account' button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Метод нажатия на ссылку "Личный аккаунт"
    @Step("Click 'Personal account' button")
    public void clickAccountButton() {
        driver.findElement(accountLink).click();
    }

    // Методы выбора раздела конструктора
    @Step("Click 'Buns' selector")
    public void clickBuns() {
        driver.findElement(buns).click();
    }

    @Step("Click 'Sauces' selector")
    public void clickSauces() {
        driver.findElement(sauces).click();
    }

    @Step("Click 'Fillings' selector")
    public void clickFillings() {
        driver.findElement(fillings).click();
    }

    // Методы проверки классов конструктора
    // Так как все позиции в списке ингредиентов всегда остаются видимыми, то проверка осуществляется через сравнение класса с известным занчением
    // Название класса меняется в зависимости от того, выбран он или нет
    @Step("Check 'Buns' selector's class")
    public void checkBuns() {
        String realClass = driver.findElement(buns).getAttribute("class");
        String expectedClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

        Assert.assertEquals(expectedClass, realClass);
    }

    @Step("Check 'Sauces' selector's class")
    public void checkSauces() {
        String realClass = driver.findElement(sauces).getAttribute("class");
        String expectedClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

        Assert.assertEquals(expectedClass, realClass);
    }

    @Step("Check 'Fillings' selector's class")
    public void checkFillings() {
        String realClass = driver.findElement(fillings).getAttribute("class");
        String expectedClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

        Assert.assertEquals(expectedClass, realClass);
    }

    // Метод ожидания загрузки страницы для неавторизованного пользователя
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Войти в аккаунт", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for main page to load")
    public void waitForLoadPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    // Метод ожидания загрузки страницы для авторизованного пользователя
    // Загрузка определяется по видимости значка загрузки
    // Дополнительное условие на проверку доступности кнопки "Оформить заказ", чтобы удостовериться, что загрузилась нужная страница
    // Добавлена для тестов в Firefox с долгой загрузкой страницы
    @Step("Wait for main page to load (authorized)")
    public void waitForLoadPageAuth() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderButton));
    }

    // Метод ожидания для проверки конструктора
    // Firefox не дожидается, когда у кнопки изменится название класса
    public void justWait() {
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ignored){
        }
    }
}
