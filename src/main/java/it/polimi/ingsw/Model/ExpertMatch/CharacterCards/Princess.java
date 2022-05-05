package it.polimi.ingsw.Model.ExpertMatch.CharacterCards;

import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.ExpertMatch.ExpertMatch;
import it.polimi.ingsw.Model.FactoryMatch.BasicMatch;
import it.polimi.ingsw.Model.FactoryMatch.Game;
import it.polimi.ingsw.Model.SchoolsMembers.Student;
import it.polimi.ingsw.Model.Wizard.Wizard;


public class Princess extends CharacterCard implements StudentEffectCard {

    public Princess(BasicMatch basicMatch, String name) {
        super(basicMatch, name);
        setCost(2);
        drawStudent(getStudentsOnCard(), 4, basicMatch.getGame().getStudentBag());
    }

    @Override
    public void useCard(ExpertMatch match) throws ExceptionGame{
        super.useCard(match);
        usedPrincessCard(getActiveWizard(), getActiveStudents().get(0));
        this.cost++;
    }


    private void usedPrincessCard(Wizard wizard, Student chosenStudent) throws ExceptionGame {
        if(getStudentsOnCard().contains(chosenStudent)) {
            wizard.getBoard().addStudentInTable(chosenStudent);
            getStudentsOnCard().remove(chosenStudent);
        }
        else
            throw new ExceptionGame("This student is not on the card");
        drawStudent(getStudentsOnCard(), 1, getBasicMatch().getGame().getStudentBag());
    }

}
