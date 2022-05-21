package it.polimi.ingsw.NetworkUtilities.Message;

public enum TypeMessage {
    PING,
    PONG,
    NUMBER_OF_PLAYERS,
    REQUEST_LOGIN,
    END_OF_TURN,
    YOUR_TURN,
    ASSISTANT_CARD,
    LIST_ASSISTANT_CARD,
    MOVE_STUDENT,
    MOVE_MOTHER_NATURE,
    ASK_MOVE_MOTHER_NATURE,
    CHOOSE_CLOUD,
    GENERIC_MESSAGE,
    CHARACTER_CARD,
    MOVED_PROFESSOR,
    TOWER_BUILT,
    ERROR,
    END_MATCH,
    GAME_INFO;
}
