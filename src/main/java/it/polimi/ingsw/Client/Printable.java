package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Wizard.AssistantsCards;

public class Printable {

    public static final String BLUE = Constants.ANSI_BLUE;
    public static final String RED = Constants.ANSI_RED;
    public static final String YELLOW = Constants.ANSI_YELLOW;
    public static final String GREEN = Constants.ANSI_GREEN;
    public static final String PINK = Constants.ANSI_PINK;
    public static final String RESET = Constants.ANSI_RESET;

    public static final String PLUS = "+";


    public static final String LINE_BLOCK =
            "█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████";

    public static final String STUDENT_BLUE = BLUE +"█"+ RESET;
    public static final String STUDENT_RED = RED + "█"+ RESET;
    public static final String STUDENT_YELLOW = YELLOW + "█"+ RESET;
    public static final String STUDENT_GREEN = GREEN +"█"+ RESET;
    public static final String STUDENT_PINK = PINK + "█"+ RESET;

    public static final String PROF_BLUE = BLUE +"███"+ RESET;
    public static final String PROF_RED = RED + "███"+ RESET;
    public static final String PROF_YELLOW = YELLOW + "███"+ RESET;
    public static final String PROF_GREEN = GREEN +"███"+ RESET;
    public static final String PROF_PINK = PINK + "███"+ RESET;

    public static final String ASSISTANT_ONE = Constants.CARD_ONE + ", Value = 1, Steps = 1";
    public static final String ASSISTANT_TWO = Constants.CARD_TWO + ", Value = 2, Steps = 1";
    public static final String ASSISTANT_THREE = Constants.CARD_THREE + ", Value = 3, Steps = 2";
    public static final String ASSISTANT_FOUR = Constants.CARD_FOUR + ", Value = 4, Steps = 2";
    public static final String ASSISTANT_FIVE = Constants.CARD_FIVE + ", Value = 5, Steps = 3";
    public static final String ASSISTANT_SIX = Constants.CARD_SIX + ", Value = 6, Steps = 3";
    public static final String ASSISTANT_SEVEN = Constants.CARD_SEVEN + ", Value = 7, Steps = 4";
    public static final String ASSISTANT_EIGHT = Constants.CARD_EIGHT + ", Value = 8, Steps = 4";
    public static final String ASSISTANT_NINE = Constants.CARD_NINE + ", Value = 9, Steps = 5";
    public static final String ASSISTANT_TEN = Constants.CARD_TEN + ", Value = 10, Steps = 5";

    public static String getAssistantCardCLI(AssistantsCards assistantsCards){
        switch (assistantsCards.getValue()){
            case 1 -> {
                return ASSISTANT_ONE;
            }
            case 2 -> {
                return ASSISTANT_TWO;
            }
            case 3 -> {
                return ASSISTANT_THREE;
            }
            case 4 -> {
                return ASSISTANT_FOUR;
            }
            case 5 -> {
                return ASSISTANT_FIVE;
            }
            case 6 -> {
                return ASSISTANT_SIX;
            }
            case 7 -> {
                return ASSISTANT_SEVEN;
            }
            case 8 -> {
                return ASSISTANT_EIGHT;
            }
            case 9 -> {
                return ASSISTANT_NINE;
            }
            case 10 -> {
                return ASSISTANT_TEN;
            }
            default -> {
                return "ERROR IN SEND ASSISTANT'S CARDS";
            }
        }
    }








}
