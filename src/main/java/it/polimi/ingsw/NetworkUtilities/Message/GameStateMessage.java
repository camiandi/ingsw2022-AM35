package it.polimi.ingsw.NetworkUtilities.Message;

public enum GameStateMessage {
    PING,
    PONG,
    LOGIN_REPLY,
    LOGIN_REQUEST,
    PARTICIPATE_TO_A_MATCH,
    CREATE_A_MATCH,
    NUMBER_OF_PLAYERS,
    PLANNING,
    ATTACK,
    END_OF_TURN,
    ASSISTANT_CARD,
    STUDENT_IN_ARCHIPELAGO,
    STUDENT_ON_BOARD,
    MOVE_MOTHER_NATURE,
    CHOOSE_CLOUD,
    GENERIC_MESSAGE,
    ASK_NUM_OF_PLAYERS,
    MOVED_PROFESSOR,
    TOWER_BUILT,
    ERROR,
    TEAM,
    MATCH_INFO;
}
