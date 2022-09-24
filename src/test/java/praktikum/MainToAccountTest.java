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
import praktikum.pageobject.AccountPage;
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.MainPage;

import static praktikum.constant.EndpointConstant.CHROME_DRIVER;
import static praktikum.constant.EndpointConstant.LOGIN_PAGE_URL;

// Переход в личный кабинет
// Проверь переход по клику на «Личный кабинет».
public class MainToAccountTest {
    private UserClient userClient = new UserClient();
    private WebDriver driver;

    @Before
    public void setUp() {
        userClient.setUp();

        RegisterData registerData = new RegisterData("testemail1@test.com", "testpassword", "testname1");
        userClient.register(registerData);
    }

    // Переход на вкладку "Личный кабинет" в Chrome
    @Test
    @DisplayName("Open account page in Chrome")
    public void MainToAccountChrome() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
        loginPage.inputLoginData("testemail1@test.com", "testpassword");
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPageAuth();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.waitForLoadPage();
    }

    // Переход на вкладку "Личный кабинет" в Firefox
    @Test
    @DisplayName("Open account page in Firefox")
    public void MainToAccountFirefox() {
        driver = new FirefoxDriver();
        driver.get(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
        loginPage.inputLoginData("testemail1@test.com", "testpassword");
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPageAuth();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.waitForLoadPage();
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
