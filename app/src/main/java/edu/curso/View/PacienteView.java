package edu.curso.View;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class PacienteView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane panel = new Pane();
        Scene scene = new Scene(panel, 600, 400);

        stage.setTitle("Minha Clínica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
