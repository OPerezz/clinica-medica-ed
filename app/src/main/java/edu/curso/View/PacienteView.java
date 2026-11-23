package edu.curso.View;


import edu.curso.Controller.PacienteController;
import edu.curso.Entity.Paciente;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class PacienteView {
    BorderPane centralPacientePane;
    Pane pane = new Pane();
    PacienteController pacControl = new PacienteController();

    public BorderPane centralPane(){
        
        if(centralPacientePane == null){
            centralPacientePane = new BorderPane(); 

            Label title = new Label("Paciente");
            VBox vbox = new VBox(30);
            TilePane tilePane = new TilePane();
            TableView<Paciente> table = new TableView<>();

            Button voltar = new Button("Voltar");

            Button cadastrar = new Button("Cadastrar");
            Button alterar = new Button("Alterar");
            Button procurar = new Button("Procurar");
            Button deletar = new Button("Deletar");

            cadastrar.setOnMouseClicked(event ->{
                cadastrar();
            });

            voltar.setOnMouseClicked(event -> {
                voltar(tilePane);
            });

            tilePane.getChildren().addAll(cadastrar, alterar, procurar, deletar);

            pane.getChildren().add(tilePane);

            TableColumn<Paciente, Long> colId = new TableColumn<>("Nº");
            TableColumn<Paciente, String> colNome = new TableColumn<>("Nome");
            TableColumn<Paciente, String> colEmail = new TableColumn<>("Email");
            TableColumn<Paciente, Long> colCPF = new TableColumn<>("CPF");
            TableColumn<Paciente, Integer> colIdade = new TableColumn<>("Idade");
            TableColumn<Paciente, String> colEndereco = new TableColumn<>("Endereço");
            TableColumn<Paciente, String> colProblema = new TableColumn<>("Problema");

            colId.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getId()).asObject());
            colNome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));
            colEmail.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getEmail()));
            colCPF.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getCpf()).asObject());
            colIdade.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getIdade()).asObject());
            colEndereco.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getEndereco()));
            colProblema.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getProblema()));

            table.getColumns().addAll(colId, colNome, colEmail, colCPF, colIdade, colEndereco, colProblema);

            vbox.getChildren().addAll(title, voltar, pane, table);

            centralPacientePane.setCenter(vbox);
        }

        return centralPacientePane;
    }

    public void voltar(TilePane tilePane){
        pane.getChildren().clear();
        pane.getChildren().add(tilePane);
    }

    public void cadastrar(){
        GridPane gridPane = new GridPane();

        Label txtId = new Label("Id");
        TextField fieldId = new TextField();

        
        Label txtNome = new Label("Nome");
        TextField fieldNome = new TextField();

        
        Label txtEmail = new Label("Email");
        TextField fieldEmail = new TextField();

        
        Label txtCPF = new Label("CPF");
        TextField fieldCPF = new TextField();

        Label txtIdade = new Label("Idade");
        TextField fieldIdade = new TextField();

        Label txtEndereco = new Label("Endereço");
        TextField fieldEndereco = new TextField();

        Label txtProblema = new Label("Problema");
        TextField fieldProblema = new TextField();


        gridPane.add(txtId, 0, 0);
        gridPane.add(fieldId, 1, 0);

        
        gridPane.add(txtNome, 0, 1);
        gridPane.add(fieldNome, 1, 1);

        gridPane.add(txtEmail, 0, 2);
        gridPane.add(fieldEmail, 1, 2);

        
        gridPane.add(txtCPF, 0, 3);
        gridPane.add(fieldCPF, 1, 3);

        
        gridPane.add(txtIdade, 0, 4);
        gridPane.add(fieldIdade, 1, 4);

        
        gridPane.add(txtEndereco, 0, 5);
        gridPane.add(fieldEndereco, 1, 5);

        
        gridPane.add(txtProblema, 0, 6);
        gridPane.add(fieldProblema, 1, 6);

        gridPane.setVgap(15);
        gridPane.setHgap(15);


        pane.getChildren().clear();
        pane.getChildren().add(gridPane);

        Binding.bindBidirectional(fieldId.textProperty(), pacControl.idPropert() new NumberStringConverter());

    }
}
