package CoreGraphicsRT;

import GUI.MWAuthorship;
import GUI.MWControl;
import GUI.MWDescription;
import GUI.MWLoadScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import Time.*;

public class App extends Application {
    //----------------------------------------------------------------------------------------
    private static Boolean reTraсe = true;

    private static PixelWriter raster; //графический буфер
    private static Label labelFPS;
    private static Label errorLabel = new Label();

    protected static GraphicsCore graphCore = new GraphicsCore(); //графическое ядро
    //----------------------------------------------------------------------------------------

    public static void mainApp(String[] args) throws Exception {
        launch(args);
    }

    //----------------------------------------------------------------------------------------
    protected static void drawInGraphicsBuffer(int x, int y, int colorARGB) {
        raster.setArgb(x, y, colorARGB);
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    @Override
    public void start(Stage primaryStage) {
        //--------------------------------------------------
        //Создаем группу (основной контейнер)
        Group group = new Group();

        //Создаем холст
        final javafx.scene.canvas.Canvas canvas = new Canvas(graphCore.getCanvasW(), graphCore.getCanvasH());
        raster = canvas.getGraphicsContext2D().getPixelWriter();
        group.getChildren().add(canvas); //паттерн обсервер

        //создаем Label
        labelFPS = new Label();
        labelFPS.setFont(new Font("Arial", 20));
        labelFPS.setTextFill(Color.web("#FFFFFF"));
        labelFPS.setTranslateX(5);
        labelFPS.setTranslateY(5);
        group.getChildren().add(labelFPS);

        //--------------------------------------------------
        // Создание меню
        MenuBar menuBar = new MenuBar();

        Menu infoMenu = new Menu("Информация");
        MenuItem controlItem = new MenuItem("Управление");
        MenuItem authorshipItem = new MenuItem("Авторство");
        MenuItem descripItem = new MenuItem("Как задать сцену?");
        infoMenu.getItems().addAll(controlItem, authorshipItem, descripItem);
        controlItem.setOnAction(event -> MWControl.newWindow("Управление"));
        authorshipItem.setOnAction(event -> MWAuthorship.newWindow("Авторство"));
        descripItem.setOnAction(event -> MWDescription.newWindow("Как задать сцену?"));

        Menu sceneMenu = new Menu("Сцена");
        MenuItem loadItem = new MenuItem("Загрузка");
        sceneMenu.getItems().addAll(loadItem);
        loadItem.setOnAction(event -> MWLoadScene.newWindow("Загрузка новой сцены"));

        menuBar.getMenus().addAll(infoMenu, sceneMenu);

        //--------------------------------------------------
        //окно вывода ошибок
        errorLabel = new Label("Окно вывода ошибок");
        errorLabel.setWrapText(true);
        errorLabel.setMaxHeight(100);
        errorLabel.setPrefHeight(50);

        //--------------------------------------------------
        //Создаем панель компоновки
        //размещаем все на панели компоновки
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(group);
        root.setBottom(errorLabel);

        //--------------------------------------------------
        //Scene - это содержание stage, сцена содержит в себе различные контейнеры с информацией
        Scene scene = new Scene(root);
        CatchAction.keyboardCapture(scene);

        //Stage - начальное полотно, которое содержит в себе все остальные компоненты, в программе их может быть >= 1
        primaryStage.setTitle(graphCore.getAppName());
        primaryStage.setMaxWidth(1440);
        primaryStage.setMaxHeight(1080);
        primaryStage.centerOnScreen();

        primaryStage.setScene(scene);

        primaryStage.show();
        mainLoop();
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public static void reProcessing() {
        reTraсe = true;
    }

    public static void setError(String str) {
        errorLabel.setText(str);
    }

    private void mainLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (reTraсe) {
                    Timer.startTimer();
                    graphCore.processing();
                    Timer.stopTimer();
                    labelFPS.setText(FPS.calculateFPS(Timer.getTimeSeconds()));
                    reTraсe = false;
                }
            }
        };
        timer.start();
    }
    //----------------------------------------------------------------------------------------
}
