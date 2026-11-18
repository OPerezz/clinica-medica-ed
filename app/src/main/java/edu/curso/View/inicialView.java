package edu.curso.View;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class inicialView {
    public VBox centralPane(){
        
                // ------ Instancia ----------

                VBox vboxCenter = new VBox();
                HBox hboxCard = new HBox(20);
                Label h2 = new Label("Visão geral");
                Label h3 = new Label("Bem-vindo ao sistema de gestão da clínica.");

                // ---------- Variaveis --------

                String[] titlesCards = {
                                "Pacientes",
                                "Medicos",
                                "Atendimentos"
                };

                String[] iconesCards = {
                                "fas-users",
                                "fas-user-md",
                                "fas-calendar-alt"
                };

                String[] textCardV = {
                                "Gerenciar Cadastro",
                                "Gerenciar Cadastro",
                                "Agendar e gerenciar atendimentos"
                };

                // ------ Injecao ----------

                vboxCenter.getChildren().add(h2);
                vboxCenter.getChildren().add(h3);
                vboxCenter.getChildren().add(hboxCard);

                for (int i = 0; i < iconesCards.length; i++) {
                        VBox card = new VBox(10);

                        FontIcon iconeCard = new FontIcon(iconesCards[i]);
                        Label titlesCard = new Label(titlesCards[i]);
                        Label textCard = new Label(textCardV[i]);

                        card.getChildren().add(iconeCard);
                        card.getChildren().add(titlesCard);
                        card.getChildren().add(textCard);

                        hboxCard.getChildren().add(card);

                        card.setStyle("-fx-background-color: #424242;" + "-fx-background-radius: 10px;");
                        iconeCard.setIconColor(Color.web("#66b5ff"));
                        titlesCard.setStyle(
                                        "-fx-font-family: Verdana;" + "-fx-font-size: 18;" + "-fx-text-fill: #FFFFFF;"
                                                        + "-fx-font-weight: bold;");
                        textCard.setStyle(
                                        "-fx-font-family: Arial;" + "-fx-font-size: 16px;" + "-fx-text-fill: #a8a8a8");

                        card.setPadding(new Insets(20));
                }

                // ------ Style ----------

                vboxCenter.setStyle("-fx-background-color: #141414");

                h2.setStyle("-fx-font-family: Segoe UI;" + "-fx-font-size: 60px;" + "-fx-font-weight: bold;"
                                + "-fx-text-fill: #ffffff;");
                VBox.setMargin(h2, new Insets(10, 0, 0, 20));

                h3.setStyle("-fx-font-family: Segoe UI;" + "-fx-font-size: 18px;" + "-fx-text-fill: #b3b3b3;");
                VBox.setMargin(h3, new Insets(10, 0, 0, 20));

                VBox.setMargin(hboxCard, new Insets(30, 0, 0, 20));

                return vboxCenter;
    }

}