package it.polimi.ingsw.Model.ExpertMatch;

import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.ExpertMatch.CharacterCards.CharacterCard;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Game;
import it.polimi.ingsw.Model.FactoryMatch.Player;
import it.polimi.ingsw.Model.SchoolsLands.Archipelago;
import it.polimi.ingsw.Model.SchoolsLands.Cloud;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.AssistantsCards;
import it.polimi.ingsw.Model.Wizard.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * Match decorator used in the ExpertMatch to extend the BasicMatch
 */
public abstract class MatchDecorator extends BasicMatch {
    protected BasicMatch basicMatch;

    public MatchDecorator(BasicMatch basicMatch){
        this.basicMatch = basicMatch;
    }

    @Override
    public void setGame(List<Player> players) throws ExceptionGame{
        basicMatch.setGame(players);
    }
    @Override
    public Player getPlayerFromWizard(Wizard wizard) throws ExceptionGame{
        return basicMatch.getPlayerFromWizard(wizard);
    }

    @Override
    public void playAssistantsCard(Player player, AssistantsCards assistantsCards) throws ExceptionGame{
        basicMatch.playAssistantsCard(player,assistantsCards);
    }

    @Override
    public void moveStudentOnArchipelago(Player player, Student student, Archipelago archipelago) throws ExceptionGame{
        basicMatch.moveStudentOnArchipelago(player,student,archipelago);
    }


    @Override
    public void chooseCloud(Player player, Cloud cloud) throws ExceptionGame{
        basicMatch.chooseCloud(player,cloud);
    }

    @Override
    public int getNumberOfPlayers(){
        return basicMatch.getNumberOfPlayers();
    }

    @Override
    public List<Player> getPlayers(){
        return basicMatch.getPlayers();
    }
    @Override
    public Game getGame(){
        return basicMatch.getGame();
    }

    @Override
    public List<Player> getActionPhaseOrderOfPlayers(){
        return basicMatch.getActionPhaseOrderOfPlayers();
    }
    @Override
    public int getNumberOfMovableStudents(){
        return basicMatch.getNumberOfMovableStudents();
    }
    @Override
    public int getNumberOfClouds(){
        return basicMatch.getNumberOfClouds();
    }
    @Override
    public int getNumberOfStudentsOnCLoud(){
        return basicMatch.getNumberOfStudentsOnCLoud();
    }

    @Override
    public int getNumberOfStudentInEntrance(){
        return basicMatch.getNumberOfStudentInEntrance();
    }
    @Override
    public int getNumberOfTowers(){
        return basicMatch.getNumberOfTowers();
    }
    @Override
    public int getPositionOfMotherNature(){
        return basicMatch.getPositionOfMotherNature();
    }
    @Override
    public void setTeams(List<Player> players) throws ExceptionGame{
        basicMatch.setTeams(players);
    }
    @Override
    public List<List<Player>> getTeams() throws ExceptionGame{
        return basicMatch.getTeams();
    }

    @Override
    public List<Player> getRivals(Player captain) throws ExceptionGame{
        return basicMatch.getRivals(captain);
    }

    @Override
    public Player getCaptainTeamOfPlayer(Player player) throws ExceptionGame{
        return basicMatch.getCaptainTeamOfPlayer(player);
    }


}