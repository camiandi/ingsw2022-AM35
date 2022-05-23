package it.polimi.ingsw.NetworkUtilities.Message;

import it.polimi.ingsw.Model.Wizard.AssistantsCards;

public class AssistantCardMessage extends Message{
    private static final long serialVersionUID = 7250516904545301604L;
    private final AssistantsCards assistantsCard;
    public AssistantCardMessage( AssistantsCards assistantCard) {
        this.assistantsCard = assistantCard;
        setType(TypeMessage.ASSISTANT_CARD);
    }

    public AssistantsCards getAssistantsCard() {
        return assistantsCard;
    }

    @Override
    public String toString() {
        return "AssistantCardMessage{" +
                "assistantsCard=" + assistantsCard +
                '}';
    }
}
