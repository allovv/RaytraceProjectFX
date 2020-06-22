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

public class MWDescription {
    private static final int modalWindowSize = 700;
    private static final int textSize = 15;

    public static void newWindow(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //создаем Label
        Label label = new Label("Описание \n" +
                "\n" +
                "Сцена задается в файле формата XML  \n" +
                "\n" +
                "   Пример: \n" +
                "\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<scene>\n" +
                "   <objects>\n" +
                "      <sphere x=\"-40\" y=\"10\" z=\"-40\" color=\"Silver\" radius=\"10\"/>\n" +
                "      <sphere x=\"40\" y=\"10\" z=\"-40\" color=\"Silver\" radius=\"10\"/>\n" +
                "      <sphere x=\"-40\" y=\"10\" z=\"40\" color=\"Silver\" radius=\"10\"/>\n" +
                "      <sphere x=\"40\" y=\"10\" z=\"40\" color=\"Silver\" radius=\"10\"/>\n" +
                "   </objects>\n" +
                "   <lightings>\n" +
                "      <light intensity=\"0.9\" x=\"0\" y=\"10\" z=\"0\"/>\n" +
                "   </lightings>\n" +
                "   <view>\n" +
                "      <camera x=\"0\" y=\"30\" z=\"-180\"/>\n" +
                "   </view>\n" +
                "</scene>" +
                "\n" +
                "\n" +
                "Доступные цвета: \n" +
                "Black, White, Red, Green, Blue, Yellow, SpaceGrey, Silver"
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
