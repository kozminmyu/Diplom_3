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
import praktikum.pageobject.LoginPage;
import praktikum.pageobject.RegisterPage;

import static praktikum.constant.EndpointConstant.*;

// Регистрация
// Проверь:
// - Успешную регистрацию.
// - Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
public class RegisterTest {
    private UserClient userClient = new UserClient();
    private WebDriver driver;

    // Признак необходимости удаления пользователя
    private boolean shouldDeleteUser;

    @Before
    public void setUp() {
        userClient.setUp();
    }

    // Успешная регистрация в Chrome
    @Test
    @DisplayName("Succesful registration in Chrome")
    public void RegisterChrome() {
        shouldDeleteUser = true;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.inputRegistrationData("testname1", "testemail1@test.com", "testpassword");
        registerPage.clickRegisterButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Успешная регистрация в Firefox
    @Test
    @DisplayName("Successful registration in Firefox")
    public void RegisterFirefox() {
        shouldDeleteUser = true;

        driver = new FirefoxDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.inputRegistrationData("testname1", "testemail1@test.com", "testpassword");
        registerPage.clickRegisterButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoadPage();
    }

    // Регистрация с коротким паролем в Chrome
    @Test
    @DisplayName("Registration with short password in Chrome")
    public void RegisterPasswordErrorChrome() {
        shouldDeleteUser = false;

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        driver = new ChromeDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.inputRegistrationData("testname1", "testemail1@test.com", "short");
        registerPage.clickRegisterButton();
        registerPage.waitForInputError();
    }

    // Регистрация с коротким паролем в Firefox
    @Test
    @DisplayName("Registration with short password in Firefox")
    public void RegisterPasswordErrorFirefox() {
        shouldDeleteUser = false;

        driver = new FirefoxDriver();
        driver.get(REGISTER_PAGE_URL);

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.waitForLoadPage();
        registerPage.inputRegistrationData("testname1", "testemail1@test.com", "short");
        registerPage.clickRegisterButton();
        registerPage.waitForInputError();
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
