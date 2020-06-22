package GUI;

import CoreGraphicsRT.App;
import CoreGraphicsRT.GraphicsCore;
import CoreGraphicsRT.ObjectsRT.SceneRT;
import ErrorRT.ErrorManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class MWLoadScene {
    private static final int modalWindowSize = 700;
    private static final int textSize = 15;

    public static void newWindow(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //создаем Label
        Label label = new Label("Загрузить новую сцену \n");
        label.setFont(new Font("Arial", textSize));
        label.setTextFill(Color.web("#000000"));
        label.setMaxWidth(modalWindowSize * 0.8);
        label.setWrapText(true);

        //поле для ввода
        TextField pathTF = new TextField("Путь до xml файла со сценой");
        pathTF.setPrefColumnCount(15);

        //выбор файла
        FileChooser fileChooser = new FileChooser();
        Button buttonChoose = new Button("Выбрать файл");
        buttonChoose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pathTF.clear();
                File file = fileChooser.showOpenDialog(window);
                if (file != null) {
                    pathTF.setText(file.toString());
                    SceneRT sceneRT = DomXML.parseXML(file);
                    GraphicsCore.setSceneRT(sceneRT);
                    App.reProcessing();
                } else {
                    ErrorManager.printError("Файл невозможно открыть!");
                }
            }
        });

        //Создаем панель компоновки
        //размещаем все на панели компоновки
        FlowPane root = new FlowPane(Orientation.VERTICAL, 20, 20, label, pathTF, buttonChoose);
        root.setAlignment(Pos.CENTER);

        //Размещаем все на сцене
        Scene scene = new Scene(root, modalWindowSize, modalWindowSize);
        window.setScene(scene);
        window.setTitle(title);
        window.showAndWait();
    }
}
