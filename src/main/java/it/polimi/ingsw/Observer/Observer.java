package it.polimi.ingsw.Observer;

import it.polimi.ingsw.NetworkUtilities.Message.Message;
public interface Observer {
    public void update(it.polimi.ingsw.NetworkUtilities.Message.Message message);

}
