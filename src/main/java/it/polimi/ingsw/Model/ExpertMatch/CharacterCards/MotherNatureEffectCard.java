package it.polimi.ingsw.Model.ExpertMatch.CharacterCards;

import it.polimi.ingsw.Model.Exception.ExceptionGame;
import it.polimi.ingsw.Model.FactoryMatch.Player;
import it.polimi.ingsw.Model.SchoolsLands.Archipelago;

public interface MotherNatureEffectCard {

    void effectMotherNatureCard(Player player, Archipelago archipelago) throws ExceptionGame;
}
