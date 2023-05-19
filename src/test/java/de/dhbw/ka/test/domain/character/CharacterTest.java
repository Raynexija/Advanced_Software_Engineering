package de.dhbw.ka.test.domain.character;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.AbilityScores;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.Skills;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import de.dhbw.ka.domain.character.races.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CharacterTest {

    private final Race testRace = new Human();
    private final Fighter testClass = new Fighter();

    private final Character character = CreateCharacter.named("Test")
            .ofRace(testRace)
            .isA(testClass)
            .withStrength(15)
            .withDexterity(14)
            .withConstitution(13)
            .withIntelligence(12)
            .withWisdom(10)
            .withCharisma(8)
            .maxHitPoints(10)
            .currentLevel(1)
            .speaking(new String[]{"Celestial", "Elvish"})
            .equippedWith(new String[]{"Leather Armor", "Longsword", "Dagger"})
            .build();

    @Test
    public void testCharacterName() {
        assertEquals("Test", character.getName());
    }

    @Test
    public void testRace() {
        assertEquals(testRace, character.getRace());
    }

    @Test
    public void testCharacterClass() {
        assertEquals(testClass, character.getCharacterClass());
    }

    @Test
    public void testAbilityScore() {
        assertEquals(16, character.getAbilityScore(AbilityScores.STRENGTH).getScore());
        assertEquals(3, character.getAbilityScore(AbilityScores.STRENGTH).getModifier());
    }

    @Test
    public void testHitPoints() {
        assertEquals(10, character.getHitPoints());
    }

    @Test
    public void testDamageTaken() {
        assertEquals(7, character.takeDamage(3));
        assertEquals(7, character.getHitPoints());
    }

    @Test
    public void testLanguages() {
        List<String> expectedLanguages = new ArrayList<>(List.of(new String[]{"Celestial", "Common", "Elvish"}));
        assertEquals(expectedLanguages.size(), character.getLanguages().size());
        assertEquals(expectedLanguages, character.getLanguages());
    }

    @Test
    public void testEquipment() {
        List<String> expectedEquipment = new ArrayList<>(List.of(new String[]{"Dagger", "Leather Armor", "Longsword"}));
        assertEquals(expectedEquipment.size(), character.getEquipment().size());
        assertEquals(expectedEquipment, character.getEquipment());

        character.removeEquipment("Dagger");
        expectedEquipment.remove("Dagger");
        assertEquals(expectedEquipment.size(), character.getEquipment().size());
        assertEquals(expectedEquipment, character.getEquipment());
    }

    @Test
    public void testLevel() {
        assertEquals(1, character.getLevel());
    }

    @Test
    public void testAbilityModifier() {
        assertEquals(3, character.abilityCheckModifier(AbilityScores.STRENGTH));
    }

    @Test
    public void testSavingThrowModifier() {
        assertEquals(5, character.savingThrowModifier(AbilityScores.STRENGTH));
        assertEquals(2, character.savingThrowModifier(AbilityScores.DEXTERITY));
    }

    @Test
    public void testSkillModifier() {
        assertEquals(-1, character.skillCheckModifier(Skills.Performance));
    }

    @Test
    public void testFlatAbilityModifier() {
        assertEquals(1, character.abilityCheckModifier(AbilityScores.INTELLIGENCE));

        character.setFlatAbilityScore(AbilityScores.INTELLIGENCE, 17);
        assertEquals(3, character.abilityCheckModifier(AbilityScores.INTELLIGENCE));
    }

    @Test
    public void testAbilityBonus() {
        assertEquals(0, character.abilityCheckModifier(AbilityScores.WISDOM));

        character.addAbilityBonus(AbilityScores.WISDOM, 2);
        assertEquals(1, character.abilityCheckModifier(AbilityScores.WISDOM));
    }

    @Test
    public void testSpeed() {
        assertEquals(30, character.getSpeed());
    }
}
