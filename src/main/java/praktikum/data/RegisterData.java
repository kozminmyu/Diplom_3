package praktikum.data;

// JSON входных данных для метода регистрации
public class RegisterData {
    private String email;
    private String password;
    private String name;

    public RegisterData() {
    }

    public RegisterData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
