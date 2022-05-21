package it.polimi.ingsw.Model.ExpertMatch.CharacterCards;

import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.ExpertMatch.ExpertMatch;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Game;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.TableOfStudents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**Implements the effect from Character card
 * and uses StudentEffectCard interface
 */

public class Minstrel extends CharacterCard implements StudentEffectCard {
    /**
     * Constructor of the class
     * @param basicMatch current match
     * @param name name of the class
     */
    public Minstrel(BasicMatch basicMatch, String name) {
        super(basicMatch, name);
        setCost(1);
    }

    /**
     * This method let the player use the card
     * @param match the current match
     * @throws ExceptionGame if an active wizard is not set
     */
    public void useCard(ExpertMatch match) throws ExceptionGame {
        super.useCard(match);
        useMinstrelCard();
        this.cost++;
    }

    /**
     * This method facilitates the usage of the card
     * @throws ExceptionGame if a move that is not permitted is made or a method fails to return a value
     */
    public void useMinstrelCard() throws ExceptionGame{
        if(getActiveStudents().size() == getPassiveStudents().size() && getActiveStudents().size() < 3) {
            List<Student> studentsInCase = new ArrayList<>();
            for(TableOfStudents t: getActiveWizard().getBoard().getTables()) {
                for(Student s: getActiveStudents()) {
                    if(t.getStudentsInTable().contains(s))
                            studentsInCase.add(s);
                }
            }
            if (studentsInCase.containsAll(getActiveStudents())) {
                if (getActiveWizard().getBoard().getStudentsInEntrance().containsAll(getPassiveStudents())) {
                    tradeStudents(getActiveStudents(), getPassiveStudents(), studentsInCase, getActiveWizard().getBoard().getStudentsInEntrance());
                } else
                    throw new ExceptionGame("The entrance of the wizard does not contains all the students selected");
            } else
                throw new ExceptionGame("The tables of the wizard does not contains all the students selected");
        } else
            throw new ExceptionGame("The list of students to trade have not the same number or there have been selected more than 2 students");
    }

    /**
     * This method is used to trade students
     * @param fromTables Tables to pick the students from
     * @param toEntrance Entrance where the students will be placed
     * @param studentsTable Collection of students in Table
     * @param studentsEntrance Collection of students in Entrance
     * @throws ExceptionGame if a move that is not permitted is made or a method fails to return a value
     */
    @Override
    public void tradeStudents(List<Student> fromTables, List<Student> toEntrance, Collection<Student> studentsTable, Collection<Student> studentsEntrance) throws ExceptionGame{
        for(TableOfStudents t: getActiveWizard().getBoard().getTables()) {
            for (Student fromTable : fromTables) {
                t.getStudentsInTable().remove(fromTable);
            }
        }

        for(Student s: toEntrance) {
            getActiveWizard().getBoard().addStudentInTable(s);
        }

        studentsEntrance.removeAll(toEntrance);
        studentsEntrance.addAll(fromTables);

        resetCard();
    }

}
