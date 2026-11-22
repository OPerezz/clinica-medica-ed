package edu.curso.View;

import java.time.format.DateTimeFormatter;

import edu.curso.Controller.AtendimentoController;
import edu.curso.Entity.Atendimento;
import javafx.beans.binding.Bindings;
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


public class AtendimentoView {
    BorderPane centralAtendPane;
    
    AtendimentoController atendControl = new AtendimentoController();
    VBox vboxTop = new VBox(20);
    Button voltarButton = new Button("Voltar");
    Pane pane = new Pane();
    TableView<Atendimento> table = new TableView<>();

    


    public BorderPane centralPane() {
        if(centralAtendPane == null){
        centralAtendPane  = new BorderPane();
        Button cadastarAtendimento = new Button("Cadastrar");
        Button alterarAtendimento = new Button("Alterar");
        Button procurarAtendimento = new Button("Procurar");
        Button deletarAtendimento = new Button("Deletar");
        TilePane tilePaneAtendimento = new TilePane();

        tilePaneAtendimento.getChildren().addAll(cadastarAtendimento, alterarAtendimento, procurarAtendimento,
                deletarAtendimento);
        pane.getChildren().add(tilePaneAtendimento);
        vboxTop.getChildren().addAll(voltarButton, pane, table);

        voltarButton.setOnMouseClicked(event -> {
            inicioAtendimentoCentralBorderPane(tilePaneAtendimento);
        });

        cadastarAtendimento.setOnMouseClicked(event -> {
            cadastrarView();
        });

        alterarAtendimento.setOnMouseClicked(event -> {
            alterarView();
        });

        deletarAtendimento.setOnMouseClicked(event -> {
            deletarView();
        });

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        TableColumn<Atendimento, Long> colId = new TableColumn<>("Nº");
        TableColumn<Atendimento, String> colMedico = new TableColumn<>("Nome do Medico");
        TableColumn<Atendimento, String> colPaciente = new TableColumn<>("Nome do Paciente");
        TableColumn<Atendimento, String> colData = new TableColumn<>("Data da Consulta");

        colMedico.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getMedico().getNome()));

        colPaciente.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getPaciente().getNome()));

        colId.setCellValueFactory(item -> new ReadOnlyLongWrapper(
                item.getValue().getId()).asObject());

        colData.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getData().format(formato)));

        table.getColumns().addAll(colId, colMedico, colPaciente, colData);

            table.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY
            );

        
        centralAtendPane.setCenter(vboxTop);

        }

        return centralAtendPane;
    }

    public void inicioAtendimentoCentralBorderPane(TilePane tilePaneAtendimento) {
        pane.getChildren().clear();
        pane.getChildren().add(tilePaneAtendimento);
    }

    public void cadastrarView() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        VBox vboxCadastrar = new VBox(20);
        Button buttonSalvar = new Button("Salvar");

        Label textId = new Label("ID Medico:");
        TextField fieldId = new TextField();

        Label textMedico = new Label("ID Medico:");
        TextField fieldMedico = new TextField();

        Label textPaciente = new Label("ID Paciente");
        TextField fieldPaciente = new TextField();

        Label textData = new Label("Data da Consulta");
        TextField fieldData = new TextField();

        
        Bindings.bindBidirectional(fieldId.textProperty(), atendControl.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldMedico.textProperty(), atendControl.medicoIdProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldPaciente.textProperty(), atendControl.pacienteIdProperty(), new NumberStringConverter());

        gridPane.add(textId, 0, 0);
        gridPane.add(fieldId, 1, 0);
        gridPane.add(textMedico, 0, 1);
        gridPane.add(fieldMedico, 1, 1);
        gridPane.add(textPaciente, 0, 2);
        gridPane.add(fieldPaciente, 1, 2);
        gridPane.add(textData, 0, 3);
        gridPane.add(fieldData, 1, 3);

        vboxCadastrar.getChildren().addAll(gridPane, buttonSalvar);

        buttonSalvar.setOnMouseClicked(event -> {
            atendControl.agendar();
        });

        pane.getChildren().clear();
        pane.getChildren().add(vboxCadastrar);
    }

    public void alterarView() {
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();
        Button procurarId = new Button("ALTERAR");

        Label txtID = new Label("ID do Atendimento");
        TextField fieldID = new TextField();

        gridPane.add(txtID, 0, 0);
        gridPane.add(fieldID, 1, 0);

        vbox.getChildren().addAll(gridPane, procurarId);

        pane.getChildren().clear();
        pane.getChildren().add(vbox);
    }

    public void deletarView() {
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();
        Button procurarId = new Button("DELETAR");

        Label txtID = new Label("ID do Atendimento");
        TextField fieldID = new TextField();

        gridPane.add(txtID, 0, 0);
        gridPane.add(fieldID, 1, 0);

        vbox.getChildren().addAll(gridPane, procurarId);

        pane.getChildren().clear();
        pane.getChildren().add(vbox);
    }
}
