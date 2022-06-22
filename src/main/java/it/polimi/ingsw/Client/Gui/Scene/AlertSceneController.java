package it.polimi.ingsw.Client.Gui.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.jfr.Event;

public class AlertSceneController {
    private final Stage stage;

    @FXML
    private Label titleLbl;
    @FXML
    private Label messageLbl;
    @FXML
    private Button okBtn;

    public AlertSceneController() {
        stage = new Stage();
        stage.initOwner(SceneController.getActiveScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setAlwaysOnTop(true);
    }

    public void setAlertTitle(String str) {
        titleLbl.setText(str);
    }

    public void setAlertMessage(String str) {
        messageLbl.setText(str);
    }

    public void displayAlert() {
        stage.show();
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public void okBtnClick(ActionEvent event) {
        stage.close();
    }
}
