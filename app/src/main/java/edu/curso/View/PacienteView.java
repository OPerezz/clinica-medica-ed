package edu.curso.View;


import edu.curso.Controller.PacienteController;
import edu.curso.Entity.Paciente;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
            HBox hbox = new HBox(30);
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

            procurar.setOnMouseClicked(event -> {
                procurar();
            });

            alterar.setOnMouseClicked(event -> {
                alterar();
            });

            deletar.setOnMouseClicked(event -> {
                deletar();
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

            table.setItems(pacControl.getLista());

            hbox.getChildren().addAll(voltar, pane);

            vbox.getChildren().addAll(title, hbox, table);

            centralPacientePane.setCenter(vbox);

            centralPacientePane.setPadding(new Insets(50,20,10,20));
        }

        return centralPacientePane;
    }

    public void montarForm(ModoFormulario modo){
        GridPane gridPane = new GridPane();
        gridPane.getChildren().clear();

        Label txtId = new Label("Id");
        TextField fieldId = new TextField();
        fieldId.setEditable(false);

        
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

        Bindings.bindBidirectional(fieldId.textProperty(), pacControl.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldNome.textProperty(), pacControl.nomeProperty());
        Bindings.bindBidirectional(fieldEmail.textProperty(), pacControl.emailProperty());
        Bindings.bindBidirectional(fieldCPF.textProperty(), pacControl.cpfProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldIdade.textProperty(), pacControl.idadeProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldEndereco.textProperty(), pacControl.enderecoProperty());
        Bindings.bindBidirectional(fieldProblema.textProperty(), pacControl.problemaProperty());


        // gridPane.add(txtId, 0, 0);
        // gridPane.add(fieldId, 1, 0);

        
        gridPane.add(txtNome, 0, 1);
        gridPane.add(fieldNome, 1, 1);

        gridPane.add(txtEmail, 3, 1);
        gridPane.add(fieldEmail, 4, 1);

        
        gridPane.add(txtCPF, 0, 2);
        gridPane.add(fieldCPF, 1, 2);

        
        gridPane.add(txtIdade, 3, 2);
        gridPane.add(fieldIdade, 4, 2);

        
        gridPane.add(txtEndereco, 0, 3);
        gridPane.add(fieldEndereco, 1, 3);

        
        gridPane.add(txtProblema, 3, 3);
        gridPane.add(fieldProblema, 4, 3);

        gridPane.setVgap(15);
        gridPane.setHgap(15);


        pane.getChildren().clear();
        pane.getChildren().add(gridPane);

        switch (modo){
            case CADASTRAR:
                    Button salvar = new Button("Salvar");
                    gridPane.add(salvar,0,5);
                    salvar.setOnMouseClicked(event -> {
                        pacControl.criar();
                    });
                    break;

            case PROCURAR:
                    break;

            case ALTERAR:
                    Button alterar = new Button("Alterar");
                    alterar.setOnMouseClicked(event -> {
                        pacControl.alterar();
                    });
                    gridPane.add(alterar, 0, 5);
                    break;

            case DELETAR:
                    Button deletar = new Button("Deletar");
                    deletar.setOnMouseClicked(event -> {
                        pacControl.deletar();
                    });
                    gridPane.add(deletar,0,5);
                    break;
            }
    }

    public void voltar(TilePane tilePane){
        pacControl.limparDados();
        pane.getChildren().clear();
        pane.getChildren().add(tilePane);
    }

    public void cadastrar(){
        montarForm(ModoFormulario.CADASTRAR);
    }

    public void procurar(){
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();

        gridPane.add(txtId, 0,0);
        gridPane.add(fieldId, 1,0);


        gridPane.setVgap(15);
        gridPane.setHgap(15);


        Bindings.bindBidirectional(fieldId.textProperty(), pacControl.idProperty(), new NumberStringConverter());

        Button procurar = new Button("Procurar");

        gridPane.add(procurar, 0,2);

        procurar.setOnMouseClicked(event -> {
            if (pacControl.procurar()) {
                montarForm(ModoFormulario.PROCURAR);
            } else {
                new Alert(Alert.AlertType.WARNING, "Paciente não encontrado")
                    .showAndWait();
            }
        });

        pane.getChildren().clear();
        pane.getChildren().add(gridPane);
    }

    public void alterar(){
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();
        
        Bindings.bindBidirectional(fieldId.textProperty(), pacControl.idProperty(), new NumberStringConverter());

        gridPane.add(txtId,0,0);
        gridPane.add(fieldId,0,1);
        
        gridPane.setVgap(15);
        gridPane.setHgap(15);


        Button procurar = new Button("Procurar");
        gridPane.add(procurar, 0,2);


        procurar.setOnMouseClicked(event -> {
            pacControl.procurar();
            montarForm(ModoFormulario.ALTERAR);
        });
        
        pane.getChildren().clear();
        pane.getChildren().add(gridPane);
    }

    public void deletar(){
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();
        
        Bindings.bindBidirectional(fieldId.textProperty(), pacControl.idProperty(), new NumberStringConverter());

        gridPane.add(txtId,0,0);
        gridPane.add(fieldId,0,1);
        
        gridPane.setVgap(15);
        gridPane.setHgap(15);


        Button procurar = new Button("Procurar");

        gridPane.add(procurar, 0,2);

        procurar.setOnMouseClicked(event -> {
            pacControl.procurar();
            montarForm(ModoFormulario.DELETAR);
        });
        
        pane.getChildren().clear();
        pane.getChildren().add(gridPane);
    }

    private enum ModoFormulario {
        CADASTRAR,
        PROCURAR,
        ALTERAR,
        DELETAR
    }
}
