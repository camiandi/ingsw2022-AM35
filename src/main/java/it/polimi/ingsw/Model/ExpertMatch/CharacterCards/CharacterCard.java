package it.polimi.ingsw.Model.ExpertMatch.CharacterCards;

import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.ExpertMatch.ExpertMatch;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Game;
import it.polimi.ingsw.Model.SchoolsLands.Archipelago;
import it.polimi.ingsw.Model.SchoolsLands.Island;
import it.polimi.ingsw.Model.SchoolsMembers.Color;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class implemented by all cards
 */

public abstract class CharacterCard {
    protected int cost;
    private String name;
    private BasicMatch basicMatch;
    private Wizard activeWizard;
    private Wizard passiveWizard;
    private Color colorEffected;
    private Archipelago archipelagoEffected;
    private List<Student> activeStudents;
    private List<Student> passiveStudents;
    private int prohibitionPass;

    private final List<Student> studentsOnCard = new ArrayList<>();

    /**
     * Constructor of the class
     * @param basicMatch current match
     * @param name
     */
    public CharacterCard(BasicMatch basicMatch, String name) {
        this.name = name;
        this.basicMatch = basicMatch;
        passiveWizard =null;
        activeWizard = null;
        colorEffected = null;
        archipelagoEffected = null;
        activeStudents = null;
        passiveStudents = null;
        prohibitionPass = 0;
    }

    /**
     *
     * @return the card cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * This method is used set the cost of the card
     * @param cost The cost of the card
     */
    protected void setCost(int cost){this.cost = cost;}

    /**
     * This method sets the active wizard
     * @param activeWizard The active wizard
     */
    public void setActiveWizard(Wizard activeWizard) {
        this.activeWizard = activeWizard;
    }

    /**
     *  This method sets the passive wizard
     * @param passiveWizard The passive wizard
     */
    public void setPassiveWizard(Wizard passiveWizard) {
        this.passiveWizard = passiveWizard;
    }

    /**
     * This method sets the color effected
     * @param colorEffected
     */
    public void setColorEffected(Color colorEffected) {
        this.colorEffected = colorEffected;
    }

    /**
     * This method sets the archipelago effected
     * @param archipelagoEffected
     */
    public void setArchipelagoEffected(Archipelago archipelagoEffected) {
        this.archipelagoEffected = archipelagoEffected;
    }

    /**
     * This method sets the active student
     * @param activeStudents
     */
    public void setActiveStudents(List<Student> activeStudents) {
        this.activeStudents = activeStudents;
    }

    /**
     * This method sets the passive student
     * @param passiveStudents
     */
    public void setPassiveStudents(List<Student> passiveStudents) {
        this.passiveStudents = passiveStudents;
    }

    /**
     * This method sets the Prohibition Pass
     * @param prohibitionPass int representing the amount of passes
     */
    public void setProhibitionPass(int prohibitionPass) {
        this.prohibitionPass = prohibitionPass;
    }

    /**
     * This method reduces the player's coins by the cost of the card and it checks if a wizard is using the card, if activeWizard has not been set, throws an Exception
     * @param match teh current match
     * @throws ExceptionGame if the active wizard is not set
     */
    public void useCard(ExpertMatch match) throws ExceptionGame{
        if(activeWizard == null){
            throw new ExceptionGame("activeWizard hasn't been set");
        }
        activeWizard.reduceCoins(getCost());

    }

    /**
     * This method gets the active wizard
     * @return the active wizard
     */
    public Wizard getActiveWizard() {
        return activeWizard;
    }

    /**
     * This method gets the passive wizard
     * @return the passive wizard
     */
    public Wizard getPassiveWizard() {
        return passiveWizard;
    }

    /**
     * This method gets the color effected
     * @return the color effected
     */
    public Color getColorEffected() {
        return colorEffected;
    }

    /**
     * This method gets the archipelago effected
     * @return The archipelago effected
     */
    public Archipelago getArchipelagoEffected() {
        return archipelagoEffected;
    }

    /**
     * This method gets the active students
     * @return a list of active students
     */
    public List<Student> getActiveStudents() {
        return activeStudents;
    }

    /**
     * This method gets the passive students
     * @return a list of passive students
     */
    public List<Student> getPassiveStudents() {
        return passiveStudents;
    }

    /**
     * This method gets the prohibition pass
     * @return an int indicating the number of passes
     */
    public int getProhibitionPass() {
        return prohibitionPass;
    }

    /**
     * Resets all the parameters of the card setting them to null
     */
    public void resetCard(){
        passiveWizard =null;
        activeWizard = null;
        colorEffected = null;
        archipelagoEffected = null;
        activeStudents = null;
        passiveStudents = null;
    }

    public List<Student> getStudentsOnCard() {
        return studentsOnCard;
    }

    /**
     * This method gets the name of the card
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the basic match
     * @return the current basic match
     */
    public BasicMatch getBasicMatch() {
        return basicMatch;
    }



    @Override
    public String toString() {
        return "CharacterCard{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", activeWizard=" + activeWizard +
                ", passiveWizard=" + passiveWizard +
                ", colorEffected=" + colorEffected +
                ", archipelagoEffected=" + archipelagoEffected +
                ", activeStudents=" + activeStudents +
                ", passiveStudents=" + passiveStudents +
                ", studentsOnCard=" + studentsOnCard +
                '}';
    }

}
