package ErrorRT;

import CoreGraphicsRT.App;

public class ErrorManager {
    public static void printError(String str) {
        System.out.println("Error: " + str);
        App.setError(str); //выводим ошибку в текстовое поле
    }
}
