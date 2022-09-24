package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import praktikum.client.UserClient;
import praktikum.data.LoginData;
import praktikum.data.RegisterData;
import praktikum.pageobject.MainPage;

import static praktikum.constant.EndpointConstant.*;

// Раздел «Конструктор»
// Проверь, что работают переходы к разделам:
// - «Булки»,
// - «Соусы»,
// - «Начинки».
public class ConstructorTest {
    private UserClient userClient = new UserClient();
    private WebDriver driver;

    @Before
    public void setUp() {
        userClient.setUp();

        // Регистрация пользователя
        RegisterData registerData = new RegisterData("testemail1@test.com", "testpassword", "testname1");
        userClient.register(registerData);
    }

    // Переход на вкладку "Соусы" в Chrome
    @Test
    @DisplayName("Open 'Sauces' section in Chrome")
    public void ConstructorSaucesChrome() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickSauces();
        mainPage.justWait();
        mainPage.checkSauces();
    }

    // Переход на вкладку "Соусы" в Firefox
    @Test
    @DisplayName("Open 'Sauces' section in Firefox")
    public void ConstructorSaucesFirefox() {
        driver = new FirefoxDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickSauces();
        mainPage.justWait();
        mainPage.checkSauces();
    }

    // Переход на вкладку "Начинки" в Chrome
    @Test
    @DisplayName("Open 'Fillings' section in Chrome")
    public void ConstructorFillingsChrome() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickFillings();
        mainPage.justWait();
        mainPage.checkFillings();
    }

    // Переход на вкладку "Начинки" в Firefox
    @Test
    @DisplayName("Open 'Fillings' section in Firefox")
    public void ConstructorFillingsFirefox() {
        driver = new FirefoxDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickFillings();
        mainPage.justWait();
        mainPage.checkFillings();
    }

    // Переход на вкладку "Булки" в Chrome
    @Test
    @DisplayName("Open 'Buns' section in Chrome")
    public void ConstructorBunsChrome() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();

        // Сначала происходит переход на вкладку "Соусы", чтобы можно было вернуться на вкладку "Булки"
        mainPage.clickSauces();

        mainPage.clickBuns();
        mainPage.justWait();
        mainPage.checkBuns();
    }

    // Переход на вкладку "Булки" в Firefox
    @Test
    @DisplayName("Open 'Buns' section in Firefox")
    public void ConstructorBunsFirefox() {
        driver = new FirefoxDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();

        // Сначала происходит переход на вкладку "Соусы", чтобы можно было вернуться на вкладку "Булки"
        mainPage.clickSauces();

        mainPage.clickBuns();
        mainPage.justWait();
        mainPage.checkBuns();
    }

    // Пост-обработка
    @After
    public void tearDown() {
        // Метод закрывает браузер
        driver.quit();

        // Удаление пользователя добавлено в пост-обработку, чтобы удаление происходило после закрытия браузера
        // Получение токена
        LoginData loginData = new LoginData("testemail1@test.com", "testpassword");
        Response response = userClient.login(loginData);
        String accessToken = userClient.getAccessToken(response);

        // Удаление созданного пользователя
        userClient.delete(accessToken);
    }
}
