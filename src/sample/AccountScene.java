package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountScene {
    private static AccountScene singleInstance = null;
    private Account account = null;

    public Account getAccount() {
        return account;
    }

    private AccountScene() {

    }

    public static AccountScene getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new AccountScene();
        return singleInstance;
    }

    public boolean canPlay() {
        return account != null;
    }

    public void startGetAccount(Scene scene, Group root, Stage primaryStage, Scene accountScene) {

        GridPane grid = new GridPane();
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.relocate(300, 300);
//Defining the Name text field
        final TextField name = new TextField();
        name.setPromptText("Enter your first name.");
        name.setPrefColumnCount(10);
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);
//Defining n text field
        final TextField nNumber = new TextField();
        nNumber.setPromptText("Enter number of rows");
        GridPane.setConstraints(nNumber, 0, 1);
        grid.getChildren().add(nNumber);

        root.getChildren().add(grid);
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);
        submit.setOnAction(event -> {
            if (name.getText().trim().equals("")) {
                label.setText("You have not enter username");
            } else if (!nNumber.getText().matches("\\d+") || nNumber.getText().trim().equals("")
                    || Integer.parseInt(nNumber.getText())==1) {
                label.setText("Please enter correct number");
            } else {
                account = Account.accountHaveBeenExist(name.getText());
                if (account == null) {
                    account = Account.makeNewAccount(name.getText());
                    GameScene.setN(Integer.parseInt(nNumber.getText()));
                }
                grid.getChildren().remove(label);
                primaryStage.setScene(accountScene);
            }

        });


    }
}
