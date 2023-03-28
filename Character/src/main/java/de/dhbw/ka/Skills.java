package de.dhbw.ka;

import static de.dhbw.ka.AbilityScores.*;

public enum Skills {
    Acrobatics(DEXTERITY),
    Animal_Handling(WISDOM),
    Arcana(INTELLIGENCE),
    Athletics(STRENGTH),
    Deception(CHARISMA),
    History(INTELLIGENCE),
    Insight(WISDOM),
    Intimidation(CHARISMA),
    Investigation(INTELLIGENCE),
    Medicine(WISDOM),
    Nature(INTELLIGENCE),
    Perception(WISDOM),
    Performance(CHARISMA),
    Persuasion(CHARISMA),
    Religion(INTELLIGENCE),
    Sleight_of_Hand(DEXTERITY),
    Stealth(DEXTERITY),
    Survival(WISDOM);

    public final AbilityScores ability;

    Skills(AbilityScores ability) {
        this.ability = ability;
    }
}
