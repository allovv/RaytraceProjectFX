package GUI;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MWControl {
    private static final int modalWindowSize = 700;
    private static final int textSize = 15;

    public static void newWindow(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //создаем Label
        Label label = new Label("Управление камерой \n" +
                "\n" +
                "Положение в горизонтальной плоскости: ← → ↑↓  \n" +
                "\n" +
                "   Влево ← \n" +
                "   Вправо → \n" +
                "   Вперед ↑ \n" +
                "   Назад ↓ \n" +
                "\n" +
                "Положение в вертикальной плоскости: Space, Tab \n" +
                "\n" +
                "   Вверх Space \n" +
                "   Вниз Tab"
        );
        label.setFont(new Font("Arial", textSize));
        label.setTextFill(Color.web("#000000"));
        label.setMaxWidth(modalWindowSize * 0.8);
        label.setWrapText(true);

        //Создаем панель компоновки
        //размещаем все на панели компоновки
        FlowPane root = new FlowPane(Orientation.VERTICAL, label);
        root.setAlignment(Pos.CENTER);

        //Размещаем все на сцене
        Scene scene = new Scene(root, modalWindowSize, modalWindowSize);
        window.setScene(scene);
        window.setTitle(title);
        window.showAndWait();
    }
}
