package it.polimi.ingsw.Client.Gui.Scene;

import it.polimi.ingsw.Model.SchoolsLands.Archipelago;
import it.polimi.ingsw.Model.SchoolsMembers.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

public class ArchipelagoPanelController {
    @FXML
    private Circle blueStudent;
    @FXML
    private Circle greenStudent;
    @FXML
    private Circle pinkStudent;
    @FXML
    private Circle redStudent;
    @FXML
    private Circle yellowStudent;
    @FXML
    private Polygon motherNature;
    @FXML
    private ImageView towerBlack;
    @FXML
    private ImageView towerWhite;
    @FXML
    private ImageView towerGrey;

    @FXML
    private Label numBlueStudents;
    @FXML
    private Label numPinkStudents;
    @FXML
    private Label numGreenStudents;
    @FXML
    private Label numRedStudents;
    @FXML
    private Label numYellowStudents;
    @FXML
    private Label numOfTowers;



    public void setArchipelago(Archipelago archipelago) {
        {
            int numberOfStudentBlue = archipelago.getStudentFromArchipelago().stream()
                    .filter(s -> s.getColor().equals(Color.BLUE))
                    .mapToInt(s -> 1).sum();
            if (numberOfStudentBlue > 0) {
                blueStudent.setVisible(true);
                numBlueStudents.setVisible(true);
                numBlueStudents.setText(String.valueOf(numberOfStudentBlue));
            }
            int numberOfStudentPink = archipelago.getStudentFromArchipelago().stream()
                    .filter(s -> s.getColor().equals(Color.PINK))
                    .mapToInt(s -> 1).sum();
            if (numberOfStudentPink > 0) {
                pinkStudent.setVisible(true);
                numPinkStudents.setVisible(true);
                numPinkStudents.setText(String.valueOf(numberOfStudentPink));
            }
            int numberOfStudentGreen = archipelago.getStudentFromArchipelago().stream()
                    .filter(s -> s.getColor().equals(Color.GREEN))
                    .mapToInt(s -> 1).sum();
            if (numberOfStudentGreen > 0) {
                greenStudent.setVisible(true);
                numGreenStudents.setVisible(true);
                numGreenStudents.setText(String.valueOf(numberOfStudentGreen));
            }
            int numberOfStudentRed = archipelago.getStudentFromArchipelago().stream()
                    .filter(s -> s.getColor().equals(Color.RED))
                    .mapToInt(s -> 1).sum();
            if (numberOfStudentRed > 0) {
                redStudent.setVisible(true);
                numRedStudents.setVisible(true);
                numRedStudents.setText(String.valueOf(numberOfStudentRed));
            }
            int numberOfStudentYellow = archipelago.getStudentFromArchipelago().stream()
                    .filter(s -> s.getColor().equals(Color.YELLOW))
                    .mapToInt(s -> 1).sum();
            if (numberOfStudentYellow > 0) {
                yellowStudent.setVisible(true);
                numYellowStudents.setVisible(true);
                numYellowStudents.setText(String.valueOf(numberOfStudentYellow));
            }
        }
        try {
            if (archipelago.getIsle().get(0).isThereTower()) {
                int numberOfTowers = archipelago.getIsle().size();
                numOfTowers.setVisible(true);
                numOfTowers.setText(String.valueOf(numberOfTowers));
                switch (archipelago.getIsle().get(0).getTower().getTowerColors()) {
                    case Gray -> towerGrey.setVisible(true);
                    case Black -> towerBlack.setVisible(true);
                    case White -> towerWhite.setVisible(true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(archipelago.isMotherNaturePresence()){
            motherNature.setVisible(true);
        }
    }

}
