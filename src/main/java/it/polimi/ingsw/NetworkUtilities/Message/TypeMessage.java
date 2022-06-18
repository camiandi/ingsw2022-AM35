package it.polimi.ingsw.NetworkUtilities.Message;

public enum TypeMessage {
    PING,
    PONG,
    SERVER_INFO,
    OK_LOGIN,
    LOGIN_RESPONSE,
    NUMBER_OF_PLAYERS,
    REQUEST_LOGIN,
    END_OF_TURN,
    YOUR_TURN,
    ASSISTANT_CARD,
    ASK_ASSISTANT_CARD,
    ASK_TO_MOVE_STUDENT,
    ASK_MOVE_MOTHER_NATURE,
    CLOUD_IN_GAME,
    MOVE_STUDENT,
    STUDENTS_ON_ENTRANCE,
    ARCHIPELAGOS_IN_GAME,
    BOARD,
    REQUEST_CURRENT_GAME,
    MOVE_MOTHER_NATURE,
    CLOUD_CHOICE,
    GENERIC_MESSAGE,
    CHARACTER_CARD_IN_GAME,
    ERROR,
    END_MATCH,
    NEW_MATCH,
    SHOW_CHARACTER_CARD,
    PLAY_CHARACTER_CARD,
    ASK_CHARACTER_CARD,
    GAME_INFO,
    DISCONNECT,
    SHOW_CHARACTER_CARD_INFO, CLIENT_UNREACHABLE,
    ACTIVE_CHARACTER_CARD,
    END_OF_CHARACTER_CARD,
    GET_BACK_FROM_USED_CHARACTER_CARD
}
