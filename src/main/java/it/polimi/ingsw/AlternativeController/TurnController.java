package it.polimi.ingsw.AlternativeController;

import it.polimi.ingsw.AlternativeView.RemoteView;
import it.polimi.ingsw.AlternativeView.ViewInterface;
import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.FactoryMatch.Player;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.NetworkUtilities.Message.GenericMessage;

import java.util.*;

public class TurnController {

    private Controller controller;
    private Map<String, ViewInterface> viewMap;
    private Player activePlayer;
    private List<Player> actionOrderOfPlayers = new ArrayList<>();
    private TurnPhase turnPhase = null;


    public TurnController(Controller controller, Map<String, ViewInterface> viewMap){
        this.controller = controller;
        this.viewMap = viewMap;
    }

    public void nextPlayerPlanningPhase(){
        int setActivePlayer = (controller.getMatch().getPlayers().indexOf(activePlayer) + 1) % controller.getMatch().getNumberOfPlayers();
        activePlayer = controller.getMatch().getPlayers().get(setActivePlayer);
    }

    public void nextPlayerActionPhase(){
        actionOrderOfPlayers.remove(activePlayer);
        activePlayer = actionOrderOfPlayers.get(0);
    }

    public void setActivePlayer(Player player) {
        activePlayer = player;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActionOrderOfPlayers(List<Player> actionOrderOfPlayers) {
        Collections.copy(this.actionOrderOfPlayers, actionOrderOfPlayers);
        activePlayer = actionOrderOfPlayers.get(0);
    }

    public void setTurnPhase(TurnPhase turnPhase) {
        this.turnPhase = turnPhase;
        switch (turnPhase) {
            case PLAY_ASSISTANT ->  {
                askingViewToPlayAnAssistantCard();}

            case MOVE_STUDENTS ->   askingViewToMoveAStudent();

        }
        System.out.println("set the phase to "+ turnPhase);
    }

    private void askingViewToMoveAStudent() {
        RemoteView remoteView = (RemoteView) viewMap.get(activePlayer.getUsername());
        remoteView.showGenericMessage(new GenericMessage("It's your turn, move " + controller.getMatch().getNumberOfMovableStudents() + " students from your board"));
        try {
            List<Student> studentsFromBoard = controller.getMatch().getGame().getWizardFromPlayer(activePlayer).getBoard().getStudentsInEntrance().stream().toList();
            remoteView.askToMoveStudentFromEntrance(studentsFromBoard);
        } catch (ExceptionGame e) {
            e.printStackTrace();
        }
    }

    private void askingViewToPlayAnAssistantCard() {
        RemoteView remoteView = (RemoteView) viewMap.get(activePlayer.getUsername());
        System.out.println("asking to play an assistant card");
        remoteView.showGenericMessage(new GenericMessage("It's your turn, pick an assistant card"));
        System.out.println("after send a generic message");
        try {
            remoteView.askAssistantCard(controller.getMatch().getGame().getWizardFromPlayer(activePlayer).getAssistantsDeck().getPlayableAssistants());
        } catch (ExceptionGame e) {
            e.printStackTrace();
        }
    }
}
