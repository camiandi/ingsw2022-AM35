package it.polimi.ingsw.Model.ExpertMatch.CharacterCards;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.CharacterCard;
import it.polimi.ingsw.Model.ExpertMatch.FactoryCharacterCard;
import it.polimi.ingsw.Model.FactoryMatch.Game;

import java.util.ArrayList;
import java.util.Random;

public class DeckCharacterCard{
    private final ArrayList<String> deckOfCharacterCards = new ArrayList<>();

    public DeckCharacterCard() {
        setDeckOfCharacterCards();
    }

    private void setDeckOfCharacterCards() {
        deckOfCharacterCards.add("Friar");//ok
        deckOfCharacterCards.add("Knight");
        deckOfCharacterCards.add("Chef");//ok
        deckOfCharacterCards.add("Archer");//ok
        deckOfCharacterCards.add("Princess");//ok
        deckOfCharacterCards.add("Jester");//ok
        deckOfCharacterCards.add("Baker"); //ok
       // deckOfCharacterCards.add("Messenger");
        deckOfCharacterCards.add("Minstrel"); //ok
    }


    public ArrayList<CharacterCard> drawCharacterCard(Game game) {
        Random random = new Random();
        ArrayList<CharacterCard> deckForAMatch = new ArrayList<>();
        FactoryCharacterCard factoryCharacterCard = new FactoryCharacterCard();
        for (int i = 0; i < 3; i++) {
            int toChoose = random.nextInt(deckOfCharacterCards.size());
            CharacterCard drawnCharacterCard = factoryCharacterCard.createACharacterCard(deckOfCharacterCards.get(toChoose), game);
            deckForAMatch.add(drawnCharacterCard);
            deckOfCharacterCards.remove(toChoose);
        }
        return deckForAMatch;
    }

}