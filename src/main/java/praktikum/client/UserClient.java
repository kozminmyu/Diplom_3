package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import praktikum.data.LoginData;
import praktikum.data.RegisterData;

import static io.restassured.RestAssured.given;
import static praktikum.constant.EndpointConstant.*;

// Вспомогательные методы REST API
public class UserClient {

    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    // Регистрация пользователя
    @Step("Register user")
    public Response register(RegisterData registerData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(registerData)
                .when()
                .post(REGISTER_URI);
    }

    // Авторизация пользователя
    public Response login(LoginData loginData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginData)
                .when()
                .post(LOGIN_URI);
    }

    // Удаление пользователя
    @Step("Delete user")
    public Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(USER_URI);
    }

    // Получение токена из ответа
    public String getAccessToken(Response response) {
        return response
                .then()
                .extract()
                .body()
                .path("accessToken");
    }
}
