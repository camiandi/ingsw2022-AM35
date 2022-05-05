package it.polimi.ingsw.Model.ExpertMatch;


import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.CharacterCard;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.DeckCharacterCard;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.InfluenceEffectCard;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.MotherNatureEffectCard;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Player;
import it.polimi.ingsw.Model.SchoolsLands.Archipelago;
import it.polimi.ingsw.Model.SchoolsLands.Island;
import it.polimi.ingsw.Model.SchoolsMembers.Color;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * this class extends the MatchDecorator to implements the ExpertMode
 */
public class ExpertMatch extends MatchDecorator {
    private final DeckCharacterCard deckCharacterCard;
    private ArrayList<CharacterCard> charactersForThisGame = new ArrayList<>();


    private InfluenceEffectCard activeInfluenceCard;
    private MotherNatureEffectCard activeMotherNatureCard;

    /**
     * Constructor of ExpertMatch
     *
     * @param basicMatch is the match that has to be modified to play in ExpertMode
     */
    public ExpertMatch(BasicMatch basicMatch) {
        super(basicMatch); //match della classe MatchDecorator
        deckCharacterCard = new DeckCharacterCard();
        activeInfluenceCard = null;
        activeMotherNatureCard = null;
    }

    @Override
    public void setGame(List<Player> players) throws ExceptionGame {
        basicMatch.setGame(players);
        setFirstCoin();
        charactersForThisGame = deckCharacterCard.drawCharacterCard(basicMatch);
    }

    /**
     * The override add a check to the number of student that are placed on the board when a student is moved
     *
     * @param player  is the player which moves the student
     * @param student is the student
     */
    @Override
    public void moveStudentOnBoard(Player player, Student student) throws ExceptionGame {
        basicMatch.moveStudentOnBoard(player, student);
        if (basicMatch.getGame().getWizardFromPlayer(player).getBoard().getStudentsFromTable(student.getColor()).size() == 3 ||
                basicMatch.getGame().getWizardFromPlayer(player).getBoard().getStudentsFromTable(student.getColor()).size() == 6 ||
                basicMatch.getGame().getWizardFromPlayer(player).getBoard().getStudentsFromTable(student.getColor()).size() == 9) {
                basicMatch.getGame().getWizardFromPlayer(player).addACoin();
        }
    }

    /**
     * Add a coin to every wizard when we set the Match to Expert Mode
     */
    private void setFirstCoin() {
        for (Wizard wizard : basicMatch.getGame().getWizards())
            wizard.addACoin();
    }

    @Override
    public ArrayList<CharacterCard> getCharactersForThisGame() {
        return charactersForThisGame;
    }


    @Override
    public void moveMotherNature(Player player, Archipelago archipelago) throws ExceptionGame {
        if(activeMotherNatureCard != null){
            activeMotherNatureCard.effectMotherNatureCard(player, archipelago);
        }else {
            basicMatch.getGame().placeMotherNature(player, archipelago);
        }
        try {
            buildTower(player, archipelago);
            basicMatch.lookUpArchipelago(archipelago);
        } catch (ExceptionGame e) {
            System.out.println(e);
        } finally {
            basicMatch.checkVictory();
        }
        if (player.equals(basicMatch.getActionPhaseOrderOfPlayers().get(basicMatch.getActionPhaseOrderOfPlayers().size() - 1))) {
            basicMatch.resetRound();
        }

        if(activeInfluenceCard != null)
            activeInfluenceCard = null;
    }

    protected void buildTower(Player player, Archipelago archipelago) throws ExceptionGame {
        boolean isMostInfluence = true;
        for(Player p : getRivals(player)){
            if(getWizardInfluenceInArchipelago(p, archipelago) >= getWizardInfluenceInArchipelago(player, archipelago)) { //influenza del player in negativo
                System.out.println("influence 1 : "+ getWizardInfluenceInArchipelago(p, archipelago) + " influence 2 : " + getWizardInfluenceInArchipelago(player, archipelago));
                isMostInfluence = false;
            }
        }
        System.out.println("most influence is " + isMostInfluence);
        if(isMostInfluence) {
            System.out.println("Captain of player " + player + "is " + basicMatch.getCaptainTeamOfPlayer(player));
            System.out.println("building tower of " + basicMatch.getCaptainTeamOfPlayer(player));
            basicMatch.getGame().buildTower( getGame().getWizardFromPlayer(basicMatch.getCaptainTeamOfPlayer(player)), archipelago);
        }


    }

    @Override
    public int getWizardInfluenceInArchipelago(Player p, Archipelago archipelago) throws ExceptionGame {
        Wizard wizard = getGame().getWizardFromPlayer(basicMatch.getCaptainTeamOfPlayer(p));
        int CardEffectWizardInfluence = basicMatch.getWizardInfluenceInArchipelago(p, archipelago);

        if(activeInfluenceCard != null){
            CardEffectWizardInfluence = activeInfluenceCard.calculateEffectInfluence(wizard, archipelago, CardEffectWizardInfluence);
        }
        return CardEffectWizardInfluence;
    }


    public void setActiveInfluenceCard(InfluenceEffectCard activeInfluenceCard) {
        this.activeInfluenceCard = activeInfluenceCard;
    }

    public InfluenceEffectCard getActiveInfluenceCard() {
        return activeInfluenceCard;
    }

    public void setActiveMotherNatureCard(MotherNatureEffectCard activeMotherNatureCard) {
        this.activeMotherNatureCard = activeMotherNatureCard;
    }

    public MotherNatureEffectCard getActiveMotherNatureCard() {
        return activeMotherNatureCard;
    }
}


