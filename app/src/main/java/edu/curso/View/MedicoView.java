package edu.curso.View;

import org.kordamp.ikonli.javafx.FontIcon;

import edu.curso.Controller.MedicoController;
import edu.curso.Entity.Medico;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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

public class MedicoView {

    BorderPane centralMedicoPane;
    private TilePane tilePane;
    Pane pane = new Pane();
    MedicoController medControl = new MedicoController();

    public BorderPane centralPane(){

        if(centralMedicoPane == null){
            centralMedicoPane = new BorderPane();

            centralMedicoPane.setStyle("-fx-background-color: #8ac6d1;");

            Label title = new Label("Medicos");
            title.setStyle(
                "-fx-font-family: Segoe UI;" +
                "-fx-font-size: 40px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #140457;"
            );

            VBox vbox = new VBox(30);
            tilePane = new TilePane();
            TableView<Medico> table = new TableView<>();

            Button cadastrar = new Button("Cadastrar");
            cadastrar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
            cadastrar.setPadding(new Insets(5,10,5,10));

            Button alterar = new Button("Alterar");
            alterar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
            alterar.setPadding(new Insets(5,10,5,10));

            Button procurar = new Button("Procurar");
            procurar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
            procurar.setPadding(new Insets(5,10,5,10));

            Button deletar = new Button("Deletar");
            deletar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
            deletar.setPadding(new Insets(5,10,5,10));

            cadastrar.setOnMouseClicked(event -> {
                cadastrar();
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

            TableColumn<Medico, Long> colId = new TableColumn<>("Nº");
            TableColumn<Medico, String> colNome = new TableColumn<>("Nome");
            // TableColumn<Medico, String> colTelefone = new TableColumn<>("Telefone");
            TableColumn<Medico, String> colEmail = new TableColumn<>("Email");
            TableColumn<Medico, Long> colCPF = new TableColumn<>("CPF");
            TableColumn<Medico, Integer> colIdade = new TableColumn<>("Idade");
            TableColumn<Medico, String> colCrm = new TableColumn<>("CRM");
            TableColumn<Medico, String> colTelefone = new TableColumn<>("Telefone");
            TableColumn<Medico, Void> colAcoes = new TableColumn<>("Ações");
            Callback<TableColumn<Medico, Void>, TableCell<Medico, Void>> callBack = 
                new Callback<>(){ 
                    public TableCell<Medico, Void> call(TableColumn<Medico, Void> tc) { 
                        TableCell<Medico, Void> tableCell = new TableCell<>(){

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
                                    Medico m = getTableRow().getItem();

                                    if(m != null){
                                        medControl.idProperty().set(m.getId());

                                        if(medControl.procurar() != null){
                                            montarForm(ModoFormulario.DELETAR);
                                        }
                                    }
                                });

                                btnAtualizar.setOnAction(e -> {
                                    Medico m = getTableRow().getItem();

                                    if(m != null){
                                        medControl.idProperty().set(m.getId());

                                        if(medControl.procurar() != null){
                                            montarForm(ModoFormulario.ALTERAR);
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

            colId.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getId()).asObject());
            colNome.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getNome()));
            // colTelefone.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getTelefone()));
            colEmail.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getEmail()));
            colCPF.setCellValueFactory(item -> new ReadOnlyLongWrapper(item.getValue().getCpf()).asObject());
            colIdade.setCellValueFactory(item -> new ReadOnlyIntegerWrapper(item.getValue().getIdade()).asObject());
            colCrm.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getCrm()));
            colTelefone.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getTelefone()));

            table.getColumns().addAll(colId, colNome, colEmail, colCPF, colIdade, colCrm,colTelefone, colAcoes);

            table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
            );

            table.setItems(medControl.getList());

            vbox.getChildren().addAll(title, pane, table);

            centralMedicoPane.setCenter(vbox);

            centralMedicoPane.setPadding(new Insets(50,20,10,20));
        }

        return centralMedicoPane;
    }

    public void montarForm(ModoFormulario modo){
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();
        gridPane.getChildren().clear();

        Label txtId = new Label("Id");
        TextField fieldId = new TextField();
        fieldId.setEditable(false);

        Label txtNome = new Label("Nome");
        TextField fieldNome = new TextField();

        // Label txtTelefone = new Label("Telefone");
        // TextField fieldTelefone = new TextField();

        Label txtEmail = new Label("Email");
        TextField fieldEmail = new TextField();

        Label txtCPF = new Label("CPF");
        TextField fieldCPF = new TextField();

        Label txtIdade = new Label("Idade");
        TextField fieldIdade = new TextField();

        Label txtCrm = new Label("CRM");
        TextField fieldCrm = new TextField();

        Label txtTelefone = new Label("Telefone");
        TextField fieldTelefone = new TextField();

        Bindings.bindBidirectional(fieldId.textProperty(), medControl.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldNome.textProperty(), medControl.nomeProperty());
        // Bindings.bindBidirectional(fieldTelefone.textProperty(), medControl.telefoneProperty());
        Bindings.bindBidirectional(fieldEmail.textProperty(), medControl.emailProperty());
        Bindings.bindBidirectional(fieldCPF.textProperty(), medControl.cpfProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldIdade.textProperty(), medControl.idadeProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fieldCrm.textProperty(), medControl.crmProperty());
        Bindings.bindBidirectional(fieldTelefone.textProperty(), medControl.telefoneProperty());

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

        gridPane.add(txtCrm, 0, 3);
        gridPane.add(fieldCrm, 1, 3);

        gridPane.add(txtTelefone, 3,3);
        gridPane.add(fieldTelefone, 4,3);

        // gridPane.add(txtProblema, 3, 3);
        // gridPane.add(fieldProblema, 4, 3);

        // gridPane.add(txtTelefone, 1, 4);
        // gridPane.add(fieldTelefone, 2, 4);

        gridPane.setVgap(15);
        gridPane.setHgap(15);

        pane.getChildren().clear();

        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);

        switch (modo){
            case CADASTRAR:
                Button salvar = new Button("Salvar");
                salvar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
                gridPane.add(salvar,0,5);

                salvar.setOnAction(event -> {
                    if(!medControl.criar()){
                        new Alert(
                            Alert.AlertType.WARNING,
                            "Não foi possível cadastrar o médico"
                        ).showAndWait();
                    }
                });
                break;

            case PROCURAR:
                fieldNome.setEditable(false);
                fieldEmail.setEditable(false);
                fieldCPF.setEditable(false);
                fieldIdade.setEditable(false);
                fieldCrm.setEditable(false);
                fieldTelefone.setEditable(false);
                break;

            case ALTERAR:
                Button alterar = new Button("Alterar");
                alterar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
                alterar.setOnAction(event -> {
                    if(medControl.alterar()){
                        alterar();
                    }else{
                        new Alert(
                            Alert.AlertType.WARNING,
                            "Não foi possível alterar o médico"
                        ).showAndWait();
                    }
                });

                gridPane.add(alterar, 0, 5);
                break;

            case DELETAR:
                Button deletar = new Button("Deletar");
                    deletar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
                deletar.setOnAction(event -> {
                    if(medControl.excluir()){
                        deletar();
                    }else{
                        new Alert(
                            Alert.AlertType.WARNING,
                            "Não foi possível excluir o médico"
                        ).showAndWait();
                    }
                });

                gridPane.add(deletar,0,5);
                break;
        }
    }

    public Button voltarButton(){
        Button voltarButton = new Button("Voltar");

        voltarButton.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");

        voltarButton.setOnMouseClicked(event -> {
            medControl.limparDados();
            pane.getChildren().clear();
            pane.getChildren().add(tilePane);
        });

        return voltarButton;
    }

    public void cadastrar(){
        medControl.limparDados();
        montarForm(ModoFormulario.CADASTRAR);
    }

    public void procurar(){
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();

        gridPane.add(txtId, 0,0);
        gridPane.add(fieldId, 1,0);

        gridPane.setVgap(15);
        gridPane.setHgap(15);

        Bindings.bindBidirectional(fieldId.textProperty(), medControl.idProperty(), new NumberStringConverter());

        Button procurar = new Button("Procurar");
        procurar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        gridPane.add(procurar, 0,2);

        procurar.setOnMouseClicked(event -> {
            if(medControl.procurar() != null){
                montarForm(ModoFormulario.PROCURAR);
            }else{
                new Alert(
                    Alert.AlertType.WARNING,
                    "Medico não encontrado"
                ).showAndWait();
            }
        });

        pane.getChildren().clear();

        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);
    }

    public void alterar(){
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();

        Bindings.bindBidirectional(fieldId.textProperty(), medControl.idProperty(), new NumberStringConverter());

        gridPane.add(txtId,0,0);
        gridPane.add(fieldId,0,1);

        gridPane.setVgap(15);
        gridPane.setHgap(15);

        Button procurar = new Button("Procurar");
        procurar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");
        gridPane.add(procurar, 0,2);

        procurar.setOnAction(event -> {
            if(medControl.procurar() != null){
                montarForm(ModoFormulario.ALTERAR);
            }else{
                new Alert(
                    Alert.AlertType.WARNING,
                    "Médico não encontrado"
                ).showAndWait();
            }
        });

        pane.getChildren().clear();

        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);
    }

    public void deletar(){
        VBox vbox = new VBox(20);
        GridPane gridPane = new GridPane();

        Label txtId = new Label("ID");
        TextField fieldId = new TextField();

        Bindings.bindBidirectional(fieldId.textProperty(), medControl.idProperty(), new NumberStringConverter());

        gridPane.add(txtId,0,0);
        gridPane.add(fieldId,0,1);

        gridPane.setVgap(15);
        gridPane.setHgap(15);

        Button procurar = new Button("Procurar");
        procurar.setStyle("-fx-background-color: #07539f;" + "-fx-text-fill: #e0e0e0;");

        gridPane.add(procurar, 0,2);

        procurar.setOnAction(event -> {
            if(medControl.procurar() != null){
                montarForm(ModoFormulario.DELETAR);
            }else{
                new Alert(
                    Alert.AlertType.WARNING,
                    "Médico não encontrado"
                ).showAndWait();
            }
        });

        pane.getChildren().clear();

        vbox.getChildren().addAll(voltarButton(), gridPane);
        pane.getChildren().add(vbox);
    }

    private enum ModoFormulario {
        CADASTRAR,
        PROCURAR,
        ALTERAR,
        DELETAR
    }
}