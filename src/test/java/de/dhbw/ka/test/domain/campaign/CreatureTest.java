package de.dhbw.ka.test.domain.campaign;

import de.dhbw.ka.domain.campaign.encounter.Creature;
import org.junit.jupiter.api.Test;

import static de.dhbw.ka.domain.character.AbilityScores.STRENGTH;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    private final Creature creature = new Creature("Test", 15, 25, 0, 15, 11, 10, 1, 3, 2);

    @Test
    public void testAbilityScore() {
        assertEquals(15, creature.abilityScore(STRENGTH).score());
        assertEquals(2, creature.abilityScore(STRENGTH).modifier());
    }

    @Test
    public void testAbilityScoreModifier() {
        int[] realModifiers = new int[]{-5, -4, -4, -3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};
        for (int i = 0; i < 30; i++) {
            assertEquals(realModifiers[i], new Creature.CreatureAbilityScore(i + 1).modifier());
        }
    }

    @Test
    public void testTakeDamage() {
        assertEquals(15, creature.takeDamage(10));
        assertEquals(15, creature.currentHitPoints());
    }

    @Test
    public void testIsAlive() {
        assertTrue(creature.isAlive());
        creature.takeDamage(25);
        assertFalse(creature.isAlive());
    }

    @Test
    public void testAC() {
        assertEquals(15, creature.armorClass());
    }

    @Test
    public void testInitiative() {
        assertEquals(0, creature.initiativeModifier());
    }

    @Test
    public void toStringTest() {
        String expected = "Name: Test, Hit points: (25/25), Armor class: 15, Initiative modifier: +0\n" +
                "[STR: 15 (+2) | DEX: 11 (+0) | CON: 10 (+0) | INT: 1 (-5) | WIS: 3 (-4) | CHA: 2 (-4)]";
        assertEquals(expected, creature.toString());
    }
}
