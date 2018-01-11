package pl.edu.agh.to2.DreamLogoIDE;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to2.DreamLogoIDE.controller.LogoAppController;

public class Main extends Application {
    private Stage primaryStage;
    private LogoAppController logoAppController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DreamLogoIDE");

        this.logoAppController = new LogoAppController(primaryStage);
        this.logoAppController.initRootLayout();
    }
}
