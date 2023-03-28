package de.dhbw.ka.test;

import static de.dhbw.ka.AbilityScores.STRENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.ka.Character;
import org.junit.jupiter.api.Test;
public class CharacterTest {

    Character character = new Character();

    @Test
    public void testSetAbilityScore() {
        assertEquals(3, character.getAbilityScore(STRENGTH).getModifier());
    }
}
