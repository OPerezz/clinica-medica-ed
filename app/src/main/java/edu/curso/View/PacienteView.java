package edu.curso.View;


import edu.curso.Entity.Paciente;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PacienteView {
    BorderPane centralPacientePane;

    public BorderPane centralPane(){
        
        if(centralPacientePane == null){
            centralPacientePane = new BorderPane(); 

            VBox vbox = new VBox(20);
            TilePane tilePane = new TilePane();
            TableView<Paciente> table = new TableView<>();

            Button voltar = new Button("Voltar");

            Button cadastrar = new Button("Cadastrar");
            Button alterar = new Button("Alterar");
            Button procurar = new Button("Procurar");
            Button deletar = new Button("Deletar");

            tilePane.getChildren().addAll(cadastrar, alterar, procurar, deletar);

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
            

            vbox.getChildren().addAll(voltar, tilePane, table);

            centralPacientePane.setCenter(vbox);
        }

        return centralPacientePane;
    }
}
