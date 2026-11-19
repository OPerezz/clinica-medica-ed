package edu.curso.View;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.curso.Entity.*;

import java.time.format.DateTimeFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.layout.GridPane;;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class AtendimentoView {
    public BorderPane centralPane() {
        TableView<Atendimento> tableViewAtendimento = new TableView<>();
        BorderPane centralAtendPane = new BorderPane();
        VBox vbox = new VBox();
        GridPane gridPane = new GridPane();


        
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
            item.getValue().getData().format(formato)
        ));

        tableViewAtendimento.getColumns().addAll(colId, colMedico, colPaciente, colData);

        Label teste = new Label("Teste");
        centralAtendPane.setCenter(tableViewAtendimento);

        return centralAtendPane;
    }
}
