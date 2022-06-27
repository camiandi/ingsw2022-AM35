package it.polimi.ingsw.Client.Gui.Scene;

import it.polimi.ingsw.Client.RemoteModel;
import it.polimi.ingsw.Model.SchoolsLands.Archipelago;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.Board;

import it.polimi.ingsw.NetworkUtilities.MoveMotherNatureMessage;
import it.polimi.ingsw.NetworkUtilities.MoveStudentMessage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Scene used during the action phase
 */
public class ActionSceneController extends GenericSceneController {

    @FXML
    private GridPane sky;
    @FXML
    private Label archipelagoSelectedLbl;
    @FXML
    private Button moveBtn;
    @FXML
    private Button playCharacterBtn;

    private Map<Integer, Student> studentMap = new HashMap<>();
    private Map<Integer, ArchipelagoPanelController> archipelagoControllerMap = new HashMap<>();
    private BoardPanelController boardPanelController;
    private Boolean ok = false;
    private Boolean moveMN = false;

    /**
     * Method used to set the archipelagos
     *
     * @param archipelagos a Map associating archipelagos with a number
     */
    public void setArchipelagos(Map<Integer, Archipelago> archipelagos) {
        Map<Integer, Archipelago> archipelagoMap = archipelagos;
        {
            int indexRow = 0, indexColumn = 0;
            for (Integer i : archipelagos.keySet()) {
                Archipelago a = archipelagoMap.get(i);
                if (indexRow == 0 && indexColumn < 5) {
                    try {
                        loadArchipelagos(i , a, indexRow, indexColumn);
                        indexColumn++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (indexRow == 0 && indexColumn - 1 == 4) {
                    indexColumn = indexColumn - 1;
                    indexRow = 1;
                    try {
                        loadArchipelagos(i, a, indexRow, indexColumn);
                        indexRow = 2;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (indexRow == 2 && indexColumn >= 0) {
                    try {
                        loadArchipelagos(i, a, indexRow, indexColumn);
                        indexColumn = indexColumn - 1;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (indexRow != 1 && indexColumn + 1 == 0) {
                    indexColumn++;
                    try {
                        indexRow = 1;
                        loadArchipelagos(i, a, indexRow, indexColumn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Method used to load archipelagos on the GUI
     *
     * @param archipelago archipelagos to print
     * @param row         used for coordinates on the screen
     * @param column      used for coordinates on the screen
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    private void loadArchipelagos(Integer i, Archipelago archipelago, int row, int column) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource("/fxml/archipelago.fxml"));
        Node node = loader.load();
        ArchipelagoPanelController controller = loader.getController();
        controller.setArchipelago(archipelago);
        node.setOnMouseClicked(MouseEvent -> {
                    if (i == remoteModel.getArchipelagoSelected()) {
                        remoteModel.setArchipelagoSelected(null);
                        archipelagoSelectedLbl.setText("");
                    } else {
                        remoteModel.setArchipelagoSelected(i);
                        archipelagoSelectedLbl.setText("You have selected \n the " + remoteModel.getArchipelagoSelected() + " archipelago");
                    }
                }
        );
        node.setDisable(false);
        sky.add(node, column, row);
        archipelagoControllerMap.put(i, controller);
    }

    /**
     * Method used to set the board on the GUI
     *
     * @param board the board to set
     */
    public void setBoard(Board board) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource("/fxml/singleBoard.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boardPanelController = loader.getController();
        boardPanelController.setRemoteModel(remoteModel);
        boardPanelController.setCurrentBoard(board, "My");
        sky.add(node, 1, 1);
        ok = true;

    }

    /**
     * Method used to load students on the GUI
     *
     * @param studentsMovable a Map associating students with an int
     */
    public void loadStudentsMovable(Map<Integer, Student> studentsMovable) {
        while (!ok) {
        }
        studentMap = studentsMovable;
        boardPanelController.setMovableStudentOnEntrance(studentMap.values().stream().toList());
    }

    /**
     * Method used to move students
     */
    public void move() {
        Integer indexStud, indexArch;
        indexArch = remoteModel.getArchipelagoSelected();
        if (moveMN) {
            if (indexArch != null) {
                notifyObserver(new MoveMotherNatureMessage(indexArch));
            }
        } else {
            indexStud = remoteModel.getStudentSelectedFromEntrance();
            System.out.println(indexArch + " , " + indexStud);
            if (indexStud != null) {
                notifyObserver(new MoveStudentMessage(indexStud, indexArch));
            }
        }
    }

    /**
     * Method used to get the archipelago index
     *
     * @param archipelago the archipelago the index of is requested
     * @return an int representing the index
     */
    private Integer getArchipelagoIndex(Archipelago archipelago) {
        Integer indexArch = null;
        for (Integer i : remoteModel.getArchipelagosMap().keySet()) {
            if (remoteModel.getArchipelagosMap().get(i).equals(archipelago)) {
                indexArch = i;
            }
        }
        return indexArch;
    }

    /**
     * Method used to get the student index
     *
     * @param student the student the index of is requested
     * @return an int representing the index
     */
  /*  private Integer getStudentIndex(Student student) {
        Integer indexStud = null;
        for (Integer i : studentMap.keySet()) {
            if (studentMap.get(i).equals(student)) {
                indexStud = i;
            }
        }
        return indexStud;
    }*/

    /**
     * Method that shows the wizards' board
     */
    public void goToBoards() {
        Platform.runLater(() -> SceneController.showWizardsBoards(getObservers()));
    }

    /**
     * Method used to set the movement of mother nature
     *
     * @param moveMN a boolean to set the movement
     */
    public void setMoveMN(Boolean moveMN) {
        //  boardPanelController.setBoard(remoteModel.getCurrentBoard(), "My");
        this.moveMN = moveMN;
        moveBtn.setText("Move Mother Nature");
    }

    /**
     * Method used to set the match in expert mode
     */
    public void setExpert() {
        playCharacterBtn.setDisable(false);
        playCharacterBtn.setVisible(true);
    }

    /**
     * Method used to switch scene
     */
    public void goToScenePlayCharacter() {
        Platform.runLater(() -> SceneController.showCharacterCardsOption(getObservers()));
    }

    /**
     * update the remote model
     *
     * @param remoteModel remote model updated
     */
    @Override
    public void setRemoteModel(RemoteModel remoteModel) {
        this.remoteModel = remoteModel;
        setButtonCharacterCard();
        setBoard(remoteModel.getCurrentBoard());
        setArchipelagos(remoteModel.getArchipelagosMap());
    //    loadStudentsMovable(remoteModel.getStudentsOnEntranceMap());
    }

    private void setButtonCharacterCard() {
        if (!remoteModel.getCharacterCardMap().isEmpty()) {
            playCharacterBtn.setVisible(true);
            playCharacterBtn.setDisable(false);
        }
    }

    public BoardPanelController getBoardPanelController() {
        return boardPanelController;
    }

    public void updateArchipelagoOnMoveStudent(Map<Integer, Archipelago> archipelagoMap) {

        for(Integer i = 1; i <= archipelagoMap.size(); i++){
            ArchipelagoPanelController controller = archipelagoControllerMap.get(i);
            Archipelago a = archipelagoMap.get(i);
            controller.setArchipelago(a);
        }
    }
}
