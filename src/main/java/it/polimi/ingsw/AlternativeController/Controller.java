package it.polimi.ingsw.AlternativeController;

import it.polimi.ingsw.AlternativeView.ViewInterface;
import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Player;
import it.polimi.ingsw.Model.Wizard.AssistantsCards;
import it.polimi.ingsw.NetworkUtilities.Message.*;
import it.polimi.ingsw.Observer.Observer;

import java.util.*;

public class Controller implements Observer {
    private final BasicMatch match;
    private GameState gameState;
    private Collection<String> playersUsername;
    private Map<String, ViewInterface> viewMap = new HashMap<>();
    private TurnController turnController;
    private Player firstPlanningPhasePlayer;

    //Initialize the Game having already a lobby
    public Controller(BasicMatch match, Set<String> playersUsername) throws ExceptionGame, CloneNotSupportedException {
        this.playersUsername = playersUsername;
        this.match = match;
    }

    public BasicMatch getMatch() {
        return match;
    }

    public void initGame() throws ExceptionGame, CloneNotSupportedException {
        List<Player> players = setListOfPlayers(this.playersUsername);
        if (match.getNumberOfPlayers() == 4) {
            match.setTeamsOne(players.get(0), players.get(1));
            match.setTeamsOne(players.get(2), players.get(3));
        }
        this.match.setGame(players);

        gameState = GameState.PLANNING_PHASE;
        turnController = new TurnController(this, viewMap);
        pickFirstPlayerPlanningPhaseHandler();

    }

    public void pickFirstPlayerPlanningPhaseHandler() {
        Random r = new Random();
        Player player = this.getMatch().getPlayers().get(r.nextInt(0, this.getMatch().getNumberOfPlayers()));
        System.out.println("first player: "+ player);
        turnController.setActivePlayer(player);
        turnController.setTurnPhase(TurnPhase.PLAY_ASSISTANT);
    }


    public void onMessageReceived(Message receivedMessage) {

        switch (gameState) {
            case PLANNING_PHASE:
                planningPhaseHandling(receivedMessage);
            case ACTION_PHASE:
                actionPhaseHandling(receivedMessage);
            case END_OF_TURN:

            default: //should never reach this condition
                //Server.Logger.warning(STR_INVALID_STATE);
                break;
        }
    }

    private List<Player> setListOfPlayers(Collection<String> playersUsername) {
        List<Player> players = new ArrayList<>();
        for (String username : playersUsername) {
            players.add(new Player(username));
        }
        return players;
    }


    private synchronized void planningPhaseHandling(Message receivedMessage) {
        Player activePlayer = turnController.getActivePlayer();
        if (receivedMessage.getType() == GameStateMessage.ASSISTANT_CARD) {
            AssistantsCards assistantsCard = ((AssistantCardMessage) receivedMessage).getAssistantsCard();
            playAssistantCard(activePlayer, assistantsCard);

        }
    }



    private void pickFirstPlayerActionPhaseHandler() {
        turnController.setActionOrderOfPlayers(match.getActionPhaseOrderOfPlayers());
        turnController.setTurnPhase(TurnPhase.MOVE_STUDENTS);

    }

    private void actionPhaseHandling(Message receivedMessage) {

        switch (receivedMessage.getType()) {
            case MOVE_STUDENT -> {
                MoveStudentMessage message = (MoveStudentMessage) receivedMessage;
                moveStudentsForThisTurn(message);
            }
            case CHOOSE_CLOUD -> {
                CloudMessage message = (CloudMessage) receivedMessage;
                selectCloudForThisTurn(message);
            }
            case MOVE_MOTHER_NATURE -> {
                MoveMotherNatureMessage message = (MoveMotherNatureMessage) receivedMessage;
                MoveMotherNatureForThisTurn(message);

            }

            //find a way to understand if influence in archipelago changed
            // case MOVE_MOTHER_NATURE -> match.moveMotherNature(receivedMessage.getPlayer(), match.getGame().getArchipelagos().get((Integer) receivedMessage.getContentOne()));
            default ->
                throw new IllegalStateException("Unexpected value: " + receivedMessage.getType());

        }

    }

    private void MoveMotherNatureForThisTurn(MoveMotherNatureMessage message) {
        try {
            match.moveMotherNature(turnController.getActivePlayer(), message.getArchipelago());
        } catch (ExceptionGame exceptionGame) {
            viewMap.get(turnController.getActivePlayer().getUsername()).sendMessage(new ErrorMessage("Can't move MotherNature in this position"));
        }
    }

    private void selectCloudForThisTurn(CloudMessage message) {
        try {
            match.chooseCloud(turnController.getActivePlayer(), message.getCloud());
        } catch (ExceptionGame e) {
            e.printStackTrace();
            viewMap.get(turnController.getActivePlayer().getUsername()).sendMessage(new ErrorMessage("Can't select this cloud"));
        }
        turnController.setTurnPhase(TurnPhase.END_TURN);
        turnController.nextPlayerActionPhase();
    }

    private void moveStudentsForThisTurn(MoveStudentMessage message) {
        try {
            if (message.getArchipelago() != null) {
                match.moveStudentOnArchipelago(turnController.getActivePlayer(), message.getStudent(), message.getArchipelago());
            } else {
                match.moveStudentOnBoard(turnController.getActivePlayer(), message.getStudent());
            }
            if (message.getNumberOfStudentMoved() == 3) {
                turnController.setTurnPhase(TurnPhase.MOVE_MOTHERNATURE);
            }

        } catch (ExceptionGame exceptionGame) {
            exceptionGame.printStackTrace();
            viewMap.get(turnController.getActivePlayer().getUsername()).sendMessage(new ErrorMessage("Can't move more students from board"));
        }
    }

    private void playAssistantCard(Player activePlayer, AssistantsCards assistantsCard) {
        try {
            match.playAssistantsCard(activePlayer, assistantsCard);
            turnController.nextPlayerPlanningPhase();
            if (match.getActionPhaseOrderOfPlayers().size() == viewMap.size()) {
                this.gameState = GameState.ACTION_PHASE;
                pickFirstPlayerActionPhaseHandler();
            }
        } catch (ExceptionGame e) {
            ViewInterface view = viewMap.get(activePlayer.getUsername());
            view.showGenericMessage(new GenericMessage("Please, insert a valid Assistant card"));
            try {
                List<AssistantsCards> availableAssistantsCards = match.getGame().getWizardFromPlayer(activePlayer).getAssistantsDeck().getPlayableAssistants();
                view.askAssistantCard(availableAssistantsCards);
            } catch (ExceptionGame er) {
                view.showGenericMessage(new GenericMessage(er.getMessage()));

            }
        }

    }


    public void addView(String username, ViewInterface view){
        viewMap.put(username, view);
    }

    @Override
    public void update(Object message)  {
        onMessageReceived((Message) message);
    }

    public void setViewMap(Map<String, ViewInterface> viewMap) {
        this.viewMap = viewMap;
    }

    private void printGame(){
        System.out.println(match);
    }
}
