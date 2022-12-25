import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String login;
        String password;
        String confirmPassword;
        System.out.println("Симуляция регистрации пользователя");
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.print("Введите логин: ");
            login = scanner.next();
            System.out.print("Введите пароль: ");
            password = scanner.next();
            System.out.print("Введите пароль повторно: ");
            confirmPassword = scanner.next();
        } while (!inputValidation(login, password, confirmPassword));
        System.out.println("Регистрация прошла успешно!");
    }

    public static boolean inputValidation(String login, String password, String confirmPassword) {
        String regexLogin = "^[a-zA-Z0-9!?<>@#$%^&*()_+~`,.]{8,}+$";
        String regexPassword = "^[a-zA-Z0-9_]{8,}+$";
        boolean result = true;
//        String regexLogin = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=?`~]).{8,}+$";

        try {
            if (!login.matches(regexLogin)) {
                result = false;
                throw new WrongLoginException("Неверно введен логин! Логин должен содержать только\n" +
                        "латинские буквы, цифры и спецсимволы. Длина логина должна быть не меньше 8 символов");
            }
            if (!password.matches(regexPassword)) {
                result = false;
                throw new WrongPasswordException("Неверно введен пароль! Пароль должен содержать только\n" +
                        "латинские буквы, цифры и знак подчеркивания. Длина пароля должна быть не меньше 8 символов");
            }
            if (!password.equals(confirmPassword)){
                result = false;
                throw new WrongPasswordException("Пароли не совпадают!");
            }
        } catch (WrongLoginException | WrongPasswordException exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }
}