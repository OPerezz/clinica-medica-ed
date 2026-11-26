package edu.curso.View;

import java.time.format.DateTimeFormatter;

import org.kordamp.ikonli.javafx.FontIcon;

import edu.curso.Controller.AtendimentoController;
import edu.curso.Entity.Atendimento;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class AtendimentoView {
    BorderPane centralAtendPane;
    private TilePane tilePaneAtendimento;
    
    AtendimentoController atendControl = new AtendimentoController();
    VBox vboxTop = new VBox(20);
    Pane pane = new Pane();
    TableView<Atendimento> table = new TableView<>();


    public BorderPane centralPane() {
        if(centralAtendPane == null){
        centralAtendPane  = new BorderPane();
        centralAtendPane.setStyle("-fx-background-color: #8ac6d1;");
        Button cadastarAtendimento = new Button("Cadastrar");
        cadastarAtendimento.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        Button alterarAtendimento = new Button("Alterar");
        alterarAtendimento.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        Button procurarAtendimento = new Button("Procurar");
        procurarAtendimento.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        Button deletarAtendimento = new Button("Deletar");
        deletarAtendimento.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        tilePaneAtendimento = new TilePane();

        Label title = new Label("Atendimentos");
        title.setStyle(
            "-fx-font-family: Segoe UI;" +
            "-fx-font-size: 40px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #140457;"
        );

        tilePaneAtendimento.getChildren().addAll(cadastarAtendimento, alterarAtendimento, procurarAtendimento,
                deletarAtendimento);
        pane.getChildren().add(tilePaneAtendimento);

        vboxTop.getChildren().addAll(title ,pane, table);
        
        cadastarAtendimento.setOnMouseClicked(event -> {
            cadastrarView();
        });

        alterarAtendimento.setOnMouseClicked(event -> {
            procurarView(ProcurarFormulario.ALTERAR);
        });

        deletarAtendimento.setOnMouseClicked(event -> {
            procurarView(ProcurarFormulario.DELETAR);
        }); 

        procurarAtendimento.setOnMouseClicked(event -> {
            procurarView(ProcurarFormulario.PROCURAR);
        });

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        TableColumn<Atendimento, Long> colId = new TableColumn<>("Nº");
        TableColumn<Atendimento, String> colMedico = new TableColumn<>("Nome do Medico");
        TableColumn<Atendimento, String> colPaciente = new TableColumn<>("Nome do Paciente");
        TableColumn<Atendimento, String> colData = new TableColumn<>("Data da Consulta");
        TableColumn<Atendimento, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Atendimento, Void>, TableCell<Atendimento, Void>> callBack =
        new Callback<>(){

        public TableCell<Atendimento, Void> call(TableColumn<Atendimento, Void> tc){

            TableCell<Atendimento, Void> tableCell = new TableCell<>(){

                private final Button btnExcluir = new Button();
                private final Button btnAtualizar = new Button();
                private final HBox painelBotoes = new HBox(10);

                {
                    FontIcon iconExcluir = new FontIcon("fas-trash");
                    iconExcluir.setIconColor(Color.web("#ef6464"));

                    FontIcon iconAtualizar = new FontIcon("fas-edit");
                    iconAtualizar.setIconColor(Color.web("#66b5ff"));

                    btnExcluir.setGraphic(iconExcluir);
                    btnAtualizar.setGraphic(iconAtualizar);

                    btnExcluir.setStyle("-fx-background-color: #424242;");
                    btnAtualizar.setStyle("-fx-background-color: #424242;");

                    painelBotoes.getChildren().addAll(btnAtualizar, btnExcluir);

                    btnExcluir.setOnAction(e -> {
                        Atendimento a = getTableRow().getItem();

                        if(a != null){
                            atendControl.idProperty().set(a.getId());

                            if(atendControl.procurar()){
                                procurarResultado(ProcurarFormulario.DELETAR);
                            }
                        }
                    });

                    btnAtualizar.setOnAction(e -> {
                        Atendimento a = getTableRow().getItem();

                        if(a != null){
                            atendControl.idProperty().set(a.getId());

                            if(atendControl.procurar()){
                                procurarResultado(ProcurarFormulario.ALTERAR);
                            }
                        }
                    });
                }

                        @Override
                        public void updateItem(Void valor, boolean empty){
                            super.updateItem(valor, empty);

                            if(!empty){
                                setGraphic(painelBotoes);
                            }else{
                                setGraphic(null);
                            }
                        }
                    };

                    return tableCell;
                }
            };

        colAcoes.setCellFactory(callBack);

        colMedico.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getM().getNome()));

        colPaciente.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getP().getNome()));

        colId.setCellValueFactory(item -> new ReadOnlyLongWrapper(
                item.getValue().getId()).asObject());

        colData.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getData().format(formato)));

        table.getColumns().addAll(colId, colMedico, colPaciente, colData, colAcoes);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setItems(atendControl.getList());
        
        centralAtendPane.setCenter(vboxTop);
        centralAtendPane.setPadding(new Insets(50,20,10,20));

        }

        return centralAtendPane;
    }

    public Button voltarButton(){
        Button voltarButton = new Button("Voltar");
        voltarButton.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");

        voltarButton.setOnMouseClicked(event -> {
            pane.getChildren().clear();
            pane.getChildren().add(tilePaneAtendimento);
        });

        return voltarButton;
    }

    public void procurarView(ProcurarFormulario modo){
        pane.getChildren().clear();
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();

        Button procurarId = new Button("Procurar Atendimento");

        procurarId.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");

        Label txtID = new Label("Nº do Atendimento");
        TextField fieldID = new TextField();

        gridPane.add(txtID, 0, 0);
        gridPane.add(fieldID, 1, 0);

        Bindings.bindBidirectional(fieldID.textProperty(), atendControl.idProperty(),new NumberStringConverter());

        procurarId.setOnMouseClicked(event -> {
            if(atendControl.procurar()){
                procurarResultado(modo);
            }else{
                System.out.println("Atendimento Não Encontrado");
            }
        });

        vbox.getChildren().addAll(voltarButton(), gridPane, procurarId);
        pane.getChildren().add(vbox);

    }

    public void cadastrarView() {
        pane.getChildren().clear();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        VBox vbox = new VBox(20);

        Label textMedico = new Label("ID Medico:");
        TextField fieldMedico = new TextField();

        Label textPaciente = new Label("ID Paciente");
        TextField fieldPaciente = new TextField();

        Label textData = new Label("Data da Consulta");
        TextField fieldData = new TextField();

        
        Bindings.bindBidirectional(fieldMedico.textProperty(), atendControl.medicoIdProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldPaciente.textProperty(), atendControl.pacienteIdProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldData.textProperty(), atendControl.dataProperty());

        gridPane.add(textMedico, 0, 1);
        gridPane.add(fieldMedico, 1, 1);
        gridPane.add(textPaciente, 0, 2);
        gridPane.add(fieldPaciente, 1, 2);
        gridPane.add(textData, 0, 3);
        gridPane.add(fieldData, 1, 3);

        Button salvar = new Button("Salvar");
        salvar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        gridPane.add(salvar,0,5);
        salvar.setOnAction(event -> {
            if(!atendControl.agendar()){
                new Alert(
                    Alert.AlertType.WARNING,
                    atendControl.erroProperty().get()
                ).showAndWait();
            }
        });


        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);
    }


    public void procurarResultado(ProcurarFormulario modo){
        pane.getChildren().clear();
        GridPane gridPane = new GridPane();
        VBox vbox = new VBox();
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        Label textId = new Label("Nº Atendimento:");
        TextField fieldId = new TextField();
        fieldId.setEditable(false);

        Label textMedicoId = new Label("ID Medico:");
        TextField fieldMedicoId = new TextField();
        fieldMedicoId.setEditable(false);

        Label textMedicoNome = new Label("Nome do Medico:");
        TextField fieldMedicoNome = new TextField(); 
        fieldMedicoNome.setEditable(false);

        Label textPacienteId = new Label("ID Paciente");
        TextField fieldPacienteId = new TextField();
        fieldPacienteId.setEditable(false);

        Label textPacienteNome = new Label("Nome do Paciente");
        TextField fieldPacienteNome = new TextField();
        fieldPacienteNome.setEditable(false);

        Label textData = new Label("Data da Consulta");
        TextField fieldData = new TextField();
        fieldData.setEditable(false);


        switch (modo){
            case DELETAR:
                    Button deletar = new Button("Deletar");
                    deletar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
                    deletar.setOnMouseClicked(event -> {
                        atendControl.deletar();
                        procurarView(ProcurarFormulario.DELETAR);
                    });
                    gridPane.add(deletar,0,5);
                    break;

            case PROCURAR:
                    break;

            case ALTERAR:
                    fieldMedicoId.setEditable(true);
                    fieldPacienteId.setEditable(true);
                    fieldData.setEditable(true);
                    fieldMedicoNome.setVisible(false);
                    textMedicoNome.setVisible(false);

                    fieldPacienteNome.setVisible(false);
                    textPacienteNome.setVisible(false);

                    Button alterar = new Button("Alterar");
                    alterar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
                    alterar.setOnAction(event -> {
                        if(atendControl.alterar()){
                            procurarView(ProcurarFormulario.ALTERAR);
                        }else{
                            new Alert(
                                Alert.AlertType.WARNING,
                                atendControl.erroProperty().get()
                            ).showAndWait();
                        }
                    });
                    gridPane.add(alterar,0,5);
                    break;
        }

        Bindings.bindBidirectional(fieldId.textProperty(), atendControl.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldMedicoId.textProperty(), atendControl.medicoIdProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldMedicoNome.textProperty(), atendControl.medicoNomeProperty());
        Bindings.bindBidirectional(fieldPacienteId.textProperty(), atendControl.pacienteIdProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldPacienteNome.textProperty(), atendControl.pacienteNomeProperty());
        Bindings.bindBidirectional(fieldData.textProperty(), atendControl.dataProperty());

        gridPane.add(textId, 0, 1);
        gridPane.add(fieldId, 1, 1);
        gridPane.add(textMedicoId, 0, 2);
        gridPane.add(fieldMedicoId, 1, 2);
        gridPane.add(textMedicoNome, 3, 2);
        gridPane.add(fieldMedicoNome, 4, 2);
        gridPane.add(textPacienteId, 0, 3);
        gridPane.add(fieldPacienteId, 1, 3);
        gridPane.add(textPacienteNome, 3, 3);
        gridPane.add(fieldPacienteNome, 4, 3);
        gridPane.add(textData, 0, 4);
        gridPane.add(fieldData, 1, 4);

        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);


    }


    private enum ProcurarFormulario {
        DELETAR,
        PROCURAR,
        ALTERAR,
    }
}
