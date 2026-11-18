package edu.curso.View;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;



public class AtendimentoView {
    public BorderPane centralPane(){
        BorderPane centralAtendPane = new BorderPane();

        Label teste = new Label("Teste");
        centralAtendPane.setCenter(teste);


        return centralAtendPane;
    }
}
