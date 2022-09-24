package praktikum;

import com.sun.tools.javac.Main;
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
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.MainPage;
import praktikum.pageobject.RecoveryPage;
import praktikum.pageobject.RegisterPage;

import static praktikum.constant.EndpointConstant.*;

// Вход
// Проверь:
// - Вход по кнопке «Войти в аккаунт» на главной,
// - Вход через кнопку «Личный кабинет»,
// - Вход через кнопку в форме регистрации,
// - Вход через кнопку в форме восстановления пароля.
public class LoginTest {
    private UserClient userClient = new UserClient();
    private WebDriver driver;

    // Признак необходимости удаления пользователя
    private boolean shouldDeleteUser;

    @Before
    public void setUp() {
        userClient.setUp();
    }

    // Переход с главной страницы на форму входа через кнопку "Войти в аккаунт" в Chrome
    @Test
    @DisplayName("Open login page through 'Login into account' button on main page in Chrome")
    public void OpenLoginPageFromMainChrome() {
        shouldDeleteUser = false;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через кнопку "Войти в аккаунт" в Firefox
    @Test
    @DisplayName("Open login page through 'Login into account' button on main page in Firefox")
    public void OpenLoginPageFromMainFirefox() {
        shouldDeleteUser = false;

        driver = new FirefoxDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через кнопку "Личный кабинет" в Chrome
    @Test
    @DisplayName("Open login page through 'Personal account' button on main page in Chrome")
    public void OpenLoginPageFromAccountChrome() {
        shouldDeleteUser = false;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через кнопку "Личный кабинет" в Firefox
    @Test
    @DisplayName("Open login page through 'Personal account' button on main page in Firefox")
    public void OpenLoginPageFromAccountFirefox() {
        shouldDeleteUser = false;

        driver = new FirefoxDriver();
        driver.get(MAIN_PAGE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPage();
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через форму регистрации в Chrome
    @Test
    @DisplayName("Open login page through registration page in Chrome")
    public void OpenLoginPageFromRegistrationChrome() {
        shouldDeleteUser = false;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через форму регистрации в Firefox
    @Test
    @DisplayName("Open login page through registration page in Firefox")
    public void OpenLoginPageFromRegistrationFirefox() {
        shouldDeleteUser = false;

        driver = new FirefoxDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через форму восстановления пароля в Chrome
    @Test
    @DisplayName("Open login page through password recovery page in Chrome")
    public void OpenLoginPageFromRecoveryChrome() {
        shouldDeleteUser = false;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(RECOVERY_PAGE_URL);

        RecoveryPage recoveryPage = new RecoveryPage(driver);

        recoveryPage.waitForLoadPage();
        recoveryPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Переход с главной страницы на форму входа через форму восстановления пароля в Firefox
    @Test
    @DisplayName("Open login page through password recovery page in Firefox")
    public void OpenLoginPageFromRecoveryFirefox() {
        shouldDeleteUser = false;

        driver = new FirefoxDriver();
        driver.get(RECOVERY_PAGE_URL);

        RecoveryPage recoveryPage = new RecoveryPage(driver);

        recoveryPage.waitForLoadPage();
        recoveryPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Авторизация в Chrome
    @Test
    @DisplayName("Login in Chrome")
    public void LoginChrome() {
        shouldDeleteUser = true;

        // Регистрация пользователя
        // Перенесена в тест, так как используется только в двух тестах
        RegisterData registerData = new RegisterData("testemail1@test.com", "testpassword", "testname1");
        userClient.register(registerData);

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
        loginPage.inputLoginData("testemail1@test.com", "testpassword");
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPageAuth();
    }

    // Авторизация в Firefox
    @Test
    @DisplayName("Login in Firefox")
    public void LoginFirefox() {
        shouldDeleteUser = true;

        // Регистрация пользователя
        // Перенесена в тест, так как используется только в двух тестах
        RegisterData registerData = new RegisterData("testemail1@test.com", "testpassword", "testname1");
        userClient.register(registerData);

        driver = new FirefoxDriver();
        driver.get(LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
        loginPage.inputLoginData("testemail1@test.com", "testpassword");
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);

        mainPage.waitForLoadPageAuth();
    }

    // Пост-обработка
    @After
    public void tearDown() {
        // Метод закрывает браузер
        driver.quit();

        // Удаление пользователя добавлено в пост-обработку, чтобы удаление происходило после закрытия браузера
        if(shouldDeleteUser) {
            // Получение токена
            LoginData loginData = new LoginData("testemail1@test.com", "testpassword");
            Response response = userClient.login(loginData);
            String accessToken = userClient.getAccessToken(response);

            // Удаление созданного пользователя
            userClient.delete(accessToken);
        }
    }
}
