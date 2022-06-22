package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.GameState;
import it.polimi.ingsw.NetworkUtilities.*;
import it.polimi.ingsw.Server.ClientConnection;
import it.polimi.ingsw.Server.SocketClientConnection;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.CharacterCard;

public class RemoteView extends ViewInterface   {

    //pay attention to the message parameters and adjust them
    private final SocketClientConnection clientConnection;

    public RemoteView(SocketClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    @Override
    public void showGenericMessage(Message genericMessage) {
        clientConnection.sendMessage(genericMessage);
    }

    public void playCharacterCard(CharacterCard card) {
        //    clientConnection.sendMessage(new CharacterCard("",card));
    }


    public void moveMotherNature(Integer archipelago) {
        clientConnection.sendMessage(new MoveMotherNatureMessage(archipelago));
    }


    @Override
    public void update(Message message){
        if(message instanceof EndMatchMessage)
            manageEndMatch(message);
        else
            sendMessage(message);
    }

    private void manageEndMatch(Message message) {
        sendMessage(message);
        if(clientConnection.getController().isMatchOnGoing()) {
            clientConnection.getController().setMatchOnGoing(false);
            clientConnection.getController().setGameState(GameState.GAME_ENDED);
        }

    }

    @Override
    public void sendMessage(Message message) {
        clientConnection.sendMessage(message);
    }




}
