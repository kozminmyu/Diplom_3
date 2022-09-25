package praktikum.constant;

public class EndpointConstant {
    // Путь к драйверу Chrome
    public static final String CHROME_DRIVER = "/WebDriver/bin/chromedriver.exe";

    // URL-ы страниц для Selenium
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final String RECOVERY_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // URL-ы методов API
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_URI = "/api/auth/register";
    public static final String LOGIN_URI = "/api/auth/login";
    public static final String USER_URI = "/api/auth/user";
}
