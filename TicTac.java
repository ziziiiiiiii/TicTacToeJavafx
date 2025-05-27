// This is a tic-tac-toe game built using javafx. It is for use with two people on the same machine. It does
// not support playing against in-game AI or online multiplayer.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

public class TicTac extends Application {
    ArrayList<Integer> buttonsPressedx = new ArrayList<>();
    ArrayList<Integer> buttonsPressedo = new ArrayList<>();
    int turnCounter = 1;

    Label labelo = new Label("O");
    Label labelx = new Label("X");
    Label labelxTurn = new Label("Player X's turn");
    Label labeloTurn = new Label("Player O's turn");
    Label labelxWin = new Label("Player X wins!");
    Label labeloWin  = new Label("Player O wins!");
    Label drawWin = new Label("It's a draw!     ");
    Label newGameLabel = new Label("Play again");
    Label conditionLabel = new Label(labelxTurn.getText());
    Font buttonFont = Font.font(22);
    Font labelFont = Font.font(19);
    Button newGameButton = new Button();
    Button[] buttons = new Button[9];

    public void start(Stage primaryStage) {
        conditionLabel.setFont(labelFont);

        newGameButton.setText(newGameLabel.getText());
        newGameButton.setOnAction(event -> {
            newGame();
        });
        newGameButton.setVisible(false);
        newGameButton.setPrefSize(75, 80);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.getChildren().addAll(conditionLabel, newGameButton);
        hbox.setSpacing(80);
        hbox.setPadding(new Insets(0, 0, 19, 0));

        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("");
            final int buttonCount = i;
            buttons[i].setPrefSize(1000, 500);
            buttons[i].setFont(buttonFont);
            buttons[i].setOnAction(event -> {  
                gameLogic(buttonCount);
            });
        }

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0));
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        GridPane.setConstraints(buttons[0], 0, 0);
        GridPane.setConstraints(buttons[1], 1, 0);
        GridPane.setConstraints(buttons[2], 2, 0);
        GridPane.setConstraints(buttons[3], 0, 1);
        GridPane.setConstraints(buttons[4], 1, 1);
        GridPane.setConstraints(buttons[5], 2, 1);
        GridPane.setConstraints(buttons[6], 0, 2);
        GridPane.setConstraints(buttons[7], 1, 2);
        GridPane.setConstraints(buttons[8], 2, 2);
        
        for(int i = 0; i < buttons.length; i++) {
            gridPane.getChildren().add(buttons[i]);
        }

        VBox vbox = new VBox(0, gridPane, hbox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 280, 320);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void gameLogic(int i) {
        if (turnCounter % 2 == 0 && buttons[i].getText().equals("")) {
            buttons[i].setText(labelo.getText());
            conditionLabel.setText(labelxTurn.getText());
            buttonsPressedo.add(i);
            turnCounter++;
        }
        else if (buttons[i].getText().equals("")) {
            buttons[i].setText(labelx.getText());
            conditionLabel.setText(labeloTurn.getText());
            buttonsPressedx.add(i);
            turnCounter++;
        }

        if(turnCounter >= 6) {
            int winConditionRow1 = 0;
            int winConditionRow2 = 0;
            int winConditionRow3 = 0;
            int winConditionCol1 = 0;
            int winConditionCol2 = 0;
            int winConditionCol3 = 0;
            int winConditionDiag1 = 0;
            int winConditionDiag2 = 0;

            if (turnCounter % 2 == 0) {
                for(int x : buttonsPressedx) {
                    if (x == 0) {
                        winConditionRow1++;
                        winConditionCol1++;
                        winConditionDiag1++;
                    }
                    else if (x == 1) {
                        winConditionRow1++;
                        winConditionCol2++;
                    }
                    else if (x == 2) {
                        winConditionRow1++;
                        winConditionCol3++;
                        winConditionDiag2++;
                    }
                    else if (x == 3) {
                        winConditionRow2++;
                        winConditionCol1++;
                    }
                    else if (x == 4) {
                        winConditionRow2++;
                        winConditionCol2++;
                        winConditionDiag1++;
                        winConditionDiag2++;
                    }
                    else if (x == 5) {
                        winConditionRow2++;
                        winConditionCol3++;
                    }
                    else if (x == 6) {
                        winConditionRow3++;
                        winConditionCol1++;
                        winConditionDiag2++;
                    }
                    else if (x == 7) {
                        winConditionRow3++;
                        winConditionCol2++;
                    }
                    else {
                        winConditionRow3++;
                        winConditionCol3++;
                        winConditionDiag1++;
                    }
                }

                if(winConditionCol1 == 3 || winConditionCol2 == 3 || winConditionCol3 == 3 || winConditionRow1 == 3 || winConditionRow2 == 3 || winConditionRow3 == 3 || winConditionDiag1 == 3 || winConditionDiag2 == 3) {
                    conditionLabel.setText(labelxWin.getText());
                    for(int y = 0; y < buttons.length; y++) {
                        buttons[y].setDisable(true);
                    }
                    newGameButton.setVisible(true);
                }
            }
            else {
                for(int x : buttonsPressedo) {
                    if (x == 0) {
                        winConditionRow1++;
                        winConditionCol1++;
                        winConditionDiag1++;
                    }
                    else if (x == 1) {
                        winConditionRow1++;
                        winConditionCol2++;
                    }
                    else if (x == 2) {
                        winConditionRow1++;
                        winConditionCol3++;
                        winConditionDiag2++;
                    }
                    else if (x == 3) {
                        winConditionRow2++;
                        winConditionCol1++;
                    }
                    else if (x == 4) {
                        winConditionRow2++;
                        winConditionCol2++;
                        winConditionDiag1++;
                        winConditionDiag2++;
                    }
                    else if (x == 5) {
                        winConditionRow2++;
                        winConditionCol3++;
                    }
                    else if (x == 6) {
                        winConditionRow3++;
                        winConditionCol1++;
                        winConditionDiag2++;
                    }
                    else if (x == 7) {
                        winConditionRow3++;
                        winConditionCol2++;
                    }
                    else {
                        winConditionRow3++;
                        winConditionCol3++;
                        winConditionDiag1++;
                    }
                }

                if(winConditionCol1 == 3 || winConditionCol2 == 3 || winConditionCol3 == 3 || winConditionRow1 == 3 || winConditionRow2 == 3 || winConditionRow3 == 3 || winConditionDiag1 == 3 || winConditionDiag2 == 3) {
                    conditionLabel.setText(labeloWin.getText());
                    for(int y = 0; y < buttons.length; y++) {
                        buttons[y].setDisable(true);
                    }
                    newGameButton.setVisible(true);
                }
            }
        }

        if(turnCounter == 10 && (conditionLabel.getText().equals(labelxTurn.getText()) || conditionLabel.getText().equals(labeloTurn.getText()))) {
            conditionLabel.setText(drawWin.getText());
            newGameButton.setVisible(true);
        }
    }

    public void newGame() {
        for(int i = 0; i < buttons.length; i++) {
            turnCounter = 1;
            buttonsPressedx.clear();
            buttonsPressedo.clear();
            buttons[i].setDisable(false);
            buttons[i].setText("");
            conditionLabel.setText(labelxTurn.getText());
        }
        newGameButton.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
